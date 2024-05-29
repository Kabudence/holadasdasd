package com.github.inncontrol.inventory.domain.model.entities;


import com.github.inncontrol.inventory.domain.model.aggregates.Items;
import com.github.inncontrol.shared.domain.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Provider extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "Items_id")
    private Items items;

    @Column(nullable = false)
    @Getter
    private String providerName;

    @Column(nullable = false)
    @Getter
    private String description;

    @Column(nullable = false)
    @Getter
    private Long contact;

    public Provider() {}


}
