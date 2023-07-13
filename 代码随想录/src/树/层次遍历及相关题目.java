package 树;

import java.util.*;

public class 层次遍历及相关题目 {
    public List<List<Integer>> levelOrder(TreeNode root) {

        ArrayList<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            while(size-- > 0){
                TreeNode node = queue.poll();
                if(node.left != null){
                    queue.add(node.left);
                }

                if(node.right != null){
                    queue.add(node.right);
                }

                list.add(node.val);
            }
            res.add(list);
        }

        return res;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            while(size-- > 0){
                TreeNode node = queue.poll();
                if(node.left != null){
                    queue.add(node.left);
                }

                if(node.right != null){
                    queue.add(node.right);
                }

                list.add(node.val);
            }
            res.add(list);
        }
        reverse(res);
        return res;
    }

    private void reverse(ArrayList<List<Integer>> res) {
        int begin = 0, end = res.size()-1;
        while(begin < end){
            List<Integer> temp = res.get(begin);
            res.set(begin,res.get(end));
            res.set(end,temp);
            begin++;
            end--;
        }
    }

    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return new ArrayList<Integer>();
        }
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            while(size-- > 0){
                TreeNode node = queue.poll();
                if(node.left != null){
                    queue.add(node.left);
                }

                if(node.right != null){
                    queue.add(node.right);
                }

                list.add(node.val);
            }
            res.add(list);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (List<Integer> list : res) {
            ans.add(list.get(list.size()-1));
        }
        return ans;
    }

    public List<Integer> largestValues(TreeNode root) {
        ArrayList<PriorityQueue<Integer>> res = new ArrayList<>();
        if(root == null){
            return new ArrayList<Integer>();
        }
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            PriorityQueue<Integer> list = new PriorityQueue<>((a,b)->{
                if(b > a){
                    return 1;
                }else if(b<a){
                    return -1;
                }else {
                    return 0;
                }
            });
            while(size-- > 0){
                TreeNode node = queue.poll();
                if(node.left != null){
                    queue.add(node.left);
                }

                if(node.right != null){
                    queue.add(node.right);
                }

                list.add(node.val);
            }
            res.add(list);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (PriorityQueue<Integer> heap : res) {
            ans.add(heap.peek());
        }
        return ans;
    }
}
