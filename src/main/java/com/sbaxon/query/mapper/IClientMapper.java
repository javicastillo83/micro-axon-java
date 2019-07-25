package com.sbaxon.query.mapper;

import com.sbaxon.query.model.Account;
import com.sbaxon.query.model.Client;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IClientMapper {

    @Select("SELECT c.id as id, c.uuid as client_uuid, c.first_name as client_firstName, c.last_name as client_lastName, c.email as client_email FROM client c")
    @Results(value = {
            @Result(property = "id", column = "client_uuid"),
            @Result(property = "firstName", column = "client_firstName"),
            @Result(property = "lastName", column = "client_lastName"),
            @Result(property = "email", column = "client_email"),
            @Result(property = "accounts", column = "id", javaType = List.class, many = @Many(select = "accounts"))

    })
    List<Client> findAll();

    @Select("SELECT a.uuid as account_uuid, a.number as account_number, a.status as account_status, a.balance as account_balance FROM account a WHERE client_id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "account_uuid"),
            @Result(property = "number", column = "account_number"),
            @Result(property = "status", column = "account_status"),
            @Result(property = "balance", column = "account_balance")
    })
    List<Account> accounts(Long clientId);

    @Select("SELECT c.id as id, c.uuid as client_uuid, c.first_name as client_firstName, c.last_name as client_lastName, c.email as client_email FROM client c where c.uuid = #{clientUUID}")
    Client findByUUID(String clientUUID);
}
