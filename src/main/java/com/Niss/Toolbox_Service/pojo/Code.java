package com.Niss.Toolbox_Service.pojo;

public class Code {
    private int id;
    private String userName;
    private String fileData;
    private String fileType;

    public Code() {
    }

    public Code(int id, String fileName, String fileData, String fileType) {
        this.id = id;
        this.userName = fileName;
        this.fileData = fileData;
        this.fileType = fileType;
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

    public void setUserName(String fileName) {
        this.userName = fileName;
    }

    public String getFileData() {
        return fileData;
    }

    public void setFileData(String fileData) {
        this.fileData = fileData;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "Code{" +
                "id=" + id +
                ", fileName='" + userName + '\'' +
                ", fileData='" + fileData + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}
