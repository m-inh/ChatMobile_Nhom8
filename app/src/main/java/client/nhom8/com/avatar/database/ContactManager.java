package client.nhom8.com.avatar.database;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;

import client.nhom8.com.avatar.models.ItemContact;

/**
 * Created by TooNies1810 on 11/28/15.
 */
public class ContactManager {
    private static final String TAG = "ContactManager";
    private Context mContext;

    public ContactManager(Context mContext) {
        this.mContext = mContext;
    }

    public ArrayList<ItemContact> getItemContactArr() {
        ArrayList<ItemContact> itemArr = new ArrayList<>();

        ContentResolver resolver = mContext.getContentResolver();
        String[] projection = {};
        Cursor c = resolver.query(ContactsContract.Contacts.CONTENT_URI, projection, null, null, null);

        if (c == null) {
            return itemArr;
        }

        c.moveToFirst();
        int indexId = c.getColumnIndex(ContactsContract.Contacts._ID);
        int indexName = c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
        int indexHasPhoneNumb = c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);

        while (!c.isAfterLast()) {
            String id = c.getString(indexId);
//            Log.i(TAG, "Id: " + id);
            String name = c.getString(indexName);
//            Log.i(TAG, "name: " + name);
            int hasPhoneNumb = Integer.parseInt(c.getString(indexHasPhoneNumb));
//            Log.i(TAG, "Has phone numb: " + hasPhoneNumb);

            String phoneNumbArr[] = new String[0];

            if (hasPhoneNumb > 0) {
                String WHERE_CLAUSE = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id;
//                Log.i(TAG, WHERE_CLAUSE);
                Cursor cPhone = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, WHERE_CLAUSE, null, null);
                if (cPhone == null) {
                    return itemArr;
                }
                cPhone.moveToFirst();

                int indexPhone = cPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                int count = 0;
                ArrayList<String> phoneTempArr = new ArrayList<>();
                while (!cPhone.isAfterLast()) {
                    String phoneNumb = cPhone.getString(indexPhone);
                    phoneTempArr.add(phoneNumb);
//                    Log.i(TAG, "phone number: " + phoneNumb);
                    count++;
                    cPhone.moveToNext();
                }
                phoneNumbArr = new String[phoneTempArr.size()];
                phoneTempArr.toArray(phoneNumbArr);

                cPhone.close();
            }

            itemArr.add(new ItemContact(name, phoneNumbArr));
            c.moveToNext();
        }

        c.close();

        return itemArr;
    }
}
