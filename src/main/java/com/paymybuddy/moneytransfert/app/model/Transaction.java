package com.paymybuddy.moneytransfert.app.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

//@ToString
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@DynamicUpdate
//@Table(name="transaction")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="transaction_type", discriminatorType = DiscriminatorType.STRING,length = 1)
public
//abstract
class Transaction {
	
	enum TransactionStatus {
		Default_Status,
		AUTHORISED,
		PROCESSING,
		REFUSED
	}


/*	@Column(name="transaction_type", insertable = false, updatable = false)
	protected int transactionType;*/

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="transaction_id")
	private int transactionId;
	
    //@NotNull(message = "Transaction date cannot be null")
	@Column(name="transaction_date")
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@CreationTimestamp
	// LocalDateTime
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
	//@Size(max = 50)
	private Account account;

	@Transient
	private int accountId;

/*	@Column(name="client_mail")
	@NotNull*/
	@Transient
	String clientMail;

	//@Positive(message = "Transaction must be positive")
	@Column(name="transaction_amount")
	private float transactionAmount;
    
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
	@Enumerated(EnumType.STRING)
	private TransactionStatus  transactionStatus;

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
			transactionStatus = TransactionStatus.Default_Status;
	}

	public Transaction(Account account, float transactionAmount, String sourceLabbel) {
		this.account = account;
		this.transactionAmount = transactionAmount;
		this.sourceLabbel = sourceLabbel;
	}

	@Override
	public String toString() {
		return "Transaction{" +
				"transactionId=" + transactionId +
				", transactionDate=" + transactionDate +
				///???
//				", account=" + account.getAccountId() +
				", accountId='" + accountId + '\'' +
				", clientMail='" + clientMail + '\'' +
				", transactionAmount=" + transactionAmount +
				", sourceLabbel='" + sourceLabbel + '\'' +
				", transactionStatus=" + transactionStatus +
				'}';
	}
}
