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
	//@NotNull(message = "Last name cannot be null")
	@Column(name="account_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Email(message = "Email should be valid")
	//@Size(max = 50)
	private int accountId;

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
	//@Size(max = 50)
	Client client;

	// To read the clientMail in the object(from the client field),
	// to be able to pass it for the page like a model, with the right ClientMail
	@Transient
	String clientMail;

	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@CreationTimestamp
	@Column(name="creation_date")
	Date creationDate;
    
    @Column(columnDefinition = "float default 0",scale = 2)
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


/*	// override without the "List<Transaction> transactions" .. Else infinite loop
	@Override
	public String toString() {
		return "Account{" +
				"accountId='" + accountId + '\'' +
				", client=" + client.getClientMail() +
				", creationDate=" + creationDate +
				", balance=" + balance +
				'}';
	}*/

	public Account(Client client) {
		this.client = client;
	}

/*	public Account(int accountId, Client client) {
		this(client);
		this.accountId = accountId;
	}*/

/*	public Account(int accountId, Client client, int balance ) {
		this(accountId, client);
		this.balance = balance;
	}*/

}
