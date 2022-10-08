package com.larissa.android.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.larissa.android.calculator.Calculator.*;



public class MainActivity extends AppCompatActivity {

    /* 计算器显示屏  */
    private String expression = "0"; //用于保存计算器表达式内容
    private TextView text_expression; //显示屏对应的Textview控件

    /* 定义本人写的计算器对象 */
    private Calculator cal_obj = new Calculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 定义 按钮对象 和 textview对象 */
        Button btn_num1, btn_num2, btn_num3, btn_num4, btn_num5, btn_num6, btn_num7, btn_num8, btn_num9, btn_num0;
        Button btn_add, btn_sub, btn_mul, btn_div, btn_point, btn_clr, btn_eql, btn_lb, btn_rb;
        ImageButton btn_back;

        /* 用ID获取控件 */
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

        /* 按钮控件设置事件监听,以进行响应操作 */
        btn_num1.setOnClickListener(my_btn_listener);
        btn_num2.setOnClickListener(my_btn_listener);
        btn_num3.setOnClickListener(my_btn_listener);
        btn_num4.setOnClickListener(my_btn_listener);
        btn_num5.setOnClickListener(my_btn_listener);
        btn_num6.setOnClickListener(my_btn_listener);
        btn_num7.setOnClickListener(my_btn_listener);
        btn_num8.setOnClickListener(my_btn_listener);
        btn_num9.setOnClickListener(my_btn_listener);
        btn_num0.setOnClickListener(my_btn_listener);

        btn_add.setOnClickListener(my_btn_listener);
        btn_sub.setOnClickListener(my_btn_listener);
        btn_mul.setOnClickListener(my_btn_listener);
        btn_div.setOnClickListener(my_btn_listener);
        btn_point.setOnClickListener(my_btn_listener);
        btn_back.setOnClickListener(my_btn_listener);
        btn_clr.setOnClickListener(my_btn_listener);
        btn_eql.setOnClickListener(my_btn_listener);
        btn_lb.setOnClickListener(my_btn_listener);
        btn_rb.setOnClickListener(my_btn_listener);
    }

    /* 定义一个listener属性 */
    private View.OnClickListener my_btn_listener = new View.OnClickListener(){
        @Override
        public void onClick(View btn_view) {
            switch (btn_view.getId()){
                case R.id.btn_num0:{
                    if(expression.equals("0")==false){
                        expression += "0";
                        text_expression.setText(expression);
                    }
                    break;
                }
                case R.id.btn_num1:{
                    if(expression.equals("0")==true){
                        expression = "1";
                        text_expression.setText(expression);
                    }
                    else
                    {
                        expression += "1";
                        text_expression.setText(expression);
                    }
                    break;
                }
                case R.id.btn_num2:{
                    if(expression.equals("0")==true){
                        expression = "2";
                        text_expression.setText(expression);
                    }
                    else
                    {
                        expression += "2";
                        text_expression.setText(expression);
                    }
                    break;
                }
                case R.id.btn_num3:{
                    if(expression.equals("0")==true){
                        expression = "3";
                        text_expression.setText(expression);
                    }
                    else
                    {
                        expression += "3";
                        text_expression.setText(expression);
                    }
                    break;
                }
                case R.id.btn_num4:{
                    if(expression.equals("0")==true){
                        expression = "4";
                        text_expression.setText(expression);
                    }
                    else
                    {
                        expression += "4";
                        text_expression.setText(expression);
                    }
                    break;
                }
                case R.id.btn_num5:{
                    if(expression.equals("0")==true){
                        expression = "5";
                        text_expression.setText(expression);
                    }
                    else
                    {
                        expression += "5";
                        text_expression.setText(expression);
                    }
                    break;
                }
                case R.id.btn_num6:{
                    if(expression.equals("0")==true){
                        expression = "6";
                        text_expression.setText(expression);
                    }
                    else
                    {
                        expression += "6";
                        text_expression.setText(expression);
                    }
                    break;
                }
                case R.id.btn_num7:{
                    if(expression.equals("0")==true){
                        expression = "7";
                        text_expression.setText(expression);
                    }
                    else
                    {
                        expression += "7";
                        text_expression.setText(expression);
                    }
                    break;
                }
                case R.id.btn_num8:{
                    if(expression.equals("0")==true){
                        expression = "8";
                        text_expression.setText(expression);
                    }
                    else
                    {
                        expression += "8";
                        text_expression.setText(expression);
                    }
                    break;
                }
                case R.id.btn_num9:{
                    if(expression.equals("0")==true){
                        expression = "9";
                        text_expression.setText(expression);
                    }
                    else
                    {
                        expression += "9";
                        text_expression.setText(expression);
                    }
                    break;
                }
                case R.id.btn_add:{
                    if(expression.equals("0")==false) {
                        expression += "+";
                        text_expression.setText(expression);
                    }
                    break;
                }
                case R.id.btn_subtract:{
                    if(expression.equals("0")==true){
                        expression = "-";
                        text_expression.setText(expression);
                    }
                    else
                    {
                        expression += "-";
                        text_expression.setText(expression);
                    }
                    break;
                }
                case R.id.btn_multiply:{
                    expression += "*";
                    text_expression.setText(expression);
                    break;
                }
                case R.id.btn_divide:{
                    expression += "/";
                    text_expression.setText(expression);
                    break;
                }
                case R.id.btn_point:{
                    expression += ".";
                    text_expression.setText(expression);
                    break;
                }
                case R.id.btn_left_bracket:{
                    if(expression.equals("0")==true){
                        expression = "(";
                        text_expression.setText(expression);
                    }
                    else
                    {
                        expression += "(";
                        text_expression.setText(expression);
                    }
                    break;
                }
                case R.id.btn_right_bracket:{
                    expression += ")";
                    text_expression.setText(expression);
                    break;
                }
                case R.id.btn_backspace:{
                    System.out.println(expression.length());
                    if(expression.length()==1){
                        expression = "0";
                        text_expression.setText(expression);
                    }
                    else {
                        expression = expression.substring(0, expression.length() - 1);
                        text_expression.setText(expression);
                    }
                    break;
                }
                case R.id.btn_clear:{
                    expression = "0";
                    text_expression.setText(expression);
                    break;
                }
                case R.id.btn_equal:{
                    expression = cal_obj.calculate(expression);
                    text_expression.setText(expression);
                    break;
                }
            }
        }
    };
}