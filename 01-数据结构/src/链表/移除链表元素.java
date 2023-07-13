package 链表;

public class 移除链表元素 {
}


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null){
            return head;
        }
        ListNode p = head,pre = null;

        while(p != null){
            if(p.val == val){
                if(pre != null){
                    pre.next = p.next;
                }else{
                    head = p.next;
//                    pre = null;
                }
            }else{
                pre = p;
            }

            p=p.next;
        }
        return head;
    }
}