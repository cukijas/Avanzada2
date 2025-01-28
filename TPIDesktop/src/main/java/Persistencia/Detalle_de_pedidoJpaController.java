/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Detalle_de_pedido;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Producto;
import Modelo.Pedido;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author emito
 */
public class Detalle_de_pedidoJpaController implements Serializable {

    public Detalle_de_pedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public Detalle_de_pedidoJpaController() {
        emf = Persistence.createEntityManagerFactory("TPIPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalle_de_pedido detalle_de_pedido) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto = detalle_de_pedido.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getId());
                detalle_de_pedido.setProducto(producto);
            }
            Pedido pedido = detalle_de_pedido.getPedido();
            if (pedido != null) {
                pedido = em.getReference(pedido.getClass(), pedido.getId());
                detalle_de_pedido.setPedido(pedido);
            }
            em.persist(detalle_de_pedido);
            if (producto != null) {
                producto.getDetalle_de_pedido().add(detalle_de_pedido);
                producto = em.merge(producto);
            }
            if (pedido != null) {
                pedido.getDetalle().add(detalle_de_pedido);
                pedido = em.merge(pedido);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalle_de_pedido detalle_de_pedido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalle_de_pedido persistentDetalle_de_pedido = em.find(Detalle_de_pedido.class, detalle_de_pedido.getId());
            Producto productoOld = persistentDetalle_de_pedido.getProducto();
            Producto productoNew = detalle_de_pedido.getProducto();
            Pedido pedidoOld = persistentDetalle_de_pedido.getPedido();
            Pedido pedidoNew = detalle_de_pedido.getPedido();
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getId());
                detalle_de_pedido.setProducto(productoNew);
            }
            if (pedidoNew != null) {
                pedidoNew = em.getReference(pedidoNew.getClass(), pedidoNew.getId());
                detalle_de_pedido.setPedido(pedidoNew);
            }
            detalle_de_pedido = em.merge(detalle_de_pedido);
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getDetalle_de_pedido().remove(detalle_de_pedido);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getDetalle_de_pedido().add(detalle_de_pedido);
                productoNew = em.merge(productoNew);
            }
            if (pedidoOld != null && !pedidoOld.equals(pedidoNew)) {
                pedidoOld.getDetalle().remove(detalle_de_pedido);
                pedidoOld = em.merge(pedidoOld);
            }
            if (pedidoNew != null && !pedidoNew.equals(pedidoOld)) {
                pedidoNew.getDetalle().add(detalle_de_pedido);
                pedidoNew = em.merge(pedidoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = detalle_de_pedido.getId();
                if (findDetalle_de_pedido(id) == null) {
                    throw new NonexistentEntityException("The detalle_de_pedido with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalle_de_pedido detalle_de_pedido;
            try {
                detalle_de_pedido = em.getReference(Detalle_de_pedido.class, id);
                detalle_de_pedido.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalle_de_pedido with id " + id + " no longer exists.", enfe);
            }
            Producto producto = detalle_de_pedido.getProducto();
            if (producto != null) {
                producto.getDetalle_de_pedido().remove(detalle_de_pedido);
                producto = em.merge(producto);
            }
            Pedido pedido = detalle_de_pedido.getPedido();
            if (pedido != null) {
                pedido.getDetalle().remove(detalle_de_pedido);
                pedido = em.merge(pedido);
            }
            em.remove(detalle_de_pedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalle_de_pedido> findDetalle_de_pedidoEntities() {
        return findDetalle_de_pedidoEntities(true, -1, -1);
    }

    public List<Detalle_de_pedido> findDetalle_de_pedidoEntities(int maxResults, int firstResult) {
        return findDetalle_de_pedidoEntities(false, maxResults, firstResult);
    }

    private List<Detalle_de_pedido> findDetalle_de_pedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalle_de_pedido.class));
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

    public Detalle_de_pedido findDetalle_de_pedido(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalle_de_pedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalle_de_pedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalle_de_pedido> rt = cq.from(Detalle_de_pedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
