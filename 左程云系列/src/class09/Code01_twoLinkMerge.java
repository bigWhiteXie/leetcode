package class09;

public class Code01_twoLinkMerge {
    static class Node{
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * 找出两条链表相交的第一个节点，它们可能相交也可能不相交，链表可能是环形也可能不是环形
     * 解题步骤：
     *      1.分别判断两条链表是否为环形链表，是的话则返回第一个入环节点
     *      2.根据单链表相交的性质有如下结论：
     *          2.1 两条非环形的链表相交，较长的链表走往超出的部分后，较短的链表开始遍历，等到它们相等即可
     *          2.2 若一条为环形，另一条非环形则无法相交
     *          2.3 若两条链表均为环形链表，首先判断是否在入环前相交，若不是则从一个入环点开始遍历环，若遍历一圈还没找到另一个入环点则说明不相交
     * @param head1
     * @param head2
     * @return
     */
    public static Node twoLinkMerge(Node head1, Node head2){
        //1.分别判断两条链表是否为环形链表，是的话则返回第一个入环节点
        Node c1 = getLoop(head1);
        Node c2 = getLoop(head2);
        //2.根据单链表相交的性质有如下结论：
        //    2.1 两条非环形的链表相交，较长的链表走往超出的部分后，较短的链表开始遍历，等到它们相等即可
        //    2.2 若一条为环形，另一条非环形则无法相交
        //    2.3 若两条链表均为环形链表，首先判断是否在入环前相交，若不是则从一个入环点开始遍历环，若遍历一圈还没找到另一个入环点则说明不相交
        if((c1 == null && c2 != null) || (c2 == null && c1 != null) ){
            return null;
        }
        Node ans = null;
        if(c1 == null && c2 == null){
            ans = noLoop(head1,head2);
        }else{
            ans = loop(head1,c1,head2,c2);
        }
        return ans;
    }

    /**
     * 找出两条链表相交的第一个节点，它们可能相交也可能不相交，链表可能是环形也可能不是环形
     * 解题步骤：
     *      1.分别判断两条链表是否为环形链表，是的话则返回第一个入环节点
     *      2.根据单链表相交的性质有如下结论：
     *          2.1 两条非环形的链表相交，较长的链表走往超出的部分后，较短的链表开始遍历，等到它们相等即可
     *          2.2 若一条为环形，另一条非环形则无法相交
     *          2.3 若两条链表均为环形链表，首先判断是否在入环前相交，若不是则从一个入环点开始遍历环，若遍历一圈还没找到另一个入环点则说明不相交
     * @param head1
     * @param head2
     * @return
     */

    public static Node twoLinkMerge2(Node head1, Node head2){
        //1.分别判断两条链表是否为环形链表，是的话则返回第一个入环节点
        Node c1 = getLoop(head1);
        Node c2 = getLoop(head2);
        //2.根据单链表相交的性质有如下结论：
        //    2.1 两条非环形的链表相交，较长的链表走往超出的部分后，较短的链表开始遍历，等到它们相等即可
        //    2.2 若一条为环形，另一条非环形则无法相交
        //    2.3 若两条链表均为环形链表，首先判断是否在入环前相交，若不是则从一个入环点开始遍历环，若遍历一圈还没找到另一个入环点则说明不相交
        if((c1 == null && c2 != null) || (c2 == null && c1 != null) ){
            return null;
        }
        Node ans = null;
        if(c1 == null && c2 == null){
            ans = noLoop(head1,head2);
        }else{
            ans = loop(head1,c1,head2,c2);
        }
        return ans;
    }

    /**
     * 求一个单链表（带头结点）的最小值节点，并输出最小值节点的值。如果最小值节点的值是偶数，
     * 则将最小值节点的值与其后继节点的值交换；如果最小值节点的值是奇数，
     * 则删除其后继节点；如果最小值节点没有后继节点，则不做任何处理
     * @param head
     */
    public static void getMinAndExchange(Node head){
        //定义cur变量用来遍历链表，min指针指向遍历过程中前值最小的节点
        Node cur = head,min = head;
        while (cur != null){
            if(min.val > cur.val){
                min = cur;
            }
            cur = cur.next;
        }

        System.out.println(min.val);
        if(min.next != null){
            //最小值是偶数，与后继节点的值做交换
            if(min.val % 2 == 0){
                int temp = min.val;
                min.val = min.next.val;
                min.next.val = temp;
            }else { //最小值是奇数则删除后继节点
                Node temp = min.next;
                min.next = temp.next;
                temp = null;
            }

        }
    }
    private static Node loop(Node head1, Node c1, Node head2, Node c2) {
        if(c1 == c2) {
            //1.判断入环前两链表是否相交
            int n = 0;
            Node cur1 = head1, cur2 = head2;
            while (cur1 != c1) {
                cur1 = cur1.next;
                n++;
            }

            while (cur2 != c2) {
                cur2 = cur2.next;
                n--;
            }

            Node s1 = n >= 0 ? head1 : head2, s2 = s1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n > 0) {
                s1 = s1.next;
                n--;
            }
            while (s1 != c1 && s2 != c2) {
                if (s1 == s2) {
                    return s1;
                }
                s1 = s1.next;
                s2 = s2.next;
            }


            return c1;
        }else {
            //2.判断入环后两链表是否相交
            Node s3 = c1.next;
            while (s3 != c1) {
                if (s3 == c2) {
                    return s3;
                }
                s3 = s3.next;
            }

            return null;
        }
    }

    private static Node noLoop(Node head1, Node head2) {
        int n = 0;
        Node cur1 = head1, cur2 = head2;
        while(cur1 != null){
            cur1 = cur1.next;
            n++;
        }

        while(cur2 != null){
            cur2 = cur2.next;
            n--;
        }

        Node s1 = n >=0 ? head1:head2, s2 = s1 == head1 ? head2:head1;
        n = Math.abs(n);
        while(n > 0){
            s1 = s1.next;
            n--;
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
        if(head != null){
            Node slow = head, fast = head;
            while(fast != null && fast.next != null){
                slow = slow.next;
                fast = fast.next.next;
                if(slow == fast){
                    break;
                }
            }
            if(slow == fast && slow != null && fast.next != null){
                fast = head;
                while(fast != slow){
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }else{
                return null;
            }
        }
        return null;
    }

    public static void main(String[] args) {
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


        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(twoLinkMerge(head1,head2).val);


        // 0->9->8->6->7->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(twoLinkMerge(head1,head2).val);
    }
}
