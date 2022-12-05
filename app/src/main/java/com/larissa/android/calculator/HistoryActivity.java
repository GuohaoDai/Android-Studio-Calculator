//package com.larissa.android.calculator;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.constraintlayout.widget.ConstraintLayout;
//import androidx.lifecycle.ViewModelProvider;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//public class HistoryActivity extends AppCompatActivity {
//
//    private static final String TAG="HistoryActivity";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_history);
//        Log.d(TAG,"onCreate() called");
//
////        int num_of_hist = getIntent().getIntExtra("HistNum", 0); //计算器的历史计算总数
////
////        /* 展示历史计算表达式 */
////        for(int i=0; i<num_of_hist; i++){
////            // 历史计算表达式
////            String hist_expression =
////                    getIntent().getStringExtra("Expression" + String.valueOf(i)) +
////                    " = " +
////                    getIntent().getStringExtra("Result" + String.valueOf(i));
////            // 动态添加控件 展示表达式
////            dynamic_add(hist_expression);
////        }
//
//        //如果是第一次创造该activity
//        if(savedInstanceState==null){
//            //对HistoryListFragment的类进行托管, 并将其Fragment展示在fragment_history_container所对应的xml中
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.fragment_history_container, HistoryListFragment.class,null).commit();
//        }
//    }
//
////    /* 在LinearLayout里添加textview控件, 展示内容为string */
////    protected void dynamic_add(String string){
////
////        // 获取LinearLayout控件
////        LinearLayout rootLayout = (LinearLayout)findViewById(R.id.Linear_Hist);
////        TextView textView = new TextView(this);
////
////        textView.setText(string);
////        textView.setTextSize(25);
////
////        // 获取父布局
////        LinearLayout.LayoutParams Layout_parent_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
////
////        // 设置父布局margin
////        Layout_parent_params.setMargins(50,30,0,0);
////
////        // 在Linearlayout里添加TexView控件
////        rootLayout.addView(textView, Layout_parent_params);
////
////    }
//
//    // 重写生命周期相关方法
//    @Override
//    public void onStart(){
//        super.onStart();
//        Log.d(TAG,"onStart() called");
//    }
//    @Override
//    public void onResume(){
//        super.onResume();
//        Log.d(TAG,"onResume() called");
//    }
//    @Override
//    public void onPause(){
//        super.onPause();
//        Log.d(TAG,"onPause() called");
//    }
//    @Override
//    public void onStop(){
//        super.onStop();
//        Log.d(TAG,"onStop() called");
//    }
//    @Override
//    public void onDestroy(){
//        super.onDestroy();
//        Log.d(TAG,"onDestroy() called");
//    }
//
//}