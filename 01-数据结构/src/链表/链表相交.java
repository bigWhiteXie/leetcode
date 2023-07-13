package 链表;

public class 链表相交 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pa = headA, pb = headB;
        int countA = 0, countB = 0;
        while(pa != null || pb != null){
            if(pa != null){
                pa = pa.next;
                countA++;
            }

            if(pb != null){
                pb = pb.next;
                countB++;
            }
        }

        int dis = countA - countB;
        pa = headA;
        pb = headB;
        if(dis >= 0){
          while(dis-- > 0){
              pa = pa.next;
          }

        }else{
            while(dis++ < 0){
                pb = pb.next;
            }
        }

        while(pa != pb && pa != null){
            pa = pa.next;
            pb = pb.next;
        }

        return pa == null ? null:pa;
    }
}
