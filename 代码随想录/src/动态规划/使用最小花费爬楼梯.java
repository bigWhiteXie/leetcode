package 动态规划;

public class 使用最小花费爬楼梯 {
    public static int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length+1];
        dp[cost.length] = 0;
        int i = dp.length-1;
        while(i >= 0){
            if(i+2 >= dp.length){
                dp[i] = cost[i] + dp[i+1];
            }else{
                dp[i] = cost[i] + Math.min(dp[i],dp[i+1]);
            }
            i--;
        }

        return Math.min(dp[0],dp[1]);
    }

    public static int dp(int[] cost,int index){
        if(index >= cost.length ){
            return 0;
        }

        int r1 = cost[index] + dp(cost,index+1);
        int r2 = cost[index] + dp(cost,index+2);

        return Math.min(r1,r2);
    }

    public static void main(String[] args) {
        int[] cost = {1,100,1,1,1,100,1,1,100,1};
        int i = minCostClimbingStairs(cost);
        System.out.println(i);
    }
}
