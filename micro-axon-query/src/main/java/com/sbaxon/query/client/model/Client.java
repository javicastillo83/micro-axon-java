package com.sbaxon.query.client.model;

import com.sbaxon.query.account.model.Account;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Client {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private List<Account> accounts = new ArrayList<>();

}
