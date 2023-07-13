package class08;

import java.util.ArrayList;

public class Code03_smalleEqualBigger {
    static class Node{
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public static Node listPartition(Node head,int target){
        if(head != null) {
            ArrayList<Node> list = new ArrayList<>();

            Node cur = head;
            while (cur != null) {
                list.add(cur);
                cur = cur.next;
            }

            patition(list, target);

            Node root = list.get(0);
            cur = root;
            for(int i=1; i<list.size(); i++){
                cur.next = list.get(i);
                cur = cur.next;
            }
            cur.next = null;
            return root;
        }
        return null;
    }

    private static void patition(ArrayList<Node> list, int target) {
        int l_bound = -1,r_bound = list.size() - 1;
        int l = 0, r = list.size() - 1;
        while(l <= r){
            if(list.get(l).val < target){
                swap(list,l,++l_bound);
            }else if(list.get(l).val == target){
                l++;
            }
            else{
                swap(list,l,r--);
                r_bound--;
            }
        }
    }

    private static void swap(ArrayList<Node> list, int l, int i) {
        Node t = list.get(l);
        list.set(l,list.get(i));
        list.set(i,t);
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        // head1 = listPartition1(head1, 4);
        head1 = listPartition(head1, 5);
        printLinkedList(head1);
    }
}
