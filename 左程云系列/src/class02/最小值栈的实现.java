package class02;

import java.util.Stack;

/**
 * 要求：每次出栈都是栈中最小元素
 */
public class 最小值栈的实现 {
    static class MinValStack{
        Stack<Integer> valStack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();
        private int min;

        public void push(int val){
            if(valStack.isEmpty()){
                min = val;
            }

            min = val<min? val:min;
            valStack.push(val);
            minStack.push(min);
        }

        public int pop(){
            if(valStack.isEmpty()){
                throw new RuntimeException("栈空，无法出栈");
            }
            valStack.pop();
            return minStack.pop();
        }

        public boolean isEmpty(){
            return valStack.isEmpty();
        }
    }

    public static void main(String[] args) {
        MinValStack stack = new MinValStack();
        stack.push(4);
        stack.push(1);
        stack.push(6);
        stack.push(3);
        stack.push(10);
        stack.push(2);

        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}
