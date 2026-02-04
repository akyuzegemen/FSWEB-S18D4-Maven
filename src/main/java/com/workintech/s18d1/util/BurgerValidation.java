package com.workintech.s18d1.util;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class BurgerValidation {

    // ---------- ID ----------
    public void validateId(Integer id) {
        if (id == null) {
            throw new BurgerErrorException("id zorunludur", HttpStatus.BAD_REQUEST);
        }
        if (id <= 0) {
            throw new BurgerErrorException("id pozitif olmalıdır", HttpStatus.BAD_REQUEST);
        }
    }

    // ---------- BODY ----------
    public void validateBody(Object body) {
        if (body == null) {
            throw new BurgerErrorException("Request body boş olamaz", HttpStatus.BAD_REQUEST);
        }
    }

    // ---------- SAVE ----------
    public void validateBurgerForSave(Burger burger) {
        validateBody(burger);

        if (burger.getName() == null || burger.getName().isBlank()) {
            throw new BurgerErrorException("Burger name zorunludur", HttpStatus.BAD_REQUEST);
        }

        if (burger.getPrice() == null) {
            throw new BurgerErrorException("price zorunludur", HttpStatus.BAD_REQUEST);
        }

        if (burger.getPrice() <= 0) {
            throw new BurgerErrorException("price 0'dan büyük olmalıdır", HttpStatus.BAD_REQUEST);
        }

        if (burger.getBreadType() == null) {
            throw new BurgerErrorException("breadType zorunludur", HttpStatus.BAD_REQUEST);
        }
    }

    // ---------- UPDATE ----------
    public void validateBurgerForUpdate(Integer pathId, Burger burger) {
        validateId(pathId);
        validateBurgerForSave(burger);

        // body'de id varsa path id ile tutarlı olmalı
        if (burger.getId() != null && !burger.getId().equals(pathId)) {
            throw new BurgerErrorException(
                    "Path id ile body id aynı olmalıdır",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    // ---------- FIND BY PRICE ----------
    public void validateFindByPrice(Double price) {
        if (price == null) {
            throw new BurgerErrorException("price zorunludur", HttpStatus.BAD_REQUEST);
        }
        if (price < 0) {
            throw new BurgerErrorException("price negatif olamaz", HttpStatus.BAD_REQUEST);
        }
    }

    // ---------- FIND BY BREAD TYPE ----------
    public void validateBreadType(BreadType breadType) {
        if (breadType == null) {
            throw new BurgerErrorException("breadType zorunludur", HttpStatus.BAD_REQUEST);
        }
    }

    // ---------- FIND BY CONTENT ----------
    public void validateContent(String content) {
        if (content == null || content.isBlank()) {
            throw new BurgerErrorException("content zorunludur", HttpStatus.BAD_REQUEST);
        }
        if (content.length() < 2) {
            throw new BurgerErrorException(
                    "content en az 2 karakter olmalıdır",
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
