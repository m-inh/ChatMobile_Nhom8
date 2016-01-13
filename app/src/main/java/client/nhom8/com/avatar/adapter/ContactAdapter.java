package client.nhom8.com.avatar.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;

import client.nhom8.com.avatar.R;
import client.nhom8.com.avatar.database.ContactManager;
import client.nhom8.com.avatar.fragment.ContactFragment;
import client.nhom8.com.avatar.models.ItemContact;

/**
 * Created by TooNies1810 on 11/28/15.
 */
public class ContactAdapter extends BaseAdapter implements Filterable,SectionIndexer {
    private Context mContext;
    private LayoutInflater inflater;
    private CustomFilter filter;
    private ArrayList<ItemContact> itemArr;
    private ArrayList<ItemContact> filterItemArr;
    private LinearLayout header;
    private String label;


    public ContactAdapter(Context mContext, ArrayList<ItemContact> itemArr) {
        this.mContext = mContext;
        this.itemArr = itemArr;
        this.filterItemArr = itemArr;
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = ((Activity) mContext).getLayoutInflater();
        View view = (View) inflater.inflate(R.layout.item_contact, null);

        header = (LinearLayout) view.findViewById(R.id.section);
        label = itemArr.get(position).getNameContact();
        char firstChar = label.toUpperCase().charAt(0);
        if (position == 0) {
            setSection(header, label);
        } else {
            String preLabel = itemArr.get(position - 1).getNameContact();
            char preFirstChar = preLabel.toUpperCase().charAt(0);
            if (firstChar != preFirstChar) {
                setSection(header, label);
            } else {
                header.setVisibility(View.GONE);
            }
        }

        TextView tvName = (TextView) view.findViewById(R.id.tv_name);
        TextView tvPhoneNumb = (TextView) view.findViewById(R.id.tv_phonenumb);

        tvName.setText(itemArr.get(position).getNameContact());
        tvPhoneNumb.setText(itemArr.get(position).getPhoneNumber()[0]);

        return view;
    }
    private void setSection(LinearLayout header, String label) {
        TextView text = new TextView(mContext);
        header.setBackgroundColor(0xffaabbcc);
        text.setTextColor(Color.WHITE);
        text.setText(label.substring(0, 1).toUpperCase());
        text.setTextSize(20);
        text.setPadding(5, 0, 0, 0);
        text.setGravity(Gravity.CENTER_VERTICAL);
        header.addView(text);
    }

    @Override
    public Filter getFilter() {

        if (filter == null) {
            filter = new CustomFilter();
        }

        return filter;
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        if (sectionIndex == 35) {
            return 0;
        }
        for (int i = 0; i < itemArr.size(); i++) {
            String l = itemArr.get(i).getNameContact();
            char firstChar = l.toUpperCase().charAt(0);
            if (firstChar == sectionIndex) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
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
