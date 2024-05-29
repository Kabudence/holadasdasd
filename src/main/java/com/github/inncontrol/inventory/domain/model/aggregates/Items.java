package com.github.inncontrol.inventory.domain.model.aggregates;


import com.github.inncontrol.inventory.domain.model.commands.CreateItemsCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

@Getter
@Entity
public class Items extends AbstractAggregateRoot<Items> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    private String itemTitle;

    @Column(nullable = false)
    @Getter
    private String itemDescription;

    @Column(nullable = false)
    @Getter
    private Integer itemQuantity;



    protected Items() {}

    public Items(CreateItemsCommand command){
        this.itemDescription = command.itemDescription();
        this.itemTitle = command.itemTitle();
        this.itemQuantity = command.itemQuantity();
    }

}
