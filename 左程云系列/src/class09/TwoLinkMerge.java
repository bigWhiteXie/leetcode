package class09;

public class TwoLinkMerge {
    static class Node{
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public static Node twoLinkMerge(Node head1, Node head2){
        //1. 判断是否为环形列表，是则返回入环节点，不是则返回null
        Node c1 = getLoop(head1);
        Node c2 = getLoop(head2);

        //2. 若一个是环另一个不是环则直接返回null
        if((c1 != null && c2 == null) || (c1 == null && c2 != null)){
            return null;
        }

        //3. 若两个都不是环，则先找到较长的列表s1,s1走完超出的部分后与s2一块走，若它两能相交则返回交点，否则返回null
        if(c1 == null && c2 == null){
            return noLoop(head1,head2);
        }

        //4. 若两个都是环，需要判断它两是在入环前还是入环后相交
        if(c1 != null && c2 != null) {
            //入环前相交：让较长的链表走完超出的部分，两个链表同时移动，若相交则返回
            //入环后相交：从入环节点开始遍历一圈，若遇到另一个入环节点则返回，若遇不到则返回null
            return loop(head1,c1,head2,c2);
        }
        return null;
    }

    private static Node loop(Node head1, Node c1, Node head2, Node c2) {
        //c1==c2说明在入环前相交
        if(c1 == c2) {
            //判断入环前是否相交
            Node cur1 = head1, cur2 = head2;
            int n = 0;
            while (cur1 != c1) {
                n++;
                cur1 = cur1.next;
            }

            while (cur2 != c2) {
                n--;
                cur2 = cur2.next;
            }

            Node s1 = n > 0 ? head1 : head2, s2 = s1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n-- > 0) {
                s1 = s1.next;
            }

            while (s1 != c1 && s2 != c2) {
                if (s1 == s2) {
                    return s1;
                }
                s1 = s1.next;
                s2 = s2.next;
            }
            return c1;
        }else { //否则 判断是否在入环后相交
            Node s1 = c1.next;
            while(s1 != c1){
                if(s1 == c2){
                    return s1;
                }
                s1 = s1.next;
            }
            return null;

        }

    }

    private static Node noLoop(Node head1, Node head2) {
        Node cur1 = head1, cur2 = head2;
        int n = 0;
        while(cur1 != null){
            n++;
            cur1 = cur1.next;
        }

        while(cur2 != null){
            n--;
            cur2 = cur2.next;
        }

        Node s1 = n > 0? head1 : head2, s2 = s1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while(n-- > 0){
            s1 = s1.next;
        }

        while(s1 != null && s2 != null){
            if(s1 == s2){
                return s1;
            }
            s1 = s1.next;
            s2 = s2.next;
        }
        return null;
    }

    private static Node getLoop(Node head) {
        Node slow = head,fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                break;
            }
        }
        if(slow != fast){
            return null;
        }

        fast = head;
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }

    public static void main(String[] args) {
        //场景1：两条相交的非环形链表
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(twoLinkMerge(head1,head2).val);

        //场景2：两条环形链表在入环前相交
        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next =  new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 =  new Node(0);
        head2.next = new Node(9);
        head2.next.next = new  Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(twoLinkMerge(head1,head2).val);

        //场景3：两条环形链表在入环后相交
        // 0->9->8->6->7->4->5->6..
        head2 =  new Node(0);
        head2.next = new  Node(9);
        head2.next.next =new  Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(twoLinkMerge(head1,head2).val);


    }
}
