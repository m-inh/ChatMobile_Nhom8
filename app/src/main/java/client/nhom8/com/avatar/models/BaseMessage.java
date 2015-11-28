package client.nhom8.com.avatar.models;

import java.io.Serializable;

/**
 *
 * @author KN_SV023
 */
public class BaseMessage implements Serializable{
    public String uid;
    public String uidSender;
    public String mes;

    public BaseMessage(String uid, String uidSender, String mes) {
        this.uid = uid;
        this.uidSender = uidSender;
        this.mes = mes;
    }

    

    public BaseMessage() {
    }
    
    
}
