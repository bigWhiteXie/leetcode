package 贪心;

public class 加油站 {
    /**
     * 解题：
     *  1. totalSum：若总油量小于总消耗，则无法走完一圈
     *  2. cursum：区间和，若该区间和小于0，则说明从该区间任意一点出发一定会出现断油，重新调整出发位置为区间+1
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int[] rest = new int[gas.length];

        for(int i=0; i<gas.length;i++){
            rest[i] = gas[i] - cost[i];
        }

        int cursum = 0;
        int totalSum = 0;
        int begin = 0;
        for(int i=0;i<rest.length;i++){

           cursum += rest[i];
           totalSum += rest[i];
           if(cursum < 0){
               begin = (i+1) % rest.length;
               cursum = 0;
           }
        }

        if(totalSum < 0){
            return -1;
        }

        return begin;
    }
}
