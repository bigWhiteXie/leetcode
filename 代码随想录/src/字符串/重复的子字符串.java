package 字符串;

public class 重复的子字符串 {
    public static boolean repeatedSubstringPattern(String s) {
        int[] next = getNext(s);
        int len = s.length();
        if(next[len-1] > 0 && len % (len - next[len-1]) == 0 ){
            return true;
        }

        return false;
    }
    public static int[] getNext(String s){
       int[] next = new int[s.length()];

       int i = 1, j = 0;
       next[0] = 0;
       while(i<s.length()){
           while(j>0 && s.charAt(i) != s.charAt(j)){
               j = next[j-1];
           }

           if(s.charAt(i) == s.charAt(j)){
               j++;
           }
           next[i] = j;
           i++;
       }

       return next;
    }

    public static void main(String[] args) {
        String s = "aaa";
        System.out.println(repeatedSubstringPattern(s));
    }
}
