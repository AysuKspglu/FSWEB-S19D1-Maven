package com.workintech.s18d2.services;

import com.workintech.s18d2.entity.Vegetable;
import java.util.List;
import java.util.Optional;

public interface VegetableService {
    List<Vegetable> getByPriceAsc();
    List<Vegetable> getByPriceDesc();
    Vegetable save(Vegetable vegetable);
    void delete(Long id);
    Optional<Vegetable> getById(Long id);
    List<Vegetable> searchByName(String name);
    boolean existsById(Long id);
}
