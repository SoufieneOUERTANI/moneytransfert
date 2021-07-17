package com.paymybuddy.moneytransfert.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name="transaction")
public class Transaction {
	
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
	
    @NotNull(message = "Transaction date cannot be null")
	@Column(name="transaction_date")
	private Date transactionDate;
	
	@ManyToOne(
			cascade = { 
					CascadeType.PERSIST, 
					CascadeType.MERGE 
					}
			)
	@JoinColumn(name="account_id")
	//@Column(name="transaction_account_id")
	private Account transactionAccountId;
	
    @NotNull(message = "Transaction ammount cannot be null")
    @Positive(message = "Transaction must be positive")
	@Column(name="transaction_amount")
	private int transactionAmount;
    
    @NotNull
	@Column(name="previous_balance")
	private int previousBalance;
	
    @NotNull
	@Column(name="new_balance")
	private int newBalance;
	
	@Column(name="source_labbel")
	private String sourceLabbel;
	
	@Column(name="destination_labbel")
	private String destinationLabbel;

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

    @NotNull(message = "Transaction status cannot be null")
	@Column(name="transaction_status")
	//private TransactionStatus  transactionStatus;
	private String  transactionStatus;


    @NotNull(message = "Transaction type cannot be null")
	@Column(name="transaction_type")
	//private TransactionType transactionType;
	private String transactionType;

	
}
