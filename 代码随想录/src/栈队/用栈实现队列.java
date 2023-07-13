package 栈队;

import java.util.Stack;

public class 用栈实现队列 {
    static class MyQueue {
        Stack<Integer> in = new Stack<>();
        Stack<Integer> out = new Stack<>();

        public MyQueue() {

        }

        public void push(int x) {
            in.push(x);
        }

        public int pop() {
            if(!out.isEmpty()){
                return out.pop();
            }
            while(!in.isEmpty()){
                out.push(in.pop());
            }

            return out.pop();
        }

        public int peek() {
            if(out.empty()){
                while(!in.isEmpty()){
                    out.push(in.pop());
                }
            }
            return out.peek();
        }

        public boolean empty() {
            return out.empty() && in.empty();
        }
    }
}
