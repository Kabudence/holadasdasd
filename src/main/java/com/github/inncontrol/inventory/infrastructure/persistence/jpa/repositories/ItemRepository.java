package com.github.inncontrol.inventory.infrastructure.persistence.jpa.repositories;

import com.github.inncontrol.inventory.domain.model.aggregates.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Items, Long> {
Optional<Items> GetItemById(Long ItemId);
List<Items> GetAllItems();
Optional<Items> GetItemByProviderId(Long ProviderId);
boolean existsByItemName(String ItemName);


}
