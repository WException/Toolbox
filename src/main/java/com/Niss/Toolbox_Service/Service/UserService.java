package com.Niss.Toolbox_Service.Service;

import com.Niss.Toolbox_Service.Mapper.ImageMapper;
import com.Niss.Toolbox_Service.Mapper.UserMapper;
import com.Niss.Toolbox_Service.pojo.User;
import com.Niss.Toolbox_Service.utils.StaticUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private ImageMapper imageMapper;

    /*
     * 用户注册
     * */
    public int reg(User user) {
        // 1.查询所有用户
        boolean flag = false;// 表示数据库中没有该用户，可以注册
        List<User> users = userMapper.findUser();
        for (User item : users) {
            if (item.getName().equals(user.getName())) {
                flag = true;
            }
        }
        // 2.遍历判断该用户名是否已经存在
        if (!flag) {
            userMapper.insertUser(user);
            System.out.println("有一个新注册成功！" + new Date());
            return 1;
        } else {
            System.out.println("用户注册失败");
            return 0;
        }
    }

    /*
     * 用户登录
     * */
    public Map login(User user) {
        User userByName = userMapper.findByName(user.getName());
        Map usermap = new HashMap();
        if (userByName == null) {
            System.out.println("用户不存在");
            usermap.put("user", "用户不存在");
            return usermap;
        } else {
            if (user.getPassword().equals(userByName.getPassword())) {
                System.out.println(user.getName() + "用户登录成功");
                usermap.put("user", userByName);
                return usermap;
            } else {
                System.out.println(user.getName() + "用户密码错误");
                usermap.put("user", "密码错误");
                return usermap;
            }
        }
    }

    /*
     * 查询所有用户
     * */
    public List<User> selectUsers() {
        return userMapper.findUser();
    }

    /*
     * 上传头像
     * */
    public String headimg(MultipartFile file, String name) {
        if (!file.isEmpty()) {
            try {
                // 创建用户专属的文件夹
                File fileBox = new File(StaticUtils.IMGPATH + name);
                if (!fileBox.isDirectory()) {
                    fileBox.mkdir();
                }
                // 创建用户专属的文件夹里的头像文件夹
                File fileBox2 = new File(StaticUtils.IMGPATH + name + "/headimg");
                if (!fileBox2.isDirectory()) {
                    fileBox2.mkdir();
                }
                // 删除头像文件夹里的文件
                String[] list = fileBox2.list();
                for (String i : list) {
                    new File(StaticUtils.IMGPATH + name + "/headimg/" + i).delete();
                }
                // 保存新的头像到文件夹
                String path = StaticUtils.IMGPATH + name + "/headimg/" + file.getOriginalFilename();
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(path));
                out.write(file.getBytes());
                // 把新头像的地址存到数据库
                userMapper.updataUserTouxiang(name, name + "/headimg/" + file.getOriginalFilename());

                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }
            String req = StaticUtils.RETURNIMGPATH + name + "/headimg/" + file.getOriginalFilename();
            System.out.println(name + "上传了一个新头像，地址为：" + req);
            return req;
        } else {
            return "上传失败，因为文件是空的.";
        }
    }

    /*
     * 用户修改个人信息
     * */
    public Map updataUser(User user) {
        Map map = new HashMap();
        int i = userMapper.updataUser(user.getName(), user.getSex(), user.getAge(), user.getJieshao(), user.getTag());
        System.out.println("admin修改信息，影响行数为：" + i);
        if (i == 1) {
            map.put("user", userMapper.findByName(user.getName()));
            return map;
        } else {
            map.put("user", "修改失败");
            return map;
        }
    }

}
