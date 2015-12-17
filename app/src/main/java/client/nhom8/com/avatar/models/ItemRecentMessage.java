package client.nhom8.com.avatar.models;

/**
 * Created by TooNies1810 on 11/28/15.
 */
public class ItemRecentMessage {
    private String uid;
    private String nameUser;
    // Tin nhan gan day nhat
    private String recentSms;

    public ItemRecentMessage(String uid, String nameUser, String recentSms) {
        this.uid = uid;
        this.nameUser = nameUser;
        this.recentSms = recentSms;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getRecentSms() {
        return recentSms;
    }

    public void setRecentSms(String recentSms) {
        this.recentSms = recentSms;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
