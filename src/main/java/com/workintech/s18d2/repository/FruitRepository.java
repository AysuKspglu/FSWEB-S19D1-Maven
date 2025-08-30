package com.workintech.s18d2.repository;

import com.workintech.s18d2.entity.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FruitRepository extends JpaRepository<Fruit, Long> {
    List<Fruit> findAllByOrderByPriceAsc();
    List<Fruit> findAllByOrderByPriceDesc();
    List<Fruit> findByNameContainingIgnoreCase(String name);
    List<Fruit> findByNameIgnoreCase(String name);

    // Testlerin kullandığı isimler için köprüler
    default List<Fruit> getByPriceAsc()  { return findAllByOrderByPriceAsc(); }
    default List<Fruit> getByPriceDesc() { return findAllByOrderByPriceDesc(); }
    default List<Fruit> searchByName(String name) { return findByNameContainingIgnoreCase(name); }
}
