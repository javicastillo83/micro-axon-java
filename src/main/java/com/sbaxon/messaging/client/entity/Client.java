package com.sbaxon.messaging.client.entity;

import com.sbaxon.messaging.account.entity.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Client {

    public Client(String uuid, String firstName, String lastName, String email) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String uuid;

    private String firstName;

    private String lastName;

    private String email;

    @OneToMany(mappedBy = "client")
    private List<Account> accounts;

}
