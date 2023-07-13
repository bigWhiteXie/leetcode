package class01;

public class Code03 {
    public static String test(String str){
        String[] split = str.split("\n");
        int n = Integer.valueOf(split[0].trim());
        StringBuilder ans = new StringBuilder();
        for(int i=1; i < split.length; i++){
            String[] row = split[i].split(" ");

            int a1 = Integer.valueOf(row[0].trim());
            int a2 = Integer.valueOf(row[1].trim());

            //将较大的值赋给tar 较小的赋给ori
            int ori = a1 <= a2 ? a1 : a2;
            int tar = ori == a1? a2 : a1;

            int temp = tar / ori;
            //tar和ori不能整除，直接返回-1
            if(tar % ori != 0){
                ans.append("-1\n");
                continue;
            }

            int s = 0;
            int total = 0;
            //判断tar和ori是否为2的指数倍数的关系
            if((temp & (temp -1)) == 0){
                while((temp >> 1) > 0){
                    temp = temp >> 1;
                    s++;
                }
                //当tar和ori相等的时候，需要进行两步才能得到答案
                if(s == 0){
                    ans.append(2 + "\n");
                    continue;
                }
                while(s > 0){
                    if(s >= 3){
                        s -= 3;
                        total++;
                        continue;
                    }
                    if(s >= 2){
                        s -= 2;
                        total++;
                        continue;
                    }

                    if(s >= 1){
                        s -= 1;
                        total++;
                        continue;
                    }

                }
                ans.append(total + "\n");
                continue;
            }

            ans.append("-1\n");

        }

        return ans.toString();
    }

    public static void main(String[] args) {
        String str = "2\n" +
                "2 4\n" +
                "4 16\n" +
                "4 64\n" +
                "4 140\n" +
                "32 128";
        System.out.println(test(str));
    }
}
