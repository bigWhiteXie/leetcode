import java.util.LinkedList;

public class Producer extends ProductPool {

    private int id;

    private String address;
    private String name;


    private String sex;


    private String phone;

    private ProductPool pool;

    public Producer(String address, int id, String name, String sex, String phone, ProductPool pool) {
        this.address = address;
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.pool = pool;
    }

    public void produce(int pid, float price){
        Product product = new Product(pid, price);
        pool.add(product);
        System.out.println("生产产品：" + product.toString());
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
