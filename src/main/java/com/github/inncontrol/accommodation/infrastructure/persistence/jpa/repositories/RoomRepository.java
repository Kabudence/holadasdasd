package com.github.inncontrol.accommodation.infrastructure.persistence.jpa.repositories;

import com.github.inncontrol.accommodation.domain.model.aggregates.Room;
import com.github.inncontrol.accommodation.domain.model.valueobjects.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByType(RoomType roomType);

    boolean existsByRoomNumber(int roomNumber);
    boolean existsByRoomNumberAndIdIsNot(int roomNumber, Long id);
}
