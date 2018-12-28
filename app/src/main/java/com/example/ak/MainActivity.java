package com.example.ak;

import android.net.wifi.hotspot2.pps.HomeSp;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.adapter.ViewPageAdapter;
import com.example.fragment.GeFragment;
import com.example.fragment.GnaFragment;
import com.example.fragment.ShuFragment;
import com.example.fragment.YingFragment;
import com.example.fragment.ZhuFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RadioGroup radio_group;
    private FragmentManager manager;
    private List<Fragment> fragments;
    private ZhuFragment zhuFragment;
    private FragmentTransaction fragmentTransaction;
    private GnaFragment gnaFragment;
    private YingFragment yingFragment;
    private GeFragment geFragment;
    private ShuFragment shuFragment;
    private DrawerLayout drawer_layout;
    private LinearLayout lauout02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radio_group = findViewById(R.id.radio_group);
        drawer_layout = findViewById(R.id.drawer_layout);
        lauout02 = findViewById(R.id.lauout02);
        lauout02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.closeDrawers();
            }
        });
        manager = getSupportFragmentManager();
        fragments = new ArrayList<>();
        //设置默认fragmnet
        radio_group.check(R.id.Ra1);
        zhuFragment = new ZhuFragment();
        fragments.add(zhuFragment);
        hideotherfragment(zhuFragment,true);
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.Ra1:
                        hideotherfragment(zhuFragment,false);
                        break;
                    case R.id.Ra2:
                        if(gnaFragment == null){
                            gnaFragment = new GnaFragment();
                            fragments.add(gnaFragment);
                            hideotherfragment(gnaFragment,true);
                        }else{
                            hideotherfragment(gnaFragment,false);
                        }
                        break;
                    case R.id.Ra3:
                        if(yingFragment == null){
                            yingFragment = new YingFragment();
                            fragments.add(yingFragment);
                            hideotherfragment(yingFragment,true);
                        }else{
                            hideotherfragment(yingFragment,false);
                        }
                        break;
                    case R.id.Ra4:
                        if(shuFragment == null){
                            shuFragment = new ShuFragment();
                            fragments.add(shuFragment);
                            hideotherfragment(shuFragment,true);
                        }else{
                            hideotherfragment(shuFragment,false);
                        }
                        break;
                    case R.id.Ra5:
                        if(geFragment == null){
                            geFragment = new GeFragment();
                            fragments.add(geFragment);
                            hideotherfragment(geFragment,true);
                        }else{
                            hideotherfragment(geFragment,false);
                        }
                        break;
                }
            }
        });
   }
   private void hideotherfragment(Fragment showfragmnet,boolean zhen){
       fragmentTransaction = manager.beginTransaction();
       if(zhen){
          fragmentTransaction.add(R.id.frame_layout,showfragmnet);
       }
       for (Fragment fragment: fragments
               ) {
           if(showfragmnet.equals(fragment)){
               fragmentTransaction.show(fragment);
           }else{
               fragmentTransaction.hide(fragment);
           }
       }
       fragmentTransaction.commit();
   }
}
