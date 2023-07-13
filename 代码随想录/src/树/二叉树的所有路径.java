package 树;

import java.util.ArrayList;
import java.util.List;

public class 二叉树的所有路径 {
    public List<String> binaryTreePaths(TreeNode root) {
        if(root == null){
            return null;
        }

        int val = root.val;
        List<String> l_paths = binaryTreePaths(root.left);
        List<String> r_paths = binaryTreePaths(root.right);
        ArrayList<String> list = new ArrayList<>();
        if(l_paths != null){
            l_paths.stream().forEach((s)->{
                s = val+"->"+s;
                list.add(s);
            });
        }

        if(r_paths != null){
            r_paths.stream().forEach((s)->{
                s = val+"->"+s;
                list.add(s);
            });
        }

        if(r_paths == null || l_paths == null){
            list.add(String.valueOf(val));
        }

        return list;


    }

}
