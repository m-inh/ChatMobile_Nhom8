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
import models.BaseMessage;

/**
 * Created by TooNies1810 on 11/29/15.
 */
public class ListenMessageService extends Service implements Runnable{

    private static final String TAG = "ListenMessageService";

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
        while(true){
            try {
                objectOutputStream.flush();
                BaseMessage msg = null;
                msg = (BaseMessage) objectInputStream.readObject();
                if(msg != null){
                    Log.i(TAG, msg.uidSender + " : " + msg.mes);
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
