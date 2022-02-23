package com.Niss.Toolbox_Service.Mapper;

import com.Niss.Toolbox_Service.pojo.Code;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CodeMapper {
    // 根据userId查询代码
    @Select("select * from code where user_id = #{userId}")
    List<Code> findCode(int userId);

    // 插入一个代码
    @Insert("INSERT INTO code (id,user_name,file_data,file_type) value (#{id},#{userName},#{fileData},#{fileType})")
    int insertCode(Code code);
}
