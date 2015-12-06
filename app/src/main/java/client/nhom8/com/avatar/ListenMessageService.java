package client.nhom8.com.avatar;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import client.nhom8.com.avatar.managers.ConnectionManager;
import modelsnet.BaseMessage;

/**
 * Created by TooNies1810 on 11/29/15.
 */
public class ListenMessageService extends Service implements Runnable {

    private static final String TAG = "ListenMessageService";
    public static final String UPDATE_MESSAGE_RECENT_ACTION = "update_message_recent_action";
    public static final String UPDATE_MESSAGE_ACTION = "update_message_action";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "on startcommand", Toast.LENGTH_LONG).show();
//        Log.i(TAG, "on startCommand");
        return Service.START_NOT_STICKY;
    }

    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private Thread th;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "on create");
//        Toast.makeText(this, "on create", Toast.LENGTH_LONG).show();

        th = new Thread(this);

        try {
            initConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //bat dau lang nghe tin nhan tu server gui ve
        th.start();
    }

    private void initConnection() throws IOException {
        objectInputStream = ConnectionManager.getIntance().getObjectInputStream();
        objectOutputStream = ConnectionManager.getIntance().getObjectOutputStream();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(TAG, "on start");
//        Toast.makeText(this, "on start", Toast.LENGTH_LONG).show();
    }

    @Override
    public void run() {
        while (true) {
            try {
                objectOutputStream.flush();
                BaseMessage baseMsg = (BaseMessage) objectInputStream.readObject();
                if (baseMsg != null) {
                    Log.i(TAG, baseMsg.uidSender + " : " + baseMsg.mes);

                    Intent mIntent = new Intent();
                    mIntent.putExtra("uid", baseMsg.uid);
                    mIntent.putExtra("uidSender", baseMsg.uidSender);
                    mIntent.putExtra("mes", baseMsg.mes);
                    mIntent.setAction(UPDATE_MESSAGE_RECENT_ACTION);

                    sendBroadcast(mIntent);

                    Intent mIntent2 = new Intent();
                    mIntent2.putExtra("uid", baseMsg.uid);
                    mIntent2.putExtra("uidSender", baseMsg.uidSender);
                    mIntent2.putExtra("mes", baseMsg.mes);
                    mIntent2.setAction(UPDATE_MESSAGE_ACTION);


                    sendBroadcast(mIntent2);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
