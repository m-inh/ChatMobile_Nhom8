package client.nhom8.com.avatar.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import client.nhom8.com.avatar.MessageActivity;
import client.nhom8.com.avatar.R;
import client.nhom8.com.avatar.adapter.MessageRecentAdapter;
import client.nhom8.com.avatar.models.ItemRecentMessage;

/**
 * Created by TooNies1810 on 11/26/15.
 */
public class MessageFragment extends Fragment implements AdapterView.OnItemClickListener{
    private View root;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_message, container, false);
        mContext = getContext();
        initViews();
        return root;
    }

    private void initViews() {
        ListView lvMessage = (ListView) root.findViewById(R.id.lv_message);
        MessageRecentAdapter mAdapter = new MessageRecentAdapter(mContext);

        lvMessage.setOnItemClickListener(this);
        lvMessage.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ItemRecentMessage item = (ItemRecentMessage) parent.getItemAtPosition(position);

        // Tao intent, dua uid muon chat vao intent sang MessageActivity
        Intent mIntent = new Intent(mContext,MessageActivity.class);
        mIntent.putExtra("uid", item.getUid());

        mContext.startActivity(mIntent);
    }
}
