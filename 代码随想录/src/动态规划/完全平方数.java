package 动态规划;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量
 *
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 */
public class 完全平方数 {
    public int numSquares(int n) {
        ArrayList<Integer> nums = new ArrayList<>();
        if(n >= 1){
            nums.add(1);
        }
        for(int i=2;i<=n;i++){
          if(isPerfectSquare(i)){
              nums.add(i);
          }
        }

        int[][] dp = new int[nums.size()+1][n+1];

        for(int j=1;j<=n;j++){
            dp[nums.size()][j] = -1;
        }

        for(int index = nums.size()-1; index>=0; index--){
            for(int j=1;j<=n;j++){
                int min = Integer.MAX_VALUE;
                int count = 0;
                int res = j;
                while(res >= 0){
                    res = j - count * nums.get(index);
                    if(res < 0){
                        break;
                    }
                    int p = dp[index+1][res];
                    if(p != -1){
                        min = Math.min(min,p+count);
                    }
                    count++;
                }

                dp[index][j] = min == Integer.MAX_VALUE ? -1 : min;
            }
        }
        return dp[0][n];
    }

    public int procee(List<Integer> list,int index,int target){
        if(target == 0){
            return 0;
        }

        if(index == list.size() || target < 0){
            return -1;
        }
        int min = Integer.MAX_VALUE;
        int count = 0;
        int res = target;
        while(res >= 0){
            res = target - count * list.get(index);
            if(res < 0){
                break;
            }
            int p = procee(list,index+1,res);
            if(p != -1){
                min = Math.min(min,p+count);
            }
            count++;
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public boolean isPerfectSquare(int num) {
        if (num < 0) {
            return false;
        }

        if (num == 0 || num == 1) {
            return true;
        }

        long start = 1;
        long end = num / 2;

        while (start <= end) {
            long mid = (start + end) / 2;
            long square = mid * mid;

            if (square == num) {
                return true;
            } else if (square < num) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return false;
    }

}
