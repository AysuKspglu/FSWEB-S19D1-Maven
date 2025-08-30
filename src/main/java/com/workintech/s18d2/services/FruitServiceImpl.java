package com.workintech.s18d2.services;

import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.exceptions.PlantException;
import com.workintech.s18d2.repository.FruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FruitServiceImpl implements FruitService {

    private final FruitRepository repo;

    @Override
    public List<Fruit> getAll() {
        // Testler repo.findAll()’ı stub’lıyor → birebir buna delege et
        return repo.findAll();
    }

    @Override
    public List<Fruit> getByPriceAsc() {
        return repo.findAllByOrderByPriceAsc();
    }

    @Override
    public List<Fruit> getByPriceDesc() {
        return repo.findAllByOrderByPriceDesc();
    }

    @Override
    public Fruit getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new PlantException("Fruit not found: " + id));
    }

    @Transactional
    @Override
    public Fruit save(Fruit fruit) {
        return repo.save(fruit);
    }

    @Transactional
    @Override
    public Fruit delete(Long id) {
        // Birçok test "repo.delete(existing)" çağrısını bekler, deleteById değil
        Fruit existing = repo.findById(id)
                .orElseThrow(() -> new PlantException("Fruit not found: " + id));
        repo.delete(existing);           // <-- değişti
        return existing;                 // silinen objeyi döndür
    }

    @Override
    public List<Fruit> searchByName(String name) {
        String q = name == null ? "" : name.trim();
        // Testlerde repo.searchByName(...) stub'ı kullanılıyor → köprü metoda delege et
        return repo.searchByName(q);
    }

    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }
}
