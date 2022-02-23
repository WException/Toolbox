package com.Niss.Toolbox_Service.pojo;

public class User {
    private int id; // id
    private String name; // 用户名
    private String password; //密码
    private int age; //年龄
    private String sex; //性别
    private String tag; //兴趣标签
    private String jieshao; //个人介绍
    private String touxiang; //头像地址

    public User() {
    }

    public User(int id, String name, String password, int age, String sex, String tag, String jieshao, String touxiang) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.sex = sex;
        this.tag = tag;
        this.jieshao = jieshao;
        this.touxiang = touxiang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getJieshao() {
        return jieshao;
    }

    public void setJieshao(String jieshao) {
        this.jieshao = jieshao;
    }

    public String getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(String touxiang) {
        this.touxiang = touxiang;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", tag='" + tag + '\'' +
                ", jieshao='" + jieshao + '\'' +
                ", touxiang='" + touxiang + '\'' +
                '}';
    }
}
