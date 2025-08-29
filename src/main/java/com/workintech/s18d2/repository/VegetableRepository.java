package com.workintech.s18d2.repository;

import com.workintech.s18d2.entity.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VegetableRepository extends JpaRepository<Vegetable, Long> {
    List<Vegetable> findAllByOrderByPriceAsc();
    List<Vegetable> findAllByOrderByPriceDesc();
    List<Vegetable> findByNameContainingIgnoreCase(String name);

    // ✅ Test uyumluluğu
    default List<Vegetable> getByPriceAsc()  { return findAllByOrderByPriceAsc(); }
    default List<Vegetable> getByPriceDesc() { return findAllByOrderByPriceDesc(); }
    default List<Vegetable> searchByName(String name) { return findByNameContainingIgnoreCase(name); }
}
