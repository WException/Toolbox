package com.Niss.Toolbox_Service.Service;

import com.Niss.Toolbox_Service.Mapper.SuggestionsMapper;
import com.Niss.Toolbox_Service.pojo.Suggestions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SuggestionsService {
    @Resource
    private SuggestionsMapper suggestionsMapper;

    public int sumbitSuggestions(Suggestions suggestions) {
        return suggestionsMapper.insertSuggestions(suggestions);
    }
}
