package client.nhom8.com.avatar.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

import client.nhom8.com.avatar.MainActivity;
import client.nhom8.com.avatar.R;
import client.nhom8.com.avatar.database.UserData;

/**
 * Created by TooNies1810 on 11/26/15.
 */
public class SettingFragment extends Fragment {
    private View root;
    private Context mContext;
    private UserData userDb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_settings, container, false);
        mContext = getContext();

        // init user data
        userDb = new UserData(mContext);

        initViews();
        return root;
    }

    private void initViews() {
        Button btnLogout = (Button) root.findViewById(R.id.btn_logout);
        TextView tvName = (TextView) root.findViewById(R.id.tv_nameUser);
        TextView tvUid= (TextView) root.findViewById(R.id.tv_uid);
        TextView tvPass = (TextView) root.findViewById(R.id.tv_passUser);

        HashMap<String, String> user = userDb.getUser();

        tvUid.setText(user.get("uid"));
        tvName.setText(user.get("username"));
        tvPass.setText(user.get("password"));

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)mContext;
                mainActivity.logOut();
            }
        });

    }
}
