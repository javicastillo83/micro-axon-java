package com.sbaxon.messaging.client.entity;

import com.sbaxon.messaging.account.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String uuid;

    private String name;

    @OneToMany(mappedBy = "client")
    private List<Account> accounts;

    public Client(String name) {
        this.name = name;
    }

    public Client(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }
}
