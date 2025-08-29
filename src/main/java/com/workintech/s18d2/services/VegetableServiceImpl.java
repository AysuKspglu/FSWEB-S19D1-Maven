package com.workintech.s18d2.services;

import com.workintech.s18d2.entity.Vegetable;
import com.workintech.s18d2.repository.VegetableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VegetableServiceImpl implements VegetableService {

    private final VegetableRepository repo;

    @Override
    public List<Vegetable> getByPriceAsc() { return repo.findAllByOrderByPriceAsc(); }

    @Override
    public List<Vegetable> getByPriceDesc() { return repo.findAllByOrderByPriceDesc(); }

    @Transactional
    @Override
    public Vegetable save(Vegetable vegetable) { return repo.save(vegetable); }

    @Transactional
    @Override
    public void delete(Long id) { repo.deleteById(id); }

    @Override
    public Optional<Vegetable> getById(Long id) { return repo.findById(id); }

    @Override
    public List<Vegetable> searchByName(String name) { return repo.findByNameContainingIgnoreCase(name); }

    @Override
    public boolean existsById(Long id) { return repo.existsById(id); }
}
