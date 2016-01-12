package client.nhom8.com.avatar.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import client.nhom8.com.avatar.R;
import client.nhom8.com.avatar.database.ContactManager;
import client.nhom8.com.avatar.fragment.ContactFragment;
import client.nhom8.com.avatar.models.ItemContact;

/**
 * Created by TooNies1810 on 11/28/15.
 */
public class ContactAdapter extends BaseAdapter implements Filterable {
    private Context mContext;
    private LayoutInflater lf;
    private CustomFilter filter;
    private ArrayList<ItemContact> itemArr;
    private ArrayList<ItemContact> filterItemArr;


    public ContactAdapter(Context mContext, ArrayList<ItemContact> itemArr) {
        this.mContext = mContext;
        this.itemArr = itemArr;
        this.filterItemArr = itemArr;

        lf = LayoutInflater.from(mContext);
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
        return itemArr.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = lf.inflate(R.layout.item_contact, null);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tvPhoneNumb = (TextView) convertView.findViewById(R.id.tv_phonenumb);

        tvName.setText(itemArr.get(position).getNameContact());
        tvPhoneNumb.setText(itemArr.get(position).getPhoneNumber()[0]);

        return convertView;
    }

    @Override
    public Filter getFilter() {

        if (filter == null) {
            filter = new CustomFilter();
        }

        return filter;
    }

    private class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                constraint = constraint.toString().toUpperCase();

                ArrayList<ItemContact> filters = new ArrayList<ItemContact>();

                for (int i = 0; i < filterItemArr.size(); i++) {
                    if (filterItemArr.get(i).getNameContact().toUpperCase().contains(constraint)) {
                        ItemContact p = new ItemContact(filterItemArr.get(i).getNameContact(), filterItemArr.get(i).getPhoneNumber());
                        filters.add(p);
                    }
                }
                results.count = filters.size();
                results.values = filters;
            } else {
                results.count = filterItemArr.size();
                results.values = filterItemArr;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            itemArr = (ArrayList<ItemContact>) results.values;
            notifyDataSetChanged();
        }
    }
}
