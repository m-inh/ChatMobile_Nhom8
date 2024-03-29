package client.nhom8.com.avatar;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectOutputStream;

import client.nhom8.com.avatar.adapter.MessageAdapter;
import client.nhom8.com.avatar.managers.ConnectionManager;
import client.nhom8.com.avatar.models.ItemMessage;
import modelsnet.BaseMessage;

/**
 * Created by TooNies1810 on 11/28/15.
 */
public class MessageActivity extends ActionBarActivity {
    private static final String TAG = "MessageActivity";
    private EditText edtMessage;
    private Button btnSend;
    private ImageButton imgBtn_send;
    private String uid;
    private String uidFriend;
    private ListView lvMessage;
    private MessageAdapter mAdapter;
    private Toolbar tool_bar;
    private ImageButton imgBtn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Intent receiveIntent = getIntent();
        this.uidFriend = receiveIntent.getStringExtra("uidFriend");
        this.uid = receiveIntent.getStringExtra("uid");
        Toast.makeText(this, "uid: " + uidFriend, Toast.LENGTH_LONG).show();

        initViews();
        setSupportActionBar(tool_bar);
        //getSupportActionBar().setTitle(null);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        //Dang ki broadcast lang nghe tin nhan toi
        IntentFilter filter = new IntentFilter();
        filter.addAction(ListenMessageService.UPDATE_MESSAGE_ACTION);
        registerReceiver(receiver, filter);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Cap nhat lai giao dien chat khi co tin nhan moi
            //kiem tra xem tin nhan toi co phai dung voi nguoi minh dang chat hay khong
            String uidSender = intent.getStringExtra("uidSender");
            String contentSms = intent.getStringExtra("mes");

            Log.i(TAG, uidSender);
            Log.i(TAG, contentSms);

            if (uidFriend.equalsIgnoreCase(uidSender)) {
                Log.i(TAG, uidSender);
                Log.i(TAG, contentSms);
                //cap nhat giao dien
                mAdapter.addItem(new ItemMessage(contentSms, ItemMessage.FRIEND_SMS));
//                lvMessage.setSelection(mAdapter.getCount() - 1);
                mAdapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    private void initViews() {
        tool_bar = (Toolbar) findViewById(R.id.tool_bar);
        TextView tvFriendName = (TextView) findViewById(R.id.tv_friendname);
        tvFriendName.setText("" + uidFriend);
        edtMessage = (EditText) findViewById(R.id.edt_message);
        //btnSend = (Button) findViewById(R.id.btn_send);
        imgBtn_send = (ImageButton) findViewById(R.id.imgBtn_send);
        lvMessage = (ListView) findViewById(R.id.lv_message);
        mAdapter = new MessageAdapter(this);

        lvMessage.setAdapter(mAdapter);

        imgBtn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contentSms = edtMessage.getText().toString();
                if (!contentSms.isEmpty()) {
                    //Neu tin nhan co noi dung thi gui
                    //Them vao listView dang chat
                    mAdapter.addItem(new ItemMessage(contentSms, ItemMessage.MY_SMS));
                    lvMessage.setSelection(mAdapter.getCount() - 1);
                    mAdapter.notifyDataSetChanged();

                    // Gui tin nhan qua socket
                    sendMessage(uidFriend, contentSms);
//                    Log.i(TAG, "count: " + mAdapter.getCount());
                    edtMessage.setText("");

                } else {
                    //Neu tin nhan khong co noi dung thi tu choi
                    Toast.makeText(MessageActivity.this, "Sms is empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imgBtn_back = (ImageButton) findViewById(R.id.imgBtn_back);
        imgBtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MessageActivity.this, "AAAAAAAAAAA", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(MessageActivity.this, MainActivity.class);
//                startActivity(i);

                onBackPressed();
            }
        });
    }

    private void sendMessage(String uidFriend, String contentSms) {
        try {
            ObjectOutputStream objectOutputStream = ConnectionManager.getIntance().getObjectOutputStream();
            BaseMessage baseMessage = new BaseMessage(uidFriend, uid, contentSms);
            objectOutputStream.writeObject(baseMessage);
            objectOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_back) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
