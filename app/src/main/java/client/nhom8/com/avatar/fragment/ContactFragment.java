package client.nhom8.com.avatar.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import client.nhom8.com.avatar.R;
import client.nhom8.com.avatar.adapter.ContactAdapter;

/**
 * Created by TooNies1810 on 11/26/15.
 */
public class ContactFragment extends Fragment {
    private View root;
    private Context mContext;

    private ListView lvContact;
    private ContactAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getContext();
        mAdapter = new ContactAdapter(getActivity());
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
    }
}
