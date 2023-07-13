package 笔试.赛码网风格;

import java.util.Scanner;

/**
 * 现在有若干节点。每个节点上有能量塔。所有节点构成一棵树。
 *
 * 某个节点u可以为和u距离不超过给定值的节点各提供一点能量。
 *
 * 此处距离的定义为两个节点之间经过的边的数量。特别的，节点u到本身的距离为零。
 * 现在给出每个节点上的能量塔可以为多远的距离内的点提供能量。
 * 小美想要探究每个节点上的能量值具体是多少。你的任务是帮助小美计算得到，并依次输出。
 *
 *
 * 第一行一个整数N，表示节点的数量。
 * 接下来一行N个以空格分开的整数，依次表示节点1，节点2，…，节点N的能量塔所能提供能量的最远距离。
 * 接下来N-1行，每行两个整数，表示两个点之间有一条边。
 * 1≤N≤500，节点上能量塔所能到达的最远距离距离不会大于 500.
 *
 * 3
 * 1 1 1
 * 1 2
 * 2 3
 * 3个节点，每个节点提供能量的最远距离是1，
 * 输出：2 3 2
 *
 */
public class Test9 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] dis = new int[n];
        for(int i=0;i<n;i++){
            dis[i] = in.nextInt();
        }

        for(int i=0;i<n-1;i++){
            int start = in.nextInt();
            int end = in.nextInt();
        }

    }


    static class Edge{
        int start;
        int end;


    }
}
