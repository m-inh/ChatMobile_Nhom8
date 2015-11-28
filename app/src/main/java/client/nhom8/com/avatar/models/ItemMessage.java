package client.nhom8.com.avatar.models;

/**
 * Created by TooNies1810 on 11/28/15.
 */
public class ItemMessage {
    public static final int MY_SMS = 1;
    public static final int FRIEND_SMS = 2;
    // Noi dung co trong tin nhan
    private String content;
    // Nguoi gui la ai, 1: MySms, 2: FriendSms
    private int type;

    public ItemMessage(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
