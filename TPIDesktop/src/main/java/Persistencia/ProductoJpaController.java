/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Articulo;
import java.util.ArrayList;
import java.util.List;
import Modelo.Detalle_de_pedido;
import Modelo.Producto;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author emito
 */
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public ProductoJpaController() {
        emf = Persistence.createEntityManagerFactory("TPIPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) {
        if (producto.getArticulos() == null) {
            producto.setArticulos(new ArrayList<Articulo>());
        }
        if (producto.getDetalle_de_pedido() == null) {
            producto.setDetalle_de_pedido(new ArrayList<Detalle_de_pedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Articulo> attachedArticulos = new ArrayList<Articulo>();
            for (Articulo articulosArticuloToAttach : producto.getArticulos()) {
                articulosArticuloToAttach = em.getReference(articulosArticuloToAttach.getClass(), articulosArticuloToAttach.getId_articulo());
                attachedArticulos.add(articulosArticuloToAttach);
            }
            producto.setArticulos(attachedArticulos);
            List<Detalle_de_pedido> attachedDetalle_de_pedido = new ArrayList<Detalle_de_pedido>();
            for (Detalle_de_pedido detalle_de_pedidoDetalle_de_pedidoToAttach : producto.getDetalle_de_pedido()) {
                detalle_de_pedidoDetalle_de_pedidoToAttach = em.getReference(detalle_de_pedidoDetalle_de_pedidoToAttach.getClass(), detalle_de_pedidoDetalle_de_pedidoToAttach.getId());
                attachedDetalle_de_pedido.add(detalle_de_pedidoDetalle_de_pedidoToAttach);
            }
            producto.setDetalle_de_pedido(attachedDetalle_de_pedido);
            em.persist(producto);
            for (Articulo articulosArticulo : producto.getArticulos()) {
                Producto oldProductoOfArticulosArticulo = articulosArticulo.getProducto();
                articulosArticulo.setProducto(producto);
                articulosArticulo = em.merge(articulosArticulo);
                if (oldProductoOfArticulosArticulo != null) {
                    oldProductoOfArticulosArticulo.getArticulos().remove(articulosArticulo);
                    oldProductoOfArticulosArticulo = em.merge(oldProductoOfArticulosArticulo);
                }
            }
            for (Detalle_de_pedido detalle_de_pedidoDetalle_de_pedido : producto.getDetalle_de_pedido()) {
                Producto oldProductoOfDetalle_de_pedidoDetalle_de_pedido = detalle_de_pedidoDetalle_de_pedido.getProducto();
                detalle_de_pedidoDetalle_de_pedido.setProducto(producto);
                detalle_de_pedidoDetalle_de_pedido = em.merge(detalle_de_pedidoDetalle_de_pedido);
                if (oldProductoOfDetalle_de_pedidoDetalle_de_pedido != null) {
                    oldProductoOfDetalle_de_pedidoDetalle_de_pedido.getDetalle_de_pedido().remove(detalle_de_pedidoDetalle_de_pedido);
                    oldProductoOfDetalle_de_pedidoDetalle_de_pedido = em.merge(oldProductoOfDetalle_de_pedidoDetalle_de_pedido);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getId());
            List<Articulo> articulosOld = persistentProducto.getArticulos();
            List<Articulo> articulosNew = producto.getArticulos();
            List<Detalle_de_pedido> detalle_de_pedidoOld = persistentProducto.getDetalle_de_pedido();
            List<Detalle_de_pedido> detalle_de_pedidoNew = producto.getDetalle_de_pedido();
            List<String> illegalOrphanMessages = null;
            for (Articulo articulosOldArticulo : articulosOld) {
                if (!articulosNew.contains(articulosOldArticulo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Articulo " + articulosOldArticulo + " since its producto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Articulo> attachedArticulosNew = new ArrayList<Articulo>();
            for (Articulo articulosNewArticuloToAttach : articulosNew) {
                articulosNewArticuloToAttach = em.getReference(articulosNewArticuloToAttach.getClass(), articulosNewArticuloToAttach.getId_articulo());
                attachedArticulosNew.add(articulosNewArticuloToAttach);
            }
            articulosNew = attachedArticulosNew;
            producto.setArticulos(articulosNew);
            List<Detalle_de_pedido> attachedDetalle_de_pedidoNew = new ArrayList<Detalle_de_pedido>();
            for (Detalle_de_pedido detalle_de_pedidoNewDetalle_de_pedidoToAttach : detalle_de_pedidoNew) {
                detalle_de_pedidoNewDetalle_de_pedidoToAttach = em.getReference(detalle_de_pedidoNewDetalle_de_pedidoToAttach.getClass(), detalle_de_pedidoNewDetalle_de_pedidoToAttach.getId());
                attachedDetalle_de_pedidoNew.add(detalle_de_pedidoNewDetalle_de_pedidoToAttach);
            }
            detalle_de_pedidoNew = attachedDetalle_de_pedidoNew;
            producto.setDetalle_de_pedido(detalle_de_pedidoNew);
            producto = em.merge(producto);
            for (Articulo articulosNewArticulo : articulosNew) {
                if (!articulosOld.contains(articulosNewArticulo)) {
                    Producto oldProductoOfArticulosNewArticulo = articulosNewArticulo.getProducto();
                    articulosNewArticulo.setProducto(producto);
                    articulosNewArticulo = em.merge(articulosNewArticulo);
                    if (oldProductoOfArticulosNewArticulo != null && !oldProductoOfArticulosNewArticulo.equals(producto)) {
                        oldProductoOfArticulosNewArticulo.getArticulos().remove(articulosNewArticulo);
                        oldProductoOfArticulosNewArticulo = em.merge(oldProductoOfArticulosNewArticulo);
                    }
                }
            }
            for (Detalle_de_pedido detalle_de_pedidoOldDetalle_de_pedido : detalle_de_pedidoOld) {
                if (!detalle_de_pedidoNew.contains(detalle_de_pedidoOldDetalle_de_pedido)) {
                    detalle_de_pedidoOldDetalle_de_pedido.setProducto(null);
                    detalle_de_pedidoOldDetalle_de_pedido = em.merge(detalle_de_pedidoOldDetalle_de_pedido);
                }
            }
            for (Detalle_de_pedido detalle_de_pedidoNewDetalle_de_pedido : detalle_de_pedidoNew) {
                if (!detalle_de_pedidoOld.contains(detalle_de_pedidoNewDetalle_de_pedido)) {
                    Producto oldProductoOfDetalle_de_pedidoNewDetalle_de_pedido = detalle_de_pedidoNewDetalle_de_pedido.getProducto();
                    detalle_de_pedidoNewDetalle_de_pedido.setProducto(producto);
                    detalle_de_pedidoNewDetalle_de_pedido = em.merge(detalle_de_pedidoNewDetalle_de_pedido);
                    if (oldProductoOfDetalle_de_pedidoNewDetalle_de_pedido != null && !oldProductoOfDetalle_de_pedidoNewDetalle_de_pedido.equals(producto)) {
                        oldProductoOfDetalle_de_pedidoNewDetalle_de_pedido.getDetalle_de_pedido().remove(detalle_de_pedidoNewDetalle_de_pedido);
                        oldProductoOfDetalle_de_pedidoNewDetalle_de_pedido = em.merge(oldProductoOfDetalle_de_pedidoNewDetalle_de_pedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = producto.getId();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Articulo> articulosOrphanCheck = producto.getArticulos();
            for (Articulo articulosOrphanCheckArticulo : articulosOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the Articulo " + articulosOrphanCheckArticulo + " in its articulos field has a non-nullable producto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Detalle_de_pedido> detalle_de_pedido = producto.getDetalle_de_pedido();
            for (Detalle_de_pedido detalle_de_pedidoDetalle_de_pedido : detalle_de_pedido) {
                detalle_de_pedidoDetalle_de_pedido.setProducto(null);
                detalle_de_pedidoDetalle_de_pedido = em.merge(detalle_de_pedidoDetalle_de_pedido);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
