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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;


import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name="account")
public class Account {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "Last name cannot be null")
	@Column(name="account_email_id")
	@Email(message = "Email should be valid")
	private String accountEmailId;
	
    @NotNull(message = "Last name cannot be null")
	@Column(name="last_name")
	private String lastName;
	
    @NotNull(message = "First name cannot be null")
	@Column(name="first_name")
	private String firstName;
    
    @NotNull(message = "Birthday cannot be null")
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@Past(message = "Date de naissance doit être dans le passé")
	@Column
	private Date birthday;

	@Column
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


	public String getAccountEmailId() {
		return accountEmailId;
	}

	public void setAccountEmailId(String accountEmailId) {
		this.accountEmailId = accountEmailId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
}
