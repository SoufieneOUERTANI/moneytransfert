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
	
    //@NotNull(message = "Transaction date cannot be null")
	@Column(name="transaction_date")
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@CreationTimestamp
	private Date transactionDate;

	@ManyToOne(
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE,
					CascadeType.DETACH,
					CascadeType.REFRESH
					}
			)
	@JoinColumn(name="transaction_account_email_id")
	@NotNull
	private Account accountEmailId;

	@NotNull(message = "Transaction ammount cannot be null")
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
	@ColumnDefault("validated_")
	//private TransactionStatus  transactionStatus;
	private String  transactionStatus;


    //@NotNull(message = "Transaction type cannot be null")
	//private TransactionType transactionType;
	@Column(name="transaction_type")
	private String transactionType;

	@PrePersist
	public void prePersist() {
		if(transactionType == null) //We set default value in case if the value is not set yet.
			transactionType = "Default_Type";

		if(transactionStatus == null) //We set default value in case if the value is not set yet.
			transactionStatus = "Default_Status";
	}

}
