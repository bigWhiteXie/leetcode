package 数组01;

public class 删除有序数组重复元素 {
    /**
     * 由于是有序数组，因此如果存在相同元素那它们一定是相邻的
     * 因此我们可以用快慢指针遍历，当快指针慢指针指向不同的时候用快指针当前的元素填充慢指针的下一个元素
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        int slow = 0, fast = 1;
        while(fast < nums.length){
            if(nums[slow] != nums[fast]){
                nums[slow+1] = nums[fast];
                slow++;
            }
            fast++;
        }

        return slow;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,1,2};
//        removeDuplicates(arr);
        String s = "123456";
        System.out.println(String.valueOf(s.toCharArray(), 0, 5));
    }
}
