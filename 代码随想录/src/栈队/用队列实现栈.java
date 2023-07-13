package 栈队;

import java.util.ArrayList;

public class 用队列实现栈 {
    static class MyStack {
        ArrayList<Integer> q1 = new ArrayList<>();

        public MyStack() {

        }

        public void push(int x) {
            q1.add(x);
        }

        public int pop() {
            return q1.remove(q1.size()-1);
        }

        public int top() {
            return q1.get(q1.size()-1);
        }

        public boolean empty() {
            return q1.isEmpty();
        }
    }
}
