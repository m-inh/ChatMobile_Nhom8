package client.nhom8.com.avatar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import client.nhom8.com.avatar.adapter.MessageAdapter;

/**
 * Created by TooNies1810 on 11/28/15.
 */
public class MessageActivity extends Activity {
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

        ListView lvMessage = (ListView) findViewById(R.id.lv_message);
        MessageAdapter mAdapter = new MessageAdapter(this);

        lvMessage.setAdapter(mAdapter);

    }
}
