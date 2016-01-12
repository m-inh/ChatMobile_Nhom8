package client.nhom8.com.avatar.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import client.nhom8.com.avatar.R;
import client.nhom8.com.avatar.adapter.ContactAdapter;
import client.nhom8.com.avatar.database.ContactManager;
import client.nhom8.com.avatar.models.ItemContact;

/**
 * Created by TooNies1810 on 11/26/15.
 */
public class ContactFragment extends Fragment {
    private View root;
    private Context mContext;

    private EditText edtSearch;
    private ListView lvContact;
    private ContactAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getContext();
        mAdapter = new ContactAdapter(mContext, initDataContact());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_contact, container, false);
        initViews();
        return root;
    }

    private void initViews() {
        lvContact = (ListView) root.findViewById(R.id.lv_contact);
        lvContact.setAdapter(mAdapter);

        edtSearch = (EditText) root.findViewById(R.id.edt_search);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ContactFragment.this.mAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private ArrayList<ItemContact> initDataContact() {
//        itemArr = new ArrayList<>();
//        itemArr.add(new ItemContact("Tran Van Tu", new String[]{"01234123123"}));
//        itemArr.add(new ItemContact("Tran Van Tu", new String[]{"01234123123"}));
//        itemArr.add(new ItemContact("Tran Van Tu", new String[]{"01234123123"}));
//        itemArr.add(new ItemContact("Tran Van Tu", new String[]{"01234123123"}));
        ArrayList<ItemContact> itemArr = new ArrayList<ItemContact>();
        ContactManager contactMgr = new ContactManager(mContext);
        itemArr = contactMgr.getItemContactArr();
        return itemArr;
    }
}
