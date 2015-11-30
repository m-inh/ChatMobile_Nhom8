package client.nhom8.com.avatar.managers;

/**
 * Created by TooNies1810 on 11/29/15.
 */
public class AppManager {
    private static AppManager appManager;
    private AppManager() {
    }

    public static AppManager getInstance(){
        if (appManager == null){
            appManager = new AppManager();
        }
        return appManager;
    }
}
