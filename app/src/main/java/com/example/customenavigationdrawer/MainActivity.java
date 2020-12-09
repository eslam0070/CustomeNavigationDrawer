package com.example.customenavigationdrawer;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    SNavigationDrawer navigationDrawer;
    FrameLayout frameLayout;
    Class aClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Locale.getDefault().getDisplayLanguage(Locale.forLanguageTag("ar"));
        setContentView(R.layout.activity_main);

        initView();
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("الرئيسة",R.drawable.news_bg));
        items.add(new MenuItem("البريد الوارد",R.drawable.message_bg));

        navigationDrawer.setMenuItemList(items);
        navigationDrawer.setAppbarTitleTV("الرئيسة");

        aClass = OneFragment.class;
        openFragment();
        setOnMenuItemClick();
        setDrawerListener();
    }

    private void setDrawerListener() {
        navigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {
            @Override
            public void onDrawerOpening() {

            }

            @Override
            public void onDrawerClosing() {
                openFragment();
            }

            @Override
            public void onDrawerOpened() {

            }

            @Override
            public void onDrawerClosed() {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

    }

    private void setOnMenuItemClick() {
        navigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                switch (position){
                    case 0:
                        aClass = OneFragment.class;
                        break;
                    case 1:
                        aClass = TwoFragment.class;
                        break;
                }
            }
        });
    }

    private void openFragment() {
        try {
            Fragment fragment = (Fragment) aClass.newInstance();
            getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out).replace(R.id.frame_layout,fragment)
            .commit();
        }catch (IllegalAccessException e){e.printStackTrace();}
        catch (InstantiationException e){e.printStackTrace();}
    }

    private void initView() {
        navigationDrawer = (SNavigationDrawer) findViewById(R.id.navigation_drawer);
        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
    }
}