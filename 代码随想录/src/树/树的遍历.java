package 树;

import com.sun.source.tree.Tree;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 树的遍历 {
    public List<TreeNode> middleOrder(TreeNode root){
        ArrayList<TreeNode> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;
        while(cur!=null || !stack.isEmpty()){
            if(cur!=null){
                stack.push(cur);
                cur = cur.left;
            }else{
                TreeNode node = stack.pop();
                res.add(node);
                cur = node.right;
            }
        }
        return res;
    }



}
