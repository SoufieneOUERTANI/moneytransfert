package com.paymybuddy.moneytransfert.app.model;


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
@DiscriminatorValue("+")
public class Versement extends Transaction{
    public Versement(Account account, float transactionAmount, String sourceLabbel) {
        super(account, transactionAmount, sourceLabbel);
    }
}
