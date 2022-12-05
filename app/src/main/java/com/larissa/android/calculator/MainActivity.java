package com.larissa.android.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;



public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity"; // tag string

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        /* 对CalculateInterfaceFragment的类进行托管, 并将其Fragment展示在main_fragment_container所对应的xml中 */
//        if(savedInstanceState==null){
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.main_fragment_container, CalculateInterfaceFragment.class,null).commit();
//        }
    }

}