package 笔试.赛码网风格;

import java.util.*;

public class Test1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String line = scanner.nextLine();

        List<Integer> list = spiltStr(line);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if(i != list.size()-1){
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());

    }


    public static List<Integer> spiltStr(String str){
       int[] table = new int[26];
        ArrayList<Integer> ans = new ArrayList<>();

        char[] arr = str.toCharArray();
        for(int i=0; i<arr.length; i++){
            table[arr[i] - 'A'] = i;
        }

        int left = 0, right = 0;
        int i=0;
        while(i<arr.length){
            //当前字符的右边界
            left = i;
            right = table[arr[i]-'A'];
            int end = right;
            while(i<right){
                end = Math.max(end,table[arr[i]-'A']);
                i++;
            }
            ans.add(end - left + 1);
            i = end+1;
        }

        return ans;
    }
}
