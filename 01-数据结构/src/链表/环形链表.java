package 链表;


public class 环形链表 {
    /**
     * 当快慢指针相遇时说明有环
     * 并且相遇时再声明两个指针，一个从head开始，一个从fast开始，每次移动一个节点，相遇的时候就是环的起点
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head.next, slow = head;

        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow =slow.next;
            if(slow == fast){
                ListNode L1 = head,L2 = fast;
                while(L1 != L2){
                    L1 = L1.next;
                    L2 = L2.next;
                }
                return L1;
            }
        }
        return null;
    }
}
