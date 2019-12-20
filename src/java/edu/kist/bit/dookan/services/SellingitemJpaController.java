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
import edu.kist.bit.dookan.entity.Cart;
import edu.kist.bit.dookan.entity.Sellingitem;
import edu.kist.bit.dookan.services.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author shankar xtha
 */
public class SellingitemJpaController implements Serializable {

    public SellingitemJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sellingitem sellingitem) {
        if (sellingitem.getCartList() == null) {
            sellingitem.setCartList(new ArrayList<Cart>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Product productid = sellingitem.getProductid();
            if (productid != null) {
                productid = em.getReference(productid.getClass(), productid.getId());
                sellingitem.setProductid(productid);
            }
            List<Cart> attachedCartList = new ArrayList<Cart>();
            for (Cart cartListCartToAttach : sellingitem.getCartList()) {
                cartListCartToAttach = em.getReference(cartListCartToAttach.getClass(), cartListCartToAttach.getId());
                attachedCartList.add(cartListCartToAttach);
            }
            sellingitem.setCartList(attachedCartList);
            em.persist(sellingitem);
            if (productid != null) {
                productid.getSellingitemList().add(sellingitem);
                productid = em.merge(productid);
            }
            for (Cart cartListCart : sellingitem.getCartList()) {
                Sellingitem oldAddedtocartitemOfCartListCart = cartListCart.getAddedtocartitem();
                cartListCart.setAddedtocartitem(sellingitem);
                cartListCart = em.merge(cartListCart);
                if (oldAddedtocartitemOfCartListCart != null) {
                    oldAddedtocartitemOfCartListCart.getCartList().remove(cartListCart);
                    oldAddedtocartitemOfCartListCart = em.merge(oldAddedtocartitemOfCartListCart);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sellingitem sellingitem) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sellingitem persistentSellingitem = em.find(Sellingitem.class, sellingitem.getId());
            Product productidOld = persistentSellingitem.getProductid();
            Product productidNew = sellingitem.getProductid();
            List<Cart> cartListOld = persistentSellingitem.getCartList();
            List<Cart> cartListNew = sellingitem.getCartList();
            if (productidNew != null) {
                productidNew = em.getReference(productidNew.getClass(), productidNew.getId());
                sellingitem.setProductid(productidNew);
            }
            List<Cart> attachedCartListNew = new ArrayList<Cart>();
            for (Cart cartListNewCartToAttach : cartListNew) {
                cartListNewCartToAttach = em.getReference(cartListNewCartToAttach.getClass(), cartListNewCartToAttach.getId());
                attachedCartListNew.add(cartListNewCartToAttach);
            }
            cartListNew = attachedCartListNew;
            sellingitem.setCartList(cartListNew);
            sellingitem = em.merge(sellingitem);
            if (productidOld != null && !productidOld.equals(productidNew)) {
                productidOld.getSellingitemList().remove(sellingitem);
                productidOld = em.merge(productidOld);
            }
            if (productidNew != null && !productidNew.equals(productidOld)) {
                productidNew.getSellingitemList().add(sellingitem);
                productidNew = em.merge(productidNew);
            }
            for (Cart cartListOldCart : cartListOld) {
                if (!cartListNew.contains(cartListOldCart)) {
                    cartListOldCart.setAddedtocartitem(null);
                    cartListOldCart = em.merge(cartListOldCart);
                }
            }
            for (Cart cartListNewCart : cartListNew) {
                if (!cartListOld.contains(cartListNewCart)) {
                    Sellingitem oldAddedtocartitemOfCartListNewCart = cartListNewCart.getAddedtocartitem();
                    cartListNewCart.setAddedtocartitem(sellingitem);
                    cartListNewCart = em.merge(cartListNewCart);
                    if (oldAddedtocartitemOfCartListNewCart != null && !oldAddedtocartitemOfCartListNewCart.equals(sellingitem)) {
                        oldAddedtocartitemOfCartListNewCart.getCartList().remove(cartListNewCart);
                        oldAddedtocartitemOfCartListNewCart = em.merge(oldAddedtocartitemOfCartListNewCart);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sellingitem.getId();
                if (findSellingitem(id) == null) {
                    throw new NonexistentEntityException("The sellingitem with id " + id + " no longer exists.");
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
            Sellingitem sellingitem;
            try {
                sellingitem = em.getReference(Sellingitem.class, id);
                sellingitem.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sellingitem with id " + id + " no longer exists.", enfe);
            }
            Product productid = sellingitem.getProductid();
            if (productid != null) {
                productid.getSellingitemList().remove(sellingitem);
                productid = em.merge(productid);
            }
            List<Cart> cartList = sellingitem.getCartList();
            for (Cart cartListCart : cartList) {
                cartListCart.setAddedtocartitem(null);
                cartListCart = em.merge(cartListCart);
            }
            em.remove(sellingitem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sellingitem> findSellingitemEntities() {
        return findSellingitemEntities(true, -1, -1);
    }

    public List<Sellingitem> findSellingitemEntities(int maxResults, int firstResult) {
        return findSellingitemEntities(false, maxResults, firstResult);
    }

    private List<Sellingitem> findSellingitemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sellingitem.class));
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

    public Sellingitem findSellingitem(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sellingitem.class, id);
        } finally {
            em.close();
        }
    }

    public int getSellingitemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sellingitem> rt = cq.from(Sellingitem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
