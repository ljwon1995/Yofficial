package com.example.yofficial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class VideoAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    private List<VideoItem> videoList = null;
    private ArrayList<VideoItem> listview;

    public VideoAdapter(Context context, List<VideoItem> videoList) {
        this.context = context;
        this.videoList = videoList;
        inflater = LayoutInflater.from(context);
        this.listview = new ArrayList<VideoItem>();
        this.listview.addAll(videoList);
    }

    @Override
    public int getCount(){
        return videoList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final ViewHolder holder;
        final VideoItem potion = videoList.get(position);

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.v_item, null);

            // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
            holder.iv_icon = (ImageView) convertView.findViewById(R.id.imageView1) ;
            holder.tv1 = (TextView) convertView.findViewById(R.id.txt_v_t) ;
            holder.tv2 = (TextView) convertView.findViewById(R.id.txt_v_u) ;
            holder.tv3= (TextView) convertView.findViewById(R.id.txt_v_n) ;
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv1.setText(potion.v_title);
        holder.tv2.setText(potion.v_uploader);
        holder.tv3.setText(potion.view_num);
        holder.iv_icon.setImageDrawable(potion.getIcon());

        return convertView;
    }

    public class ViewHolder {
        TextView tv1;
        TextView tv2;
        TextView tv3;
        ImageView iv_icon;
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public VideoItem getItem(int position) {
        return videoList.get(position) ;
    }





    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        videoList.clear();
        if (charText.length() == 0) {
            videoList.addAll(listview);
        } else {
            for (VideoItem potion : listview) {
                if (potion.getV_title().toLowerCase(Locale.getDefault()).contains(charText)) {
                    videoList.add(potion);
                }
            }
        }
        notifyDataSetChanged();
    }

}
