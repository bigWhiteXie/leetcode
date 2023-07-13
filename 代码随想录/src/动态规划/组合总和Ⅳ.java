package 动态规划;

/**
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。
 * 请你从 nums 中找出并返回总和为 target 的元素排列的个数。
 * 输入：nums = [1,2,3], target = 4
 * 输出：7
 * 解释：
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 注意：本题求的是排列，之前做题习惯是从左到右尝试，这种尝试方法会直接过滤掉相同的组合，
 *      而此题需要保留相同的组合，因此每层递归都需要完全遍历数组
 */
public class 组合总和Ⅳ {

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;

        for(int index=1; index<=target;index++){
            int count = 0;
            for(int i=0; i<nums.length;i++){
                int res = index - nums[i];
                if(res > 0) {
                    int p = dp[res];
                    count += p;
                }
            }

            dp[index] = count;
        }
        return dp[target];
    }

    public int process(int[] nums, int target){
        if(target == 0){
            return 1;
        }



        int count = 0;
        for(int i=0; i<nums.length;i++){
            int res = target - nums[i];
            if(res >= 0) {
                int p = process(nums, res);
                count += p;
            }
        }

        return count;


    }
}
