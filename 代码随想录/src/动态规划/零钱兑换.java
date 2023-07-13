package 动态规划;

/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 */
public class 零钱兑换 {
    public int coinChange(int[] coins, int amount) {
        int[][] dp =new int[coins.length+1][amount+1];

        for(int j=0; j<=amount; j++){
            dp[coins.length][0] = -1;
        }

        for(int index=coins.length - 1; index>=0; index--){
            for(int j=1; j<=amount; j++){
                int min = Integer.MAX_VALUE;
                int count = 0;
                int res = j;
                while(res >= 0){
                    res = j - count * coins[index];
                    if(res < 0){
                        break;
                    }
                    int p = dp[index+1][res];
                    if(p != -1) {
                        min = Math.min(min, count + p);
                    }
                    count++;

                }

                dp[index][j] = min == Integer.MAX_VALUE ? -1 : min;
            }
        }
        return dp[0][amount];
    }

    int process(int[] coins, int index, int target){
        if(target == 0){
            return 0;
        }

        if(index == coins.length || target < 0){
            return -1;
        }

        int min = Integer.MAX_VALUE;
        int count = 0;
        int res = target;
        while(res >= 0){
            res = target - count * coins[index];
            if(res < 0){
                break;
            }
            int p = process(coins,index+1,res);
            if(p != -1) {
                min = Math.min(min, count + p);
            }
            count++;

        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
