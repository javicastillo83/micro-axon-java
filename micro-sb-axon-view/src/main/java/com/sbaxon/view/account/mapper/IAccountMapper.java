package com.sbaxon.view.account.mapper;

import com.sbaxon.view.account.model.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IAccountMapper {

    @Select("SELECT a.uuid as uuid, a.name as account_name, a.balance as account_balance, c.name as client_name FROM account a LEFT JOIN client c ON a.client_id = c.id")
    @Results(value = {
            @Result(property = "id", column = "uuid"),
            @Result(property = "name", column = "account_name"),
            @Result(property = "balance", column = "account_balance"),
            @Result(property = "client.name", column = "client_name")
    })
    List<Account> findAll();
}
