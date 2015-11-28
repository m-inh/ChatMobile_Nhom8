package client.nhom8.com.avatar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import client.nhom8.com.avatar.adapter.MessageAdapter;
import client.nhom8.com.avatar.models.ItemMessage;

/**
 * Created by TooNies1810 on 11/28/15.
 */
public class MessageActivity extends Activity {
    private static final String TAG = "MessageActivity";
    private EditText edtMessage;
    private Button btnSend;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Intent receiveIntent = getIntent();
        this.uid = receiveIntent.getStringExtra("uid");
        Toast.makeText(this, "uid: " + uid, Toast.LENGTH_LONG).show();

        initViews();
    }

    private void initViews() {
        edtMessage = (EditText) findViewById(R.id.edt_message);
        btnSend = (Button) findViewById(R.id.btn_send);
        final ListView lvMessage = (ListView) findViewById(R.id.lv_message);
        final MessageAdapter mAdapter = new MessageAdapter(this);

        lvMessage.setAdapter(mAdapter);

        btnSend.setOnClickListener(new View.OnClickListener() {
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
                    sendMessage(uid, contentSms);
//                    Log.i(TAG, "count: " + mAdapter.getCount());
                } else {
                    //Neu tin nhan khong co noi dung thi tu choi
                    Toast.makeText(MessageActivity.this, "Sms is empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendMessage(String uid, String contentSms) {

    }
}
