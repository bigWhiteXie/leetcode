package 贪心;

import java.util.HashMap;
import java.util.PriorityQueue;

public class 柠檬水找零 {
    public boolean lemonadeChange(int[] bills) {
        int five = 0,ten = 0,twenty = 0;
        for (int bill : bills) {
            if(bill - 5 > 0){
                int draw = bill - 5;
                if(draw == 5){
                    if(five > 0) {
                        five--;
                        ten++;
                    }else{
                        return false;
                    }
                }else if(draw == 15){
                    if(five >0 && ten > 0){
                        five--;
                        ten--;
                        twenty++;
                    }else if(five >= 3){
                        five -= 3;
                        twenty++;
                    }else{
                        return false;
                    }
                }
            }else{
                five++;
            }

        }
        return true;
    }
}
