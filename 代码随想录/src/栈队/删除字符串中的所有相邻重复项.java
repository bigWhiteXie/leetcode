package 栈队;

import java.util.ArrayList;

/**
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 *
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 *
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 *
 * 输入："abbaca"
 * 输出："ca"
 */
public class 删除字符串中的所有相邻重复项 {
    public static String removeDuplicates(String s) {
        StringBuilder builder = new StringBuilder();

        for(int i=0;i<s.length();i++){
            if(builder.length() > 0 && builder.charAt(builder.length()-1) == s.charAt(i)){
                builder.deleteCharAt(builder.length()-1);
            }else{
                builder.append(s.charAt(i));
            }
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        String s = "abbaca";
        System.out.println(removeDuplicates(s));
    }
}
