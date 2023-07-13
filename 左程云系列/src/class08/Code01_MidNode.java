package class08;

import java.util.ArrayList;
import java.util.Arrays;

public class Code01_MidNode {
    static class Node{
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public static Node midOrUpMidNode(Node root){
        if(root != null){
            Node slow = root,fast = root;
            while(slow.next!=null && fast != null && fast.next != null){
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
        return null;
    }

    public static Node midOrDownMidNode(Node root){
        if(root != null){
            Node slow = root,fast = root;
            while(slow.next!=null && fast!= null && fast.next!=null && fast.next.next != null){
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
        return null;
    }

    public static Node midOrUpMidPreNode(Node head) {
        Node pre = null;
        if(head != null){
            Node slow = head,fast = head;
            while(slow.next!=null && fast!=null && fast.next != null){
                pre = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
        }
        return pre;
    }

    public static Node midOrDownMidPreNode(Node head) {
        Node pre = null;
        if(head != null){
            Node slow = head,fast = head;
            while(slow.next!=null && fast!=null && fast.next != null && fast.next.next != null){
                pre = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
        }
        return pre;
    }

    public static Node reverseList(Node head){
        if(head != null){
            Node cur = head,next = head.next,t;
            while(next != null){
                t = next.next;
                next.next = cur;

                cur = next;
                next = t;
            }
            head.next = null;
            return cur;
        }
        return null;
    }
    public static Node right1(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 1) / 2);
    }

    public static Node right2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get(arr.size() / 2);
    }


    public static Node right3(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 3) / 2);
    }

    public static Node right4(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 2) / 2);
    }

    public static void main(String[] args) {
        Node test = null;
        test = new Node(0);
        test.next = new Node(1);
        test.next.next = new Node(2);
        test.next.next.next = new Node(3);
        test.next.next.next.next = new Node(4);
        test.next.next.next.next.next = new Node(5);
        test.next.next.next.next.next.next = new Node(6);
        test.next.next.next.next.next.next.next = new Node(7);
        test.next.next.next.next.next.next.next.next = new Node(8);
        test.next.next.next.next.next.next.next.next.next = new Node(9);

        Node ans1 = null;
        Node ans2 = null;

        ans1 = midOrUpMidNode(test);
        ans2 = right2(test);
        System.out.println(ans1 != null ? ans1.val : "无");
        System.out.println(ans2 != null ? ans2.val : "无");

        ans1 = midOrDownMidNode(test);
        ans2 = right1(test);
        System.out.println(ans1 != null ? ans1.val : "无");
        System.out.println(ans2 != null ? ans2.val : "无");

        ans1 = midOrUpMidPreNode(test);
        ans2 = right4(test);
        System.out.println(ans1 != null ? ans1.val : "无");
        System.out.println(ans2 != null ? ans2.val : "无");

        ans1 = midOrDownMidPreNode(test);
        ans2 = right3(test);
        System.out.println(ans1 != null ? ans1.val : "无");
        System.out.println(ans2 != null ? ans2.val : "无");

        Node root = reverseList(test);
        while(root!=null){
            System.out.print(root.val + " ");
            root = root.next;
        }
    }


}
