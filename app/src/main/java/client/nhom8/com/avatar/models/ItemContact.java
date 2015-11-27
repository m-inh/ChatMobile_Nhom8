package client.nhom8.com.avatar.models;

/**
 * Created by TooNies1810 on 11/28/15.
 */
public class ItemContact {
    private String nameContact;
    private String[] phoneNumber;

    public ItemContact(String nameContact, String[] phoneNumber) {
        this.nameContact = nameContact;
        this.phoneNumber = phoneNumber;
    }

    public String getNameContact() {
        return nameContact;
    }

    public void setNameContact(String nameContact) {
        this.nameContact = nameContact;
    }

    public String[] getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String[] phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
