package com.Niss.Toolbox_Service.pojo;

public class Suggestions {
    private int id;
    private String name;
    private String email;
    private int num;
    private String descc;

    public Suggestions() {
    }

    public Suggestions(int id, String name, String email, int num, String descc) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.num = num;
        this.descc = descc;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDescc() {
        return descc;
    }

    public void setDescc(String descc) {
        this.descc = descc;
    }

    @Override
    public String toString() {
        return "Suggestions{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", num=" + num +
                ", descc='" + descc + '\'' +
                '}';
    }
}
