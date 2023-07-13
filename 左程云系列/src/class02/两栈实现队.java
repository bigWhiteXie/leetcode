package class02;

import java.util.Stack;

public class 两栈实现队 {
    static class MyQue {
        private Stack<Integer> inputStack = new Stack<>();
        private Stack<Integer> outputStack = new Stack<>();

        public void push(int val) {
            inputStack.push(val);
        }

        public int poll() {
            in2out();

            return outputStack.pop();
        }

        private void in2out() {
            if (outputStack.isEmpty()) {
                while (!inputStack.isEmpty()) {
                    outputStack.push(inputStack.pop());
                }
            }
        }

        public boolean isEmpty() {
            return inputStack.isEmpty() && outputStack.isEmpty();
        }

        public int peek() {
            in2out();
            return outputStack.peek();
        }
    }

    public static void main(String[] args) {
        MyQue test = new MyQue();
        test.push(1);
        test.push(2);
        test.push(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());

    }
}
