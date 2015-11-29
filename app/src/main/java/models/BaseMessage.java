package models;

import java.io.Serializable;
/**
 *
 * @author KN_SV023
 */
public class BaseMessage implements Serializable {
    private static final long serialVersionUID = 1L;
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
