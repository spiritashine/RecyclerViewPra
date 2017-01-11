package com.hujie.recyclerviewpra;

import android.os.Handler;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout_a);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_a);
        ArrayList<ContentFragment> fragments=new ArrayList<>();
        for (int i=0;i<8;i++){
            fragments.add(new ContentFragment());
        }
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),this,fragments));


    }
}
