package 笔试.赛码网风格;

public class Test11 {
    public ListNode reverseList (ListNode head) {
        ListNode pre = null,cur = head;
        while(cur != null){
            ListNode t = cur.next;
            cur.next = pre;
            pre = cur;
            cur = t;
        }
        return pre;
    }

    static class ListNode {
      int val;
      ListNode next = null;
      public ListNode(int val) {
        this.val = val;
      }
    }
}
