package com.base.navigationview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.main);
        initView ();
    }

    private void initView() {

        mDrawer = (DrawerLayout) findViewById (R.id.drawer);

        NavigationView navigationView = (NavigationView) findViewById (R.id.nv);
        //防止图片灰色
        navigationView.setItemIconTintList (null);

        //头布局
        View headerView = navigationView.getHeaderView (0);
        ImageView imageView = (ImageView) headerView.findViewById (R.id.iv);
        imageView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Toast.makeText (MainActivity.this, "我是头布局的图片", Toast.LENGTH_LONG).show ();
            }
        });


        //界面初始化的时候根据需求隐藏指定item
        for (int i = 0; i < navigationView.getMenu ().size (); i++) {
            int id = navigationView.getMenu ().getItem (i).getItemId ();
            switch (id) {
                case R.id.favorite:
                    Log.e (TAG, "initView: " + "favorite");
                    navigationView.getMenu ().getItem (i).setVisible (false);
                    break;
            }
        }
        //界面初始化的时候根据需求移除指定的Group
        navigationView.getMenu ().removeGroup (R.id.g2);

        //设置某个Group是否可点击 false:不可点击(如果设置为false，这个Group所有的item将不可点击) true:可点击
        navigationView.getMenu ().setGroupEnabled (R.id.g1, true);

        //item布局
        navigationView.setNavigationItemSelectedListener (new NavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //item被选中 和return true配合可以把item变成被选中状态
                item.setCheckable (true);
                switch (item.getItemId ()) {
                    case R.id.favorite:
                        break;
                    case R.id.wallet:
                        //点击重新设置item的文本内容
                        item.setTitle ("我是item-wallet-2");
                        //关闭导航菜单-----1. mDrawer.closeDrawers ()      2. mDrawer.closeDrawers (GravityCompat.STAR)
                        //mDrawer.closeDrawers ();
                        mDrawer.closeDrawer (GravityCompat.START);
                        break;
                    case R.id.photo:
                        break;
                    case R.id.file:
                        Toast.makeText (MainActivity.this, "我是item-file", Toast.LENGTH_LONG).show ();
                        item.setVisible (false);
                        break;
                }
                //
                return true;
            }
        });


    }
}
