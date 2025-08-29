package com.workintech.s18d2.controller;

import com.workintech.s18d2.dto.ApiResponse;
import com.workintech.s18d2.entity.Vegetable;
import com.workintech.s18d2.exceptions.NotFoundException;
import com.workintech.s18d2.services.VegetableService;
import com.workintech.s18d2.validations.RequestValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/workintech/vegetables")
public class VegetableController {

    private final VegetableService service;

    // [GET]/workintech/vegetables => tüm vegetables kayıtlarını price göre artan sırada döner
    @GetMapping
    public List<Vegetable> getAllAsc() {
        return service.getByPriceAsc();   // Service imzalarıyla uyumlu
    }

    // [GET]/workintech/vegetables/{id} => İlgili id deki vegetable obje
    @GetMapping("/{id}")
    public Vegetable getById(@PathVariable Long id) {
        RequestValidator.requirePositiveId(id);
        return service.getById(id)
                .orElseThrow(() -> new NotFoundException("Vegetable not found: " + id));
    }

    // [GET]/workintech/vegetables/desc => price'a göre azalan
    @GetMapping("/desc")
    public List<Vegetable> getAllDesc() {
        return service.getByPriceDesc();
    }

    // [POST]/workintech/vegetables => id'ye göre insert/update
    @PostMapping
    public ResponseEntity<ApiResponse<Vegetable>> upsert(@Valid @RequestBody Vegetable vegetable) {
        RequestValidator.validateVegetable(vegetable);
        boolean existed = service.existsById(vegetable.getId());
        Vegetable saved = service.save(vegetable);
        String msg = existed ? "Vegetable updated" : "Vegetable inserted";
        return ResponseEntity.ok(
                ApiResponse.<Vegetable>builder()
                        .success(true)
                        .message(msg)
                        .data(saved)
                        .timestamp(Instant.now())
                        .build()
        );
    }

    // [POST]/workintech/vegetables/{name} => name içeren tüm kayıtları döner
    @PostMapping("/{name}")
    public List<Vegetable> searchByName(@PathVariable String name) {
        return service.searchByName(name);
    }

    // [DELETE]/workintech/vegetables/{id} => id'ye göre siler
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        RequestValidator.requirePositiveId(id);
        if (!service.existsById(id)) {
            throw new NotFoundException("Vegetable not found: " + id);
        }
        service.delete(id);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Vegetable deleted: " + id)
                        .timestamp(Instant.now())
                        .build()
        );
    }
}
