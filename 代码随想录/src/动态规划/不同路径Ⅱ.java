package 动态规划;

public class 不同路径Ⅱ {
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;

        int[][] dp = new int[row][col];
        dp[row-1][col-1] = 1;

        for(int x = col-1;x>=0;x--){
            for(int y = row-1;y>=0; y--){
                if(obstacleGrid[y][x] == 1){
                    dp[y][x] = -1;
                    break;
                }

                if(y == row-1 && x == col - 1){
                    dp[y][x] = 1;
                    break;
                }

                int r1 = -1, r2 = -1;

                //向右移动
                if(x < col - 1){
                    int x_res = dp[x + 1][y];
                    if(x_res != -1){
                        r1 = x_res;
                    }
                }

                //向下移动
                if(y < row - 1){
                    int y_res = dp[x][y+1];
                    if(y_res != -1){
                        r2 = y_res;
                    }
                }

                if(r1 != -1 && r2 != -1){
                    dp[y][x] =  r1 + r2;
                }else if (r1 == -1 && r2 == -1){
                    dp[y][x] = -1;
                }else{
                    dp[y][x] = r1 == -1 ? r2 : r1;
                }
            }
        }

        return dp[0][0];
    }

    public static int tracking(int[][] grid,int x,int y){
        if(grid[y][x] == 1){
            return -1;
        }

        if(y == grid.length-1 && x == grid[0].length - 1){
            return 1;
        }


        int r1 = -1, r2 = -1;

        //向右移动
        if(x < grid[0].length - 1){
            int x_res = tracking(grid, x + 1, y);
            if(x_res != -1){
                r1 = x_res;
            }
        }

        //向下移动
        if(y < grid.length - 1){
            int y_res = tracking(grid, x, y+1);
            if(y_res != -1){
                r2 = y_res;
            }
        }

        if(r1 != -1 && r2 != -1){
            return r1 + r2;
        }else if (r1 == -1 && r2 == -1){
            return -1;
        }else{
            return r1 == -1 ? r2 : r1;
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{0,0},{0,1}};
        uniquePathsWithObstacles(grid);
    }
}
