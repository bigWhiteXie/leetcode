package 笔试.赛码网风格;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Test13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<String> list = new ArrayList<>();
        scanner.nextLine();

        for(int i=0; i<n;i++){
            String line = scanner.nextLine();


            list.add(line);
        }
        int[] tab = new int[26];

        int number = num(0, list,tab);
        System.out.println(number);
    }

    private static int num(int index,ArrayList<String> list,int[] tab) {
        if(index == list.size()){
            return 1;
        }
        String s = list.get(index);

        int ans = 0;
        HashSet<Character> set = new HashSet<>();
        for(int i=0;i<s.length();i++){
            set.add(s.charAt(i));
        }

        for (Character c : set) {
            if(tab[c- 'a']==0 ) {
                tab[c- 'a']++;
                ans += num(index + 1, list, tab);
                tab[c - 'a']--;
            }
        }

        return ans;
    }


}
