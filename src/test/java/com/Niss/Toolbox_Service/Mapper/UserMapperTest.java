package com.Niss.Toolbox_Service.Mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void test1() {
        System.out.println(userMapper.findUser());
    }
}
