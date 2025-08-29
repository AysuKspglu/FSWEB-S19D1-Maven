package com.workintech.s18d2.controller;

import com.workintech.s18d2.dto.ApiResponse;
import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.exceptions.NotFoundException;
import com.workintech.s18d2.services.FruitService;
import com.workintech.s18d2.validations.RequestValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/workintech/fruits")
public class FruitController {

    private final FruitService service;

    @GetMapping
    public List<Fruit> getAllAsc() { return service.getByPriceAsc(); }

    @GetMapping("/{id}")
    public Fruit getById(@PathVariable Long id) {
        RequestValidator.requirePositiveId(id);
        return service.getById(id); // Optional yok
    }

    @GetMapping("/desc")
    public List<Fruit> getAllDesc() { return service.getByPriceDesc(); }

    @PostMapping
    public ResponseEntity<ApiResponse<Fruit>> upsert(@Valid @RequestBody Fruit fruit) {
        RequestValidator.validateFruit(fruit);
        boolean existed = service.existsById(fruit.getId());
        Fruit saved = service.save(fruit);
        String msg = existed ? "Fruit updated" : "Fruit inserted";
        return ResponseEntity.ok(
                ApiResponse.<Fruit>builder()
                        .success(true).message(msg).data(saved).timestamp(Instant.now()).build()
        );
    }

    @PostMapping("/{name}")
    public List<Fruit> searchByName(@PathVariable String name) {
        return service.searchByName(name);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        RequestValidator.requirePositiveId(id);
        service.delete(id); // 🔁 service NotFoundException fırlatır; test thenReturn(Fruit) ile stub'lar
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true).message("Fruit deleted: " + id).timestamp(Instant.now()).build()
        );
    }
}
