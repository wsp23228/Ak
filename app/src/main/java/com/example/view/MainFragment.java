package com.example.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adapter.Myadapter;
import com.example.ak.MainActivity;
import com.example.bean.Userbean;
import com.example.net.Getasynctask;
import com.example.xlistview.XListView;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainFragment extends Fragment implements XListView.IXListViewListener {

    private XListView list_view;
    private Myadapter myAdapte;
    private String title;

    public static Fragment getinstance(String title){
        //创建Fragment1
        MainFragment fragment1 = new MainFragment();
        //创建bundle
        Bundle bundle = new Bundle();
        //添加值
        bundle.putString("title",title);
        //把值添加到Arguments中
        fragment1.setArguments(bundle);
        return fragment1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获取Arguments
        Bundle arguments = getArguments();
        //获取值
        title = arguments.getString("title");
        //创建textview
        list_view = new XListView(getActivity());
        list_view.setXListViewListener(MainFragment.this);
        list_view.setPullRefreshEnable(true);
        list_view.setPullLoadEnable(true);
        new MyAsyn().execute();
        //设置值
        return list_view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
    public void getloadrefresh(){
        list_view.stopRefresh();
        list_view.stopLoadMore();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd  HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        list_view.setRefreshTime(format.format(date));
    }

    class  MyAsyn extends AsyncTask<Integer,Integer,String> {
        //耗时的操作
        @Override
        protected String doInBackground(Integer... integers) {
            String url = "http://result.eolinker.com/k2BaduF2a6caa275f395919a66ab1dfe4b584cc60685573?uri="+title+"";
            String s = Getasynctask.getastask(url,"GET",getActivity());
            Log.i("sss", "doInBackground: "+s);
            return s;
        }

        //当后台操作结束时，此方法将会被调用，计算结果将做为参数传递到此方法中，直接将结果显示到UI组件上
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                Gson gson = new Gson();
                Userbean userbean = gson.fromJson(s, Userbean.class);
                if (userbean != null) {
                    myAdapte = new Myadapter(getActivity(), userbean);
                    list_view.setAdapter(myAdapte);
                }
            }
        }
    }
}
