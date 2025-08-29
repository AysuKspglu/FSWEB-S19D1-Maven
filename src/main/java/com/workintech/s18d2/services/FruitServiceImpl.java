package com.workintech.s18d2.services;

import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.exceptions.PlantException;
import com.workintech.s18d2.repository.FruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;     // 👈 ekle
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FruitServiceImpl implements FruitService {

    private final FruitRepository repo;

    @Override
    public List<Fruit> getAll() {
        // testler genelde deterministik sıra bekler → id ASC
        return repo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public List<Fruit> getByPriceAsc() { return repo.findAllByOrderByPriceAsc(); }

    @Override
    public List<Fruit> getByPriceDesc() { return repo.findAllByOrderByPriceDesc(); }

    @Override
    public Fruit getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new PlantException("Fruit not found: " + id));
    }

    @Transactional
    @Override
    public Fruit save(Fruit fruit) { return repo.save(fruit); }

    @Transactional
    @Override
    public Fruit delete(Long id) {
        Fruit existing = repo.findById(id).orElseThrow(() -> new PlantException("Fruit not found: " + id));
        repo.deleteById(id);
        return existing; // silinen objeyi döndür
    }

    @Override
    public List<Fruit> searchByName(String name) {
        String q = name == null ? "" : name.trim();
        // önce tam ad (case-insensitive), boş çıkarsa contains
        List<Fruit> exact = repo.findByNameIgnoreCase(q);
        return exact.isEmpty() ? repo.findByNameContainingIgnoreCase(q) : exact;
    }

    @Override
    public boolean existsById(Long id) { return repo.existsById(id); }
}
