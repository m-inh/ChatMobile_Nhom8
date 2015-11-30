package client.nhom8.com.avatar.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import client.nhom8.com.avatar.MessageActivity;
import client.nhom8.com.avatar.R;
import client.nhom8.com.avatar.adapter.MessageRecentAdapter;
import client.nhom8.com.avatar.managers.AppManager;
import client.nhom8.com.avatar.models.ItemRecentMessage;

/**
 * Created by TooNies1810 on 11/26/15.
 */
public class MessageFragment extends Fragment implements AdapterView.OnItemClickListener {
    private static final String TAG = "MessageFragment";
    private View root;
    private Context mContext;
    private ListView lvMessage;
    private MessageRecentAdapter mAdapter;

    private String uid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_message, container, false);
        mContext = getContext();

        Bundle bundleFromMain = getArguments();
        this.uid = bundleFromMain.getString("uid");

        initViews();
        return root;
    }

    private void initViews() {
        lvMessage = (ListView) root.findViewById(R.id.lv_message);
        mAdapter = new MessageRecentAdapter(mContext);

        lvMessage.setOnItemClickListener(this);
        lvMessage.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ItemRecentMessage item = (ItemRecentMessage) parent.getItemAtPosition(position);

        // Tao intent, dua uid muon chat vao intent sang MessageActivity
        Intent mIntent = new Intent(mContext, MessageActivity.class);
        mIntent.putExtra("uidFriend", item.getUid());
        mIntent.putExtra("uid", uid);

        mContext.startActivity(mIntent);
    }

    public void updateMessage(Intent intent) {
        String uid = intent.getStringExtra("uid");
        String uidSender = intent.getStringExtra("uidSender");
        String mes = intent.getStringExtra("mes");

        Log.i(TAG, uid);
        Log.i(TAG, uidSender);
        Log.i(TAG, mes);

        String nameUidSender = "No name";

        mAdapter.addItem(new ItemRecentMessage(uidSender,nameUidSender,mes));
        mAdapter.notifyDataSetChanged();
    }
}
