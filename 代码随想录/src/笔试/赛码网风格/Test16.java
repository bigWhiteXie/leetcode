package 笔试.赛码网风格;

import java.util.*;

public class Test16 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String str = "";
        HashMap<String, Integer> map = new HashMap<>();
        String s = generateStr(n,str,map);

        System.out.println(s);
    }

    private static String generateStr(int n, String str, Map<String,Integer> map) {
        if(countPalindromicSubstrings(str) > n){
            return "";
        }
        String s1 = str + 'r';
        String s2 = str + 'e';
        String s3 = str + 'd';

        if(countPalindromicSubstrings(s1) == n){
            return s1;
        }

        if(countPalindromicSubstrings(s2) == n){
            return s2;
        }

        if(countPalindromicSubstrings(s3) == n){
            return s3;
        }

        String str1 = generateStr(n, s1,map);
        if(str1 != null && !str1.equals("")){
            return str1;
        }
        String str2 = generateStr(n, s2,map);
        if(str2!=null && !str2.equals("")){
            return str2;
        }
        String str3 = generateStr(n, s3,map);
        return str3;
    }

    public static int countPalindromicSubstrings(String s) {
        int count = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            // 以s[i]为中心向两边扩展
            count += countPalindromicSubstrings(s, i, i);
            // 以s[i]和s[i+1]之间的空隙为中心向两边扩展
            count += countPalindromicSubstrings(s, i, i + 1);
        }
        return count;
    }

    private static int countPalindromicSubstrings(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }

}
