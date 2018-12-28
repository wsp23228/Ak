package com.example.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.adapter.ViewPageAdapter;
import com.example.ak.R;
import com.example.view.Basefragment;
import java.util.ArrayList;
import java.util.List;


public class ZhuFragment extends Basefragment {
    private String[] titles;
    private String[] title;
    private List<TextView> list;
    private HorizontalScrollView hs;
    private LinearLayout linearlayout;
    private ViewPager viewpager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        TextView text_View = view.findViewById(R.id.base_title);
        text_View.setText(R.string.app_zhu);
		
        hs = (HorizontalScrollView)view.findViewById(R.id.hs);
        linearlayout = (LinearLayout)view.findViewById(R.id.linearlayout);
        viewpager = (ViewPager)view.findViewById(R.id.viewpager);
		
        //创建数组,存放标题
        titles = new String[] { "头条", "娱乐", "科技" };
        title = new String[] { "tt", "shehui", "gn" };
        viewpager.setAdapter(new ViewPageAdapter(getActivity().getSupportFragmentManager(),title));
        //创建集合,存放textview
        list = new ArrayList<TextView>();
        for (int i = 0; i < titles.length; i++) {
            //创建textview
            TextView textView = new TextView(getActivity());
            textView.setText(titles[i]);
            textView.setTextSize(20);
            textView.setTextColor(Color.rgb(250,250,250));
            textView.setId(i + 20);
            //点击事件
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = view.getId();
                    viewpager.setCurrentItem(id-20);
                }
            });
            //刚进程序第一个默认红色,其他为黑色
            if(i ==0){
                textView.setTextColor(Color.RED);
            }else {
                textView.setTextColor(Color.BLACK);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(10,10,20,40);
            //添加到布局中
            linearlayout.setBackground(getActivity().getDrawable(R.color.app_color));
            linearlayout.addView(textView,layoutParams);
            //添加到集合
            list.add(textView);
        }

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //滑动
            @Override
            public void onPageScrolled(int position, float v, int i1) {
                //list中存的是 textview
                for (int i = 0; i < list.size(); i++) {
                    if(position==i){
                        list.get(i).setTextColor(getResources().getColor(R.color.app_r));
                    }else{
                        list.get(i).setTextColor(getResources().getColor(R.color.app_w));
                    }
                }
                //获取当前的textview
                TextView textView = list.get(position);
                //width是每次滑动的距离
                int width = textView.getWidth()+10;
                //让scrollView滑动   滑动距离是textview之间的间距
                hs.scrollTo(width*position,0);

            }
            //选中
            @Override
            public void onPageSelected(int i) {

            }
            //改变
            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
