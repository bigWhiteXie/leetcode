package 链表;

public class 删除链表中倒数第n个节点 {
    /**
     * 思想：利用快慢指针，快指针比满指针先移动n+1个位置，
     * 当快指针到达末尾的时候慢指针到达倒数n+1个节点
     * 注意：加上头结点，可以把每个节点都算进去，不加头节点当元素较少时容易出错
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode slow = dummy,fast = dummy;
        while(n-- > 0 && fast != null){
            fast = fast.next;
        }

        fast = fast.next;

        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return dummy.next;


    }
}
