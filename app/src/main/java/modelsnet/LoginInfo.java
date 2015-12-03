package modelsnet;
import java.io.Serializable;
import models.UserInfo;
/**
 *
 * @author KN_SV023
 */
public class LoginInfo implements Serializable{
    private UserInfo userInfo;
    private boolean isLogin;

    public LoginInfo(UserInfo userInfo, boolean isLogin) {
        this.userInfo = userInfo;
        this.isLogin = isLogin;
    }

    public LoginInfo() {
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public boolean isIsLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }



}
