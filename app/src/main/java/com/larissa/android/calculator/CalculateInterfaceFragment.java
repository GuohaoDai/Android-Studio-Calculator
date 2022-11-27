package com.larissa.android.calculator;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class CalculateInterfaceFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "CalculateInterfaceFragment";
    private static final String KEY_expression="KEY_expression"; // 标识已输入算数表达式的键

    /* 计算器相关参数  */
    private String expression = "0"; //用于保存计算器表达式内容
    private int state_flag=1; //1为平常模式， 2时要求输入积分下限，3时输入上限, 4时输入积分精度
    private String low, up, var_expression, acc_num;

    /* 定义 按钮对象 和 textview对象 */
    private Button btn_num1, btn_num2, btn_num3, btn_num4, btn_num5, btn_num6, btn_num7, btn_num8, btn_num9, btn_num0;
    private Button btn_add, btn_sub, btn_mul, btn_div, btn_point, btn_clr, btn_eql, btn_lb, btn_rb, btn_kramp, btn_pow, btn_cal, btn_var;
    private ImageButton btn_back;
    private TextView text_expression, hint_info; //显示屏对应的Textview控件

    private int screenOrientation;// 屏幕方向

    //保存数据用的viewmodel类
    private CalculatorViewModel viewmodel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate() called");
    }

    /* 展示Fragment的Layout */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater,container,savedInstanceState);
        Log.d(TAG,"onCreateView() called");

        //设置要展示的Fragment页面, 根据不同方向设置为计算显示页面
        screenOrientation=getResources().getConfiguration().orientation; // 方向
        View view;
        if(screenOrientation==Configuration.ORIENTATION_PORTRAIT)
            view=inflater.inflate(R.layout.fragment_calculate, container,false);
        else if(screenOrientation==Configuration.ORIENTATION_LANDSCAPE)
            view=inflater.inflate(R.layout.fragment_calculate_land, container,false);
        else
            view=null;
        return view;
    }

    /* 对Fragment View里的内容进行逻辑操作 */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG,"onViewCreated() called");

        state_flag = 1;
        viewmodel = new ViewModelProvider(this).get(CalculatorViewModel.class);//viewmodel

        /* 根据不同屏幕方向, 用ID获取控件 */
        if(screenOrientation== Configuration.ORIENTATION_PORTRAIT){
            btn_num1 = (Button) view.findViewById(R.id.btn_num1);
            btn_num2 = (Button) view.findViewById(R.id.btn_num2);
            btn_num3 = (Button) view.findViewById(R.id.btn_num3);
            btn_num4 = (Button) view.findViewById(R.id.btn_num4);
            btn_num5 = (Button) view.findViewById(R.id.btn_num5);
            btn_num6 = (Button) view.findViewById(R.id.btn_num6);
            btn_num7 = (Button) view.findViewById(R.id.btn_num7);
            btn_num8 = (Button) view.findViewById(R.id.btn_num8);
            btn_num9 = (Button) view.findViewById(R.id.btn_num9);
            btn_num0 = (Button) view.findViewById(R.id.btn_num0);
            btn_add = (Button) view.findViewById(R.id.btn_add);
            btn_sub = (Button) view.findViewById(R.id.btn_subtract);
            btn_mul = (Button) view.findViewById(R.id.btn_multiply);
            btn_div = (Button) view.findViewById(R.id.btn_divide);
            btn_point = (Button) view.findViewById(R.id.btn_point);
            btn_back = (ImageButton) view.findViewById(R.id.btn_backspace);
            btn_clr = (Button) view.findViewById(R.id.btn_clear);
            btn_eql = (Button) view.findViewById(R.id.btn_equal);
            btn_lb = (Button) view.findViewById(R.id.btn_left_bracket);
            btn_rb = (Button) view.findViewById(R.id.btn_right_bracket);
            text_expression = (TextView) view.findViewById(R.id.text_expression);
        }
        else if(screenOrientation==Configuration.ORIENTATION_LANDSCAPE){ //横向
            btn_num1 = (Button) view.findViewById(R.id.btn_num1_l);
            btn_num2 = (Button) view.findViewById(R.id.btn_num2_l);
            btn_num3 = (Button) view.findViewById(R.id.btn_num3_l);
            btn_num4 = (Button) view.findViewById(R.id.btn_num4_l);
            btn_num5 = (Button) view.findViewById(R.id.btn_num5_l);
            btn_num6 = (Button) view.findViewById(R.id.btn_num6_l);
            btn_num7 = (Button) view.findViewById(R.id.btn_num7_l);
            btn_num8 = (Button) view.findViewById(R.id.btn_num8_l);
            btn_num9 = (Button) view.findViewById(R.id.btn_num9_l);
            btn_num0 = (Button) view.findViewById(R.id.btn_num0_l);
            btn_add = (Button) view.findViewById(R.id.btn_add_l);
            btn_sub = (Button) view.findViewById(R.id.btn_subtract_l);
            btn_mul = (Button) view.findViewById(R.id.btn_multiply_l);
            btn_div = (Button) view.findViewById(R.id.btn_divide_l);
            btn_point = (Button) view.findViewById(R.id.btn_point_l);
            btn_back = (ImageButton) view.findViewById(R.id.btn_backspace_l);
            btn_clr = (Button) view.findViewById(R.id.btn_clear_l);
            btn_eql = (Button) view.findViewById(R.id.btn_equal_l);
            btn_lb = (Button) view.findViewById(R.id.btn_left_bracket_l);
            btn_rb = (Button) view.findViewById(R.id.btn_right_bracket_l);
            text_expression = (TextView) view.findViewById(R.id.text_expression_l);

            btn_kramp = (Button) view.findViewById(R.id.btn_kramp_l);
            btn_pow = (Button) view.findViewById(R.id.btn_pow_l);
            btn_cal = (Button) view.findViewById(R.id.btn_cal_l);
            btn_var = (Button) view.findViewById(R.id.btn_var_l);
            hint_info = (TextView) view.findViewById(R.id.text_info_l);
        }

//        /* 如果有数据被保存过, 则获取 */
//        if(savedInstanceState!=null){
//            expression=savedInstanceState.getString(KEY_expression,"");
//        }

        /* 按钮控件设置事件监听,以进行响应操作. 监听函数为重写的onClick */
        btn_num1.setOnClickListener(this);
        btn_num2.setOnClickListener(this);
        btn_num3.setOnClickListener(this);
        btn_num4.setOnClickListener(this);
        btn_num5.setOnClickListener(this);
        btn_num6.setOnClickListener(this);
        btn_num7.setOnClickListener(this);
        btn_num8.setOnClickListener(this);
        btn_num9.setOnClickListener(this);
        btn_num0.setOnClickListener(this);

        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_div.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_clr.setOnClickListener(this);
        btn_eql.setOnClickListener(this);
        btn_lb.setOnClickListener(this);
        btn_rb.setOnClickListener(this);

        if(screenOrientation==Configuration.ORIENTATION_LANDSCAPE)
        {
            btn_kramp.setOnClickListener(this);
            btn_pow.setOnClickListener(this);
            btn_cal.setOnClickListener(this);
            btn_var.setOnClickListener(this);
        }

        SetCalAttribute();//设置属性
        text_expression.setText(expression); //在显示屏上显示计算表达式
    }

    /* 定义一个监听事件对象, 里面定义按键后的业务逻辑 */
    @Override
    public void onClick(View btn_view) {
        try{
            int btn_id = btn_view.getId();
            if(btn_id==btn_num0.getId()){
                if(expression.equals("0")==false){
                    expression += "0";
                    text_expression.setText(expression);
                }
            }
            else if(btn_id==btn_num1.getId()){
                if(expression.equals("0")==true){
                    expression = "1";
                    text_expression.setText(expression);
                }
                else{
                    expression += "1";
                    text_expression.setText(expression);
                }
            }
            else if(btn_id==btn_num2.getId()){
                if(expression.equals("0")==true)
                {
                    expression = "2";
                    text_expression.setText(expression);
                }
                else
                {
                    expression += "2";
                    text_expression.setText(expression);
                }
            }
            else if(btn_id==btn_num3.getId()){
                if(expression.equals("0")==true){
                    expression = "3";
                    text_expression.setText(expression);
                }
                else
                {
                    expression += "3";
                    text_expression.setText(expression);
                }
            }
            else if(btn_id==btn_num4.getId()){
                if(expression.equals("0")==true){
                    expression = "4";
                    text_expression.setText(expression);
                }
                else
                {
                    expression += "4";
                    text_expression.setText(expression);
                }
            }
            else if(btn_id==btn_num5.getId()){
                if(expression.equals("0")==true){
                    expression = "5";
                    text_expression.setText(expression);
                }
                else
                {
                    expression += "5";
                    text_expression.setText(expression);
                }
            }
            else if(btn_id==btn_num6.getId()){
                if(expression.equals("0")==true){
                    expression = "6";
                    text_expression.setText(expression);
                }
                else
                {
                    expression += "6";
                    text_expression.setText(expression);
                }
            }
            else if(btn_id==btn_num7.getId()){
                if(expression.equals("0")==true){
                    expression = "7";
                    text_expression.setText(expression);
                }
                else
                {
                    expression += "7";
                    text_expression.setText(expression);
                }
            }
            else if(btn_id==btn_num8.getId()){
                if(expression.equals("0")==true){
                    expression = "8";
                    text_expression.setText(expression);
                }
                else
                {
                    expression += "8";
                    text_expression.setText(expression);
                }
            }
            else if(btn_id==btn_num9.getId()){
                if(expression.equals("0")==true){
                    expression = "9";
                    text_expression.setText(expression);
                }
                else
                {
                    expression += "9";
                    text_expression.setText(expression);
                }
            }
            else if(btn_id==btn_add.getId()){
                if(expression.equals("0")==false) {
                    expression += "+";
                    text_expression.setText(expression);
                }
            }
            else if(btn_id==btn_sub.getId()){
                if(expression.equals("0")==true){
                    expression = "-";
                    text_expression.setText(expression);
                }
                else
                {
                    expression += "-";
                    text_expression.setText(expression);
                }
            }
            else if(btn_id==btn_mul.getId()){
                expression += "*";
                text_expression.setText(expression);
            }
            else if(btn_id==btn_div.getId()){
                expression += "/";
                text_expression.setText(expression);
            }
            else if(btn_id==btn_point.getId()){
                expression += ".";
                text_expression.setText(expression);
            }
            else if(btn_id==btn_lb.getId()){
                if(expression.equals("0")==true){
                    expression = "(";
                    text_expression.setText(expression);
                }
                else
                {
                    expression += "(";
                    text_expression.setText(expression);
                }
            }
            else if(btn_id==btn_rb.getId()){
                expression += ")";
                text_expression.setText(expression);
            }
            else if(btn_id==btn_back.getId()){
                if(expression.length()==1){
                    expression = "0";
                    text_expression.setText(expression);
                }
                else {
                    expression = expression.substring(0, expression.length() - 1);
                    text_expression.setText(expression);
                }
            }
            else if(btn_id==btn_clr.getId()){
                expression = "0";
                text_expression.setText(expression);
            }
            else if(btn_id==btn_eql.getId()){
                if(state_flag==1)
                {
                    viewmodel.expression_list.add(expression); //记录算术表达式
                    expression = Calculator.transformed_calculate(expression);
                    viewmodel.res_list.add(expression); //记录结果
                    text_expression.setText(expression);
                }
                else if(state_flag==2)
                {
                    var_expression = expression;
                    expression = "";
                    text_expression.setText(expression);
                    hint_info.setText("请输入积分下限，然后按“=”键继续");
                    state_flag=3;
                }
                else if(state_flag==3)
                {
                    low = expression;
                    expression = "";
                    text_expression.setText(expression);
                    hint_info.setText("请输入积分上限，然后按“=”键继续");
                    state_flag=4;
                }
                else if(state_flag==4)
                {
                    up = expression;
                    expression = "";
                    text_expression.setText(expression);
                    hint_info.setText("请输入积分精度(正整数，越大越精确，建议500以上)，然后按“=”键继续");
                    state_flag=5;
                }
                else if(state_flag==5)
                {
                    acc_num = expression;
                    expression = Calculus.cal_calculus(var_expression, low, up, acc_num);
                    viewmodel.expression_list.add("∫"+"{"+low+"}"+"_{"+up+"}_("+var_expression+")dx"); //记录积分表达式
                    viewmodel.res_list.add(expression); //记录结果
                    text_expression.setText(expression);
                    hint_info.setText("");
                    state_flag=1;
                }

            }
            else if(btn_id==btn_pow.getId()){
                expression += "^";
                text_expression.setText(expression);
            }
            else if(btn_id==btn_kramp.getId()){
                expression += "!";
                text_expression.setText(expression);
            }
            else if(btn_id==btn_var.getId()){
                if(state_flag==2)
                {
                    if(expression.equals("0"))
                    {
                        expression = "X";
                    }
                    else
                    {
                        expression += "X";
                    }
                    text_expression.setText(expression);
                }
            }
            else if(btn_id==btn_cal.getId()){
                expression = "";
                text_expression.setText(expression);
                hint_info.setText("请输入积分表达式，变量键为Var（请勿省略变量X和数字之间的*号），然后按“=”键继续");
                state_flag = 2;
            }
        }
        catch (Exception e){
            state_flag = 1;
            expression = "Error!";
            text_expression.setText(expression);
        }
        SetVMdlAttribute();//保存属性
    }

    // 生命周期结束时保存数据
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"onSaveInstanceState(Bundle) called");
        savedInstanceState.putString(KEY_expression, expression);
    }

    /*重写其他方法*/
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.d(TAG,"onDestroyView() called");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }
    @Override
    public void onDetach(){
        super.onDetach();
        Log.d(TAG,"onDetach() called");
    }

    private void SetVMdlAttribute(){
        viewmodel.acc_num = acc_num;
        viewmodel.expression = expression;
        viewmodel.low = low;
        viewmodel.up = up;
        viewmodel.state_flag = state_flag;
        viewmodel.var_expression = var_expression;
    }

    private void SetCalAttribute(){
        acc_num = viewmodel.acc_num;
        expression = viewmodel.expression;
        low = viewmodel.low;
        up = viewmodel.up;
        state_flag = 1;
        var_expression = viewmodel.var_expression;
    }

}
