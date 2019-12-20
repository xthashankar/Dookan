/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kist.bit.dookan.services;

import edu.kist.bit.dookan.entity.Cart;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import edu.kist.bit.dookan.entity.Userdetail;
import edu.kist.bit.dookan.entity.Sellingitem;
import edu.kist.bit.dookan.entity.Orders;
import edu.kist.bit.dookan.services.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author shankar xtha
 */
public class CartJpaController implements Serializable {

    public CartJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cart cart) {
        if (cart.getOrdersList() == null) {
            cart.setOrdersList(new ArrayList<Orders>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Userdetail buyerid = cart.getBuyerid();
            if (buyerid != null) {
                buyerid = em.getReference(buyerid.getClass(), buyerid.getId());
                cart.setBuyerid(buyerid);
            }
            Sellingitem addedtocartitem = cart.getAddedtocartitem();
            if (addedtocartitem != null) {
                addedtocartitem = em.getReference(addedtocartitem.getClass(), addedtocartitem.getId());
                cart.setAddedtocartitem(addedtocartitem);
            }
            List<Orders> attachedOrdersList = new ArrayList<Orders>();
            for (Orders ordersListOrdersToAttach : cart.getOrdersList()) {
                ordersListOrdersToAttach = em.getReference(ordersListOrdersToAttach.getClass(), ordersListOrdersToAttach.getId());
                attachedOrdersList.add(ordersListOrdersToAttach);
            }
            cart.setOrdersList(attachedOrdersList);
            em.persist(cart);
            if (buyerid != null) {
                buyerid.getCartList().add(cart);
                buyerid = em.merge(buyerid);
            }
            if (addedtocartitem != null) {
                addedtocartitem.getCartList().add(cart);
                addedtocartitem = em.merge(addedtocartitem);
            }
            for (Orders ordersListOrders : cart.getOrdersList()) {
                Cart oldCartidOfOrdersListOrders = ordersListOrders.getCartid();
                ordersListOrders.setCartid(cart);
                ordersListOrders = em.merge(ordersListOrders);
                if (oldCartidOfOrdersListOrders != null) {
                    oldCartidOfOrdersListOrders.getOrdersList().remove(ordersListOrders);
                    oldCartidOfOrdersListOrders = em.merge(oldCartidOfOrdersListOrders);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cart cart) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cart persistentCart = em.find(Cart.class, cart.getId());
            Userdetail buyeridOld = persistentCart.getBuyerid();
            Userdetail buyeridNew = cart.getBuyerid();
            Sellingitem addedtocartitemOld = persistentCart.getAddedtocartitem();
            Sellingitem addedtocartitemNew = cart.getAddedtocartitem();
            List<Orders> ordersListOld = persistentCart.getOrdersList();
            List<Orders> ordersListNew = cart.getOrdersList();
            if (buyeridNew != null) {
                buyeridNew = em.getReference(buyeridNew.getClass(), buyeridNew.getId());
                cart.setBuyerid(buyeridNew);
            }
            if (addedtocartitemNew != null) {
                addedtocartitemNew = em.getReference(addedtocartitemNew.getClass(), addedtocartitemNew.getId());
                cart.setAddedtocartitem(addedtocartitemNew);
            }
            List<Orders> attachedOrdersListNew = new ArrayList<Orders>();
            for (Orders ordersListNewOrdersToAttach : ordersListNew) {
                ordersListNewOrdersToAttach = em.getReference(ordersListNewOrdersToAttach.getClass(), ordersListNewOrdersToAttach.getId());
                attachedOrdersListNew.add(ordersListNewOrdersToAttach);
            }
            ordersListNew = attachedOrdersListNew;
            cart.setOrdersList(ordersListNew);
            cart = em.merge(cart);
            if (buyeridOld != null && !buyeridOld.equals(buyeridNew)) {
                buyeridOld.getCartList().remove(cart);
                buyeridOld = em.merge(buyeridOld);
            }
            if (buyeridNew != null && !buyeridNew.equals(buyeridOld)) {
                buyeridNew.getCartList().add(cart);
                buyeridNew = em.merge(buyeridNew);
            }
            if (addedtocartitemOld != null && !addedtocartitemOld.equals(addedtocartitemNew)) {
                addedtocartitemOld.getCartList().remove(cart);
                addedtocartitemOld = em.merge(addedtocartitemOld);
            }
            if (addedtocartitemNew != null && !addedtocartitemNew.equals(addedtocartitemOld)) {
                addedtocartitemNew.getCartList().add(cart);
                addedtocartitemNew = em.merge(addedtocartitemNew);
            }
            for (Orders ordersListOldOrders : ordersListOld) {
                if (!ordersListNew.contains(ordersListOldOrders)) {
                    ordersListOldOrders.setCartid(null);
                    ordersListOldOrders = em.merge(ordersListOldOrders);
                }
            }
            for (Orders ordersListNewOrders : ordersListNew) {
                if (!ordersListOld.contains(ordersListNewOrders)) {
                    Cart oldCartidOfOrdersListNewOrders = ordersListNewOrders.getCartid();
                    ordersListNewOrders.setCartid(cart);
                    ordersListNewOrders = em.merge(ordersListNewOrders);
                    if (oldCartidOfOrdersListNewOrders != null && !oldCartidOfOrdersListNewOrders.equals(cart)) {
                        oldCartidOfOrdersListNewOrders.getOrdersList().remove(ordersListNewOrders);
                        oldCartidOfOrdersListNewOrders = em.merge(oldCartidOfOrdersListNewOrders);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cart.getId();
                if (findCart(id) == null) {
                    throw new NonexistentEntityException("The cart with id " + id + " no longer exists.");
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
            Cart cart;
            try {
                cart = em.getReference(Cart.class, id);
                cart.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cart with id " + id + " no longer exists.", enfe);
            }
            Userdetail buyerid = cart.getBuyerid();
            if (buyerid != null) {
                buyerid.getCartList().remove(cart);
                buyerid = em.merge(buyerid);
            }
            Sellingitem addedtocartitem = cart.getAddedtocartitem();
            if (addedtocartitem != null) {
                addedtocartitem.getCartList().remove(cart);
                addedtocartitem = em.merge(addedtocartitem);
            }
            List<Orders> ordersList = cart.getOrdersList();
            for (Orders ordersListOrders : ordersList) {
                ordersListOrders.setCartid(null);
                ordersListOrders = em.merge(ordersListOrders);
            }
            em.remove(cart);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cart> findCartEntities() {
        return findCartEntities(true, -1, -1);
    }

    public List<Cart> findCartEntities(int maxResults, int firstResult) {
        return findCartEntities(false, maxResults, firstResult);
    }

    private List<Cart> findCartEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cart.class));
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

    public Cart findCart(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cart.class, id);
        } finally {
            em.close();
        }
    }

    public int getCartCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cart> rt = cq.from(Cart.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
