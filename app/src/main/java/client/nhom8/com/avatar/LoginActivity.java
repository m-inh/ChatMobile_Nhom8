package client.nhom8.com.avatar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import client.nhom8.com.avatar.define.Define;
import client.nhom8.com.avatar.managers.ConnectionManager;
import client.nhom8.com.avatar.managers.UserManager;
import models.LoginInfo;
import models.UserLogin;

/**
 * Created by TooNies1810 on 11/26/15.
 */
public class LoginActivity extends Activity {
    private static final int REQUEST_CODE_REGISTER = 1111;
    private static final String TAG = "LoginActivity";
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //init Dialog
        pDialog = new ProgressDialog(this);
        pDialog.setTitle("Login");
        pDialog.setCancelable(false);

        initViews();
    }

    private void initViews() {
        final EditText edtUsername = (EditText) findViewById(R.id.edt_username);
        final EditText edtPass = (EditText) findViewById(R.id.edt_password);

        Button btnLogin = (Button) findViewById(R.id.btn_login);
        Button btnRegister = (Button) findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(mIntent, REQUEST_CODE_REGISTER);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String pass = edtPass.getText().toString();

                if (!username.isEmpty() && !pass.isEmpty()) {
                    checkLogin(edtUsername.getText().toString(), edtPass.getText().toString());
                } else {
                    Toast.makeText(LoginActivity.this, "Please fill your information", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private ObjectOutputStream dout;
    private ObjectInputStream din;

    //request username va pass len server de su li
    //Neu user dung thi luu user vao sqlite, luu session, chuyen den MainActivity
    private void checkLogin(String username, String pass) {
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
                    while (true) {
                        try {
                            infor = (LoginInfo) din.readObject();
                            if (infor != null) {
                                if (infor.isIsLogin()) {
                                    Log.i(TAG, "LoginInfo is ok");
                                    UserManager.getIntance().setUserID(infor.getUserID());
//                                    ChatFrm chatFrm = new ChatFrm();
//                                    this.setVisible(false);
                                    Message msg = new Message();
                                    msg.setTarget(mHandler);
                                    msg.sendToTarget();
                                    break;
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
            hideDialog();
            Intent mIntent = new Intent(LoginActivity.this, MainActivity.class);

            startActivity(mIntent);
            finish();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_REGISTER) {
            if (resultCode == RESULT_OK) {
                // dien thong tin vua dang ki vao form

            } else if (resultCode == RESULT_CANCELED) {
                // neu ket qua tra ve la cancel thi khong lam gi ca
                return;
            }
        }
    }

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
}
