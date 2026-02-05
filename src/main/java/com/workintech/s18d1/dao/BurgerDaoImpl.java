package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import com.workintech.s18d1.util.BurgerValidation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BurgerDaoImpl implements BurgerDao{

    private final EntityManager entityManager;

    private final BurgerValidation burgerValidation;


    @Autowired
    public BurgerDaoImpl(BurgerValidation burgerValidation, EntityManager entityManager)
    {
        this.burgerValidation = burgerValidation;
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public Burger save(Burger burger) {  // Now I have to add some exception handler mechanism
        burgerValidation.validateBurgerForSave(burger);
//        if(entityManager.find(Burger.class, burger.getId()) != null)
//        {
//            throw new BurgerException("This id belongs to another burger", HttpStatus.BAD_REQUEST);
//        }
        entityManager.persist(burger);
        return burger;
    }

    @Override
    public Burger findById(Long id) {
        burgerValidation.validateId(id);
        Burger burgerFound = entityManager.find(Burger.class, id);

        if(burgerFound == null)
        {
            throw new BurgerException("Burger not found", HttpStatus.NOT_FOUND);
        }
        return burgerFound;
    }

    @Override
    public List<Burger> findAll() {
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b", Burger.class);
        return query.getResultList();
    }

    @Override
    public List<Burger> findByPrice(int price) {
        double dPrice = price;
        burgerValidation.validateFindByPrice(dPrice);
        return entityManager
                .createQuery(
                        "SELECT b FROM Burger b WHERE b.price > :price ORDER BY b.price DESC",
                        Burger.class
                )
                .setParameter("price", dPrice)
                .getResultList();
    }



    @Override
    public List<Burger> findByBreadType(BreadType breadType) {
        burgerValidation.validateBreadType(breadType);

        TypedQuery<Burger> query = entityManager.createQuery(
                "SELECT b FROM Burger b WHERE b.breadType = :breadType ORDER BY b.name ASC",
                Burger.class
        );
        query.setParameter("breadType", breadType);
        return query.getResultList();
    }

    @Override
    public List<Burger> findByContent(String contents) {
        burgerValidation.validateContent(contents);
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b WHERE b.content = :contents",
                Burger.class);
        query.setParameter("contents", contents);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Burger update(Burger burger) {
        burgerValidation.validateBurgerForUpdate(burger.getId(), burger);
        Burger burgerFound = entityManager.find(Burger.class, burger.getId());
        return entityManager.merge(burger);
    }

    @Transactional
    @Override
    public Burger remove(Long id) {
        burgerValidation.validateId(id);
        Burger burger = entityManager.find(Burger.class, id);
        if(burger == null)
        {
            throw new BurgerException("Burger is not found with id: "+ id, HttpStatus.NOT_FOUND);
        }
        entityManager.remove(burger);
        return burger;
    }
}
