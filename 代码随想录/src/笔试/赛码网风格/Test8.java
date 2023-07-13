package 笔试.赛码网风格;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 现在商店里有N个物品，每个物品有原价和折扣价。
 *
 * 小美想要购买商品。小美拥有X元，一共Y张折扣券。
 *
 * 小美需要最大化购买商品的数量，并在所购商品数量尽量多的前提下，尽量减少花费。
 *
 * 你的任务是帮助小美求出最优情况下的商品购买数量和花费的钱数。
 *
 * 3 5 1
 * 4 3
 * 3 1
 * 6 5
 *
 * 输出 2 5
 */
public class Test8 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int money = in.nextInt();
        int count = in.nextInt();

        Good[] goods = new Good[n];

        for(int i=0; i<n; i++){
            int old = in.nextInt();
            int pri = in.nextInt();
            goods[i] = new Good(old,pri);
        }

        computeCost(goods,money,count);


    }

    private static void computeCost(Good[] goods, int money, int count) {
        int num = 0, cost = 0;

        HashSet<Good> buy = new HashSet<>();
        PriorityQueue<Good> oldHeap = new PriorityQueue<>(new OldComp());
        PriorityQueue<Good> priHeap = new PriorityQueue<>(new PriComp());

        for (Good good : goods) {
            oldHeap.add(good);
            priHeap.add(good);
        }

        while(count-- > 0){
            if(priHeap.peek().price <= money){
                Good good = priHeap.poll();
                money -= good.price;
                cost += good.price;
                buy.add(good);
            }
        }
        while(money > 0 && oldHeap.size() > 0){
            Good good = oldHeap.poll();
            if(good.oldPrice <= money && !buy.contains(good)){
                money -= good.oldPrice;
                cost += good.oldPrice;
                buy.add(good);
            }
        }

        System.out.println(buy.size() +" " + cost);
    }


    static class Good{
        int oldPrice;
        int price;

        public Good(int oldPrice, int price) {
            this.oldPrice = oldPrice;
            this.price = price;
        }
    }

    static class OldComp implements Comparator<Good>{

        @Override
        public int compare(Good o1, Good o2) {
            return o1.oldPrice - o2.oldPrice;
        }
    }

    static class PriComp implements Comparator<Good>{

        @Override
        public int compare(Good o1, Good o2) {
            return o1.price - o2.price;
        }
    }

    static class DisComp implements  Comparator<Good>{

        @Override
        public int compare(Good o1, Good o2) {
            return (o2.oldPrice - o2.price) - (o1.oldPrice - o1.price);
        }
    }
}
