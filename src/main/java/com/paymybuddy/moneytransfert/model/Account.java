package com.paymybuddy.moneytransfert.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;


import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name="account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="account_id")
	private int accountId;
	
    @NotNull(message = "Last name cannot be null")
	@Column(name="last_name")
	private String lastName;
	
    @NotNull(message = "First name cannot be null")
	@Column(name="first_name")
	private String firstName;
    
    @NotNull(message = "First name cannot be null")
    @Column
	private Date birthday;

	@Column
	@Past(message = "Date de naissance doit être dans le passé")
    private String adress;
    
    @Column(columnDefinition = "float default 0")
    float balance;
	
//	@OneToMany(
//			mappedBy = "transactionAccountId",
//			cascade = CascadeType.ALL,
//			orphanRemoval = true
//			)
//	List<Transaction> transactions;
	
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
	
}
