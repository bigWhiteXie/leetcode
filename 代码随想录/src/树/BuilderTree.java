package 树;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuilderTree {
    public static TreeNode buildTree(List<Integer> list){
        Integer val = list.remove(0);
        TreeNode node = new TreeNode(val);
        TreeNode root = node;
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(node);
        while(!queue.isEmpty() && list.size()>0){
            TreeNode poll = queue.poll();

            Integer left = list.size()>0 ?list.remove(0):null;
            Integer right = list.size()>0?list.remove(0):null;

            poll.left = left!=null?new TreeNode(left):null;
            poll.right = right!=null?new TreeNode(right):null;

            if(poll.left!=null){
                queue.add(poll.left);
            }

            if(poll.right != null){
                queue.add(poll.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(5,1,4,null,null,3,6));
        TreeNode node = buildTree(list);
        System.out.println(node);

    }
}
