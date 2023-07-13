package 链表;

public class 替换空格 {
    public static String replaceSpace(String s) {
        StringBuilder builder = new StringBuilder();
        int l = s.length() - 1;
        //1. 扩充字符串：为每个' '扩充两个' '
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == ' '){
                builder.append("  ");
            }
        }
        s += builder.toString();

        //双指针
        int r = s.length() - 1;
        char[] array = s.toCharArray();
        while(l >=0){
            if(array[l] == ' '){
                array[r--] = '0';
                array[r--] = '2';
                array[r--] = '%';
                l--;
            }else{
                array[r--] = array[l--];
            }
        }
        return  new String(array);
    }

    public static void main(String[] args) {
        String s = "We are happy.";
        System.out.println(replaceSpace(s));
    }
}
