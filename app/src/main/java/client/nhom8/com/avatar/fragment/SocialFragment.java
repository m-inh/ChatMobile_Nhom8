package client.nhom8.com.avatar.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import client.nhom8.com.avatar.R;

/**
 * Created by TooNies1810 on 11/26/15.
 */
public class SocialFragment extends Fragment {
    private View root;
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_social, container, false);

        initViews();
        return root;
    }

    private void initViews() {
    }
}
