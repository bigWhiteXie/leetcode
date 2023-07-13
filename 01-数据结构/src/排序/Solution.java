package 排序;

import java.math.BigInteger;

class Solution {
    public static int findValidSplit(int[] nums) {
        int pre = 1, last = 1;
        
        for(int i=0; i<nums.length; i++){
            last *= nums[i]; 
        }
        
        for(int i=0; i<nums.length; i++){
            pre *= nums[i];
            last = last/nums[i];
            if(gcd(pre,last)){
                return i;
            }
        }
        
        return -1;
    }
    
    public static boolean gcd(int a, int b){
        int min = a > b? b : a;
        for(int i=2;i<=min; i++){
            if(a%i==0 && b%i==0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
    }
}