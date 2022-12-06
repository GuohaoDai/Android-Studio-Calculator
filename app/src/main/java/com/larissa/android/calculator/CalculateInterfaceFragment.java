package com.larissa.android.calculator;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

public class CalculateInterfaceFragment extends Fragment implements View.OnClickListener, MenuProvider {
    private static final String TAG = "CalculateInterfaceFragment";
    private View view; // fragment所在的View

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

        //得到fragment所在的view
        view = inflater.inflate(R.layout.fragment_calculate, container,false);

        //得到Activity对象，设置菜单
        getActivity().addMenuProvider(this);

        return view;
    }

    /* 对Fragment View里的内容进行逻辑操作 */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG,"onViewCreated() called");
        screenOrientation=getResources().getConfiguration().orientation; // 方向
        viewmodel = new ViewModelProvider(requireActivity()).get(CalculatorViewModel.class);//得到所托管的Activity的viewmodel
        viewmodel.state_flag = 1;

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

        text_expression.setText(viewmodel.expression); //在显示屏上显示计算表达式
    }

    /* 定义一个监听事件对象, 里面定义按键后的业务逻辑 */
    @Override
    public void onClick(View btn_view) {
        try{
            int btn_id = btn_view.getId();
            if(btn_id==btn_num0.getId()){
                if(viewmodel.expression.equals("0")==false){
                    viewmodel.expression += "0";
                    text_expression.setText(viewmodel.expression);
                }
            }
            else if(btn_id==btn_num1.getId()){
                if(viewmodel.expression.equals("0")==true){
                    viewmodel.expression = "1";
                    text_expression.setText(viewmodel.expression);
                }
                else{
                    viewmodel.expression += "1";
                    text_expression.setText(viewmodel.expression);
                }
            }
            else if(btn_id==btn_num2.getId()){
                if(viewmodel.expression.equals("0")==true)
                {
                    viewmodel.expression = "2";
                    text_expression.setText(viewmodel.expression);
                }
                else
                {
                    viewmodel.expression += "2";
                    text_expression.setText(viewmodel.expression);
                }
            }
            else if(btn_id==btn_num3.getId()){
                if(viewmodel.expression.equals("0")==true){
                    viewmodel.expression = "3";
                    text_expression.setText(viewmodel.expression);
                }
                else
                {
                    viewmodel.expression += "3";
                    text_expression.setText(viewmodel.expression);
                }
            }
            else if(btn_id==btn_num4.getId()){
                if(viewmodel.expression.equals("0")==true){
                    viewmodel.expression = "4";
                    text_expression.setText(viewmodel.expression);
                }
                else
                {
                    viewmodel.expression += "4";
                    text_expression.setText(viewmodel.expression);
                }
            }
            else if(btn_id==btn_num5.getId()){
                if(viewmodel.expression.equals("0")==true){
                    viewmodel.expression = "5";
                    text_expression.setText(viewmodel.expression);
                }
                else
                {
                    viewmodel.expression += "5";
                    text_expression.setText(viewmodel.expression);
                }
            }
            else if(btn_id==btn_num6.getId()){
                if(viewmodel.expression.equals("0")==true){
                    viewmodel.expression = "6";
                    text_expression.setText(viewmodel.expression);
                }
                else
                {
                    viewmodel.expression += "6";
                    text_expression.setText(viewmodel.expression);
                }
            }
            else if(btn_id==btn_num7.getId()){
                if(viewmodel.expression.equals("0")==true){
                    viewmodel.expression = "7";
                    text_expression.setText(viewmodel.expression);
                }
                else
                {
                    viewmodel.expression += "7";
                    text_expression.setText(viewmodel.expression);
                }
            }
            else if(btn_id==btn_num8.getId()){
                if(viewmodel.expression.equals("0")==true){
                    viewmodel.expression = "8";
                    text_expression.setText(viewmodel.expression);
                }
                else
                {
                    viewmodel.expression += "8";
                    text_expression.setText(viewmodel.expression);
                }
            }
            else if(btn_id==btn_num9.getId()){
                if(viewmodel.expression.equals("0")==true){
                    viewmodel.expression = "9";
                    text_expression.setText(viewmodel.expression);
                }
                else
                {
                    viewmodel.expression += "9";
                    text_expression.setText(viewmodel.expression);
                }
            }
            else if(btn_id==btn_add.getId()){
                if(viewmodel.expression.equals("0")==false) {
                    viewmodel.expression += "+";
                    text_expression.setText(viewmodel.expression);
                }
            }
            else if(btn_id==btn_sub.getId()){
                if(viewmodel.expression.equals("0")==true){
                    viewmodel.expression = "-";
                    text_expression.setText(viewmodel.expression);
                }
                else
                {
                    viewmodel.expression += "-";
                    text_expression.setText(viewmodel.expression);
                }
            }
            else if(btn_id==btn_mul.getId()){
                viewmodel.expression += "*";
                text_expression.setText(viewmodel.expression);
            }
            else if(btn_id==btn_div.getId()){
                viewmodel.expression += "/";
                text_expression.setText(viewmodel.expression);
            }
            else if(btn_id==btn_point.getId()){
                viewmodel.expression += ".";
                text_expression.setText(viewmodel.expression);
            }
            else if(btn_id==btn_lb.getId()){
                if(viewmodel.expression.equals("0")==true){
                    viewmodel.expression = "(";
                    text_expression.setText(viewmodel.expression);
                }
                else
                {
                    viewmodel.expression += "(";
                    text_expression.setText(viewmodel.expression);
                }
            }
            else if(btn_id==btn_rb.getId()){
                viewmodel.expression += ")";
                text_expression.setText(viewmodel.expression);
            }
            else if(btn_id==btn_back.getId()){
                if(viewmodel.expression.length()==1){
                    viewmodel.expression = "0";
                    text_expression.setText(viewmodel.expression);
                }
                else {
                    viewmodel.expression = viewmodel.expression.substring(0, viewmodel.expression.length() - 1);
                    text_expression.setText(viewmodel.expression);
                }
            }
            else if(btn_id==btn_clr.getId()){
                viewmodel.expression = "0";
                text_expression.setText(viewmodel.expression);
            }
            else if(btn_id==btn_eql.getId()){
                if(viewmodel.state_flag==1)
                {
                    viewmodel.expression_list.add(viewmodel.expression); //记录算术表达式
                    viewmodel.expression = Calculator.transformed_calculate(viewmodel.expression);
                    viewmodel.res_list.add(viewmodel.expression); //记录结果
                    text_expression.setText(viewmodel.expression);
                }
                else if(viewmodel.state_flag==2)
                {
                    viewmodel.var_expression = viewmodel.expression;
                    viewmodel.expression = "";
                    text_expression.setText(viewmodel.expression);
                    hint_info.setText("请输入积分下限，然后按“=”键继续");
                    viewmodel.state_flag=3;
                }
                else if(viewmodel.state_flag==3)
                {
                    viewmodel.low = viewmodel.expression;
                    viewmodel.expression = "";
                    text_expression.setText(viewmodel.expression);
                    hint_info.setText("请输入积分上限，然后按“=”键继续");
                    viewmodel.state_flag=4;
                }
                else if(viewmodel.state_flag==4)
                {
                    viewmodel.up = viewmodel.expression;
                    viewmodel.expression = "";
                    text_expression.setText(viewmodel.expression);
                    hint_info.setText("请输入积分精度(正整数，越大越精确，建议500以上)，然后按“=”键继续");
                    viewmodel.state_flag=5;
                }
                else if(viewmodel.state_flag==5)
                {
                    viewmodel.acc_num = viewmodel.expression;
                    viewmodel.expression = Calculus.cal_calculus(viewmodel.var_expression, viewmodel.low, viewmodel.up, viewmodel.acc_num);
                    viewmodel.expression_list.add("∫"+"{"+viewmodel.low+"}"+"_{"+viewmodel.up+"}_("+viewmodel.var_expression+")dx"); //记录积分表达式
                    viewmodel.res_list.add(viewmodel.expression); //记录结果
                    text_expression.setText(viewmodel.expression);
                    hint_info.setText("");
                    viewmodel.state_flag=1;
                }

            }
            else if(btn_id==btn_pow.getId()){
                viewmodel.expression += "^";
                text_expression.setText(viewmodel.expression);
            }
            else if(btn_id==btn_kramp.getId()){
                viewmodel.expression += "!";
                text_expression.setText(viewmodel.expression);
            }
            else if(btn_id==btn_var.getId()){
                if(viewmodel.state_flag==2)
                {
                    if(viewmodel.expression.equals("0"))
                    {
                        viewmodel.expression = "X";
                    }
                    else
                    {
                        viewmodel.expression += "X";
                    }
                    text_expression.setText(viewmodel.expression);
                }
            }
            else if(btn_id==btn_cal.getId()){
                viewmodel.expression = "";
                text_expression.setText(viewmodel.expression);
                hint_info.setText("请输入积分表达式，变量键为Var（请勿省略变量X和数字之间的*号），然后按“=”键继续");
                viewmodel.state_flag = 2;
            }
        }
        catch (Exception e){
            viewmodel.state_flag = 1;
            viewmodel.expression = "Error!";
            text_expression.setText(viewmodel.expression);
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
            case R.id.btn_hist: // 如果点击的是history, 则导航到History页面上
                NavDirections action=CalculateInterfaceFragmentDirections.actionCal2Hist();
                Navigation.findNavController(view).navigate(action);
                return true;

            default:
                return true;
        }
    }

    /*重写其他方法*/
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.d(TAG,"onDestroyView() called");
        getActivity().removeMenuProvider(this);
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

}
