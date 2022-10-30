package com.larissa.android.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.larissa.android.calculator.Calculator.*;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /* 计算器显示屏  */
    private String expression = "0"; //用于保存计算器表达式内容
    private TextView text_expression, hint_info; //显示屏对应的Textview控件

    /* 定义 按钮对象 和 textview对象 */
    private Button btn_num1, btn_num2, btn_num3, btn_num4, btn_num5, btn_num6, btn_num7, btn_num8, btn_num9, btn_num0;
    private Button btn_add, btn_sub, btn_mul, btn_div, btn_point, btn_clr, btn_eql, btn_lb, btn_rb, btn_kramp, btn_pow, btn_cal, btn_var;
    private ImageButton btn_back;
    private int screenOrientation;

    /* 保存信息时所用到的键 */
    private static final String TAG="MainActivity"; // tag string
    private static final String KEY_expression="KEY_expression"; // 标识已输入算数表达式的键
    private static final String KEY_tips="KEY_tips"; // 标识提醒信息的键

    /* 定义状态参数，用于指示现在处于什么界面 */
    private int state_flag;
    private String low, up, var_expression, acc_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screenOrientation=getResources().getConfiguration().orientation; // 方向

        state_flag = 1; //1为平常模式， 2时要求输入积分下限，3时输入上限, 4时输入积分精度

        /* 根据不同屏幕方向, 用ID获取控件 */

        if(screenOrientation== Configuration.ORIENTATION_PORTRAIT){
            btn_num1 = (Button) findViewById(R.id.btn_num1);
            btn_num2 = (Button) findViewById(R.id.btn_num2);
            btn_num3 = (Button) findViewById(R.id.btn_num3);
            btn_num4 = (Button) findViewById(R.id.btn_num4);
            btn_num5 = (Button) findViewById(R.id.btn_num5);
            btn_num6 = (Button) findViewById(R.id.btn_num6);
            btn_num7 = (Button) findViewById(R.id.btn_num7);
            btn_num8 = (Button) findViewById(R.id.btn_num8);
            btn_num9 = (Button) findViewById(R.id.btn_num9);
            btn_num0 = (Button) findViewById(R.id.btn_num0);
            btn_add = (Button) findViewById(R.id.btn_add);
            btn_sub = (Button) findViewById(R.id.btn_subtract);
            btn_mul = (Button) findViewById(R.id.btn_multiply);
            btn_div = (Button) findViewById(R.id.btn_divide);
            btn_point = (Button) findViewById(R.id.btn_point);
            btn_back = (ImageButton) findViewById(R.id.btn_backspace);
            btn_clr = (Button) findViewById(R.id.btn_clear);
            btn_eql = (Button) findViewById(R.id.btn_equal);
            btn_lb = (Button) findViewById(R.id.btn_left_bracket);
            btn_rb = (Button) findViewById(R.id.btn_right_bracket);
            text_expression = (TextView) findViewById(R.id.text_expression);
        }
        else if(screenOrientation==Configuration.ORIENTATION_LANDSCAPE){ //横向
            btn_num1 = (Button) findViewById(R.id.btn_num1_l);
            btn_num2 = (Button) findViewById(R.id.btn_num2_l);
            btn_num3 = (Button) findViewById(R.id.btn_num3_l);
            btn_num4 = (Button) findViewById(R.id.btn_num4_l);
            btn_num5 = (Button) findViewById(R.id.btn_num5_l);
            btn_num6 = (Button) findViewById(R.id.btn_num6_l);
            btn_num7 = (Button) findViewById(R.id.btn_num7_l);
            btn_num8 = (Button) findViewById(R.id.btn_num8_l);
            btn_num9 = (Button) findViewById(R.id.btn_num9_l);
            btn_num0 = (Button) findViewById(R.id.btn_num0_l);
            btn_add = (Button) findViewById(R.id.btn_add_l);
            btn_sub = (Button) findViewById(R.id.btn_subtract_l);
            btn_mul = (Button) findViewById(R.id.btn_multiply_l);
            btn_div = (Button) findViewById(R.id.btn_divide_l);
            btn_point = (Button) findViewById(R.id.btn_point_l);
            btn_back = (ImageButton) findViewById(R.id.btn_backspace_l);
            btn_clr = (Button) findViewById(R.id.btn_clear_l);
            btn_eql = (Button) findViewById(R.id.btn_equal_l);
            btn_lb = (Button) findViewById(R.id.btn_left_bracket_l);
            btn_rb = (Button) findViewById(R.id.btn_right_bracket_l);
            text_expression = (TextView) findViewById(R.id.text_expression_l);

            btn_kramp = (Button) findViewById(R.id.btn_kramp_l);
            btn_pow = (Button) findViewById(R.id.btn_pow_l);
            btn_cal = (Button) findViewById(R.id.btn_cal_l);
            btn_var = (Button) findViewById(R.id.btn_var_l);
            hint_info = (TextView) findViewById(R.id.text_info_l);
        }

        /* 如果有数据被保存过, 则获取 */
        if(savedInstanceState!=null){
            expression=savedInstanceState.getString(KEY_expression,"");
            text_expression.setText(expression);
        }

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

    }

    // 生命周期结束时保存数据
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"onSaveInstanceState(Bundle) called");
        savedInstanceState.putString(KEY_expression, expression);
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
                else
                {
                    expression += "1";
                    text_expression.setText(expression);
                }
            }
            else if(btn_id==btn_num2.getId()){
                if(expression.equals("0")==true){
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
                    expression = Calculator.transformed_calculate(expression);
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

    }
}