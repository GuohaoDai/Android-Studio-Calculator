package com.larissa.android.calculator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryListFragment extends Fragment {
    private static final String TAG = "HistoryListFragment";
    private HistoryViewModel viewmodel;
    private RecyclerView recyclerView;

    /* 封装容器:用于单独控制1个RecycleItemView */
    private class HistoryItemHolder extends RecyclerView.ViewHolder{
        private Button ButtonExpression; //计算表达式
        private Button ButtonResult; //计算结果

        //构造函数: 用于定义Expression和Result的Button控件
        public HistoryItemHolder(View view){
            super(view);
            ButtonExpression=view.findViewById(R.id.history_expression);
            ButtonResult=view.findViewById(R.id.history_result);
        }

        //往Expression和Result控件填充显示数据
        public void bind(String Expression, String Result){
            ButtonExpression.setText(Expression);
            ButtonResult.setText(Result);
        }
    }

    /* HistoryItemAdapter: 用于创建HistoryItemHolder对象，以及给这些视图写入数据 */
    private class HistoryItemAdapter extends RecyclerView.Adapter<HistoryItemHolder>{
        private List<String> expression_list, res_list;

        public HistoryItemAdapter(List<String> expression_list, List<String> res_list){
            this.expression_list=expression_list;
            this.res_list=res_list;
        }

        /* 当RecycleView创造新的ViewHolder时, 会调用该方法, 将history_item填充入RecycleView里 */
        @NonNull
        @Override
        public HistoryItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=getLayoutInflater().inflate(R.layout.history_item, parent,false);
            return new HistoryItemHolder(view);
        }

        /* 将HistoryItemHolder和数据关联, 将数据填入HistoryItemHolder中 */
        @Override
        public void onBindViewHolder(@NonNull HistoryItemHolder holder, int position) {
            String expression=expression_list.get(position);
            String result=res_list.get(position);
            holder.bind(expression, result);
        }

        /* 设置完Adapter后, 会自动重复调用getItemCount()次的onBindViewHolder(), position会从0开始  */
        @Override
        public int getItemCount() {
            return expression_list.size();
        }
    }

    //定义viewmodel
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        viewmodel=new ViewModelProvider(this).get(HistoryViewModel.class);
    }

    //将fragment_history_list页面填充进被托管的页面, 在进入该托管页面时被调用
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater,container,savedInstanceState);
        View view=inflater.inflate(R.layout.fragment_history_list,container,false);
        return view;
    }

    //在被填充的页面中, 给里面的RecycleView设置管理器, 在托管页面被填充后调用
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //view是从onCreateView中返回的
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.history_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        updateUI(viewmodel.expression_list, viewmodel.res_list);
    }

    private void updateUI(List<String>expression_list, List<String>res_list){
        HistoryItemAdapter adapter=new HistoryItemAdapter(expression_list, res_list);
        recyclerView.setAdapter(adapter);
    }
}
