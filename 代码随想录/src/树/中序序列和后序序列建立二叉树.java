package 树;

import java.util.HashMap;
import java.util.Map;

public class 中序序列和后序序列建立二叉树 {
    private static Map<Integer,Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        //建立hash表方便快速查找元素在中序序列的位置
        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }

        return builderTree(inorder,0,inorder.length-1,postorder,0,postorder.length-1);
    }

    private TreeNode builderTree(int[] inorder, int inBegin, int inEnd, int[] postorder, int pBegin, int pEnd) {
        if(inBegin > inEnd || pBegin > pEnd){
            return null;
        }
        int inVal = postorder[pEnd];
        int index = map.get(inVal);

        TreeNode node = new TreeNode(inVal);

        int mostOfLeft = index - inBegin; //保存中序左子树个数，用来确定后序数列的个数
        node.left = builderTree(inorder,inBegin,index-1,postorder,pBegin,pBegin+mostOfLeft-1);
        node.right = builderTree(inorder,index+1,inEnd,postorder,pBegin+mostOfLeft,pEnd-1);
        return node;
    }
}
