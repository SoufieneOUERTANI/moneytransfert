package com.paymybuddy.moneytransfert.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name="client")
public class Client {

    @Id
    @NotNull(message = "Last name cannot be null")
    @Column(name="client_mail")
    @Email(message = "Email should be valid")
    String clientMail;

    @NotNull(message = "First name cannot be null")
    @Column(name="first_name")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Column(name="last_name")
    private String lastName;

    @NotNull(message = "Birthday cannot be null")
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    @Past(message = "Date de naissance doit être dans le passé")
    @Column
    private Date birthday;

    @Column
    private String adress;

    // To avoid infinite loop de @OneToMany => @ManyToOne => @OneToMany => ...
    //@Transient .. But KO .. Because avoiding the cascade to be executed
    @OneToMany(
            fetch=FetchType.LAZY,
            mappedBy = "client",
            cascade = CascadeType.ALL,
			orphanRemoval = true
    )
    List<Account> accounts;

    // Constructeur sans les accomptes
    public Client(String clientMail, String lastName, String firstName, Date birthday, String adress) {
        this.clientMail = clientMail;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.adress = adress;
    }

    // override without the "List<Account> accounts" .. Else infinite loop
    @Override
    public String toString() {
        return "Client{" +
                "clientMail='" + clientMail + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthday=" + birthday +
                ", adress='" + adress + '\'' +
                '}';
    }
}
