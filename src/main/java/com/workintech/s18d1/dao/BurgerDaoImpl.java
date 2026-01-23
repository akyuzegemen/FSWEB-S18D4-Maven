package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BurgerDaoImpl implements BurgerDao{

    private EntityManager entityManager;


    @Autowired
    public BurgerDaoImpl(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public Burger save(Burger burger) {  // Now I have to add some execption handler mechanism
        entityManager.persist(burger);
        return burger;
    }

    @Override
    public Burger findById(Long id) {
        return null;
    }

    @Override
    public List<Burger> findAll() {
        return List.of();
    }

    @Override
    public List<Burger> findByPrice(Double price) {
        return List.of();
    }

    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        return List.of();
    }

    @Override
    public List<Burger> findByContent(String contents) {
        return List.of();
    }

    @Override
    public Burger update(Burger burger) {
        return null;
    }

    @Override
    public Burger remove(Long id) {
        return null;
    }
}
