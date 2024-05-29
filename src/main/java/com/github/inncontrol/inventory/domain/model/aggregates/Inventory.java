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
    private String Brand;



    protected Inventory() {}

    public Inventory(CreateItemsCommand command){
        this.productTitle = command.itemDescription();
        this.productDescription = command.itemTitle();
        this.productQuantity = command.itemQuantity();
        this.Brand = command.Brand();
    }

    public void addItem(Integer quantity) {
        this.productQuantity += quantity;
    }

    public void substractItem(Integer quantity) {
        this.productQuantity -= quantity;
    }

}
