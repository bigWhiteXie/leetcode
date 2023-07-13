package 动态规划;

import java.util.ArrayList;
import java.util.List;

public class 整数拆分_回溯 {
    int max = -1;
    List<Integer> path = new ArrayList<>();

    public int integerBreak(int n) {
        tracking(n, 1);
        return max;
    }

    public void tracking(int res, int num) {
        if (res == 0) {
            return;
        }
        int ans = 1;
        for (Integer item : path) {
            ans *= item;
        }

        if (num == 1) {
            for (int i = 1; i < res; i++) {

                ans *= i;
                max = Math.max(max, ans);
                path.add(i);

                tracking(res - i, num + 1);
                ans /= i;
                path.remove(path.size() - 1);
            }
        } else {
            for (int i = 1; i <= res; i++) {
                ans *= i;
                max = Math.max(max, ans);
                path.add(i);

                tracking(res - i, num + 1);
                ans /= i;
                path.remove(path.size() - 1);
            }
        }

    }
}