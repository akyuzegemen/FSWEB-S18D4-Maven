package com.workintech.s18d1.util;

import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class BurgerValidation {

    // ---------- ID ----------
    public void validateId(Long id) {
        if (id == null) {
            throw new BurgerException("id zorunludur", HttpStatus.BAD_REQUEST);
        }
        if (id <= 0) {
            throw new BurgerException("id pozitif olmalıdır", HttpStatus.BAD_REQUEST);
        }
    }

    // ---------- BODY ----------
    public void validateBody(Object body) {
        if (body == null) {
            throw new BurgerException("Request body boş olamaz", HttpStatus.BAD_REQUEST);
        }
    }

    // ---------- SAVE ----------
    public void validateBurgerForSave(Burger burger) {
        validateBody(burger);

        if (burger.getName() == null || burger.getName().isBlank()) {
            throw new BurgerException("Burger name zorunludur", HttpStatus.BAD_REQUEST);
        }

        if (burger.getPrice() == null) {
            throw new BurgerException("price zorunludur", HttpStatus.BAD_REQUEST);
        }

        if (burger.getPrice() <= 0) {
            throw new BurgerException("price 0'dan büyük olmalıdır", HttpStatus.BAD_REQUEST);
        }

        if (burger.getBreadType() == null) {
            throw new BurgerException("breadType zorunludur", HttpStatus.BAD_REQUEST);
        }
    }

    // ---------- UPDATE ----------
    public void validateBurgerForUpdate(Long pathId, Burger burger) {
        validateId(pathId);
        validateBurgerForSave(burger);
    }

    // ---------- FIND BY PRICE ----------
    public void validateFindByPrice(Double price) {
        if (price == null) {
            throw new BurgerException("price zorunludur", HttpStatus.BAD_REQUEST);
        }
        if (price < 0) {
            throw new BurgerException("price negatif olamaz", HttpStatus.BAD_REQUEST);
        }
    }

    // ---------- FIND BY BREAD TYPE ----------
    public void validateBreadType(BreadType breadType) {
        if (breadType == null) {
            throw new BurgerException("breadType zorunludur", HttpStatus.BAD_REQUEST);
        }
    }

    // ---------- FIND BY CONTENT ----------
    public void validateContent(String content) {
        if (content == null || content.isBlank()) {
            throw new BurgerException("content zorunludur", HttpStatus.BAD_REQUEST);
        }
        if (content.length() < 2) {
            throw new BurgerException(
                    "content en az 2 karakter olmalıdır",
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
