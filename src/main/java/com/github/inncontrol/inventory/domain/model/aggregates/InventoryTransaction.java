package com.github.inncontrol.inventory.domain.model.aggregates;


import com.github.inncontrol.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class InventoryTransaction extends AuditableAbstractAggregateRoot<Inventory> {

    private Inventory TransactionItems;

    public void Transactions(TransactionType Transaction, Integer Quantity){
        if (Transaction == TransactionType.INCOMING){
            this.TransactionItems.addItem(Quantity);
        }
        else if (Transaction == TransactionType.OUTGOING){
            this.TransactionItems.substractItem(Quantity);
        }
        else {
            throw new IllegalArgumentException("Invalid Transaction Type");
        }
    }

    protected enum TransactionType {
        INCOMING,
        OUTGOING
    }

    public InventoryTransaction() {}


}
