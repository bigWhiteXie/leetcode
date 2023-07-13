package 树;

import com.sun.org.apache.bcel.internal.generic.FASTORE;
import com.sun.org.apache.bcel.internal.generic.RET;
import sun.reflect.generics.tree.Tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class KthLargestLevelSum {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }

    public long kthLargestSum(TreeNode root, int k){
        LinkedList<TreeNode> queue = new LinkedList<>();
        PriorityQueue<Long> heap = new PriorityQueue<>(new Comp());
        TreeNode curEnd = root, nextEnd = null;
        queue.add(root);
        long sum = 0;
        while(!queue.isEmpty()){
            TreeNode node = queue.removeFirst();
            sum += node.val;
            if(node.left != null){
                queue.add(node.left);
                nextEnd = node.left;
            }

            if(node.right != null){
                queue.add(node.right);
                nextEnd = node.right;
            }

            if(node == curEnd){
                heap.add(sum);
                sum = 0;
                curEnd = nextEnd;
            }

        }

        while(--k>0){
            heap.poll();
        }
        return heap.poll();

    }

    public class Comp implements Comparator<Long>{


        @Override
        public int compare(Long o1, Long o2) {
            return o2.compareTo(o1);
        }
    }

    public static void main(String[] args) {
        Long o1 = 3l, o2 = 4l;
        System.out.println(o1.compareTo(o2));
    }

    public boolean gcd(long a, long b){
        long min = a > b? b : a;
        for(long i = 2l; i<=min; i++){
            if(a%i==0 && b%i==0){
                return false;
            }
        }
        return true;
    }
}
