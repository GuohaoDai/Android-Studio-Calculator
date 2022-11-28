package com.larissa.android.calculator;

import android.util.Log;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class HistoryViewModel extends ViewModel {
    public List<String> expression_list = new ArrayList<String>();  // 使用动态数组保存历史表达式
    public List<String> res_list = new ArrayList<String>();  // 保存计算结果

    public HistoryViewModel(){
        for(int i=0;i<20;i++){
            expression_list.add("1+" + String.valueOf(i));
            res_list.add(String.valueOf(1+i));
        }
    }
}
