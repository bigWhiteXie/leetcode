package 贪心;

public class 摆动序列 {
    /**
     * 题解：将题目转化为数组中有多少山顶、山谷元素
     * 山顶：preDiff>=0 postDiff < 0;
     * 山谷：preDiff<=0 postDiff > 0;
     * res初始化为1，最右边的元素我们遍历不到，但它一定是山顶或山谷
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        if(nums.length == 0){ return 0;}
        if(nums.length == 1){
            return 1;
        }

        int res = 1;
        int preDiff = 0;
        int postDiff = 0;
        for(int i=0;i<nums.length-1;i++){
            if(nums[i] == nums[i+1]){
                continue;
            }
            postDiff = nums[i+1] - nums[i];
            //考虑到两端元素，当preDiff=0时也满足情况
            if((preDiff>=0 && postDiff < 0) || (preDiff <=0 && postDiff > 0)){
                res+=1;
            }
            preDiff = postDiff;

        }

        return res;
    }


}
