package 笔试.赛码网风格;

import java.util.ArrayList;
import java.util.List;

public class Test12 {
    public static ListNode reorderList (ListNode head) {
        ListNode dumyhead = new ListNode(-1); // 设置一个虚拟头结点
        dumyhead.next = head; // 将虚拟头结点指向head，这样方面后面做删除操作
        ListNode cur = dumyhead;

        while (cur.next != null && cur.next.next != null && cur.next.next.next != null) {
            ListNode t1 = cur.next;
            ListNode t2 = t1.next.next;
            ListNode t3 = t2.next != null?t2.next:t2;
            ListNode t4 = t3 != null?t3.next:null;



            cur.next = cur.next.next.next;
            t3.next = t1;
            t1.next.next = t4;

            cur = t1.next;

        }
        return dumyhead.next;
    }

     static class ListNode {
       int val;
       ListNode next = null;

         public ListNode(int val) {
             this.val = val;
         }
     }
//    {1,2,3,4,5}
    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        node.next.next.next.next.next = new ListNode(6);
        node.next.next.next.next.next.next = new ListNode(7);
        ListNode list = reorderList(node);
        while(list!=null){
            System.out.print(list.val + " ");
            list = list.next;
        }
    }
}
