package 动态规划;

public class 最长公共子数组 {
    /**
     * 动态规划1：
     * 该解法的思想是
     * dp[i][j][k]:表示nums1以i开始，nums2以j开始，不考虑nums1[i]和nums2[j]就已经存在长度为k的最长公共子数组，考虑i，j时的公共子数组长度
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength1(int[] nums1, int[] nums2) {
        int[][][] dp = new int[nums1.length+1][nums2.length+1][Math.min(nums1.length,nums2.length)+1];
        int m = Math.min(nums1.length,nums2.length);
        //当j=nums2.length时
        for(int i = 0; i<=nums1.length;i++) {
            for (int k = 0 ;k <= m; k++) {
                dp[i][nums2.length][k] = k;
            }
        }

        //当i=nums1.length时
        for(int i = 0; i<=nums2.length;i++) {
            for (int k = 0 ;k <= m; k++) {
                dp[nums1.length][i][k] = k;
            }
        }

        for(int i=nums1.length-1;i>=0; i--){
            for(int j=nums2.length-1; j>=0;j--){
                for(int k = 0; k<m; k++) {
                    int r = k;
                    if (k!=m && nums1[i] == nums2[j]) {
                        r = dp[i+1][j+1][k+1];
                    }
                    int r1 = dp[i][j+1][0];
                    int r2 = dp[i+1][j][0];
                    dp[i][j][k] = Math.max(r,Math.max(r1,r2));
                }
            }
        }

        return dp[0][0][0];
    }

    /**
     * 动态规划2：
     * dp[i][j]表示的是公共子数组从nums[i]和nums[j]起始时的长度
     * 由于求的是子数组而不是子序列，因此当nums[i]和nums[j]不匹配时，公共长度要重新置为0
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength2(int[] nums1, int[] nums2) {
        //dp[i][j],表示nums1以i开始 nums2以j开始时的最长公共长度
        int[][] dp = new int[nums1.length+1][nums2.length+1];

        int maxLength = 0;
        for(int i=nums1.length-1;i>=0;i--){
            for(int j=nums2.length-1;j>=0;j--){
                if(nums1[i] == nums2[j]){
                    dp[i][j] = 1 + dp[i+1][j+1];
                }else{
                    dp[i][j] = 0;
                }
                maxLength = Math.max(dp[i][j],maxLength);
            }
        }

        return maxLength;
    }


    /**
     * 由于求的是子数组而不是子序列
     * 因此当不匹配时，count要重新置为0
     * @param nums1
     * @param nums2
     * @param i
     * @param j
     * @param count
     * @return
     */
    private static int findLongestCommonSubarray(int[] nums1, int[] nums2, int i, int j, int count) {
        if (i == nums1.length || j == nums2.length) {
            return count;
        }

        if (nums1[i] == nums2[j]) {
            count = findLongestCommonSubarray(nums1, nums2, i + 1, j + 1, count + 1);
        }

        int count1 = findLongestCommonSubarray(nums1, nums2, i + 1, j, 0);
        int count2 = findLongestCommonSubarray(nums1, nums2, i, j + 1, 0);

        return Math.max(count, Math.max(count1, count2));
    }
}
