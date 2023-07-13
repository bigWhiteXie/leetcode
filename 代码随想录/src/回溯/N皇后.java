package 回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * 二维递归问题
 *  每层递归处理一行
 */
public class N皇后 {
    List<List<String>> res = new ArrayList<>();
    List<String> chessboard = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        backtracking(n,0);
        return res;
    }

    public void backtracking(int n,int row){
        if(row == n){
            res.add(new ArrayList<>(chessboard));
        }

        for(int i=0; i<n;i++){ //遍历该行的所有位置
            if(validPosition(row,i)){ //判断当前位置是否合法
                //若位置合法则构造当前行
                StringBuilder builder = new StringBuilder();
                for(int j=0;j<n;j++){
                    builder.append(".");
                }
                builder.replace(i,i+1,"Q");
                chessboard.add(builder.toString());
                backtracking(n,row+1);
                chessboard.remove(chessboard.size()-1);
            }
        }
    }

    private boolean validPosition(int row, int index) {
        for (int i = 0; i < chessboard.size(); i++) {
            String cur = chessboard.get(i);
            int pos = cur.indexOf("Q");
            if(pos == index || pos+(row-i) == index || pos - (row -i) ==index){
                return false;
            }

        }
        return true;
    }

}
