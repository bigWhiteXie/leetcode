package class02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class 两队实现栈 {
    static class MyStack{
        private LinkedList<Integer> que = new LinkedList<>();
        private LinkedList<Integer> help = new LinkedList<>();


        public void push(int val){
            que.add(val);
        }

        public int poll(){
            while(que.size()>1){
                help.add(que.poll());

            }
            int ans = que.poll();

            //将help中的元素倒回que中，用交换地址的方式最快
            //交换que和help，保证help中没有元素
            LinkedList temp = que;
            que = help;
            help = temp;

            return ans;
        }

        public int peek(){
            while(que.size()>1){
                help.add(que.poll());

            }
            int ans = que.poll();

            LinkedList temp = que;
            que = help;
            help = temp;
            que.add(ans);

            return ans;
        }

        public boolean isEmpty(){
            return que.isEmpty();
        }

    }


    public static void main(String[] args) {
//        System.out.println("test begin");
//        MyStack myStack = new MyStack();
//        Stack<Integer> test = new Stack<>();
//        int testTime = 1000000;
//        int max = 1000000;
//        for (int i = 0; i < testTime; i++) {
//            if (myStack.isEmpty()) {
//                if (!test.isEmpty()) {
//                    System.out.println("Oops");
//                }
//                int num = (int) (Math.random() * max);
//                myStack.push(num);
//                test.push(num);
//            } else {
//                if (Math.random() < 0.25) {
//                    int num = (int) (Math.random() * max);
//                    myStack.push(num);
//                    test.push(num);
//                } else if (Math.random() < 0.5) {
//                    if (!(myStack.peek()==test.peek())) {
//                        System.out.println("Oops");
//                    }
//                } else if (Math.random() < 0.75) {
//                    if (!(myStack.poll()==test.pop())) {
//                        System.out.println("Oops");
//                    }
//                } else {
//                    if (myStack.isEmpty() != test.isEmpty()) {
//                        System.out.println("Oops");
//                    }
//                }
//            }
//        }
//
//        System.out.println("test finish!");
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.peek());
    }
}
