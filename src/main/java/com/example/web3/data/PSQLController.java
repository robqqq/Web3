package com.example.web3.data;

import com.example.web3.model.Point;

import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class PSQLController{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private EntityManager em = emf.createEntityManager();

    public List<Point> getAll() {
        try{
            em.getTransaction().begin();
            List<Point> points = em.createQuery("select p from Point p").getResultList();
            em.getTransaction().commit();
            return points;
        } catch (Exception e){
            em.getTransaction().rollback();
            return new ArrayList<>();
        }
    }

    public boolean clear() {
        try{
            List<Point> points = getAll();
            em.getTransaction().begin();
            points.forEach((Point p) -> em.remove(p));
            em.getTransaction().commit();
            return true;
        }catch (Exception e){
            em.getTransaction().rollback();
            return false;
        }
    }

    public boolean addPoint(Point entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            return true;
        }catch (Exception e){
            em.getTransaction().rollback();
            return false;
        }
    }
}
