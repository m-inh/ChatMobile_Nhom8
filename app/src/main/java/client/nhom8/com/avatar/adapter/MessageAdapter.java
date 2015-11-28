package client.nhom8.com.avatar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import client.nhom8.com.avatar.R;
import client.nhom8.com.avatar.models.ItemMessage;
import client.nhom8.com.avatar.models.ItemRecentMessage;

/**
 * Created by TooNies1810 on 11/28/15.
 */
public class MessageAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater lf;
    private ArrayList<ItemMessage> itemArr;

    public MessageAdapter(Context mContext) {
        this.mContext = mContext;
        lf = LayoutInflater.from(mContext);

        initData();
    }

    private void initData() {
        itemArr = new ArrayList<>();

        // Tao du lieu gia
        itemArr.add(new ItemMessage("Hom qua lam bai chan vai", 2));
        itemArr.add(new ItemMessage("Tao cung the", 1));
        itemArr.add(new ItemMessage("Sai nhieu lam, chac duoc 9 la cung :(((", 1));
        itemArr.add(new ItemMessage("%^$&*@*$", 2));
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
            convertView = lf.inflate(R.layout.item_message,null);
        }

        TextView tvFriendSms = (TextView) convertView.findViewById(R.id.tv_friendsms);
        TextView tvMySms= (TextView) convertView.findViewById(R.id.tv_mysms);

        ItemMessage item = itemArr.get(position);

        if (item.getType() == ItemMessage.FRIEND_SMS){
            tvFriendSms.setText(item.getContent());
            tvMySms.setVisibility(View.GONE);
        } else if (item.getType() == ItemMessage.MY_SMS){
            tvMySms.setText(item.getContent());
            tvFriendSms.setVisibility(View.GONE);
        }
        return convertView;
    }
}
