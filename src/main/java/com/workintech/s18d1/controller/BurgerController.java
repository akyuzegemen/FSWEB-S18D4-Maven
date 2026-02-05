package com.workintech.s18d1.controller;


import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/burger")
public class BurgerController {
    private final BurgerDao burgerDao;

    @Autowired
    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }

    //[GET]/workintech/burgers => tüm burger listini dönmeli.
    @GetMapping
    public List<Burger> getAllBurgers()
    {
        return burgerDao.findAll();
    }

    //[GET]/workintech/burgers/{id} => İlgili id deki burger objesini dönmeli.
    @GetMapping("/{id}")
    public Burger getById(@PathVariable Long id)
    {
        return burgerDao.findById(id);
    }

    //[POST]/workintech/burgers => Bir adet burger objesini veritabanına kaydeder.
    @PostMapping
    public Burger saveBurger(@RequestBody Burger burger)
    {
        return burgerDao.save(burger);
    }

    //[PUT]/workintech/burgers/{id} => İlgili id deki burger objesinin değerlerini yeni gelen data ile değiştirir.
    @PutMapping
    public Burger updateBurger(@RequestBody Burger burger)
    {
        return burgerDao.update(burger);
    }

    //[DELETE]/workintech/burgers/{id} => İlgili id değerindeki burger objesini veritabanından siler.
    @DeleteMapping("/{id}")
    public Burger deleteBurger(@PathVariable Long id)
    {
        return burgerDao.remove(id);
    }

    //[GET]/workintech/burgers/findByPrice => RequestBody'de price değerini alır ve BurgerDaoImpl sınıfındaki findByPrice metodunu çağırır.
    @GetMapping("/price/{price}")
    public List<Burger> findByPrice(@PathVariable int price)
    {
        return burgerDao.findByPrice(price);
    }

    //[GET]/workintech/burgers/findByBreadType => RequestBody'de breadType değerini alır ve BurgerDaoImpl sınıfındaki findByBreadType metodunu çağırır.
    @GetMapping("/breadType/{breadType}")
    public List<Burger> findByBreadType(@PathVariable BreadType breadType)
    {
        return burgerDao.findByBreadType(breadType);
    }

    //[GET]/workintech/burgers/findByContent => RequestBody'de content değerini alır ve BurgerDaoImpl sınıfındaki findByContent metodunu çağırır.
    @GetMapping("/content/{content}")
    public List<Burger> findByContent(@PathVariable String content)
    {
        return burgerDao.findByContent(content);
    }





}
