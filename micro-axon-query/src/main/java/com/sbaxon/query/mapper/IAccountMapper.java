package com.sbaxon.query.mapper;

import com.sbaxon.query.model.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IAccountMapper {



    @Select("SELECT a.uuid as account_uuid, a.number as account_number, a.status as account_status, a.balance as account_balance, c.first_name as client_firstName FROM account a, client c WHERE a.client_id = c.id")
    @Results(value = {
            @Result(property = "id", column = "account_uuid"),
            @Result(property = "number", column = "account_number"),
            @Result(property = "status", column = "account_status"),
            @Result(property = "balance", column = "account_balance"),
            @Result(property = "client.firstName", column = "client_firstName")
    })
    List<Account> findAll();
}
