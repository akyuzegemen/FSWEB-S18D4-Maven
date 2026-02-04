package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BurgerDaoImpl implements BurgerDao{

    @PersistenceContext
    private EntityManager entityManager;


//    @Autowired
//    public BurgerDaoImpl(EntityManager entityManager)
//    {
//        this.entityManager = entityManager;
//    }

    @Transactional
    @Override
    public Burger save(Burger burger) {  // Now I have to add some exception handler mechanism
        entityManager.persist(burger);
        return burger;
    }

    @Override
    public Burger findById(Long id) {
        return entityManager.find(Burger.class, id);
    }

    @Override
    public List<Burger> findAll() {
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b", Burger.class);
        return query.getResultList();
    }

    @Override
    public List<Burger> findByPrice(Double price) {

        String sql = "SELECT * FROM fsweb_18_4.findByPrice(:price)";

        return entityManager
                .createNativeQuery(sql, Burger.class)
                .setParameter("price", price)
                .getResultList();
    }


    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        return entityManager.createNativeQuery("SELECT * FROM fsweb_18_4.findByBreadType(:breadType)", Burger.class).setParameter("breadType", breadType.name()).getResultList();
    }

    @Override
    public List<Burger> findByContent(String contents) {
        return List.of();
    }

    @Transactional
    @Override
    public Burger update(Burger burger) {
        return entityManager.merge(burger);
    }

    @Transactional
    @Override
    public Burger remove(Long id) {
        Burger burger = entityManager.find(Burger.class, id);
        entityManager.remove(burger);
        return burger;
    }
}
