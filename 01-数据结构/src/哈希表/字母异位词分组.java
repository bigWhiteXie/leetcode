package 哈希表;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 字母异位词分组 {
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> records = new ArrayList<>();
        for(int i=0; i<strs.length; i++){
            ArrayList<String> list = new ArrayList<>();
            list.add(strs[i]);
            for(int j=i+1; j<strs.length;j++){

                boolean flag = isAnagrams(strs[i], strs[j]);
                if(flag){
                    list.add(strs[j]);
                    int end = strs.length;
                    for(int k=j+1;k<end;k++){
                        strs[k-1] = strs[k];
                    }
                    String[] str2 = Arrays.copyOf(strs, end - 1);
                    strs = str2;
                    j--;

                }
            }
            records.add(list);

        }

        return records;
    }

    public static boolean isAnagrams(String s1, String s2){
        int[] record = new int[26];
        for(int i=0; i<s1.length(); i++){
            record[s1.charAt(i) - 'a']++;
        }

        for(int i=0; i<s2.length(); i++){
            record[s2.charAt(i) - 'a']--;
        }

        for(int i:record){
            if(i!=0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[]  strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = groupAnagrams(strs);
        for(List<String> list:lists){
            System.out.println(list);
        }

    }
}
