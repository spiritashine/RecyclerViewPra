package com.hujie.recyclerviewpra;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioGroup;

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

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.navigation);
        //通过BottomSheetBehavior的from方法设置底部导航
        final BottomSheetBehavior bottomSheetBehavior=BottomSheetBehavior.from(radioGroup);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        MyFloatingBehavior<FloatingActionButton> myFloatingBehavior=MyFloatingBehavior.from(floatingActionButton);
        //回调接口，通过FloatingActionButton的属性设置底部导航
        myFloatingBehavior.setOnStateChangeListener(new MyFloatingBehavior.OnStateChangeListener() {
            @Override
            public void onStateChange(boolean isShow) {
                if (isShow){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            }
        });
    }
}
