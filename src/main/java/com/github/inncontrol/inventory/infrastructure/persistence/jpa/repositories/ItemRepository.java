package com.github.inncontrol.inventory.infrastructure.persistence.jpa.repositories;

import com.github.inncontrol.inventory.domain.model.aggregates.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findById(Long id);
    List<Inventory> findAll();
    List<Inventory> findAllByBrand(String brand);
    boolean existsByProductTitle(String itemName);
}
