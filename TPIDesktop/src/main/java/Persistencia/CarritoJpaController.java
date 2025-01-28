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
import Modelo.Carrito;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author emito
 */
public class CarritoJpaController implements Serializable {

    public CarritoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public CarritoJpaController() {
        emf = Persistence.createEntityManagerFactory("TPIPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Carrito carrito) {
        if (carrito.getArticulos() == null) {
            carrito.setArticulos(new ArrayList<Articulo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Articulo> attachedArticulos = new ArrayList<Articulo>();
            for (Articulo articulosArticuloToAttach : carrito.getArticulos()) {
                articulosArticuloToAttach = em.getReference(articulosArticuloToAttach.getClass(), articulosArticuloToAttach.getId_articulo());
                attachedArticulos.add(articulosArticuloToAttach);
            }
            carrito.setArticulos(attachedArticulos);
            em.persist(carrito);
            for (Articulo articulosArticulo : carrito.getArticulos()) {
                Carrito oldCarritoOfArticulosArticulo = articulosArticulo.getCarrito();
                articulosArticulo.setCarrito(carrito);
                articulosArticulo = em.merge(articulosArticulo);
                if (oldCarritoOfArticulosArticulo != null) {
                    oldCarritoOfArticulosArticulo.getArticulos().remove(articulosArticulo);
                    oldCarritoOfArticulosArticulo = em.merge(oldCarritoOfArticulosArticulo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Carrito carrito) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Carrito persistentCarrito = em.find(Carrito.class, carrito.getId());
            List<Articulo> articulosOld = persistentCarrito.getArticulos();
            List<Articulo> articulosNew = carrito.getArticulos();
            List<String> illegalOrphanMessages = null;
            for (Articulo articulosOldArticulo : articulosOld) {
                if (!articulosNew.contains(articulosOldArticulo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Articulo " + articulosOldArticulo + " since its carrito field is not nullable.");
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
            carrito.setArticulos(articulosNew);
            carrito = em.merge(carrito);
            for (Articulo articulosNewArticulo : articulosNew) {
                if (!articulosOld.contains(articulosNewArticulo)) {
                    Carrito oldCarritoOfArticulosNewArticulo = articulosNewArticulo.getCarrito();
                    articulosNewArticulo.setCarrito(carrito);
                    articulosNewArticulo = em.merge(articulosNewArticulo);
                    if (oldCarritoOfArticulosNewArticulo != null && !oldCarritoOfArticulosNewArticulo.equals(carrito)) {
                        oldCarritoOfArticulosNewArticulo.getArticulos().remove(articulosNewArticulo);
                        oldCarritoOfArticulosNewArticulo = em.merge(oldCarritoOfArticulosNewArticulo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = carrito.getId();
                if (findCarrito(id) == null) {
                    throw new NonexistentEntityException("The carrito with id " + id + " no longer exists.");
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
            Carrito carrito;
            try {
                carrito = em.getReference(Carrito.class, id);
                carrito.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The carrito with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Articulo> articulosOrphanCheck = carrito.getArticulos();
            for (Articulo articulosOrphanCheckArticulo : articulosOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Carrito (" + carrito + ") cannot be destroyed since the Articulo " + articulosOrphanCheckArticulo + " in its articulos field has a non-nullable carrito field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(carrito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Carrito> findCarritoEntities() {
        return findCarritoEntities(true, -1, -1);
    }

    public List<Carrito> findCarritoEntities(int maxResults, int firstResult) {
        return findCarritoEntities(false, maxResults, firstResult);
    }

    private List<Carrito> findCarritoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Carrito.class));
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

    public Carrito findCarrito(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Carrito.class, id);
        } finally {
            em.close();
        }
    }

    public int getCarritoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Carrito> rt = cq.from(Carrito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
