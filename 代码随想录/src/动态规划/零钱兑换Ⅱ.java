package 动态规划;

/**
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 * 假设每一种面额的硬币有无限个。
 */
public class 零钱兑换Ⅱ {
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length+1][amount+1];

        for(int i=0;i<=coins.length; i++){
            dp[i][0] = 1;
        }

        for(int index = coins.length-1; index>=0; index--){
            for(int j=1;j<=amount;j++){
                int res = j;
                int count = 0;
                while (res >= 0){
                    int p = dp[index+1][res];
                    count += p;

                    res -= coins[index];
                    if(res < 0){
                        break;
                    }
                }

                dp[index][j] = count;
            }
        }

        return dp[0][amount];
    }

    private int process(int[] coins, int index, int amount) {
        if(amount == 0){
            return 1;
        }
        if(index == coins.length){
            return 0;
        }
        if(amount < 0){
            return -1;
        }

        int res = amount;
        int count = 0;
        while (res >= 0){
            int p = process(coins,index+1,res);
            count += p;

            res -= coins[index];
            if(res < 0){
                break;
            }
        }

        return count;
    }



    public static void main(String[] args) {
        零钱兑换Ⅱ ins = new 零钱兑换Ⅱ();
        int change = ins.change(5, new int[]{1, 2, 5});
        System.out.println(change);
    }
}
