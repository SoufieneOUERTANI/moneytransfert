package com.paymybuddy.moneytransfert.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@DynamicUpdate
//@Table(name="transaction")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("-")
public class Virement extends Transaction{
    public Virement(Account account, int transactionAmount, String sourceLabbel) {
        super(account, transactionAmount, sourceLabbel);
    }
}
