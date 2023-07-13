import java.util.ArrayList;
import java.util.LinkedList;

public class ProductPool {
    private LinkedList<Product> list = new LinkedList<>();

    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Producer> producers = new ArrayList<>();


    public void addCustomer(Customer customer){
        customers.add(customer);
        System.out.println("添加顾客：" + customer.toString());
    }

    public void addProducer(Producer producer){
        producers.add(producer);
        System.out.println("添加生产者：" + producer.toString());
    }
    public void add(Product p){
        list.add(p);
    }

    public Product subtract(){
        return list.pop();
    }

    public int size(){
        return list.size();
    }

    public void display(){
        System.out.println("=================================生产者队列=================================");
        for(Producer p:producers){
            System.out.println(p);
        }
        System.out.println("=================================顾客队列=================================");
        for(Customer c:customers){
            System.out.println(c);
        }
        System.out.println("=================================产品队列=================================");
        for(Product p:list){
            System.out.println(p);
        }
    }
}
