/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Articulo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Producto;
import Modelo.Carrito;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author emito
 */
public class ArticuloJpaController implements Serializable {

    public ArticuloJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public ArticuloJpaController() {
        emf = Persistence.createEntityManagerFactory("TPIPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Articulo articulo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto = articulo.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getId());
                articulo.setProducto(producto);
            }
            Carrito carrito = articulo.getCarrito();
            if (carrito != null) {
                carrito = em.getReference(carrito.getClass(), carrito.getId());
                articulo.setCarrito(carrito);
            }
            em.persist(articulo);
            if (producto != null) {
                producto.getArticulos().add(articulo);
                producto = em.merge(producto);
            }
            if (carrito != null) {
                carrito.getArticulos().add(articulo);
                carrito = em.merge(carrito);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Articulo articulo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articulo persistentArticulo = em.find(Articulo.class, articulo.getId_articulo());
            Producto productoOld = persistentArticulo.getProducto();
            Producto productoNew = articulo.getProducto();
            Carrito carritoOld = persistentArticulo.getCarrito();
            Carrito carritoNew = articulo.getCarrito();
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getId());
                articulo.setProducto(productoNew);
            }
            if (carritoNew != null) {
                carritoNew = em.getReference(carritoNew.getClass(), carritoNew.getId());
                articulo.setCarrito(carritoNew);
            }
            articulo = em.merge(articulo);
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getArticulos().remove(articulo);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getArticulos().add(articulo);
                productoNew = em.merge(productoNew);
            }
            if (carritoOld != null && !carritoOld.equals(carritoNew)) {
                carritoOld.getArticulos().remove(articulo);
                carritoOld = em.merge(carritoOld);
            }
            if (carritoNew != null && !carritoNew.equals(carritoOld)) {
                carritoNew.getArticulos().add(articulo);
                carritoNew = em.merge(carritoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = articulo.getId_articulo();
                if (findArticulo(id) == null) {
                    throw new NonexistentEntityException("The articulo with id " + id + " no longer exists.");
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
            Articulo articulo;
            try {
                articulo = em.getReference(Articulo.class, id);
                articulo.getId_articulo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The articulo with id " + id + " no longer exists.", enfe);
            }
            Producto producto = articulo.getProducto();
            if (producto != null) {
                producto.getArticulos().remove(articulo);
                producto = em.merge(producto);
            }
            Carrito carrito = articulo.getCarrito();
            if (carrito != null) {
                carrito.getArticulos().remove(articulo);
                carrito = em.merge(carrito);
            }
            em.remove(articulo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Articulo> findArticuloEntities() {
        return findArticuloEntities(true, -1, -1);
    }

    public List<Articulo> findArticuloEntities(int maxResults, int firstResult) {
        return findArticuloEntities(false, maxResults, firstResult);
    }

    private List<Articulo> findArticuloEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Articulo.class));
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

    public Articulo findArticulo(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Articulo.class, id);
        } finally {
            em.close();
        }
    }

    public int getArticuloCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Articulo> rt = cq.from(Articulo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
