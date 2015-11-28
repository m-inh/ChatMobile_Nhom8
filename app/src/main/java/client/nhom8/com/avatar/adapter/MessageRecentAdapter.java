package client.nhom8.com.avatar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import client.nhom8.com.avatar.R;
import client.nhom8.com.avatar.models.ItemRecentMessage;

/**
 * Created by TooNies1810 on 11/28/15.
 */
public class MessageRecentAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater lf;
    private ArrayList<ItemRecentMessage> itemArr;

    public MessageRecentAdapter(Context mContext) {
        this.mContext = mContext;
        lf = LayoutInflater.from(mContext);

        initData();
    }

    private void initData() {
        itemArr = new ArrayList<>();

        // Tao du lieu gia
        itemArr.add(new ItemRecentMessage("1","Tran Duc Hung", "ok bai bai"));
        itemArr.add(new ItemRecentMessage("2","Tran Duc Hieu", "Goodbye"));
        itemArr.add(new ItemRecentMessage("3","Tran Duc Hang", "ok men"));
        itemArr.add(new ItemRecentMessage("4","Tran Duc Hiem", "bien di may"));
        itemArr.add(new ItemRecentMessage("5","Tran Duc Hun", "<3"));
        itemArr.add(new ItemRecentMessage("6","Tran Duc Hen", "I love you"));
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
