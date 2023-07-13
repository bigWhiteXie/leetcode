package 笔试.赛码网风格;

import java.util.Scanner;

/**
 * 现在小美获得了一个字符串。小美想要使得这个字符串是回文串。
 * 小美找到了你。你可以将字符串中至多两个位置改为任意小写英文字符’a’-‘z’。
 * 你的任务是帮助小美在当前制约下，获得字典序最小的回文字符串。
 * 数据保证能在题目限制下形成回文字符串。
 * 注：回文字符串：即一个字符串从前向后和从后向前是完全一致的字符串。
 * 例如字符串abcba, aaaa, acca都是回文字符串。字符串abcd, acea都不是回文字符串。
 *
 * acca
 * 输出：aaaa
 */
public class Test7 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        String ans = minStr(line);
    }

    private static String minStr(String line) {

        return null;
    }
}
