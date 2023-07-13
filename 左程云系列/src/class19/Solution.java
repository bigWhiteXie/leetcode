package class19;

public class Solution {
    public static String reverseWords(String s) {
        char[] arr = s.toCharArray();
        String str = removeSpace(arr);
        char[] sa = str.toCharArray();
        reverse(sa,0,sa.length-1);
        int i=0;
        while(i<sa.length){
            int begin = i,end = i;
            while(end<sa.length && sa[end]!=' '){
                end++;
            }
            if(sa[end] == ' '){
                end--;
            }
            reverse(sa,begin,end);
            i = end+2;

        }

        return new String(sa);
    }

    public static String removeSpace(char[] s){
        StringBuilder sb = new StringBuilder();
        int slow=0,fast = s.length - 1;
        while(s[slow] == ' '){slow++;}
        while(s[fast] == ' '){fast--;}

        while(slow <= fast){
            if(s[slow] != ' '){
                sb.append(s[slow]);
            }else{
                if(sb.charAt(sb.length()-1) != s[slow]){
                    sb.append(s[slow]);
                }
            }
            slow++;
        }

        return sb.toString();
    }

    public static void reverse(char[] arr,int begin, int end){

        while(begin < end){
            char t = arr[begin];
            arr[begin] = arr[end];
            arr[end] = t;
            begin++;
            end--;
        }
    }


    public static void main(String[] args) {
        String a = "  im  china  ";
        reverseWords(a);
    }
}
