package 滑动窗口;

/**
 * 输入：fruits = [1,2,3,2,2]
 * 输出：4
 * 解释：可以采摘 [2,3,2,2] 这四棵树。
 * 如果从第一棵树开始采摘，则只能采摘 [1,2] 这两棵树。
 *
 */
public class 水果成篮 {
    public static int totalFruit(int[] fruits) {
        //果篮的数量
        int capacity = 2, max = 0,size = 0;
        int group[] = new int[]{-1,-1};
        int j = 0;
        for(int i=0; i<fruits.length; i++){
            //新的窗口开始时将group、capacity和size都初始化
            group[0] = -1;
            group[1] = -1;
            capacity = 2;
            size = 0;
            j = i;
            //窗口
            while(j < fruits.length && capacity >= 0 ){
                int flag = 0;
                //遍历果篮，看是否有同种类水果
                for(int m=0; m < group.length; m++){
                    if(group[m] == fruits[j]){
                        flag = 1;
                        break;
                    }
                }
                // 若有同种类水果则数量+1,若无同种类水果则判断容量是否够，
                // 够的话则size+1,capacity-1，并添加这种水果
                if(flag == 0) {
                    if(capacity > 0){
                        group[group.length -capacity] = fruits[j];
                        size++;
                    }
                    capacity--;
                }else if(flag == 1){
                    size ++;
                }
                max = size > max ? size : max;
                j++;
            }
            if(j == fruits.length){
                break;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] fruits = {0,1,2,2};
        System.out.println(totalFruit(fruits));
    }
}
