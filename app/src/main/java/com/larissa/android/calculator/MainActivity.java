package com.larissa.android.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuProvider;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements MenuProvider {
    private static final String TAG="MainActivity"; // tag string

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 设置菜单 */
        addMenuProvider(this);

        /* 对CalculateInterfaceFragment的类进行托管, 并将其Fragment展示在main_fragment_container所对应的xml中 */
        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_fragment_container, CalculateInterfaceFragment.class,null).commit();
        }
    }

    /* 菜单相关方法 */
    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.activity_main,menu);
    }
    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.btn_hist: // 如果点击的是history
                Intent intent=new Intent(this, HistoryActivity.class);//如果点击菜单,则弹到帮助页面
                startActivity(intent);
                return true;

            default:
                return true;
        }
    }
}