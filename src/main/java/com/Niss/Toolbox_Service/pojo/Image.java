package com.Niss.Toolbox_Service.pojo;

import java.sql.Date;

public class Image {
    private int id;
    private int userId;
    private int size;
    private String path;
    private String type;
    private Date time;
    private String name;
    private int favoritesId;


    public Image() {
    }

    public Image(int id, int userId, int size, String path, String type, Date time, String name, int favoritesId) {
        this.id = id;
        this.userId = userId;
        this.size = size;
        this.path = path;
        this.type = type;
        this.time = time;
        this.name = name;
        this.favoritesId = favoritesId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date tiem) {
        this.time = tiem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFavoritesId() {
        return favoritesId;
    }

    public void setFavoritesId(int favoritesId) {
        this.favoritesId = favoritesId;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", userId=" + userId +
                ", size=" + size +
                ", path='" + path + '\'' +
                ", type='" + type + '\'' +
                ", time=" + time +
                ", name='" + name + '\'' +
                ", favoritesId='" + favoritesId + '\'' +
                '}';
    }
}
