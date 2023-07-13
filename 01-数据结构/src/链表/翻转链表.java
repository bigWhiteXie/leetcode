package 链表;

public class 翻转链表 {
    public static ListNode reverseList(ListNode head) {
        ListNode p = head,pre = null,t;
        while(p != null){
            t = p.next;
            p.next = pre;
            pre = p;
            p = t;
        }
        return pre;
    }
}
