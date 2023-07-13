package 树;

import java.util.HashMap;
import java.util.Map;

public class 中序序列和先序序列建立二叉树 {
    private static Map<Integer,Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] preorder) {
        //建立hash表方便快速查找元素在中序序列的位置
        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }

        return builderTree(inorder,0,inorder.length-1,preorder,0,preorder.length-1);
    }

    private TreeNode builderTree(int[] inorder, int inBegin, int inEnd, int[] preoder, int pBegin, int pEnd) {
        if(inBegin > inEnd || pBegin > pEnd){
            return null;
        }
        //找到根节点
        int inVal = preoder[pBegin];

        //根节点在中序序列的位置
        int index = map.get(inVal);

        TreeNode node = new TreeNode(inVal);

        int numOfLeft = index - inBegin; //保存中序左子树个数，用来确定后序数列的个数
        //中序[begin,index-1]  先序[pBegin+1,pBegin+numOfLeft]
        node.left = builderTree(inorder,inBegin,index-1,preoder,pBegin+1,pBegin+numOfLeft);

        //中序[index+1,end]  先序[pBegin+numOfLeft+1,pEnd]
        node.right = builderTree(inorder,index+1,inEnd,preoder,pBegin+numOfLeft+1,pEnd);
        return node;
    }
}
