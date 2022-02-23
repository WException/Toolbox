package com.Niss.Toolbox_Service.Mapper;

import com.Niss.Toolbox_Service.pojo.Image;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ImageMapper {
    // 根据userId查询图片
    @Select("select * from image where user_id = #{userId}")
    List<Image> findImage(int userId);

    // 插入一个图片
    @Insert("insert into image (id,user_id,size,path,type,time,name) values (#{id},#{userId},#{size},#{path},#{type},#{time},#{name})")
    void insertImage(Image image);

    // 根据图片的名称和用户的名称查询图片
    @Select("SELECT * FROM image WHERE name = #{name} AND user_id = #{userId}")
    Image findImageByName(String name, int userId);

    // 根据图片的名称和用户的名称删除图片
    @Delete("DELETE FROM image WHERE name = #{name} AND user_id = #{userId}")
    int deleteImageByName(String name, int userId);

    // 修改favoritesId
    @Update("UPDATE image SET favoritesId = #{favoritesId} WHERE name = #{name} AND user_id = #{userId}")
    int updateImageFavoritesId(int userId, String name, int favoritesId);

    // 根据favoritesId查询图片
    @Select("select * from image where favoritesId = #{favoritesId}")
    List<Image> findImageByFavoritesId(int favoritesId);

    // 修改favoritesId
    @Update("UPDATE image SET favoritesId = #{temp} WHERE favoritesId = #{favoritesId}")
    int updateFavoritesIdByFavoritesId(int favoritesId, int temp);
}
