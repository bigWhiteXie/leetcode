package 贪心;

public class 买卖股票最佳时机 {
    public static int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length+1][2];
        dp[prices.length][0] = 0;
        dp[prices.length][1] = 0;


        int index = prices.length-1;

        while(index>=0){
            //持有股票并卖掉
            int r1 = prices[index] + dp[index+1][1];
            //持有股票但不卖
            int r1_1 = dp[index+1][0];
            //没有股票时考虑购买股票
            int r2 = -prices[index] + dp[index+1][0];
            //没有股票但不买
            int r2_2 = dp[index+1][1];

            dp[index][0] = Math.max(r1,r1_1);
            dp[index][1] = Math.max(r2,r2_2);
            index--;
        }

        return dp[0][1];
    }

    public int tracking(int[] prices,int index,boolean flag){
        if(index == prices.length){
            return 0;
        }
        int r1 =0;
        int r2 =0;
        int r3 =0;
        //持有股票可以考虑卖掉股票
        if(flag) {
            r1 = prices[index] + tracking(prices,index+1,false);
        }

        //没有股票可以考虑买入股票
        if(!flag){
            r2 = -prices[index] + tracking(prices,index+1,true);
        }

        r3 = tracking(prices,index+1,flag);

        return Math.max(Math.max(r1,r2),r3);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{7,1,5,3,6,4};
        maxProfit(nums);
    }
}
