package com.github.inncontrol.communications.infrastructure.persistence.jpa.repositories;

import com.github.inncontrol.communications.domain.model.aggregates.Message;
import com.github.inncontrol.communications.domain.model.valueobjects.ProfileIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT DISTINCT m.receiver.profileId FROM Message m WHERE m.sender = :senderId")
    List<Long> findAllReceiversBySenderId(ProfileIdentifier senderId);

    List<Message> getAllBySenderAndReceiver(ProfileIdentifier sender, ProfileIdentifier receiver);
}
