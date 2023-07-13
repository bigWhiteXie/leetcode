package 链表;

public class 两两交换链表中的节点 {
    public ListNode swapPairs(ListNode head) {
        ListNode vhead = new ListNode(),pre = vhead,p= head,t,temp;
        vhead.next = head;
        if(pre.next == null){
            return  head;
        }
        while(p.next != null ){
          t = p.next;
          temp = t.next;

          pre.next = t;
          p.next = t.next;
          t.next = p;

          pre = p;
          p = temp;



        }
        return vhead.next;
    }
}
