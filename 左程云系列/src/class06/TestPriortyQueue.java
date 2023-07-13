package class06;

import java.util.PriorityQueue;

public class TestPriortyQueue {
    /**
     * jdk提供的堆不具备动态调整的能力
     * @param args
     */
    public static void main(String[] args) {
        PriorityQueue<Code01_topK.Customer> queue = new PriorityQueue<>(new Code01_topK.DaddyCom());
        Code01_topK.Customer customer = new Code01_topK.Customer(1, 2, 1);
        Code01_topK.Customer customer2 = new Code01_topK.Customer(2, 3, 1);
        Code01_topK.Customer customer3 = new Code01_topK.Customer(3, 4, 1);
        queue.add(customer);
        queue.add(customer3);
        queue.add(customer2);
        System.out.println(queue.peek().buyer);
        customer3.buyer=0;

        System.out.println(queue.peek().buyer);
    }
}
