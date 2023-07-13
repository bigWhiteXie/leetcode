package 笔试.赛码网风格;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Test17 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] capacity = new int[n]; //水杯大小
        int[] init = new int[n]; //初始水量
        int[] cost = new int[n]; //魔法消耗

        for(int i=0; i<n; i++){
            capacity[i] = scanner.nextInt();
        }
        for(int i=0; i<n; i++){
            init[i] = scanner.nextInt();
        }
        for(int i=0; i<n; i++){
            cost[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] test = new int[m];
        for(int i=0; i<m; i++){
            test[i] = scanner.nextInt();
            System.out.print(practice(capacity,init,cost,test[i]-1) + " ");
        }



    }

    public static int practice(int[] cap, int[] init, int[] costs,int j){
        PriorityQueue<Info> heap = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        int[] dis = new int[j+1];
        for(int i=0; i<=j; i++){
            dis[i] = cap[i] - init[i];
            heap.add(new Info(costs[i],i));
        }
        int total = 0;
        while(!heap.isEmpty()){
            Info info = heap.poll();
            int index = info.index;
            int c = info.cost;
            int end = j;
            int num = 0;
            while(end >= index){
                num += dis[end];
                dis[end] = 0;
                end--;
            }
            total += num*c;
        }

        return total;
    }
    static class Info{
        int cost;
        int index;

        public Info(int cost, int index) {
            this.cost = cost;
            this.index = index;
        }
    }
}
