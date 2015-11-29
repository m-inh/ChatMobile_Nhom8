package client.nhom8.com.avatar;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import java.util.ArrayList;

import client.nhom8.com.avatar.adapter.MyViewpagerAdapter;
import client.nhom8.com.avatar.fragment.ContactFragment;
import client.nhom8.com.avatar.fragment.MessageFragment;
import client.nhom8.com.avatar.fragment.SettingFragment;
import client.nhom8.com.avatar.fragment.SocialFragment;
import client.nhom8.com.avatar.session.LoginSession;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private MyViewpagerAdapter mPagerAdapter;

    private LoginSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init session
        session = new LoginSession(this);
        if (!session.isLogin()){
            logOut();
        }

        // Set up the ViewPager with the pager adapter.
        ArrayList<Fragment> fragmentArr = new ArrayList<>();
        fragmentArr.add(new ContactFragment());
        fragmentArr.add(new MessageFragment());
        fragmentArr.add(new SocialFragment());
        fragmentArr.add(new SettingFragment());

        ArrayList<String> titleFragmentArr = new ArrayList<>();
        titleFragmentArr.add("Contact");
        titleFragmentArr.add("Message");
        titleFragmentArr.add("Social");
        titleFragmentArr.add("Settings");
        mPagerAdapter = new MyViewpagerAdapter(getSupportFragmentManager(), fragmentArr, titleFragmentArr);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_container);
        mViewPager.setAdapter(mPagerAdapter);

        // set up tabs with viewpager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    public void logOut(){
        // set session la false, chuyen den Login activity
        session.setLogin(false);

        // chuyen den Login activiy
        Intent mIntent = new Intent(this,LoginActivity.class);
        startActivity(mIntent);
        finish();
    }

}
