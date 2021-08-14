package com.paymybuddy.moneytransfert.app.model;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import lombok.*;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.format.annotation.DateTimeFormat;

//@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name="account")
@FilterDef(name="accountFilter", parameters={
		@ParamDef( name="userMail", type="string" )
})
@Filters( {
		@Filter(name="accountFilter", condition=":userMail = client_mail")
} )
public class Account {
/*	@Autowired
	EntityManager entityManager;*/

	@Id
	@NotNull(message = "Last name cannot be null")
	@Column(name="account_id")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Email(message = "Email should be valid")
	@Size(max = 50)
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
	@Size(max = 50)
	Client client;

	@Transient
	String ClientMail;

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
				", client=" + client.getClientMail() +
				", creationDate=" + creationDate +
				", balance=" + balance +
				'}';
	}

	public Account(String accountId, Client client) {
		this.accountId = accountId;
		this.client = client;
/*		Query q = entityManager.createNativeQuery("SELECT a.account_id FROM account a WHERE a.client_mail = :client_mail");
		q.setParameter("id", client.getClientMail());
		//Object[] accountIds = (Object[]) q.getSingleResult();
		List<String> accountIds = (List<String>) q.getSingleResult();
		Long max = accountIds.stream().map( o -> o.split("-")[0])
          .mapToLong(Long::parseLong)
          .max()
          .orElse(0L);
		this.accountId = max + "-" + client;*/
	}

	public Account(String accountId, int balance, Client client) {
		this.accountId = accountId;
		this.client = client;
		this.balance = balance;
	}

}
