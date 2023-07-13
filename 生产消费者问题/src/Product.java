
import java.util.*;

/**
 * 
 */
public class Product {

    private int pid;

    private float price;



    public Product(int pid, float price) {
        this.pid = pid;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", price=" + price +
                '}';
    }
}