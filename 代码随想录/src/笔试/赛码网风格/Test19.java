package 笔试.赛码网风格;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Test19 {
    Map<State,Integer> usedMap = new HashMap<Test19.State,Integer>();
    public int getEstTime (int[][] map, int a_x, int a_y, int b_x, int b_y) {
        State state = new State(a_x, a_y, b_x, b_y);
        if(usedMap.get(state)!=null){
            return usedMap.get(state);
        }
        if(a_x == b_x && a_y == b_y){
            return 0;
        }


        int c_a_x = a_x,c_a_y = a_y;
        int c_b_x = b_x,c_b_y = b_y;
        int row = Integer.MAX_VALUE,col = Integer.MAX_VALUE;
        //向右走一步如果条件允许
        if(a_x < b_x){
            if(map[a_x+1][a_y] == 1) {
                c_a_x = a_x + 1;
            }
            //右走一步后仍然小于b_x
            if(c_a_x < b_x) {
                if (map[b_x - 1][b_y] == 1) {
                    c_b_x = b_x - 1;
                }
            }
            if(a_x != c_a_x || b_x != c_b_x) {
                row = 1 + getEstTime(map, c_a_x, c_a_y, c_b_x, c_b_y);
                if(row == 0){
                    return -1;
                }
            }
        }

        if(a_x > b_x){
            if(map[a_x-1][a_y] == 1) {
                c_a_x = a_x - 1;
            }
            //右走一步后仍然小于b_x
            if(c_a_x > b_x) {
                if (map[b_x + 1][b_y] == 1) {
                    c_b_x = b_x + 1;
                }
            }
            if(a_x != c_a_x || b_x != c_b_x) {
                row = 1 + getEstTime(map, c_a_x, c_a_y, c_b_x, c_b_y);
                if(row == 0){
                    return -1;
                }
            }
        }

        if(a_y < b_y){
            if(map[a_x][a_y+1] == 1) {
                c_a_y = a_y + 1;
            }
            //右走一步后仍然小于b_x
            if(c_a_y < b_y) {
                if (map[b_x][b_y-1] == 1) {
                    c_b_y = b_y - 1;
                }
            }
            if(a_y != c_a_y || b_y != c_b_y) {
                col = 1 + getEstTime(map, c_a_x, c_a_y, c_b_x, c_b_y);
                if(col == 0){
                    return -1;
                }
            }
        }

        if(a_y > b_y){
            if(map[a_x][a_y-1] == 1) {
                c_a_y = a_y - 1;
            }
            //右走一步后仍然小于b_x
            if(c_a_y > b_y) {
                if (map[b_x][b_y+1] == 1) {
                    c_b_y = b_y + 1;
                }
            }
            if(a_y != c_a_y || b_y != c_b_y) {
                col = 1 + getEstTime(map, c_a_x, c_a_y, c_b_x, c_b_y);
                if(col == 0){
                    return -1;
                }
            }
        }

        int res = Math.min(row,col);
        usedMap.put(state,res);
        return res == Integer.MAX_VALUE ? -1:res;

    }

    class State{
        int a_x;
        int a_y;
        int b_x;
        int b_y;

        public State(int a_x, int a_y, int b_x, int b_y) {
            this.a_x = a_x;
            this.a_y = a_y;
            this.b_x = b_x;
            this.b_y = b_y;
        }
    }

}
