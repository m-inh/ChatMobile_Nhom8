package client.nhom8.com.avatar.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by TooNies1810 on 11/29/15.
 */
public class UserData extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "USER_DATA.db";
    private static final String TABLE_NAME = "USER";
    private static final String UID = "uid";
    private static final String USERNAME= "username";
    private static final String PASSWORD = "password";
    private static final String TAG = "UserData";

    public UserData(Context mContext){
        super(mContext, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME
                + "(" + UID + " text,"
                + USERNAME + " text,"
                + PASSWORD + " text"
                + ")");

        Log.i(TAG, "Create new user database");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void addUser(String uid, String name, String pass){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put("uid", uid);
        content.put("username", name);
        content.put("password", pass);

        db.insert(TABLE_NAME, null, content);
        db.close();
    }

    public HashMap<String, String> getUser(){
        SQLiteDatabase db  = this.getReadableDatabase();

        HashMap<String, String> user = new HashMap<>();
        String select_sentence = "SELECT * FROM " + TABLE_NAME;
        Cursor c = db.rawQuery(select_sentence, null);
        if (c == null){
            return null;
        }

        c.moveToFirst();
        int indexUid = c.getColumnIndex("uid");
        int indexName= c.getColumnIndex("username");
        int indexPass = c.getColumnIndex("password");

        user.put("uid", c.getString(indexUid));
        user.put("username", c.getString(indexName));
        user.put("password", c.getString(indexPass));

        return user;
    }

    public void deleteUser(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, null, null);
        db.close();
        Log.i(TAG, "delete database successful");
    }
}
