package com.sbaxon.query.model;

import lombok.Data;

@Data
public class Account {

    private String id;

    private String number;

    private String status;

    private double balance;

    private Client client;

}
