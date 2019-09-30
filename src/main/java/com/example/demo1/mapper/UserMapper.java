package com.example.demo1.mapper;

import com.example.demo1.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {

    @Insert("insert into users (name, account_id, token, gmt_create, gmt_modified, avatar_url) values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl})")
    void insert(User user);

    @Select("select * from users where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from users where id = #{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from users where account_id = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update users set name = #{name}, token = #{token}, gmt_modified = #{gmtModified}, avatar_url = #{avatarUrl} where id = #{id}")
    void update(User dbUser);
}
