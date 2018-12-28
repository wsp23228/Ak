package com.example.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.view.MainFragment;

public class ViewPageAdapter extends FragmentPagerAdapter {
    //通过网页后缀的数量来创建多少个Fragmnet
    String[] titles;  //网页后缀
    public ViewPageAdapter(FragmentManager fm,String[] titles) {
        super(fm);
        this.titles=titles;
    }
    //当前fragment
    @Override
    public Fragment getItem(int i) {
        return MainFragment.getinstance(titles[i]);
    }
    //fragment 数量
    @Override
    public int getCount() {
        return titles.length;
    }
}
