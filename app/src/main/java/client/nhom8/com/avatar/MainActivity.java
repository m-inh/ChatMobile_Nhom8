package client.nhom8.com.avatar;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import client.nhom8.com.avatar.adapter.MyViewpagerAdapter;
import client.nhom8.com.avatar.database.UserData;
import client.nhom8.com.avatar.define.Define;
import client.nhom8.com.avatar.fragment.ContactFragment;
import client.nhom8.com.avatar.fragment.MessageFragment;
import client.nhom8.com.avatar.fragment.SettingFragment;
import client.nhom8.com.avatar.fragment.SocialFragment;
import client.nhom8.com.avatar.managers.ConnectionManager;
import client.nhom8.com.avatar.managers.UserManager;
import client.nhom8.com.avatar.session.LoginSession;
import models.UserInfo;
import modelsnet.LoginInfo;
import modelsnet.UserLogin;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ViewPager mViewPager;
    private MyViewpagerAdapter mPagerAdapter;
    private ProgressDialog pDialog;

    private LoginSession session;

    private UserData userDb;
    private String uid;

    private ContactFragment contactFragment;
    private MessageFragment messageFragment;
    private SocialFragment socialFragment;
    private SettingFragment settingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init user database
        userDb = new UserData(this);
        HashMap<String, String> user = userDb.getUser();
        uid = user.get("uid");

        // init session
        session = new LoginSession(this);
        if (!session.isLogin()) {
            logOut();
        }

        //init Dialog
        pDialog = new ProgressDialog(this);
        pDialog.setTitle("Login");
        pDialog.setCancelable(false);

        // Set up the ViewPager with the pager adapter.
        ArrayList<Fragment> fragmentArr = new ArrayList<>();
        Bundle b = new Bundle();
        b.putString("uid",uid);
        contactFragment = new ContactFragment();
        messageFragment = new MessageFragment();
        messageFragment.setArguments(b);
        socialFragment = new SocialFragment();
        settingFragment = new SettingFragment();

        fragmentArr.add(contactFragment);
        fragmentArr.add(messageFragment);
        fragmentArr.add(socialFragment);
        fragmentArr.add(settingFragment);

        ArrayList<String> titleFragmentArr = new ArrayList<>();
        titleFragmentArr.add("Contact");
        titleFragmentArr.add("Message");
        titleFragmentArr.add("Social");
        titleFragmentArr.add("Settings");
        mPagerAdapter = new MyViewpagerAdapter(getSupportFragmentManager(), fragmentArr, titleFragmentArr);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_container);
        mViewPager.setAdapter(mPagerAdapter);

        // set up tabs with viewpager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        //dang ki broadcast lang nghe tin nhan tu server
        IntentFilter filter = new IntentFilter();
        filter.addAction(ListenMessageService.UPDATE_MESSAGE_RECENT_ACTION);
        registerReceiver(receiver, filter);

        //kiem tra thong tin dang nhap cua phien truoc
        //neu thong tin sai se quay lai man hinh dang nhap
//        checkLogin(user.get("username"), user.get("password"));

        //test without dang nhap khong qua login
        Intent mIntent = new Intent(MainActivity.this, ListenMessageService.class);
        startService(mIntent);
        messageFragment.updateListFriend(UserManager.getIntance().getUserInfo().getListFriend());
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "Hang ve activiy");
            //Goi fragment cap nhat du lieu
            messageFragment.updateMessage(intent);
        }
    };

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    private ObjectOutputStream dout;
    private ObjectInputStream din;

    //request username va pass len server de su li
    //Neu user dung thi luu user vao sqlite, luu session, chuyen den MainActivity
    private void checkLogin(final String username, final String pass) {
        final UserLogin userLogin =
                new UserLogin(username, pass, 1);
        //Hien dialog doi
        showDialog();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Socket socketClient;
                try {
                    socketClient = new Socket(Define.IP, Define.PORT);

                    if (socketClient.isConnected()) {
                        Log.i(TAG, "Server is accept!");
//                        hideDialog();
                    }
                    dout = new ObjectOutputStream(socketClient.getOutputStream());
                    din = new ObjectInputStream(socketClient.getInputStream());
                    ConnectionManager.getIntance().setSoc(socketClient);
                    ConnectionManager.getIntance().setObjectInputStream(din);
                    ConnectionManager.getIntance().setObjectOutputStream(dout);

                    dout.writeObject(userLogin);
                    dout.flush();

                    //Lang nghe thong tin gui ve
                    LoginInfo infor = null;
                    while (infor == null) {
                        try {
                            infor = (LoginInfo) din.readObject();
                            if (infor != null) {
                                if (infor.isIsLogin()) {
                                    Log.i(TAG, "LoginInfo success!");
                                    UserInfo userInfo = infor.getUserInfo();
                                    UserManager.getIntance().setUserInfo(userInfo);
//                                    ChatFrm chatFrm = new ChatFrm();
//                                    this.setVisible(false);

                                    // Add new user to SQLite db
//                                    userDb.addUser(infor.getUserID(), username, pass);

                                    Message msg = new Message();
                                    msg.arg1 = 1;
                                    msg.setTarget(mHandler);
                                    msg.sendToTarget();
                                } else {
                                    // Gui thong bao sai tai khoan
                                    Message msg = new Message();
                                    msg.arg1 = 0;
                                    msg.setTarget(mHandler);
                                    msg.sendToTarget();
                                }
                            } else {
                                Log.i(TAG, "LoginInfo is null");
                            }
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
//                    Toast.makeText(LoginActivity.this, "Connection is failed!", Toast.LENGTH_LONG).show();
                }
            }
        }).start();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // hide progresss dialog
            hideDialog();

            if (msg.arg1 == 1) {
                session.setLogin(true);
                //call service to listener
                Intent mIntent = new Intent(MainActivity.this, ListenMessageService.class);
                startService(mIntent);
                messageFragment.updateListFriend(UserManager.getIntance().getUserInfo().getListFriend());
            } else if (msg.arg1 == 0) {
                Toast.makeText(MainActivity.this, "Tài khoản không đúng, xin kiểm tra lại", Toast.LENGTH_LONG).show();
                logOut();
            }
        }
    };

    private void showDialog() {
        if (!pDialog.isShowing()) {
            pDialog.show();
        }
    }

    private void hideDialog() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }

    public void logOut() {
        // xoa du lieu nguoi dung khoi sqlite db
        userDb.deleteUser();

        // set session la false, chuyen den Login activity
        session.setLogin(false);

        // chuyen den Login activiy
        Intent mIntent = new Intent(this, LoginActivity.class);
        startActivity(mIntent);
        finish();
    }


}
