package client.nhom8.com.avatar.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;

import client.nhom8.com.avatar.R;
import client.nhom8.com.avatar.managers.UserManager;
import client.nhom8.com.avatar.models.ItemRecentMessage;
import models.Friend;

/**
 * Created by TooNies1810 on 11/28/15.
 */
public class MessageRecentAdapter extends BaseAdapter{
    private static final String TAG = "MessageRecentAdapter";
    private Context mContext;
    private LayoutInflater lf;
    private ArrayList<ItemRecentMessage> itemArr;

    public MessageRecentAdapter(Context mContext) {
        this.mContext = mContext;
        lf = LayoutInflater.from(mContext);

        initData();
        updateListFriend(UserManager.getIntance().getUserInfo().getListFriend());
    }

    private void initData() {
        itemArr = new ArrayList<>();
        Log.i(TAG, "new messageRecentItem arr");

        // Tao du lieu gia
//        itemArr.add(new ItemRecentMessage("1","Tran Duc Hung", "ok bai bai"));
//        itemArr.add(new ItemRecentMessage("2","Tran Duc Hieu", "Goodbye"));
//        itemArr.add(new ItemRecentMessage("3","Tran Duc Hang", "ok men"));
//        itemArr.add(new ItemRecentMessage("4","Tran Duc Hiem", "bien di may"));
//        itemArr.add(new ItemRecentMessage("5","Tran Duc Hun", "<3"));
//        itemArr.add(new ItemRecentMessage("6","Tran Duc Hen", "I love you"));
//        itemArr.add(new ItemRecentMessage("1001","icthub1", "null"));
//        itemArr.add(new ItemRecentMessage("1002","icthub2", "null"));
//        itemArr.add(new ItemRecentMessage("1003","icthub3", "null"));
//        itemArr.add(new ItemRecentMessage("1004","icthub4", "null"));
//        itemArr.add(new ItemRecentMessage("1005","icthub5", "null"));
//        itemArr.add(new ItemRecentMessage("1006","icthub6", "null"));

//        this.addItem(new ItemRecentMessage("1001", "123", "ok ko ok"));
    }

    public void updateListFriend(Vector<Friend> listFriend){
//        itemArr = new ArrayList<>();

        for (int i = 0; i < listFriend.size(); i++) {
            Friend friend = listFriend.get(i);
            itemArr.add(new ItemRecentMessage(friend.getUserID(), friend.getUsername(), "null"));
        }
    }

    public void addItem(ItemRecentMessage item){
        //Neu id cua sender ton tai thi replace, neu khong thi add moi
        for (int i = 0; i < itemArr.size(); i++) {
            if (itemArr.get(i).getUid().equalsIgnoreCase(item.getUid())){
                itemArr.get(i).setRecentSms(item.getRecentSms());
                Log.i(TAG, "replace new itemRecentMsg");
                return;
            }
        }

        itemArr.add(item);
        Log.i(TAG, "add new itemRecentMsg");
    }

    @Override
    public int getCount() {
        return itemArr.size();
    }

    @Override
    public Object getItem(int position) {
        return itemArr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = lf.inflate(R.layout.item_recentsms,null);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tvRecentSms = (TextView) convertView.findViewById(R.id.tv_recentsms);

        tvName.setText(itemArr.get(position).getNameUser());
        tvRecentSms.setText(itemArr.get(position).getRecentSms());

        return convertView;
    }
}
