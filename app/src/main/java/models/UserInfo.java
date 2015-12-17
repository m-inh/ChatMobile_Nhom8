package models;

import java.io.Serializable;
import java.util.Vector;

public class UserInfo implements Serializable{
    private String userID;
    private String username;
    private String password;
    private Vector<Friend> listFriend;

    public UserInfo(String userID, String username, String passWord, Vector<Friend> listFriend) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.listFriend = listFriend;
    }

    public UserInfo() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassWord() {
        return password;
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }

    public Vector<Friend> getListFriend() {
        return listFriend;
    }

    public void setListFriend(Vector<Friend> listFriend) {
        this.listFriend = listFriend;
    }


}
