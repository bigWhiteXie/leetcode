
import java.util.*;

/**
 * 
 */
public class Test {

    public static void main(String[] args) {
        //创建存放产品的队列
        ProductPool pool = new ProductPool();
        //创建消费者
        Customer customer = new Customer("天津西青区", 1, "张三", "男", "11111", pool);

        //创建生产者
        //注意：生产者和消费者使用的要是同一个产品队列
        Producer producer = new Producer("天津西青区", 1, "张三", "男", "11111", pool);

        pool.addCustomer(customer);
        pool.addProducer(producer);
        System.out.println("===================================生产产品===================================");
        for(int i=1; i<=10;i++){
            producer.produce(i,i*10);
        }

        pool.display();

        System.out.println("===================================消费产品===================================");

        for(int i=1; i<=10;i++){
            customer.consume();
        }

        pool.display();

    }


}