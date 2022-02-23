package com.Niss.Toolbox_Service.controller;

import com.Niss.Toolbox_Service.Service.ImgService;
import com.Niss.Toolbox_Service.pojo.Image;
import com.Niss.Toolbox_Service.pojo.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin // 跨域注解
@RestController
public class ImgController {
    @Resource
    ImgService imgService;

    @RequestMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam(value = "name", required = false) String name) {
        return imgService.handleFileUpload(file, name);
    }

    @RequestMapping("/finduserimg")
    @ResponseBody
    public List<Image> findUserImg(@RequestBody User user) {
        System.out.println(user);
        System.out.println(imgService.findUserImg(user.getName()));
        return imgService.findUserImg(user.getName());
    }

    @RequestMapping("/yvlanimg")
    @ResponseBody
    public String yvlanImg(@RequestParam(value = "imgname") String imgName, @RequestParam(value = "username") String userName) {
        return imgService.yvlanImg(imgName, userName);
    }

    @RequestMapping("/deleteimg")
    @ResponseBody
    public String deleteImg(@RequestParam(value = "imgname") String imgName, @RequestParam(value = "username") String userName) {
        return imgService.deleteImg(imgName, userName);
    }

    @RequestMapping("/selectimgfavorites")
    @ResponseBody
    public String selectImgFavorites(@RequestParam(value = "imgname") String imgName, @RequestParam(value = "username") String userName) {
        return imgService.selectImgFavorites(userName, imgName);
    }

    @RequestMapping("/insertimgfavorites")
    @ResponseBody
    public int insertImgFavorites(@RequestParam(value = "imgname") String imgName, @RequestParam(value = "username") String userName, @RequestParam(value = "favoritesname") String favoritesName) {
        return imgService.insertImgFavorites(userName, imgName, favoritesName);
    }

    @RequestMapping("/finduserimgbyfavoritesId")
    @ResponseBody
    public List<Image> findUserImgByFavoritesId(@RequestParam(value = "username") String userName, @RequestParam(value = "favoritesname") String favoritesName) {
        return imgService.findUserImgByFavoritesId(userName, favoritesName);
    }

    @RequestMapping("/imgdelectfavorites")
    @ResponseBody
    public int delectFavorites(@RequestParam(value = "username") String userName, @RequestParam(value = "favoritesname") String favoritesName) {
        return imgService.delectFavorites(userName, favoritesName);
    }
}
