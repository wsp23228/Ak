package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ak.R;
import com.example.bean.Userbean;
import com.nostra13.universalimageloader.core.ImageLoader;

public class Myadapter extends BaseAdapter {
   private Context context;
   private Userbean userbean;
    //构造方法
    public Myadapter(Context context, Userbean userbean) {
        this.context = context;
        this.userbean = userbean;
    }
    //统计总条目数量
    @Override
    public int getCount() {
        return userbean.getResult().getData().size();
    }
    //当前显示的条目
    @Override
    public Object getItem(int position) {
        return userbean.getResult().getData().get(position);
    }
    //获取当前条目id
    @Override
    public long getItemId(int position) {
        return position;
    }
    //加载视图
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            //加载布局
            convertView = View.inflate(context,R.layout.list_layout,null);
            holder=  new ViewHolder();
            TextView textView1 = holder.textView1 = convertView.findViewById(R.id.text_name);
            ImageView imageView = holder.imageView = convertView.findViewById(R.id.im_icon);
            convertView.setTag(holder);
        }  else {   //else里面说明，convertView已经被复用了，说明convertView中已经设置过tag了，即holder
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView1.setText(userbean.getResult().getData().get(position).getAuthor_name());
        ImageLoader.getInstance().displayImage(userbean.getResult().getData().get(position).getThumbnail_pic_s(),holder.imageView);
        // ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(),holder.imageView);
        return convertView;
    }
    private class ViewHolder {
        TextView textView1;
        ImageView imageView;
    }
}
