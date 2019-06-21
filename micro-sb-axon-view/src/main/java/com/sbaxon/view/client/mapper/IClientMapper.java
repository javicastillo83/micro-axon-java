package com.sbaxon.view.client.mapper;

import com.sbaxon.view.client.model.Client;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IClientMapper {

    @Select("SELECT c.uuid as uuid,c.name as name FROM client c")
    @Results(value = {
            @Result(property = "id", column = "uuid"),
            @Result(property = "name", column = "name")
    })
    List<Client> findAll();
}
