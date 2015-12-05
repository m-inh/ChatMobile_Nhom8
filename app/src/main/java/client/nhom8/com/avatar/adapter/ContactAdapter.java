package client.nhom8.com.avatar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import client.nhom8.com.avatar.R;
import client.nhom8.com.avatar.database.ContactManager;
import client.nhom8.com.avatar.models.ItemContact;

/**
 * Created by TooNies1810 on 11/28/15.
 */
public class ContactAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater lf;
    private ArrayList<ItemContact> itemArr = new ArrayList<>();

    public ContactAdapter(Context mContext) {
        this.mContext = mContext;
        lf = LayoutInflater.from(mContext);

        initDataContact();
    }

    private void initDataContact() {
//        itemArr = new ArrayList<>();
//        itemArr.add(new ItemContact("Tran Van Tu", new String[]{"01234123123"}));
//        itemArr.add(new ItemContact("Tran Van Tu", new String[]{"01234123123"}));
//        itemArr.add(new ItemContact("Tran Van Tu", new String[]{"01234123123"}));

        ContactManager contactMgr = new ContactManager(mContext);
        itemArr = contactMgr.getItemContactArr();
    }

    @Override
    public int getCount() {
        return itemArr.size();
    }

    @Override
    public ItemContact getItem(int position) {
        return itemArr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = lf.inflate(R.layout.item_contact, null);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tvPhoneNumb = (TextView) convertView.findViewById(R.id.tv_phonenumb);

        tvName.setText(itemArr.get(position).getNameContact());
//        tvPhoneNumb.setText(itemArr.get(position).getPhoneNumber()[0]);

        return convertView;
    }
}
