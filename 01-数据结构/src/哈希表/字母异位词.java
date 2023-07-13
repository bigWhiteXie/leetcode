package 哈希表;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class 字母异位词 {
    public static boolean isAnagram(String s, String t) {
       if(s.length() != t.length()){
           return false;
       }
        int[] record = new int[26];

        for(int i=0; i<s.length(); i++){
            record[s.charAt(i) - 'a']++;
        }

        for(int i=0; i<t.length(); i++){
            record[t.charAt(i) - 'a']--;
        }

        for(int i=0; i<record.length; i++){
            if(record[i] != 0){
                return false;
            }
        }

        HashMap<Object, String> map = new HashMap<>();
        ArrayList<String> list = new ArrayList<>(map.values());
        

        return true;
    }

    public static void main(String[] args) {
        String s = "anagram", t = "nagaram";
        System.out.println(isAnagram(s, t));
    }
}
