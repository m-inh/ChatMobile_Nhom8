package client.nhom8.com.avatar.managers;

import models.UserInfo;

/**
 *
 * @author KN_SV023
 */
public class UserManager {
    private static UserManager _sharePointer;
    private UserInfo userInfo;
    private UserManager(){}

    public static UserManager getIntance(){
        if(_sharePointer == null){
            _sharePointer = new UserManager();
        }
        return _sharePointer;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
