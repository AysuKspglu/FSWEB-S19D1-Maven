package com.workintech.s18d2.validations;

import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.entity.Vegetable;
import com.workintech.s18d2.exceptions.BadRequestException;
import com.workintech.s18d2.exceptions.NegativeIdException;

public final class RequestValidator {
    private RequestValidator() {}

    public static void requirePositiveId(Long id) {
        if (id == null || id <= 0) throw new NegativeIdException(id);
    }

    public static void validateFruit(Fruit f) {
        if (f == null || f.getId() == null || f.getName() == null || f.getName().isBlank()
                || f.getPrice() == null || f.getPrice() < 0 || f.getFruitType() == null) {
            throw new BadRequestException("Fruit has missing/invalid fields");
        }
    }

    public static void validateVegetable(Vegetable v) {
        if (v == null || v.getId() == null || v.getName() == null || v.getName().isBlank()
                || v.getPrice() == null || v.getPrice() < 0) {
            // grownOnTree primitive boolean -> null olamaz, ekstra kontrol gerekmiyor
            throw new BadRequestException("Vegetable has missing/invalid fields");
        }
    }
}
