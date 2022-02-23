package com.Niss.Toolbox_Service.controller;

import com.Niss.Toolbox_Service.Service.UserService;
import com.Niss.Toolbox_Service.pojo.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@CrossOrigin // 跨域注解
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/reg")
    @ResponseBody
    public int reg(@RequestBody User user) {
        return userService.reg(user);
    }

    @PostMapping("/login")
    @ResponseBody
    public Map login(@RequestBody User user) {
        return userService.login(user);
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> User() {
        return userService.selectUsers();
    }

    @RequestMapping("/headimg")
    @ResponseBody
    public String headimg(@RequestParam("file") MultipartFile file, @RequestParam(value = "name", required = false) String name) {
        System.out.println(name + "访问头像");
        return userService.headimg(file, name);
    }

    @RequestMapping("/updatauser")
    @ResponseBody
    public Map updataUser(@RequestBody User user) {
        System.out.println(user);
        return userService.updataUser(user);
    }

}
