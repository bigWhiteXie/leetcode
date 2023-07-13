package class01;

public class Code03_KM {
    /**
     * 一个数组中，只有一种数出现了K次，其余数都出现了M次，求出现K次的数 (K<M ,且M不为1)
     * 思路：将每个数展开成32位的二进制，并创建一个大小为32位的数组，按照数的每个位置是否为1再对数组元素+1，
     * 最后遍历数组，如果第i位上的值不是m的倍数并且余数为k说明要求的数第i位上为1
     * @param arr
     * @param k
     * @param m
     * @return
     */
    public static int KM(int[] arr, int k, int m){
        int eor = 0;
        int[] count = new int[32];
        for(int item:arr){
            for(int i = 0; i<32; i++){
                int temp = item >> i;
                if((temp & 1) != 0){
                    count[i]++;
                }
                if(temp <= 0){
                    break;
                }
            }
        }
        int ans = 0;
        for(int i=0; i<32; i++){
            if(count[i] % m == 0){
                continue;
            }

            if(count[i]%m == k){
                ans += 1<<i;
            }else{
                return -1;
            }
        }

        return ans;

    }

    public static void main(String[] args) {
        int[] ints = {1, 1,1, 2, 2, 2, 3, 3, 3, 4, 4,5,6,5,6,5,6};
        int km = KM(ints, 2, 3);
        System.out.println(km);
    }
}
