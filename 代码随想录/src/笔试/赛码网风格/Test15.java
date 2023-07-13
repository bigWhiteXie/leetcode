package 笔试.赛码网风格;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Test15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<n;i++){
            String line = scanner.nextLine();
            String ans = judge(line);
            list.add(ans);
        }

        for(String str:list){
            System.out.println(str);
        }


    }

    private static String judge(String line) {
        if(line.length() != 5){
            return "No";
        }
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('B',1);
        map.put('a',1);
        map.put('i',1);
        map.put('d',1);
        map.put('u',1);

        for(int i=0;i<line.length();i++){

            Integer count = map.getOrDefault(line.charAt(i), 0);
            map.put(line.charAt(i),count-1);
        }

        for (Character key : map.keySet()) {
            if(map.get(key) != 0){
                return "No";
            }
        }
        return "Yes";
    }
}
