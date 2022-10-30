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

    /* 字符串插入字符 */
    public static String string_insert(String origin_string, String insert_string, int pos)
    {
        StringBuilder sb = new StringBuilder(origin_string);
        return sb.insert(pos, insert_string).toString();
    }

    /*
       向左或向右搜寻距离最近一次表达式的结束之处, 返回结束处的坐标。
       比如3^(3+4)+1，会返回")"处坐标
    */
    public static int search_end(int start_idx, String str, boolean right_search)
    {
        boolean curly_flag = false;
        int now_idx = start_idx;
        String now_char;
        int left_bracket_num = 0;
        int right_bracket_num = 0;
        while(true)
        {
            if(right_search==true) // 如果从右开始搜寻
            {
                now_idx++; // now_idx 最终结果为右括号应该插入的位置
                if(now_idx==str.length())
                {
                    now_idx=str.length();
                    break;
                }
            }
            else
            {
                now_idx--; // now_idx 最终结果为左括号应该插入的位置
                if(now_idx==-1)
                {
                    now_idx=0;
                    break;
                }
            }

            now_char = str.charAt(now_idx)+"";
            if(curly_flag == false) // 目前不在括号内遍历
            {
                if("0123456789.()".indexOf(now_char) == -1) //如果下一位不是数字或括号，则停止循环
                {
                    if(right_search==false)
                        now_idx++;
                    break;
                }
                else // 如果下一位是数字或括号
                {
                    if(now_char.equals("("))
                    {
                        curly_flag = true;
                        left_bracket_num++;
                        if(right_search==false)
                        {
                            break;
                        }
                    }
                    else if(now_char.equals(")"))
                    {
                        curly_flag = true;
                        right_bracket_num++;
                        if(right_search==true)
                        {
                            now_idx++;
                            break;
                        }
                    }
                    else{} // 如果目前是数字，则不进行任何操作
                }
            }
            else if(curly_flag == true) // 目前仍遍历在括号内
            {
                if(now_char.equals("("))
                {
                    curly_flag = true;
                    left_bracket_num++;
                    if(left_bracket_num==right_bracket_num) // 如果左右括号数相等则停止循环
                    {
                        if(right_search==true) // 如果从右开始搜寻
                            now_idx++;
                        break;
                    }
                }
                else if(now_char.equals(")"))
                {
                    curly_flag = true;
                    right_bracket_num++;
                    if(left_bracket_num==right_bracket_num) // 如果左右括号数相等则停止循环
                    {
                        if(right_search==true) // 如果从右开始搜寻
                            now_idx++;
                        break;
                    }
                }
                else{} // 如果目前是数字或其他字符，则不进行任何操作
            }
        }
        return now_idx;
    }

    /* 在^两边插入括号 */
    public static String update_pow(String str_origin, int idx)
    {
        //插入右括号
        int res = Calculator.search_end(idx, str_origin, true);
        str_origin = Calculator.string_insert(str_origin, ")", res);

        //插入左括号
        res = Calculator.search_end(idx, str_origin, false);
        str_origin = Calculator.string_insert(str_origin, "(", res);
//        System.out.println(String.format("%s", str_origin));
        return str_origin;
    }

    /* 在!两边插入括号 */
    public static String update_kramp(String str_origin, int idx)
    {
        //插入右括号
        str_origin = Calculator.string_insert(str_origin, ")", idx+1);

        //插入左括号
        int res = Calculator.search_end(idx, str_origin, false);
        str_origin = Calculator.string_insert(str_origin, "(", res);
//        System.out.println(String.format("%s", str_origin));
        return str_origin;
    }

    /*
       更新表达式，将优先级最高的操作符加上括号
       如：2*2.5!+4.5^4*5 --> 2*(2.5!)+(4.5^4)*5
       本人设定!优先级大于^
    */
    public static String update_expression(String str_origin)
    {
        String str_new = str_origin;
        // 给!两侧加括号
        int frontLength = 0;//定义该变量用于记录匹配"^"的元素前面的长度
        int i = 0;
        while(str_origin.contains("!"))
        {
            int oper_index = str_origin.indexOf("!");
            str_origin = str_origin.substring(oper_index+1);
            str_new = Calculator.update_kramp(str_new, oper_index+i*2+frontLength);
            frontLength += oper_index+1;
            i++;
        }

        // 给^两侧加括号
        str_origin = str_new;
        frontLength = 0;//定义该变量用于记录匹配"^"的元素前面的长度
        i = 0;
        while(str_origin.contains("^"))
        {
            int oper_index = str_origin.indexOf("^");
            str_origin = str_origin.substring(oper_index+1);
            str_new = Calculator.update_pow(str_new, oper_index+i*2+frontLength);
            frontLength += oper_index+1;
            i++;
        }

        return str_new;
    }

    /* 将!和^的表达式用括号包括起来 2*5^6 --> 2*(5^6) */
//    public String add_braket(String expression)
//    {
//
//    }

//    public String check_expression(String expression)
//    {
//
//    }

//    public String calculate(String expression)
//    {
//
//    }

    /* 计算器业务逻辑 */
    public static String calculate(String expression)
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


        try // 没有报错时
        {
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

                if("+-/*!^()".indexOf(c_temp) != -1) // 如果当前遍历字符为运算符
                {
                    if(!curly_mode) // 如果还没遍历到括号内
                    {
                        if("+-/*^".indexOf(c_temp) != -1) //如果是双目运算符
                        {
                            oper_list.push(c_temp);
                        }
                        else if(c_temp.equals("!")) // 如果是!等后置运算符
                        {
                            double top_data_pop = data_list.pop();
                            System.out.printf("%f\n", top_data_pop);
                            double mul_cal = 1;
                            for(int j=1; j<=top_data_pop; j++)
                            {
                                mul_cal = mul_cal*j;
                            }
                            data_list.push(mul_cal);
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
                                c_temp = Calculator.calculate(curly_str.substring( 1, curly_str.length()-1 )); //去掉字符串的首尾括号 还没去掉
                                curly_str = "";
                            }
                        }
                        else
                        {
                            curly_str += c_temp;
                        }
                    }
                }

                if ("+-/*!^()".indexOf(c_temp) == -1) // 如果当前遍历字符为数字
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
                            else if(oper_top.equals("^"))
                            {
                                double data_top = data_list.pop();
                                data_list.push(Math.pow(data_top, new_data));
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
        catch (Exception e) // 报错时
        {
            return "Error!";
        }

    }

    /* 将表达式按优先级加括号后的calculate */
    public static String transformed_calculate(String expression)
    {
        expression = Calculator.update_expression(expression);
        expression = Calculator.calculate(expression);
        return expression;
    }

//    /* 调试信息 */
//    public static void main(String args[])
//    {
//        String str = "(0.1)^2+3^5.0+4!*5^(3*2)";
//        Calculator cal = new Calculator();
//        String res_cal = cal.calculate(str);
//        System.out.println(res_cal);
//    }
}

