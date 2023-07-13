package 笔试.赛码网风格;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 小美在玩一项游戏。该游戏的目标是尽可能抓获敌人。
 * 敌人的位置将被一个二维坐标 (x, y) 所描述。
 * 小美有一个全屏技能，该技能能一次性将若干敌人一次性捕获。
 * 捕获的敌人之间的横坐标的最大差值不能大于A，纵坐标的最大差值不能大于B。
 * 现在给出所有敌人的坐标，你的任务是计算小美一次性最多能使用技能捕获多少敌人。
 *
 * 3 1 1
 * 1 1
 * 1 2
 * 1 3
 * 输出：2
 */
public class Test5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        Point[] list = new Point[n];
        for(int i=0;i<n;i++){
            int x = in.nextInt();
            int y = in.nextInt();

            list[i] = new Point(x,y);
        }

        int num = maxCatch(list,a,b);
        System.out.println(num);

    }

    public static int maxCatch(Point[] arr, int a, int b) {
        int slow = 0, fast = 0;
        int max = 0;
        Arrays.sort(arr);
        while(slow < arr.length){
            if(fast == arr.length){
                break;
            }
            int x = arr[slow].x;
            int y = arr[slow].y;
            while(fast<arr.length && arr[fast].x - x <=a && arr[fast].y - y <= b ){
                fast++;
            }
            max = Math.max(max,fast-slow);
            slow++;

        }


        return max;
    }

    static class Point implements Comparable<Point>{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if(x != o.x){
                return x - o.x;
            }
            return y - o.y;
        }
    }

//    static class PointComp implements Comparator<Point>{
//
//        @Override
//        public int compare(Point o1, Point o2) {
//            if(o1.x != o2.x){
//                return o1.x - o2.x;
//            }
//
//            return o1.y - o2.y;
//        }
//    }


}
