package class01;

import java.util.HashMap;
import java.util.Set;

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

//        HashMap<Integer, Integer> map = new HashMap<>();
//        for (int item : arr) {
//            int i = 0;
//            int temp = item;
//            while((temp >> i) > 0){
//                if(((temp >> i) & 1) != 0){
//                    Integer absent = map.putIfAbsent(i, 1);
//                    if(absent != null) {
//                        map.put(i, map.get(i) + 1);
//                    }
//                }
//                i++;
//            }
//        }
//
//        Set<Integer> keySet = map.keySet();
//        int ans = 0;
//        for (Integer key : keySet) {
//            if(map.get(key) % m == k){
//                ans += (1 << key);
//            }
//        }
//
//        return ans;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int item : arr) {
            for(int i=0;i<32;i++ ){
                if((item & (1<<i)) != 0){
                    Integer absent = map.putIfAbsent(i, 1);
                    if(absent != null){
                        map.put(i,map.get(i) + 1);
                    }
                }
            }
        }
        int ans = 0;
        for (Integer key : map.keySet()) {
            if(map.get(key) % m == k){
                ans += (1 << key);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] ints = {1, 1,1, 2, 2, 2, 3, 3, 3, 4, 4,4,5,6,6,5,6};
        int km = KM(ints, 2, 3);
        System.out.println(km);
    }
}
