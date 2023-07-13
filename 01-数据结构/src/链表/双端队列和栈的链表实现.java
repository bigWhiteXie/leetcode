package 链表;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class 双端队列和栈的链表实现 {
    public static class Node {
        public int value;
        public Node next;
        public Node last;

        public Node(int data) {
            value = data;
        }
    }
    static class DoubleEndQueue{
        Node head;
        Node rear;

        public void headAdd(Node node){
            if(isEmpty()){
                head = node;
                rear = node;
                return ;
            }

            node.next = head;
            head.last = node;
            head = node;
        }

        public Node headPop(){
            Node n;
            if(isEmpty()){
                return null;
            }
            if(head == rear){
                n = head;
                head = null;
                rear = null;
                return n;
            }
            n = head;
            head = head.next;
            head.last = null;
            return n;

        }

        public void endAdd(Node node){
            if(isEmpty()){
                head = node;
                rear = node;
                return ;
            }

            node.last = rear;
            rear.next = node;
            rear = node;
        }

        public Node endPop(){
            Node n;
            if(isEmpty()){
                return null;
            }
            if(head == rear){
                n = head;
                head = null;
                rear = null;
                return n;
            }
            n = rear;
            rear = rear.last;
            head.next = null;
            return n;

        }

        public boolean isEmpty(){
            return head == null || rear == null;
        }
    }


    public static List<Node> randomNodeList(int size,int range){
        List<Node> list = new ArrayList<>();
        while(size-- > 0){
            int num = (int) (Math.random() * (range + 1));
            Node node = new Node(num);
            list.add(node);
        }

        return list;
    }

    public static void main(String[] args) {
        List<Node> list = randomNodeList(10, 6);
        DoubleEndQueue doubleEndQueue = new DoubleEndQueue();
        for(Node n:list){
            System.out.print(n.value + " ");
            doubleEndQueue.headAdd(n);
        }
        System.out.println();
        while(!doubleEndQueue.isEmpty()){
            Node node = doubleEndQueue.headPop();
            System.out.print(node.value + " ");
        }


    }
}
