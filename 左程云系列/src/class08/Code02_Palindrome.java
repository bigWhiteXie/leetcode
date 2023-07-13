package class08;

import javax.swing.plaf.SliderUI;

public class Code02_Palindrome {
    static class Node{
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }
    public static boolean isPalindrome3(Node head){
        if(head != null){
            Node slow = head,fast = head;
            while(slow.next != null && fast != null && fast.next != null){
                slow = slow.next;
                fast = fast.next.next;
            }

            Node root = reversed(slow);
            Node cur = head;
            while (cur != slow && root != null){
                if(cur.val != root.val){
                    return false;
                }
                cur = cur.next;
                root = root.next;
            }
            return true;
        }
        return true;
    }

    private static Node reversed(Node head) {
        if(head != null){
            Node cur = head,next = head.next,t;
            head.next = null;
            while(next != null){
                t = next.next;
                next.next = cur;

                cur = next;
                next = t;
            }
            return cur;
        }
        return null;
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
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.println(isPalindrome3(head));
        printLinkedList(head);

    }
}
