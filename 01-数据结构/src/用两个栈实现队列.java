import java.util.Stack;

public class 用两个栈实现队列 {
    public static void main(String[] args) {
        CQueue queue = new CQueue();
        int[] arr = {1,2,3,4,5,6};
        for(int x:arr){
            queue.appendTail(x);
        }
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());

        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        Integer peek = s.peek();
        System.out.println(s.get(0));
    }


}

class CQueue {
    private Stack<Integer> st1 = new Stack<Integer>();
    private Stack<Integer> st2 = new Stack<Integer>();
    public CQueue() {

    }

    public void appendTail(int value) {
          st1.push(value);
    }

    public int deleteHead() {
        if(st1.isEmpty() && st2.isEmpty()){
            return -1;
        }
        if(st2.isEmpty()){
            while(!st1.isEmpty()){
                Integer x = st1.pop();
                st2.push(x);
            }
            return st2.pop();
        }

        return st2.pop();
    }
}
