package 字符串;

public class KMP {
    public static int[] getNext(String s){
        char[] arr = s.toCharArray();
        //初始化next数组，next[0] = 0
        int[] next = new int[arr.length];
        next[0] = 0;
        //j表示当前子串的前缀末尾，同时也表示当前子串最大公共前后缀长度
        //i表示当前子串后缀末尾
        int j=0, i = 1;

        //遍历s得到所有前缀子串的最大公共前后缀长度
        for(;i<arr.length;i++){
            while(j > 0 && arr[i] != arr[j]){
                j = next[j-1];
            }

            //若此时前缀末尾和后缀末尾相等
            if(arr[i] == arr[j]){
                j++;
            }

            //此时j有两种情况，一种是匹配成功得到公共长度
            next[i] = j;

        }

        return next;

    }


    public static int KMP(String s, String t){
        int[] next = getNext(t);
        char[] sarr = s.toCharArray();
        char[] tarr = t.toCharArray();
        int j = 0; //表示当前模式串第j位以前已经匹配成功

        for(int i=0;i<sarr.length;i++){
            int begin = i-j;
            while(j< tarr.length && i<sarr.length &&  sarr[i] == tarr[j]){
                i++;
                j++;
            }

            if(j == tarr.length){
                return begin;
            }

            //当i走到尽头并且没完成匹配时，结束
            if(i == sarr.length){
                return -1;
            }

            while(j>0 && sarr[i] != tarr[j]){
                j = next[j-1];
            }
            if(sarr[i] == tarr[j]){
                j++;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        String s = "aabaabaafa", t="aabaaf";
        System.out.println(KMP(s, t));
    }

}
