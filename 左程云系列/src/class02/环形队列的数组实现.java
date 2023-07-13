package class02;

import java.util.ArrayList;
import java.util.List;

public class 环形队列的数组实现 {
    static class MyQue{
        private int[] arr;
        private int size;
        private int limit;
        private int head;
        private int rear;

        public MyQue(int limit){
            arr = new int[limit];
            this.limit = limit;
            head = 0;
            rear = 0;
            size = 0;
        }


        public void push(int a){
            if(size == limit){
                System.out.println("队满，无法继续添加");
                return ;
            }

            arr[(rear++) % limit] = a;
            size++;
        }


        public int pop(){
            if(size == 0){
                throw new RuntimeException("队列为空，无法出队");
            }

            int ans = arr[(head++) % limit];
            size--;
            return ans;
        }

        public boolean isEmpty(){
            return size ==0;
        }
    }

    public static List<Integer> randomNodeList(int size, int range){
        List<Integer> list = new ArrayList<>();
        while(size-- > 0){
            int num = (int) (Math.random() * (range + 1));
            list.add(num);
        }

        return list;
    }

    public static void main(String[] args) {
        MyQue myQue = new MyQue(6);
        List<Integer> list = randomNodeList(12, 7);
        for(int i:list){
            System.out.print(i + " ");
            myQue.push(i);
        }

        System.out.println();
        while(!myQue.isEmpty()){
            System.out.print(myQue.pop() + " ");
        }
    }

}
