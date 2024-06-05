package com.github.inncontrol.inventory.infrastructure.persistence.jpa.repositories;

import com.github.inncontrol.inventory.domain.model.aggregates.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findAllByBrand(String brand);
    boolean existsByProductTitle(String itemName);
}
