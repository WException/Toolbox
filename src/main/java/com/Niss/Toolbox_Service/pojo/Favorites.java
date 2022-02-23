package com.Niss.Toolbox_Service.pojo;

import java.sql.Date;

public class Favorites {
    private int id;
    private String userName;
    private Date time;
    private String favoritesName;

    public Favorites() {
    }

    public Favorites(int id, String userName, Date time, String favoritesName) {
        this.id = id;
        this.userName = userName;
        this.time = time;
        this.favoritesName = favoritesName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getFavoritesName() {
        return favoritesName;
    }

    public void setFavoritesName(String favoritesName) {
        this.favoritesName = favoritesName;
    }

    @Override
    public String toString() {
        return "Favorites{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", time=" + time +
                ", favoritesName='" + favoritesName + '\'' +
                '}';
    }
}
