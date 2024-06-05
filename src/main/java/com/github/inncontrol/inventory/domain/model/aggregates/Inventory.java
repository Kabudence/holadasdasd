package com.github.inncontrol.inventory.domain.model.aggregates;

import com.github.inncontrol.inventory.domain.model.commands.CreateItemsCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

@Getter
@Entity
public class Inventory extends AbstractAggregateRoot<Inventory> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productTitle;

    @Column(nullable = false)
    private String productDescription;

    @Column(nullable = false)
    private Integer productQuantity;

    @Column(nullable = false)
    private String brand;



    protected Inventory() {}




    public Inventory(CreateItemsCommand command) {
        this.productTitle = command.itemTitle();
        this.productDescription = command.itemDescription();
        this.productQuantity = command.itemQuantity();
        this.brand = command.brand();
         }

    public Inventory updateInformation(String title, String description, Integer quantity, String brand) {
        this.productTitle = title;
        this.productDescription = description;
        this.productQuantity = quantity;
        this.brand = brand;
        return this;
    }
}
