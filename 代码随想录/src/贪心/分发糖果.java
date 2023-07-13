package 贪心;

import java.util.HashMap;
import java.util.PriorityQueue;

public class 分发糖果 {
    public static int candy(int[] ratings) {
        int cur = 1,sum = 0;
        HashMap<Integer, Integer> candiesMap = new HashMap<>();
        PriorityQueue<Point> heap = new PriorityQueue<>((a,b)->{
            return a.rate - b.rate;
        });

        int index = 0;
        for (int rating : ratings) {
            Point point = new Point(index++, rating);
            heap.add(point);

        }

        while(candiesMap.keySet().size() < ratings.length){
            //取出最小元素
            Point point = heap.poll();
            int curIndex = point.index;
            //判断map中该元素是否已存在
            if(!candiesMap.containsKey(point.index)){
                //判断相邻位置是否存在元素
                if(candiesMap.containsKey(curIndex+1) || candiesMap.containsKey(curIndex-1)){
                    int max = Integer.MIN_VALUE;
                    //存在则比较相邻元素和当前元素的rate
                    if(candiesMap.get(curIndex+1)!=null){
                        if(ratings[curIndex] > ratings[curIndex+1]) {
                            max = Math.max(max, candiesMap.get(curIndex + 1));
                        }
                    }

                    if(candiesMap.get(curIndex-1)!=null){
                        if(ratings[curIndex] > ratings[curIndex-1]) {
                            max = Math.max(max, candiesMap.get(curIndex - 1));
                        }
                    }
                    if(max!=Integer.MIN_VALUE) {
                        candiesMap.put(curIndex, max + 1);
                        sum += max +1;
                    }else{
                        candiesMap.put(curIndex,1);
                        sum += 1;
                    }
                }else{
                    candiesMap.put(curIndex,1);
                    sum += 1;
                }
            }
        }
        return  sum;
    }

    static class Point{
        int index;
        int rate;

        public Point(int index, int rate) {
            this.index = index;
            this.rate = rate;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,3,2,2,1};
        int candy = candy(arr);
        System.out.println(candy);
    }

}
