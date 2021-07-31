package com.paymybuddy.moneytransfert.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@ToString
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@DynamicUpdate
//@Table(name="transaction")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="transaction_type", discriminatorType = DiscriminatorType.STRING,length = 1)
public abstract class Transaction {
	
	enum TransactionStatus {
		AUTHORISED,
		PROCESSING,
		REFUSED
	}
	
	enum TransactionType {
		REMITTANCE,
		INTERNAL_TRANSFER,
		EXTERNAL_TRANSFER,
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="transaction_id")
	private int transactionId;
	
    //@NotNull(message = "Transaction date cannot be null")
	@Column(name="transaction_date")
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@CreationTimestamp
	private Date transactionDate;

	@ManyToOne
//			(
//			cascade = {
//					CascadeType.PERSIST,
//					CascadeType.MERGE,
//					CascadeType.DETACH,
//					CascadeType.REFRESH
//					}
//			)
	@JoinColumn(name="account_id")
	@NotNull
	private Account account;

	@NotNull(message = "Transaction amount cannot be null")
    //@Positive(message = "Transaction must be positive")
	@Column(name="transaction_amount")
	private int transactionAmount;
    
//    @NotNull
//	@Column(name="previous_balance")
//	private int previousBalance;
//
//    @NotNull
//	@Column(name="new_balance")
//	private int newBalance;
	
	@Column(name="source_labbel")
	private String sourceLabbel;

//	@Column(name="destination_labbel")
//	private String destinationLabbel;

//	@ManyToOne(
//			cascade = { 
//					CascadeType.PERSIST, 
//					CascadeType.MERGE 
//					}
//			)
//	@JoinColumn(name="account_id")
//	//@Column(name="source_account")
//	private Account sourceAccount;
//	
//	@ManyToOne(
//			cascade = { 
//					CascadeType.PERSIST, 
//					CascadeType.MERGE 
//					}
//			)
//	@JoinColumn(name="account_id")
//	//@Column(name="destination_account")
//	private Account destinationAccount;

    //@NotNull(message = "Transaction status cannot be null")
	@Column(name="transaction_status")
	//private TransactionStatus  transactionStatus;
	private String  transactionStatus;


    //@NotNull(message = "Transaction type cannot be null")
	//private TransactionType transactionType;

	// On l'a remplacé par  @@DiscriminatorColumn
//	@Column(name="transaction_type")
//	private String transactionType;

	@PrePersist
	public void prePersist() {
//		if(transactionType == null) //We set default value in case if the value is not set yet.
//			transactionType = "Default_Type";

		if(transactionStatus == null) //We set default value in case if the value is not set yet.
			transactionStatus = "Default_Status";
	}

	public Transaction(Account account, int transactionAmount, String sourceLabbel) {
		this.account = account;
		this.transactionAmount = transactionAmount;
		this.sourceLabbel = sourceLabbel;
	}
}
