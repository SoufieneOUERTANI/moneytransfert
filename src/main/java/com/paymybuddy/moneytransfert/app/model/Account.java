package com.paymybuddy.moneytransfert.app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

//@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name="account")
public class Account {

	@Id
	@NotNull(message = "Last name cannot be null")
	@Column(name="account_id")
	//@Email(message = "Email should be valid")
	private String accountId;

	@ManyToOne
//			(
//			cascade = {
//					CascadeType.PERSIST,
//					CascadeType.MERGE,
//					CascadeType.DETACH,
//					CascadeType.REFRESH
//			}
//	)
	@JoinColumn(name="client_mail")
	@NotNull
	Client client;

	@Transient
	String clientMail;

	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@CreationTimestamp
	@Column(name="creation_date")
	Date creationDate;
    
    @Column(columnDefinition = "float default 0")
    float balance;

	// To avoid infinite loop de @OneToMany => @ManyToOne => @OneToMany => ...
	//@Transient .. But KO .. Because avoiding the cascade to be executed
	@OneToMany(
			fetch=FetchType.LAZY,
			mappedBy = "account",
			cascade = CascadeType.ALL
			/*,
			orphanRemoval = true*/
	)
	List<Transaction> transactions;




//	@OneToMany(
//			mappedBy = "sourceAccount", 
//			cascade = CascadeType.ALL,
//			orphanRemoval = true
//			)
//	List<Transaction> sourceTransactions;
//	
//	@OneToMany(
//			mappedBy = "destinationAccount", 
//			cascade = CascadeType.ALL,
//			orphanRemoval = true
//			)

//	List<Transaction> destinationTransactions;


	// override without the "List<Transaction> transactions" .. Else infinite loop
	@Override
	public String toString() {
		return "Account{" +
				"accountId='" + accountId + '\'' +
				", client=" + client +
				", creationDate=" + creationDate +
				", balance=" + balance +
				'}';
	}

	public Account(String accountId, Client client) {
		this.accountId = accountId;
		this.client = client;
	}

	public Account(String accountId, int balance, Client client) {
		this.accountId = accountId;
		this.client = client;
		this.balance = balance;
	}

}
