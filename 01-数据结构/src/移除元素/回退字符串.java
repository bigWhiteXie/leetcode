package 移除元素;

public class 回退字符串 {
    /**
     * 输入：s = "ab#c", t = "ad#c"
     * 输出：true
     * 解释：s 和 t 都会变成 "ac"。
     * @param s
     * @param t
     * @return
     */
    public static boolean backspaceCompare(String s, String t) {
      int skip_s = 0, skip_t = 0,i_s = s.length() -1,i_t = t.length() - 1;
      while(i_s >= 0 || i_t >= 0 ){
          while(i_s >= 0){
              if(s.charAt(i_s) == '#'){
                  i_s--;
                  skip_s++;
              }else if(skip_s > 0){
                  skip_s--;
                  i_s --;
              }else{
                  break;
              }
          }

          while(i_t >= 0){
              if(t.charAt(i_t) == '#'){
                  i_t--;
                  skip_t++;
              }else if(skip_t > 0){
                  skip_t--;
                  i_t --;
              }else{
                  break;
              }
          }
          //当i_s和i_t都未遍历完字符串时
          if(i_s >= 0 && i_t >= 0) {
              if (s.charAt(i_s) != t.charAt(i_t)) {
                  return false;
              }
          }
          //当其中一个已经遍历完时
          if((i_s >= 0 && i_t < 0) || (i_t >= 0 && i_s < 0)){
              return false;
          }
          i_s--;
          i_t--;
      }
      return true;
    }

    public static void main(String[] args) {
        String s = "ab#c", t = "ad#cc";
        System.out.println(回退字符串.backspaceCompare(s, t));
    }
}
