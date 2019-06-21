package com.sbaxon.view.client.model;

import com.sbaxon.view.account.model.Account;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Client {

    private String id;

    private String name;

    private List<Account> accounts = new ArrayList<>();

}
