package 字符串;

public class MyKMP {
    public static int[] getNext(String s){
        int[] next = new int[s.length()];
        //i：待比较的当前子串公共前缀的末尾
        //j：待比较的当前子串公共后缀的末尾
        int i = 1, j = 0;
        next[0] = 0;

        while(i<s.length()){
            while(j>0 && s.charAt(j) != s.charAt(i)){
                j = next[j-1];
            }
            //计算的是前后缀长度而j表示的是前缀末尾下标，若前后缀相等应该将下标+1得到长度
            //若前后缀不相等说明此时j=0，公共前后缀长度=0
            if(s.charAt(i) == s.charAt(j)){
                j++;
            }

            next[i] = j;
            i++;
        }
        return next;
    }


    public static int getIndex(String f,String s){
        int[] next = getNext(s);
        int j = 0; //子串当前索引
        int i = 0;
        while (i<f.length()){
            while(i<f.length() && j<s.length()&& f.charAt(i) == s.charAt(j)){
                i++;
                j++;
            }
            if(j == s.length()){
                return i-j;
            }else{
                while(j>0 && f.charAt(i) != s.charAt(j)){
                    j = next[j-1];
                }
                if(f.charAt(i) == s.charAt(j)){
                    j++;
                }
            }
            i++;
        }
        return -1;
    }


    public static void main(String[] args) {
        String s = "aabaabaafa", t="aabaaf";
        System.out.println(getIndex(s, t));
    }
}
