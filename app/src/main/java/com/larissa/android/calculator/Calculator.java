/*
    Description: Calculator class
    Author: Guohao Dai
    Date: 2022/10/2
 */

package com.larissa.android.calculator;

import java.util.Stack;

public class Calculator
{
    public Calculator(){}

    public String calculate(String expression)
    {
        /*
            四则运算（带括号）程序思路：
            (1)不带括号的思想:

                · 定义两个栈：操作栈存+ - * /符号，数值栈存实际的数值
                · 遍历公式字符，遇到符号直接入操作数栈
                · 遇到数值a:
                    · 如果数值栈空则a直接进入数值栈
                    · 数值栈不为空则弹出 操作栈 的栈顶（从栈中删除）
                        · 栈顶为 + ，则将符号重新入栈，数值进入数值栈。
                        · 栈顶为 - ，则将符号变为 + 并入栈， 数值a取反并入数值栈
                        · 栈顶为 * / ，则弹出 数值栈 栈顶数值b，将b与a用符号进行计算，将结果放入数值栈
                · 遍历结束，操作栈中只有 + 符号，数值栈最少有一个数值。每次弹出一个符号和两个数值，计算后结果入数值栈，
                  直到数值栈只剩下一个元素（操作栈可能剩下一个 +），这个元素就是最终的结果。

            (2)带括号的思想:

                · 使用递归方法, 先识别出最大的括号的包含区域, 然后将该区域的表达式再送入本类中, 返回该括号表达式的结果。
        */

        int len =expression.length(); // 字符串表达式长度
        int left_curly_num = 0;  // 左括号数量
        int right_curly_num = 0; // 右括号数量

        Stack<Double> data_list = new Stack<>(); // 数值栈
        Stack<String> oper_list = new Stack<>(); // 操作符栈

        double res = 0; // 最终结果
        String curly_str = ""; // 括号表达式
        String num_temp = ""; // 用于存储1位以上数字的表达式
        boolean curly_mode = false; //目前是否在括号表达式内遍历

        for(int i=0; i<len; i++) //遍历括号表达式
        {
            String c_temp = expression.charAt(i) + "";

            if( "+-/*()".indexOf(c_temp) != -1) // 如果当前遍历字符为运算符
            {
                if(!curly_mode) // 如果还没遍历到括号内
                {
                    if("+-/*".indexOf(c_temp) != -1) //如果是四则运算符
                    {
                        oper_list.push(c_temp);
                    }
                    else if(c_temp.equals("(")) // 如果是左括号
                    {
                        left_curly_num += 1;
                        curly_str += c_temp;
                        curly_mode = true;
                    }
                }
                else if(curly_mode) // 如果遍历到了括号内, 递归处理括号内的信息
                {
                    if(c_temp.equals("("))
                    {
                        left_curly_num += 1;
                        curly_str += c_temp;
                    }
                    else if(c_temp.equals(")"))
                    {
                        right_curly_num += 1;
                        curly_str += c_temp;
                        if( left_curly_num == right_curly_num) // 如果已遍历完所有括号, 则把该括号内的内容再放入计算器类里进行计算(递归思想)
                        {
                            curly_mode = false;
                            Calculator cal_obj = new Calculator();
                            c_temp = cal_obj.calculate(curly_str.substring( 1, curly_str.length()-1 )); //去掉字符串的首尾括号 还没去掉
                            curly_str = "";
                        }
                    }
                    else
                    {
                        curly_str += c_temp;
                    }
                }
            }

            if ("+-/*()".indexOf(c_temp) == -1) // 如果当前遍历字符为数字
            {
                if(!curly_mode) // 如果还没遍历到括号表达式内, 则进行四则数值操作
                {
                    double new_data = 0;
                    if(c_temp.equals(".")==false){
                        new_data = Double.parseDouble(c_temp);
                    }

                    /* 令程序能够将个位以上的数值放入栈中 */
                    if(i != (len-1)) // 还没遍历到最后一个字符
                    {
                        String c_next = expression.charAt(i+1) + "";
                        if("1234567890.".indexOf(c_next) != -1) // 下一位是数字
                        {
                            num_temp += c_temp;
                            continue;
                        }
                        else // 下一位不是数字
                        {
                            num_temp += c_temp;
                            new_data = Double.parseDouble(num_temp); // 直接计算
                            num_temp = "";
                        }
                    }
                    else // 遍历到最后一个字符
                    {
                        if(num_temp.length() == 0) // 最后一位是单个数字
                        {
                            new_data = Double.parseDouble(c_temp);
                        }
                        else // 最后一位 是 某个大数字的其中一位
                        {
                            num_temp += c_temp;
                            new_data = Double.parseDouble(num_temp);
                            num_temp = "";
                        }
                    }

                    /* 数值计算 */
                    if( oper_list.empty() && data_list.empty() )
                    {
                        data_list.push(new_data);
                    }
                    else
                    {
                        String oper_top = oper_list.pop();

                        if(oper_top.equals("+"))
                        {
                            oper_list.push(oper_top);
                            data_list.push(new_data);
                        }
                        else if(oper_top.equals("-"))
                        {
                            oper_list.push("+");
                            data_list.push(-new_data);
                        }
                        else if(oper_top.equals("*"))
                        {
                            double data_top = data_list.pop();
                            data_list.push(data_top * new_data);
                        }
                        else if(oper_top.equals("/"))
                        {
                            double data_top = data_list.pop();
                            data_list.push(data_top / new_data);
                        }
                    }
                }
                else if(curly_mode) // 如果还没遍历到括号内, 直接将
                {
                    curly_str += c_temp;
                }
            }
        }

        // 最后操作符栈只剩下+号, 把数值栈的结果都加起来即是最终结果.
        while (data_list.size() != 1)
        {
            double num1 = data_list.pop();
            double num2 = data_list.pop();
            data_list.push(num1+num2);
        }

        res = data_list.pop();
        return String.valueOf(res);
    }
}
