package class26;

public class Solution {
    public int Fibonacci(int n) {

        //边界条件判断
        if(n==0){
            return 0;
        }
        if(n==1||n==2){
            return n;
        }
        //设置底数矩阵
        int[][] base = {{1,1},{1,0}};
        int[][] res = matrixPower(base,n-2);

        return 2*res[0][0]+res[1][0]; 

    }

    public int Fibonacci2(int n) {

        //边界条件判断
        if(n==0){
            return 0;
        }
        if(n==1||n==2){
            return n;
        }
        //设置底数矩阵
        int[][] base = {{1,1},{1,0}};
        int[][] res = matrixPower(base,n-2);

        return res[0][0]+ 2 * res[1][0];

    }

    //求矩阵的N次方的结果
    public int[][] matrixPower(int[][] base,int n){
        int[][] res = new int[base.length][base[0].length];
        for (int i = 0; i < base.length; i++) {
            res[i][i] = 1;
        }

        while(n>0){
            if((n & 1) != 0){
                res =muliMatrix(res,base);
            }
            base = muliMatrix(base,base);
            n=n>>1;
        }

        return res;
    }
    public int[][] muliMatrix(int[][] m1,int[][] m2){
        int[][] temp = new int[m1.length][m2[0].length];

        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                for (int k = 0; k < m1[0].length; k++) {
                    temp[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }

        return temp;

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.Fibonacci(5));
        System.out.println(solution.Fibonacci2(5));
    }
}