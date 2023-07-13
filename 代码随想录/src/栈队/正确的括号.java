package 栈队;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static javax.swing.UIManager.put;

/**给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 有效字符串需满足：
 左括号必须用相同类型的右括号闭合。
 左括号必须以正确的顺序闭合。
 每个右括号都有一个对应的相同类型的左括号。
 **/
public class 正确的括号 {
    private static final Map<Character,Character> map = new HashMap<Character,Character>(){{
        put('{','}'); put('[',']'); put('(',')'); put('?','?');
    }};

    public static boolean isValid(String s){
        char[] arr = s.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (char c : arr) {
            Character c1;
            if(!stack.isEmpty() && ( c1 = map.get(stack.peek()))!=null && c1== c){
                stack.pop();
            }else{
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "([)]";
        System.out.println(isValid(s));
    }

}
