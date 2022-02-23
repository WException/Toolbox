package com.Niss.Toolbox_Service.Mapper;

import com.Niss.Toolbox_Service.pojo.Favorites;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FavoritesMapper {
    // 根据userName查询Favorites
    @Select("SELECT * FROM favorites WHERE userName = #{userName}")
    List<Favorites> findFavorites(String userName);

    // 根据id查询Favorites
    @Select("SELECT * FROM favorites WHERE id = #{id}")
    Favorites findFavoritesById(int id);

    // 根据userName和favoritesName查询Favorites
    @Select("SELECT * FROM favorites WHERE userName = #{userName} AND favoritesName = #{favoritesName}")
    Favorites findFavoritesByUserNameAndFavoritesName(String userName, String favoritesName);

    // 插入一个Favorites
    @Insert("INSERT INTO favorites (id,userName,time,favoritesName) VALUE (#{id},#{userName},#{time},#{favoritesName})")
    int insertFavorites(Favorites favorites);

    // 删除一个Favorites
    @Delete("DELETE FROM favorites WHERE userName = #{userName} AND favoritesName = #{favoritesName}")
    int deleteFavorites(Favorites favorites);
}
