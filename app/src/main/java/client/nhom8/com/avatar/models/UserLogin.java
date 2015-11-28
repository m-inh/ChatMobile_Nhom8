package client.nhom8.com.avatar.models;

import java.io.Serializable;

public class UserLogin implements Serializable{
    private String userName;
    private String passWord;
    private int port;
    public UserLogin(String userName, String passWord, int port){
        this.userName = userName;
        this.passWord = passWord;
        this.port = port;
    }
    public UserLogin(){
        this.userName = "";
        this.passWord = "";
        port = 8080;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
        
}
