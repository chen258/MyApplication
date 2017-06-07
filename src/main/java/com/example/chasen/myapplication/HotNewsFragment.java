package com.example.chasen.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.chasen.myapplication.model.MyModelBase;
import com.example.chasen.myapplication.model.NewsMyModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by chasen on 2017/5/25.
 */

public class HotNewsFragment extends Fragment {
    List<MyModelBase> list = new ArrayList<MyModelBase>();
    ListBaseAdapter adapter;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.hotlayout,null);
        final SlideCutListView listView = (SlideCutListView) convertView.findViewById(R.id.listview);
        listView.setRemoveListener(new SlideCutListView.RemoveListener() {
            @Override
            public void removeItem(SlideCutListView.RemoveDirection direction, int position) {
                Log.d(TAG,"removeItem");
                if(list!=null&&adapter!=null){
                    list.remove(position);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        NewsMyModel model = new NewsMyModel();
        model.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494485084884&di=5ab8d0e25b87b112fd6e828818773e83&imgtype=0&src=http%3A%2F%2Fi-3.yxdown.com%2F2016%2F3%2F11%2F37b9fd23-c5a0-41f9-8d1f-b5d214908247.jpg");
        model.setTitle("漂亮的图");
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        adapter = new ListBaseAdapter(list,getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG,"AAAAAAAAAAAAAA");
            }
        });
        return convertView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
