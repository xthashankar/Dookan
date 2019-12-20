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
import edu.kist.bit.dookan.entity.Product;
import java.util.ArrayList;
import java.util.List;
import edu.kist.bit.dookan.entity.Cart;
import edu.kist.bit.dookan.entity.Userdetail;
import edu.kist.bit.dookan.services.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

/**
 *
 * @author shankar xtha
 */
public class UserdetailJpaController implements Serializable {

    public UserdetailJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Userdetail userdetail) {
        if (userdetail.getProductList() == null) {
            userdetail.setProductList(new ArrayList<Product>());
        }
        if (userdetail.getCartList() == null) {
            userdetail.setCartList(new ArrayList<Cart>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Product> attachedProductList = new ArrayList<Product>();
            for (Product productListProductToAttach : userdetail.getProductList()) {
                productListProductToAttach = em.getReference(productListProductToAttach.getClass(), productListProductToAttach.getId());
                attachedProductList.add(productListProductToAttach);
            }
            userdetail.setProductList(attachedProductList);
            List<Cart> attachedCartList = new ArrayList<Cart>();
            for (Cart cartListCartToAttach : userdetail.getCartList()) {
                cartListCartToAttach = em.getReference(cartListCartToAttach.getClass(), cartListCartToAttach.getId());
                attachedCartList.add(cartListCartToAttach);
            }
            userdetail.setCartList(attachedCartList);
            em.persist(userdetail);
            for (Product productListProduct : userdetail.getProductList()) {
                Userdetail oldUserdetailidOfProductListProduct = productListProduct.getUserdetailid();
                productListProduct.setUserdetailid(userdetail);
                productListProduct = em.merge(productListProduct);
                if (oldUserdetailidOfProductListProduct != null) {
                    oldUserdetailidOfProductListProduct.getProductList().remove(productListProduct);
                    oldUserdetailidOfProductListProduct = em.merge(oldUserdetailidOfProductListProduct);
                }
            }
            for (Cart cartListCart : userdetail.getCartList()) {
                Userdetail oldBuyeridOfCartListCart = cartListCart.getBuyerid();
                cartListCart.setBuyerid(userdetail);
                cartListCart = em.merge(cartListCart);
                if (oldBuyeridOfCartListCart != null) {
                    oldBuyeridOfCartListCart.getCartList().remove(cartListCart);
                    oldBuyeridOfCartListCart = em.merge(oldBuyeridOfCartListCart);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Userdetail userdetail) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Userdetail persistentUserdetail = em.find(Userdetail.class, userdetail.getId());
            List<Product> productListOld = persistentUserdetail.getProductList();
            List<Product> productListNew = userdetail.getProductList();
            List<Cart> cartListOld = persistentUserdetail.getCartList();
            List<Cart> cartListNew = userdetail.getCartList();
            List<Product> attachedProductListNew = new ArrayList<Product>();
            for (Product productListNewProductToAttach : productListNew) {
                productListNewProductToAttach = em.getReference(productListNewProductToAttach.getClass(), productListNewProductToAttach.getId());
                attachedProductListNew.add(productListNewProductToAttach);
            }
            productListNew = attachedProductListNew;
            userdetail.setProductList(productListNew);
            List<Cart> attachedCartListNew = new ArrayList<Cart>();
            for (Cart cartListNewCartToAttach : cartListNew) {
                cartListNewCartToAttach = em.getReference(cartListNewCartToAttach.getClass(), cartListNewCartToAttach.getId());
                attachedCartListNew.add(cartListNewCartToAttach);
            }
            cartListNew = attachedCartListNew;
            userdetail.setCartList(cartListNew);
            userdetail = em.merge(userdetail);
            for (Product productListOldProduct : productListOld) {
                if (!productListNew.contains(productListOldProduct)) {
                    productListOldProduct.setUserdetailid(null);
                    productListOldProduct = em.merge(productListOldProduct);
                }
            }
            for (Product productListNewProduct : productListNew) {
                if (!productListOld.contains(productListNewProduct)) {
                    Userdetail oldUserdetailidOfProductListNewProduct = productListNewProduct.getUserdetailid();
                    productListNewProduct.setUserdetailid(userdetail);
                    productListNewProduct = em.merge(productListNewProduct);
                    if (oldUserdetailidOfProductListNewProduct != null && !oldUserdetailidOfProductListNewProduct.equals(userdetail)) {
                        oldUserdetailidOfProductListNewProduct.getProductList().remove(productListNewProduct);
                        oldUserdetailidOfProductListNewProduct = em.merge(oldUserdetailidOfProductListNewProduct);
                    }
                }
            }
            for (Cart cartListOldCart : cartListOld) {
                if (!cartListNew.contains(cartListOldCart)) {
                    cartListOldCart.setBuyerid(null);
                    cartListOldCart = em.merge(cartListOldCart);
                }
            }
            for (Cart cartListNewCart : cartListNew) {
                if (!cartListOld.contains(cartListNewCart)) {
                    Userdetail oldBuyeridOfCartListNewCart = cartListNewCart.getBuyerid();
                    cartListNewCart.setBuyerid(userdetail);
                    cartListNewCart = em.merge(cartListNewCart);
                    if (oldBuyeridOfCartListNewCart != null && !oldBuyeridOfCartListNewCart.equals(userdetail)) {
                        oldBuyeridOfCartListNewCart.getCartList().remove(cartListNewCart);
                        oldBuyeridOfCartListNewCart = em.merge(oldBuyeridOfCartListNewCart);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = userdetail.getId();
                if (findUserdetail(id) == null) {
                    throw new NonexistentEntityException("The userdetail with id " + id + " no longer exists.");
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
            Userdetail userdetail;
            try {
                userdetail = em.getReference(Userdetail.class, id);
                userdetail.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userdetail with id " + id + " no longer exists.", enfe);
            }
            List<Product> productList = userdetail.getProductList();
            for (Product productListProduct : productList) {
                productListProduct.setUserdetailid(null);
                productListProduct = em.merge(productListProduct);
            }
            List<Cart> cartList = userdetail.getCartList();
            for (Cart cartListCart : cartList) {
                cartListCart.setBuyerid(null);
                cartListCart = em.merge(cartListCart);
            }
            em.remove(userdetail);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Userdetail> findUserdetailEntities() {
        return findUserdetailEntities(true, -1, -1);
    }

    public List<Userdetail> findUserdetailEntities(int maxResults, int firstResult) {
        return findUserdetailEntities(false, maxResults, firstResult);
    }

    private List<Userdetail> findUserdetailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Userdetail.class));
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

    public Userdetail findUserdetail(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Userdetail.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserdetailCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Userdetail> rt = cq.from(Userdetail.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public Userdetail checkLogin(String email) throws NonexistentEntityException{
    EntityManager em =getEntityManager();
    Userdetail results =null;
    try{
        results=(Userdetail)em.createNamedQuery("Userdetail.findByEmail")
                .setParameter("email",email)
                .getSingleResult();
    }catch(NullPointerException | NoResultException e){
        throw new NonexistentEntityException("The users with email:" + email+"non longer exists");
    }
    return results;

    }
}
