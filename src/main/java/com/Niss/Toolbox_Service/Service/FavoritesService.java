package com.Niss.Toolbox_Service.Service;

import com.Niss.Toolbox_Service.Mapper.FavoritesMapper;
import com.Niss.Toolbox_Service.pojo.Favorites;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

@Service
public class FavoritesService {
    @Resource
    private FavoritesMapper favoritesMapper;

    /**
     * 用户新增一个收藏夹
     *
     * @param favorites 要创建的favorites
     * @return 状态信息
     */
    public String insertFavorites(Favorites favorites) {
        // 1、为Favorites实体类并赋值
        Date sDate = new Date(System.currentTimeMillis());
        favorites.setTime(sDate);
        // 2、调用FavoritesMapper将Favorites存入数据库
        int res = favoritesMapper.insertFavorites(favorites);
        if (res == 1) {
            return "创建成功";
        } else {
            return "创建失败" + res;
        }
    }

    /**
     * 列出用户的所有收藏夹
     *
     * @param userName 用户名name
     * @return 该用户的所有收藏夹
     */
    public List<Favorites> selectFavorites(String userName) {
        return favoritesMapper.findFavorites(userName);
    }

    /**
     * 删除收藏夹
     *
     * @param favorites 要删除的收藏夹
     * @return 状态信息
     */
    public String deleteFavorites(Favorites favorites) {
        int res = favoritesMapper.deleteFavorites(favorites);
        if (res == 1) {
            return "删除成功";
        } else {
            return "删除失败" + res;
        }
    }

}
