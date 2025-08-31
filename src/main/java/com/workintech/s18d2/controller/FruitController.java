package com.workintech.s18d2.controller;

import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.services.FruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/fruit") // <-- testler /fruit, /fruit/desc, /fruit/name/{name} bekliyor
public class FruitController {

    private final FruitService fruitService;

    // GET /fruit  -> fiyat artan
    @GetMapping
    public List<Fruit> getAllAsc() {
        return fruitService.getByPriceAsc();
    }

    // GET /fruit/desc -> fiyat azalan
    @GetMapping("/desc")
    public List<Fruit> getAllDesc() {
        return fruitService.getByPriceDesc();
    }

    // GET /fruit/name/{name} -> adı geçenler
    @GetMapping("/name/{name}")
    public List<Fruit> searchByName(@PathVariable String name) {
        return fruitService.searchByName(name);
    }

    // POST /fruit -> kaydet (body: Fruit)
    @PostMapping
    public Fruit save(@RequestBody Fruit fruit) {
        return fruitService.save(fruit);
    }

    // GET /fruit/{id} -> id ile getir
    @GetMapping("/{id}")
    public Fruit getById(@PathVariable Long id) {
        return fruitService.getById(id);
    }

    // DELETE /fruit/{id} -> sil (silinen objeyi döndürüyoruz; test sadece 200 bekliyor)
    @DeleteMapping("/{id}")
    public Fruit delete(@PathVariable Long id) {
        return fruitService.delete(id);
    }
}
