package com.Niss.Toolbox_Service.controller;

import com.Niss.Toolbox_Service.Service.FavoritesService;
import com.Niss.Toolbox_Service.pojo.Favorites;
import com.Niss.Toolbox_Service.pojo.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin //跨域注解
@RestController
public class FavoritesController {
    @Resource
    private FavoritesService favoritesService;

    @RequestMapping("/insertfavorites")
    @ResponseBody
    public String insertFavorites(@RequestBody Favorites favorites) {
        return favoritesService.insertFavorites(favorites);
    }

    @RequestMapping("/selectfavorites")
    @ResponseBody
    public List<Favorites> selectFavorites(@RequestBody User user) {
        return favoritesService.selectFavorites(user.getName());
    }

    @RequestMapping("/deletefavorites")
    @ResponseBody
    public String deleteFavorites(@RequestBody Favorites favorites) {
        return favoritesService.deleteFavorites(favorites);
    }
}
