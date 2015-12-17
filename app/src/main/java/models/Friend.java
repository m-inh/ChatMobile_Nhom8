package models;

import java.io.Serializable;

public class Friend implements Serializable{
    private String username;
    private String lastName;
    private String fristName;
    private String userID;

    public Friend(String lastName, String fristName, String userID, String username) {
        this.username = username;
        this.lastName = lastName;
        this.fristName = fristName;
        this.userID = userID;
    }

    public Friend() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFristName() {
        return fristName;
    }

    public void setFristName(String fristName) {
        this.fristName = fristName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }



}
