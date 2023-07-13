
import java.util.*;

/**
 * 
 */
public class Customer {

    private String address;


    private int id;


    private String name;


    private String sex;


    private String phone;

    private ProductPool pool;

    public Customer() {

    }

    public Customer(String address, int id, String name, String sex, String phone, ProductPool pool) {
        this.address = address;
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.pool = pool;
    }

    public Product consume(){
        if(pool.size() > 0) {
            Product pop = pool.subtract();
            System.out.println("消费产品：" + pop.toString());
            return pop;
        }
        System.out.println("队列中无产品了");
        return null;
    }
    /**
     * @return
     */
    @Override
    public String toString() {
        return "Customer{" +
                "address='" + address + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}