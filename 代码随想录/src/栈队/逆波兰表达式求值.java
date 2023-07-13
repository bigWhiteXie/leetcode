package 栈队;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
 *
 * 请你计算该表达式。返回一个表示表达式值的整数。
 *
 * 注意：
 *
 * 有效的算符为 '+'、'-'、'*' 和 '/' 。
 * 每个操作数（运算对象）都可以是一个整数或者另一个表达式。
 * 两个整数之间的除法总是 向零截断 。
 * 表达式中不含除零运算。
 * 输入是一个根据逆波兰表示法表示的算术表达式。
 * 答案及所有中间计算结果可以用 32 位 整数表示。
 */
public class 逆波兰表达式求值 {
    public static int evalRPN(String[] tokens) {
        List<Integer> list = new ArrayList<>();
        int ans = 0;
        for (int i = 0; i < tokens.length; i++) {
            int it = 0;
            int len = list.size();
            switch (tokens[i]){
                case "+":it = Integer.valueOf(list.remove(len-2)) + Integer.valueOf(list.remove(len-2));break;
                case "-":it = Integer.valueOf(list.remove(len-2)) - Integer.valueOf(list.remove(len-2));break;
                case "*":it = Integer.valueOf(list.remove(len-2)) * Integer.valueOf(list.remove(len-2));break;
                case "/":it = Integer.valueOf(list.remove(len-2)) / Integer.valueOf(list.remove(len-2));break;
                default:
                    list.add(Integer.valueOf(tokens[i]));
            }

            if(it != 0){
                list.add(it);
            }

        }

        return list.get(0);
    }

    public static void main(String[] args) {
        String[] tokens = {"2","1","+","3","*"};
        System.out.println(evalRPN(tokens));

        String[] token2 = {"4","13","5","/","+"};
        System.out.println(evalRPN(token2));
    }
}
