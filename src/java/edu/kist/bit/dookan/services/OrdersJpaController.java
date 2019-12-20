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
import edu.kist.bit.dookan.entity.Cart;
import edu.kist.bit.dookan.entity.Bill;
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
public class OrdersJpaController implements Serializable {

    public OrdersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Orders orders) {
        if (orders.getBillList() == null) {
            orders.setBillList(new ArrayList<Bill>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cart cartid = orders.getCartid();
            if (cartid != null) {
                cartid = em.getReference(cartid.getClass(), cartid.getId());
                orders.setCartid(cartid);
            }
            List<Bill> attachedBillList = new ArrayList<Bill>();
            for (Bill billListBillToAttach : orders.getBillList()) {
                billListBillToAttach = em.getReference(billListBillToAttach.getClass(), billListBillToAttach.getId());
                attachedBillList.add(billListBillToAttach);
            }
            orders.setBillList(attachedBillList);
            em.persist(orders);
            if (cartid != null) {
                cartid.getOrdersList().add(orders);
                cartid = em.merge(cartid);
            }
            for (Bill billListBill : orders.getBillList()) {
                Orders oldOrdersidOfBillListBill = billListBill.getOrdersid();
                billListBill.setOrdersid(orders);
                billListBill = em.merge(billListBill);
                if (oldOrdersidOfBillListBill != null) {
                    oldOrdersidOfBillListBill.getBillList().remove(billListBill);
                    oldOrdersidOfBillListBill = em.merge(oldOrdersidOfBillListBill);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Orders orders) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Orders persistentOrders = em.find(Orders.class, orders.getId());
            Cart cartidOld = persistentOrders.getCartid();
            Cart cartidNew = orders.getCartid();
            List<Bill> billListOld = persistentOrders.getBillList();
            List<Bill> billListNew = orders.getBillList();
            if (cartidNew != null) {
                cartidNew = em.getReference(cartidNew.getClass(), cartidNew.getId());
                orders.setCartid(cartidNew);
            }
            List<Bill> attachedBillListNew = new ArrayList<Bill>();
            for (Bill billListNewBillToAttach : billListNew) {
                billListNewBillToAttach = em.getReference(billListNewBillToAttach.getClass(), billListNewBillToAttach.getId());
                attachedBillListNew.add(billListNewBillToAttach);
            }
            billListNew = attachedBillListNew;
            orders.setBillList(billListNew);
            orders = em.merge(orders);
            if (cartidOld != null && !cartidOld.equals(cartidNew)) {
                cartidOld.getOrdersList().remove(orders);
                cartidOld = em.merge(cartidOld);
            }
            if (cartidNew != null && !cartidNew.equals(cartidOld)) {
                cartidNew.getOrdersList().add(orders);
                cartidNew = em.merge(cartidNew);
            }
            for (Bill billListOldBill : billListOld) {
                if (!billListNew.contains(billListOldBill)) {
                    billListOldBill.setOrdersid(null);
                    billListOldBill = em.merge(billListOldBill);
                }
            }
            for (Bill billListNewBill : billListNew) {
                if (!billListOld.contains(billListNewBill)) {
                    Orders oldOrdersidOfBillListNewBill = billListNewBill.getOrdersid();
                    billListNewBill.setOrdersid(orders);
                    billListNewBill = em.merge(billListNewBill);
                    if (oldOrdersidOfBillListNewBill != null && !oldOrdersidOfBillListNewBill.equals(orders)) {
                        oldOrdersidOfBillListNewBill.getBillList().remove(billListNewBill);
                        oldOrdersidOfBillListNewBill = em.merge(oldOrdersidOfBillListNewBill);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = orders.getId();
                if (findOrders(id) == null) {
                    throw new NonexistentEntityException("The orders with id " + id + " no longer exists.");
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
            Orders orders;
            try {
                orders = em.getReference(Orders.class, id);
                orders.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orders with id " + id + " no longer exists.", enfe);
            }
            Cart cartid = orders.getCartid();
            if (cartid != null) {
                cartid.getOrdersList().remove(orders);
                cartid = em.merge(cartid);
            }
            List<Bill> billList = orders.getBillList();
            for (Bill billListBill : billList) {
                billListBill.setOrdersid(null);
                billListBill = em.merge(billListBill);
            }
            em.remove(orders);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Orders> findOrdersEntities() {
        return findOrdersEntities(true, -1, -1);
    }

    public List<Orders> findOrdersEntities(int maxResults, int firstResult) {
        return findOrdersEntities(false, maxResults, firstResult);
    }

    private List<Orders> findOrdersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Orders.class));
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

    public Orders findOrders(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Orders.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrdersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Orders> rt = cq.from(Orders.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
