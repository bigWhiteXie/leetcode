package class08;

import class09.Code02_TreeOrder;

import java.util.HashMap;

public class Code04_randomCopy {
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
        }
    }

    public static Node randomCopy(Node head) {
        if (head != null) {
            HashMap<Node, Node> nodeMap = new HashMap<>();
            Node cur = head;
            while (cur != null) {
                nodeMap.put(cur, new Node(cur.val));
                cur = cur.next;
            }

            cur = head;
            Node root;
            root = nodeMap.get(cur);
            Node cur2 = root;
            while (cur.next != null) {
                cur2.next = nodeMap.get(cur.next);
                cur2.random = nodeMap.get(cur.random);
                cur = cur.next;
                cur2 = cur2.next;
            }

            return root;
        }
        return null;
    }

    public static boolean linkEqual(Node node1, Node node2) {
        Node cur1 = node1, cur2 = node2;

        while (cur1 != null && cur2 != null) {
            if(cur1.random != null && cur2.random != null) {
                if (cur1.random.val != cur2.random.val || cur1.val != cur2.val) {
                    return false;
                }
            }else{
                if(cur1.random == null && cur2.random == null){
                    if(cur1.val != cur2.val){
                        return false;
                    }
                }else{
                    return false;
                }
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        if (cur1 != null || cur2 != null) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);

        head1.random = head1.next.next;
        head1.next.next.random = head1;

        // head1 = listPartition1(head1, 4);
        Node head2 = randomCopy(head1);
        boolean equal = linkEqual(head1, head2);
        System.out.println(equal);

    }
}
