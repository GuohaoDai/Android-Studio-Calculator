package com.larissa.android.calculator;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class CalculatorViewModel extends ViewModel{
    private static final String TAG = "CalculatorViewModel";
    public String expression = "0"; //用于保存计算器表达式内容
    public int state_flag = 1;
    public String low, up, var_expression, acc_num;

    public List<String> expression_list = new ArrayList<String>();  // 使用动态数组保存历史表达式
    public List<String> res_list = new ArrayList<String>();  // 保存计算结果


    public CalculatorViewModel(){
        Log.d(TAG, "QuizViewModel instance created");
    }

    @Override
    protected void onCleared(){
        super.onCleared();
        Log.d(TAG, "QuizViewModel instance about to be destroyed");
    }
}