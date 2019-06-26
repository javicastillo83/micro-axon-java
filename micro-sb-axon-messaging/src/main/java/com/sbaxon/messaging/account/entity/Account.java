package com.sbaxon.messaging.account.entity;

import com.sbaxon.messaging.client.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String uuid;

    private String number;

    private double balance;

    private AccountStatus status;


    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    public Account(String uuid, String number) {
        this.uuid = uuid;
        this.number = number;
    }


}
