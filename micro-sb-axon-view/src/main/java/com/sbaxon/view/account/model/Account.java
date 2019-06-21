package com.sbaxon.view.account.model;

import com.sbaxon.view.client.model.Client;
import lombok.Data;

@Data
public class Account {

    private String id;

    private String name;

    private double balance;

    private Client client;
}
