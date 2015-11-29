package client.nhom8.com.avatar.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import client.nhom8.com.avatar.MainActivity;
import client.nhom8.com.avatar.R;

/**
 * Created by TooNies1810 on 11/26/15.
 */
public class SettingFragment extends Fragment {
    private View root;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_settings, container, false);
        mContext = getContext();
        initViews();
        return root;
    }

    private void initViews() {
        Button btnLogout = (Button) root.findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)mContext;
                mainActivity.logOut();
            }
        });

    }
}
