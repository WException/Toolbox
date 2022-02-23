package com.Niss.Toolbox_Service.Mapper;

import com.Niss.Toolbox_Service.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    // 根据用户名查询用户
    @Select("select * from user where name = #{name}")
    User findByName(String name);

    // 查询所有用户
    @Select("select * from user")
    List<User> findUser();

    // 插入一个用户
    @Insert("insert into user (id,name,password) value (#{id},#{name},#{password})")
    void insertUser(User user);

    // 修改用户头像地址
    @Update(" UPDATE user SET touxiang = #{touxiang} WHERE name = #{name}")
    void updataUserTouxiang(String name, String touxiang);

    // 修改用户信息
    @Update(" UPDATE user SET sex = #{sex},sex = #{sex},age = #{age},jieshao = #{jieshao},tag = #{tag} WHERE name = #{name}")
    int updataUser(String name, String sex, int age, String jieshao, String tag);
}
