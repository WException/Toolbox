package com.Niss.Toolbox_Service.controller;

import com.Niss.Toolbox_Service.Service.SuggestionsService;
import com.Niss.Toolbox_Service.pojo.Suggestions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin // 跨域注解
@RestController
public class SuggestionsController {
    @Resource
    private SuggestionsService suggestionsService;

    @RequestMapping("/sumbitsuggestions")
    @ResponseBody
    public int sumbitSuggestions(@RequestBody Suggestions suggestions) {
        System.out.println(suggestions);
        return suggestionsService.sumbitSuggestions(suggestions);
    }
}
