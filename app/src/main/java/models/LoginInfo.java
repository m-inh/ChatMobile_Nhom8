package models;

import java.io.Serializable;
/**
 *
 * @author KN_SV023
 */
public class LoginInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userID;
    private boolean isLogin;

    public LoginInfo(String userID, boolean isLogin) {
        this.userID = userID;
        this.isLogin = isLogin;
    }

    public LoginInfo() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean isIsLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

}
