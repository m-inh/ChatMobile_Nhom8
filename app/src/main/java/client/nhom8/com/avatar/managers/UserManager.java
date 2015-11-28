package client.nhom8.com.avatar.managers;
/**
 *
 * @author KN_SV023
 */
public class UserManager {
    private static UserManager _sharePointer;
    private String userID;
    private UserManager(){

    }
    public static UserManager getIntance(){
        if(_sharePointer == null){
            _sharePointer = new UserManager();
        }
        return _sharePointer;
    }
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
