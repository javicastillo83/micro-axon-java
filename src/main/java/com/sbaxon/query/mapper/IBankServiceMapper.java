package com.sbaxon.query.mapper;

import com.sbaxon.query.model.BankService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IBankServiceMapper {

    @Select("SELECT b.id as id, b.uuid as bankservice_uuid, b.name as bankservice_name, b.type as bankservice_type FROM bank_service b")
    @Results(value = {
            @Result(property = "id", column = "bankservice_uuid"),
            @Result(property = "name", column = "bankservice_name"),
            @Result(property = "type", column = "bankservice_type")

    })
    List<BankService> findAll();

}
