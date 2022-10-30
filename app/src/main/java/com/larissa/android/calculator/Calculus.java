package com.larissa.android.calculator;

import java.util.Stack;

public class Calculus
{
    /* 创造蒙特卡洛采样 */
    public static Stack create_MC_samples(double low, double up, int num)
    {
        Stack<Double> MC_spls = new Stack<>();
        double temp = 100;
        for(int i=0; i<num; i++)
        {
            if((temp<1e-10)&&(temp>-1e-10))//如果temp=0
            {
                continue;
            }
            temp = (up-low)/num*i+low;
            MC_spls.push(temp);
        }
        return MC_spls;
    }

    /* 将变量转化为数 */
    public static String transform_expression(String expression, String num)
    {

        return "1";
    }

    /* 计算积分 */
    public static String cal_calculus(String expression, String low, String up, String accuracy_num)
    {
        int accuracy_num_int = Integer.valueOf(accuracy_num);
        double low_double = Double.parseDouble(low);
        double up_double = Double.parseDouble(up);
        Stack<Double> MC_spls;
        int i = 0;
        String this_mc; //当前mc采样字符串
        String expression_temp;
        double res = 0; //结果
        String var_char = "X"; // 符号

        double gap = up_double-low_double;
        if((gap<1e-10)&&(gap>(-1e-10))) //如果上下限相同或间距很小
        {
            return "0.0";
        }
        else if(gap<0) // 如果上下限输反
        {
            MC_spls = Calculus.create_MC_samples(up_double, low_double, accuracy_num_int);
            while(MC_spls.empty()==false)
            {
                expression_temp = expression;
                this_mc = String.valueOf(MC_spls.pop());
                expression_temp = expression_temp.replaceAll(var_char, this_mc);
                res += Double.valueOf(Calculator.transformed_calculate(expression_temp));
                i++;
            }
            res = (gap/i) * res; // 不对！
            return String.valueOf(res);
        }
        else
        {
            MC_spls = Calculus.create_MC_samples(low_double, up_double, accuracy_num_int);
            while(MC_spls.empty()==false)
            {
                expression_temp = expression;
                this_mc = String.valueOf(MC_spls.pop());
                expression_temp = expression_temp.replaceAll(var_char, this_mc);
                res += Double.valueOf(Calculator.transformed_calculate(expression_temp));
                i++;
            }
            res = (gap/i) * res;
            return String.valueOf(res);
        }
    }

    /* 调试信息 */
//    public static void main(String args[])
//    {
//        String res = Calculus.cal_calculus("(2*X)/5", "5", "6", 1000);
//        System.out.println(res);
//    }

}
