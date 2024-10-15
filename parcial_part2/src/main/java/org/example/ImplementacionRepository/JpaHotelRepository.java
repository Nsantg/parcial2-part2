package org.example.ImplementacionRepository;

import org.example.interfaces.HotelRepository;
import org.example.domain.Hotel;

import javax.persistence.*;
import java.util.List;

public class JpaHotelRepository implements HotelRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("HotelPU");

    @Override
    public void guardar(Hotel hotel) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(hotel);
            tx.commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Hotel obtenerPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Hotel.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Hotel> obtenerTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT h FROM Hotel h", Hotel.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(Hotel hotel) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(hotel);
            tx.commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminar(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Hotel hotel = em.find(Hotel.class, id);
            if (hotel != null) {
                em.remove(hotel);
            }
            tx.commit();
        } finally {
            em.close();
        }
    }
}
