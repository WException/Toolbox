package com.Niss.Toolbox_Service.Mapper;

import com.Niss.Toolbox_Service.pojo.Suggestions;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SuggestionsMapper {
    @Insert("insert into suggestions (name,email,num,descc) values (#{name},#{email},#{num},#{descc})")
    int insertSuggestions(Suggestions suggestions);
}
