import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class 包含min函数的栈 {
    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(-1);
        stack.top();
        System.out.println(stack.min());
        stack.push(1);
        stack.top();
        System.out.println(stack.min());
    }
}

class MinStack {

    /** initialize your data structure here. */
    private LinkedList<Integer> stack1 = new LinkedList<>();
    private LinkedList<Integer> stack2 = new LinkedList<>();

    public MinStack() {

    }

    public void push(int x) {
        if(stack1.isEmpty() || x <= stack2.getLast()){
            System.out.println(x + "入栈");
            stack2.add(x);
            stack1.add(x);
            return ;
        }
        stack1.add(x);
    }

    public void pop() {
         if(stack1.getLast().equals(stack2.getLast())){
             stack1.removeLast();
             stack2.removeLast();
         }else{
             stack1.removeLast();
         }
    }

    public int top() {
     return stack1.getLast();
    }

    public int min() {

        return stack2.getLast();
    }
}
