/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kist.bit.dookan.services;

import edu.kist.bit.dookan.entity.Bill;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import edu.kist.bit.dookan.entity.Orders;
import edu.kist.bit.dookan.services.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author shankar xtha
 */
public class BillJpaController implements Serializable {

    public BillJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Bill bill) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Orders ordersid = bill.getOrdersid();
            if (ordersid != null) {
                ordersid = em.getReference(ordersid.getClass(), ordersid.getId());
                bill.setOrdersid(ordersid);
            }
            em.persist(bill);
            if (ordersid != null) {
                ordersid.getBillList().add(bill);
                ordersid = em.merge(ordersid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Bill bill) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bill persistentBill = em.find(Bill.class, bill.getId());
            Orders ordersidOld = persistentBill.getOrdersid();
            Orders ordersidNew = bill.getOrdersid();
            if (ordersidNew != null) {
                ordersidNew = em.getReference(ordersidNew.getClass(), ordersidNew.getId());
                bill.setOrdersid(ordersidNew);
            }
            bill = em.merge(bill);
            if (ordersidOld != null && !ordersidOld.equals(ordersidNew)) {
                ordersidOld.getBillList().remove(bill);
                ordersidOld = em.merge(ordersidOld);
            }
            if (ordersidNew != null && !ordersidNew.equals(ordersidOld)) {
                ordersidNew.getBillList().add(bill);
                ordersidNew = em.merge(ordersidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bill.getId();
                if (findBill(id) == null) {
                    throw new NonexistentEntityException("The bill with id " + id + " no longer exists.");
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
            Bill bill;
            try {
                bill = em.getReference(Bill.class, id);
                bill.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bill with id " + id + " no longer exists.", enfe);
            }
            Orders ordersid = bill.getOrdersid();
            if (ordersid != null) {
                ordersid.getBillList().remove(bill);
                ordersid = em.merge(ordersid);
            }
            em.remove(bill);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Bill> findBillEntities() {
        return findBillEntities(true, -1, -1);
    }

    public List<Bill> findBillEntities(int maxResults, int firstResult) {
        return findBillEntities(false, maxResults, firstResult);
    }

    private List<Bill> findBillEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Bill.class));
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

    public Bill findBill(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Bill.class, id);
        } finally {
            em.close();
        }
    }

    public int getBillCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Bill> rt = cq.from(Bill.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
