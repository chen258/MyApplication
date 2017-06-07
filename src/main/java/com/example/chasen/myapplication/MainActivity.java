package com.example.chasen.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


import static com.example.chasen.myapplication.R.id.tablayout;

public class MainActivity extends AppCompatActivity {
    private NavigationView  navigationView ;
    private NewsFragmentPagerAdapter adapter;
    private List<Fragment> list_frag;
    private List<String> list_tabName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initNaviView();
        initTab();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //注册EventBus
        EventBus.getDefault().register(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        Log.d("chasen","getEvent");
    };
    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void initTab() {
        TabLayout tablayout = (TabLayout) findViewById(R.id.tablayout);
        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
        HotNewsFragment hotFrag = new HotNewsFragment();
        SportFragment sportFrag = new SportFragment();
        JokeFragment jokeFrag = new JokeFragment();
        list_tabName = new ArrayList<>();
        list_frag = new ArrayList<>();
        list_frag.add(hotFrag);
        list_frag.add(sportFrag);
        list_frag.add(jokeFrag);
        list_tabName.add("热点");
        list_tabName.add("体育");
        list_tabName.add("Joke");
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.addTab(tablayout.newTab().setText(list_tabName.get(0)));
        tablayout.addTab(tablayout.newTab().setText(list_tabName.get(1)));
        tablayout.addTab(tablayout.newTab().setText(list_tabName.get(2)));
        adapter = new NewsFragmentPagerAdapter(this.getSupportFragmentManager(),list_frag,list_tabName);
        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);
    }

    void initNaviView(){
        navigationView = (NavigationView) findViewById(R.id.design_navigation_view);
        View view = navigationView.getHeaderView(0);
        ImageView iv = (ImageView) view.findViewById(R.id.header_iv);
        if(iv == null) return;
        Picasso.with(this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1494485084884&di=5ab8d0e25b87b112fd6e828818773e83&imgtype=0&src=http%3A%2F%2Fi-3.yxdown.com%2F2016%2F3%2F11%2F37b9fd23-c5a0-41f9-8d1f-b5d214908247.jpg")
                .transform(new Transformation() {
                    @Override
                    public Bitmap transform(Bitmap source) {
                        int size = Math.min(source.getWidth(), source.getHeight());
                        int x = (source.getWidth() - size) / 2;
                        int y = (source.getHeight() - size) / 2;

                        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
                        if (squaredBitmap != source) {
                            source.recycle();
                        }

                        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

                        Canvas canvas = new Canvas(bitmap);
                        Paint paint = new Paint();
                        BitmapShader shader = new BitmapShader(squaredBitmap,
                                BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
                        paint.setShader(shader);
                        paint.setAntiAlias(true);

                        float r = size / 2f;
                        canvas.drawCircle(r, r, r, paint);

                        squaredBitmap.recycle();
                        return bitmap;
                    }

                    @Override
                    public String key() {
                        return "circle";
                    }
                }).into(iv);
    }

}
