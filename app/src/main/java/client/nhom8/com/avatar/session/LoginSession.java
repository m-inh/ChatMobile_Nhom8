package client.nhom8.com.avatar.session;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.SharedPreferences.*;

/**
 * Created by TooNies1810 on 11/29/15.
 */
public class LoginSession {
    private static final String LOGIN_SESSION_KEY = "login_state";
    private Context mContext;
    private SharedPreferences pref;
    private Editor editor;

    private static final String SHARE_PREFERENCE_NAME = "USER_LOGIN_SESSION";

    public LoginSession(Context mContext) {
        this.mContext = mContext;
        pref = mContext.getSharedPreferences(SHARE_PREFERENCE_NAME, mContext.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void setLogin(boolean state){
        // Ghi trang thai login vao sharePref
        editor.putBoolean(LOGIN_SESSION_KEY, state);
        editor.commit();
    }

    public boolean isLogin(){
        return pref.getBoolean(LOGIN_SESSION_KEY, false);
    }
}
