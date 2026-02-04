package com.workintech.s18d1.controller;


import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerErrorException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workintech/burgers")
public class BurgerController {
    private final BurgerDao burgerDao;

    @Autowired
    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }


    //[GET]/workintech/burgers => tüm burger listini dönmeli.
    @GetMapping("/")
    public List<Burger> getAllBurgers()
    {
        return burgerDao.findAll();
    }

    //[GET]/workintech/burgers/{id} => İlgili id deki burger objesini dönmeli.
    @GetMapping("/{id}")
    public Burger getById(@PathVariable Long id)
    {

        Burger foundBurger = burgerDao.findById(id);
        return foundBurger;
    }

    //[POST]/workintech/burgers => Bir adet burger objesini veritabanına kaydeder.
    @PostMapping("/")
    public Burger saveBurger(@RequestBody Burger burger)
    {
        return burgerDao.save(burger);
    }

    //[PUT]/workintech/burgers/{id} => İlgili id deki burger objesinin değerlerini yeni gelen data ile değiştirir.
    @PutMapping("/{id}")
    public Burger updateBurger(@PathVariable Long id, @RequestBody Burger burger)
    {
        Burger burgerOld = burgerDao.findById(id);
        if(burgerOld != null)
        {
            return burgerDao.update(burger);
        }
        throw new BurgerErrorException("Burger with that id NOT_FOUND", HttpStatus.NOT_FOUND);

    }

    //[DELETE]/workintech/burgers/{id} => İlgili id değerindeki burger objesini veritabanından siler.

    @DeleteMapping("/{id}")
    public Burger deleteBurger(@PathVariable Long id)
    {
        return burgerDao.remove(id);
    }

    //[GET]/workintech/burgers/findByPrice => RequestBody'de price değerini alır ve BurgerDaoImpl sınıfındaki findByPrice metodunu çağırır.
    @GetMapping("/findByPrice")
    public List<Burger> findByPrice(@RequestParam Double price)
    {
        return burgerDao.findByPrice(price);
    }

    //[GET]/workintech/burgers/findByBreadType => RequestBody'de breadType değerini alır ve BurgerDaoImpl sınıfındaki findByBreadType metodunu çağırır.
    @GetMapping("/findByBreadType")
    public List<Burger> findByBreadType(@RequestParam BreadType breadType)
    {
        return burgerDao.findByBreadType(breadType);
    }

    //[GET]/workintech/burgers/findByContent => RequestBody'de content değerini alır ve BurgerDaoImpl sınıfındaki findByContent metodunu çağırır.
    @GetMapping("/findByContent")
    public List<Burger> findByContent(@RequestBody String content)
    {
        return burgerDao.findByContent(content);
    }





}
