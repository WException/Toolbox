package com.Niss.Toolbox_Service.controller;

import com.Niss.Toolbox_Service.Service.CodeService;
import com.Niss.Toolbox_Service.pojo.Code;
import com.Niss.Toolbox_Service.utils.StaticUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.io.IOException;

@CrossOrigin // 跨域注解
@RestController
public class CodeController {

    @Resource
    private CodeService codeService;

    @RequestMapping(value = "/code", method = RequestMethod.POST)
    @ResponseBody
    public String show2(@RequestBody Code code) {
        String req = codeService.insertCode(code.getUserName(), code.getFileData(), code.getFileType());
        return req;
    }
}
