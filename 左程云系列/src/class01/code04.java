package class01;

import javax.management.MBeanAttributeInfo;

public class code04 {
    public static int mySqrt(int x) {
        int left =1, right = x;
        int mid;
        while(left <= right){
            mid = left + ((right - left) >> 1);
            long res = (long) mid * mid;
            if(res == x ){
                return mid;
            }else if( res > x){
                right = mid - 1;
            } else{
                left = mid + 1;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(2147395599));
    }
}
