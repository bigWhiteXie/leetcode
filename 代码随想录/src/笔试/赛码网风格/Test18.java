package 笔试.赛码网风格;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test18 {
    private List<Integer> res =  new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    /**
     * @param price int整型一维数组 宝石价格列表
     * @param k int整型 行囊个数
     * @return int整型
     */
    public int putGems (int[] price, int k) {
        tracking(price,0,k);
        Integer[] array = res.toArray(new Integer[0]);
        Arrays.sort(array);
        return array[array.length - 1] - array[0];

    }

    public void tracking(int[] price,int index,int k){
        if(k == 0){
            if(index != price.length){
                path.remove(path.size()-1);
                path.add(price.length-1);
            }
            int sum = 0;
            int begin = 0;
            for (int end : path) {
                sum += price[begin] + price[end];
                begin = end+1;
            }
            res.add(sum);
            return;
        }

        for(int i=index;i<=price.length - k;i++){
            path.add(i);
            tracking(price,i+1,k-1);
            path.remove(path.size()-1);
        }

    }

    public static void main(String[] args) {
        int[] prices = {2,3,5,3};
        int k = 2;
    }
}
