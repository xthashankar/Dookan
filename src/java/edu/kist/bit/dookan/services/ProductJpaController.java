/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kist.bit.dookan.services;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import edu.kist.bit.dookan.entity.Userdetail;
import edu.kist.bit.dookan.entity.Category;
import edu.kist.bit.dookan.entity.Product;
import edu.kist.bit.dookan.services.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

/**
 *
 * @author shankar xtha
 */
public class ProductJpaController implements Serializable {

    public ProductJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Product product) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Userdetail userdetailid = product.getUserdetailid();
            if (userdetailid != null) {
                userdetailid = em.getReference(userdetailid.getClass(), userdetailid.getId());
                product.setUserdetailid(userdetailid);
            }
            Category categoryid = product.getCategoryid();
            if (categoryid != null) {
                categoryid = em.getReference(categoryid.getClass(), categoryid.getId());
                product.setCategoryid(categoryid);
            }
            em.persist(product);
            if (userdetailid != null) {
                userdetailid.getProductList().add(product);
                userdetailid = em.merge(userdetailid);
            }
            if (categoryid != null) {
                categoryid.getProductList().add(product);
                categoryid = em.merge(categoryid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Product product) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Product persistentProduct = em.find(Product.class, product.getId());
            Userdetail userdetailidOld = persistentProduct.getUserdetailid();
            Userdetail userdetailidNew = product.getUserdetailid();
            Category categoryidOld = persistentProduct.getCategoryid();
            Category categoryidNew = product.getCategoryid();
            if (userdetailidNew != null) {
                userdetailidNew = em.getReference(userdetailidNew.getClass(), userdetailidNew.getId());
                product.setUserdetailid(userdetailidNew);
            }
            if (categoryidNew != null) {
                categoryidNew = em.getReference(categoryidNew.getClass(), categoryidNew.getId());
                product.setCategoryid(categoryidNew);
            }
            product = em.merge(product);
            if (userdetailidOld != null && !userdetailidOld.equals(userdetailidNew)) {
                userdetailidOld.getProductList().remove(product);
                userdetailidOld = em.merge(userdetailidOld);
            }
            if (userdetailidNew != null && !userdetailidNew.equals(userdetailidOld)) {
                userdetailidNew.getProductList().add(product);
                userdetailidNew = em.merge(userdetailidNew);
            }
            if (categoryidOld != null && !categoryidOld.equals(categoryidNew)) {
                categoryidOld.getProductList().remove(product);
                categoryidOld = em.merge(categoryidOld);
            }
            if (categoryidNew != null && !categoryidNew.equals(categoryidOld)) {
                categoryidNew.getProductList().add(product);
                categoryidNew = em.merge(categoryidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = product.getId();
                if (findProduct(id) == null) {
                    throw new NonexistentEntityException("The product with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Product product;
            try {
                product = em.getReference(Product.class, id);
                product.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The product with id " + id + " no longer exists.", enfe);
            }
            Userdetail userdetailid = product.getUserdetailid();
            if (userdetailid != null) {
                userdetailid.getProductList().remove(product);
                userdetailid = em.merge(userdetailid);
            }
            Category categoryid = product.getCategoryid();
            if (categoryid != null) {
                categoryid.getProductList().remove(product);
                categoryid = em.merge(categoryid);
            }
            em.remove(product);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Product> findProductEntities() {
        return findProductEntities(true, -1, -1);
    }

    public List<Product> findProductEntities(int maxResults, int firstResult) {
        return findProductEntities(false, maxResults, firstResult);
    }

    private List<Product> findProductEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Product.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Product findProduct(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Product.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Product> rt = cq.from(Product.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Product> getByProduct(Userdetail userdetailid) throws NonexistentEntityException{
    EntityManager em = getEntityManager();
    List<Product> results = null;
    try{
        results = em.createQuery("SELECT p FROM Product p WHERE p.userdetailid = :uid").setParameter("uid",userdetailid).getResultList();
    }catch(NullPointerException | NoResultException e){
        throw new NonexistentEntityException("The users with id:" + userdetailid +"non longer exists");
    }
    return results;

    }
}
