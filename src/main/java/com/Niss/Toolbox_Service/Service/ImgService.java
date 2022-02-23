package com.Niss.Toolbox_Service.Service;

import com.Niss.Toolbox_Service.Mapper.FavoritesMapper;
import com.Niss.Toolbox_Service.Mapper.ImageMapper;
import com.Niss.Toolbox_Service.Mapper.UserMapper;
import com.Niss.Toolbox_Service.pojo.Favorites;
import com.Niss.Toolbox_Service.pojo.Image;
import com.Niss.Toolbox_Service.pojo.User;
import com.Niss.Toolbox_Service.utils.StaticUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.sql.Date;
import java.util.List;

@Service
public class ImgService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private ImageMapper imageMapper;
    @Resource
    private FavoritesMapper favoritesMapper;

    /*
     * 用户上传图片
     * */
    public String handleFileUpload(MultipartFile file, String name) {
        if (!file.isEmpty()) {
            try {
                //如果文件夹不存在,创建文件夹
                File fileBox = new File(StaticUtils.IMGPATH + name);
                if (!fileBox.isDirectory()) {
                    fileBox.mkdir();
                }
                // 文件在本地完整路径
                String path = StaticUtils.IMGPATH + name + "/" + file.getOriginalFilename();
                // 判断文件是否已经存在
                File fileTemp = new File(path);
                if (fileTemp.exists()) {
                    return "文件已经存在";
                }

                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(path));

                // 将图片相关信息存入数据库
                Image image = new Image();
                image.setPath(name + "/" + file.getOriginalFilename());
                image.setSize((int) file.getSize());
                image.setType(file.getContentType());
                image.setUserId(userMapper.findByName(name).getId());
                image.setName(file.getOriginalFilename());

                Date sDate = new Date(System.currentTimeMillis());
                image.setTime(sDate);

                imageMapper.insertImage(image);

                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }
            String req = StaticUtils.RETURNIMGPATH + name + "/" + file.getOriginalFilename();
            return req;
        } else {
            return "上传失败，因为文件是空的.";
        }
    }

    /*
     * 用户查看自己的图床
     * */
    public List<Image> findUserImg(String name) {
        // 返回一个json，数组 item为{图片名称，图片大小，图片链接...}
        int userid = userMapper.findByName(name).getId();
        List<Image> images = imageMapper.findImage(userid);
        return images;
    }

    /**
     * 预览图片时根据图片名返回图片链接url
     *
     * @param imgName  图片的名字
     * @param userName 用户的名字
     * @return 该图片的url链接
     */
    public String yvlanImg(String imgName, String userName) {
        int userId = userMapper.findByName(userName).getId();
        Image imageByName = imageMapper.findImageByName(imgName, userId);
        String url = StaticUtils.RETURNIMGPATH + imageByName.getPath();
        return url;
    }

    /**
     * 根据图片名和用户名删除图片
     *
     * @param imgName  图片的名字
     * @param userName 用户的名字
     * @return 删除是否成功的提示信息
     */
    public String deleteImg(String imgName, String userName) {
        //删除文件夹中的文件
        File file = new File(StaticUtils.IMGPATH + userName + "/" + imgName);
        file.delete();
        // 删除数据库中的文件
        int userId = userMapper.findByName(userName).getId();
        int i = imageMapper.deleteImageByName(imgName, userId);
        if (i == 1) {
            return "删除成功";
        } else {
            return "删除失败:" + i;
        }
    }

    /**
     * 根据图片名和用户名返回该图片所属的收藏夹名称
     *
     * @param userName 用户名
     * @param imgName  图片名
     * @return 若存在则返回收藏夹名称，若不存在返回"该图片暂不属于任何收藏夹"
     */
    public String selectImgFavorites(String userName, String imgName) {
        User user = userMapper.findByName(userName);
        Image image = imageMapper.findImageByName(imgName, user.getId());
        if (image.getFavoritesId() != 0 && image.getFavoritesId() != -1) {
            Favorites favorites = favoritesMapper.findFavoritesById(image.getFavoritesId());
            return favorites.getFavoritesName();
        } else {
            return "该图片暂不属于任何收藏夹";
        }
    }

    /**
     * 根据用户名，图片名，收藏夹名修改相关图片的favoritesId字段
     *
     * @param userName      用户名
     * @param imgName       图片名
     * @param favoritesName 收藏夹名
     * @return 状态码
     */
    public int insertImgFavorites(String userName, String imgName, String favoritesName) {
        int userId = userMapper.findByName(userName).getId();
        Favorites favorites = favoritesMapper.findFavoritesByUserNameAndFavoritesName(userName, favoritesName);
        int i = imageMapper.updateImageFavoritesId(userId, imgName, favorites.getId());
        return i;
    }

    /**
     * 根据用户名和文件夹名查询该文件夹下的图片。
     *
     * @param userName      用户名
     * @param favoritesName 文件夹名
     * @return 图片列表
     */
    public List<Image> findUserImgByFavoritesId(String userName, String favoritesName) {
        Favorites favorites = favoritesMapper.findFavoritesByUserNameAndFavoritesName(userName, favoritesName);
        return imageMapper.findImageByFavoritesId(favorites.getId());
    }

    /**
     * 用户删除标签时的操作，删除标签后把所有归类为此标签的图片favoritesId设为0
     *
     * @param userName      用户名
     * @param favoritesName 文件夹名
     * @return 状态码
     */
    public int delectFavorites(String userName, String favoritesName) {
        Favorites favorites = favoritesMapper.findFavoritesByUserNameAndFavoritesName(userName, favoritesName);
        int i = imageMapper.updateFavoritesIdByFavoritesId(favorites.getId(), -1);
        return i;
    }
}
