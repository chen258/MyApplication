package com.example.chasen.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chasen.myapplication.model.MyModelBase;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by chasen on 2017/5/25.
 */

public class ListBaseAdapter extends BaseAdapter {
    private List<MyModelBase> list ;
    private Context context;

    public ListBaseAdapter(List<MyModelBase> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.listitem,null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.item_iv);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.item_tv);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MyModelBase modelBase = list.get(position);
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        final int screenWidth = wm.getDefaultDisplay().getWidth();
        Picasso.with(context).load(modelBase.getUrl()).placeholder(R.drawable.ic_launcher).transform(new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                Matrix matrix = new Matrix();
                int oldwidth = source.getWidth();
                int oldheight = source.getHeight();
                Log.d("chasen",oldheight+" "+oldwidth);
                float newwidth =  screenWidth;
                float newheight = context.getResources().getDimension(R.dimen.image_view_height);
                Log.d("chasen",newheight+" "+newwidth);
                float scaleW = 1f;
                float scaleH = newheight/oldheight;
                matrix.postScale(scaleW,scaleH);
                Bitmap bitmap = Bitmap.createBitmap(source,0,0,oldwidth,oldheight,matrix,true);
                if(bitmap!=source){
                    source.recycle();
                }
                return bitmap;
            }

            @Override
            public String key() {
                return "newsImage";
            }
        }).into(viewHolder.imageView);
        viewHolder.textView.setText(modelBase.getTitle());
        return convertView;
    }

    static class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
