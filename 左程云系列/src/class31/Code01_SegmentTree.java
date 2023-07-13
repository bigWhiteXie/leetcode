package class31;

import jdk.swing.interop.SwingInterOpUtils;

public class Code01_SegmentTree {
    static class SegmentTree{
        private int[] arr;
        private int[] sum;
        private int[] lazy;
        private int[] change;
        private boolean[] update;

        public SegmentTree(int[] origin) {
            this.arr = new int[origin.length+1];
            sum = new int[arr.length << 2];
            lazy = new int[arr.length << 2];
            change = new int[arr.length << 2];
            update = new boolean[arr.length << 2];
            for (int i = 0; i < origin.length; i++) {
                arr[i+1] = origin[i];
            }
        }

        /**
         * 根据需求建立线段树某个数组的指定元素
         */
        public void build(int l, int r, int rt){
            if (l == r) {
                sum[rt] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(l, mid, rt << 1);
            build(mid + 1, r, rt << 1 | 1);
            recall(rt);
        }

        /**
         * 在[L,R]上增加num
         * @param L
         * @param R
         * @param num
         * @param l rt的左区间
         * @param r rt的右区间
         * @param rt 业务数组坐标
         */
        public void add(int L, int R, int num, int l, int r, int rt){
            //若当前任务把该rt对应区间覆盖住则直接修改
            if(l >= L && r <= R){
                int count = r - l + 1;
                sum[rt] += count * num;
                lazy[rt] += num;
                return;
            }

            //若无法完全覆盖则试图将任务下发到左右孩子
            int mid = (l+r)>>1;

            //将任务下发，确保子节点更新成最新结果
            pushDown(rt,mid-l+1,r-mid);
            if(L <= mid){
                add(L,R,num,l,mid,rt << 1);
            }

            if(R > mid){
                add(L,R,num,mid+1,r,rt << 1|1);
            }

            //业务数据回溯,更新之前经过的节点
            recall(rt);
        }

        public void update(int L, int R, int num, int l, int r, int rt){
            //若当前任务把该rt对应区间覆盖住则直接修改
            if(l >= L && r <= R){
                int count = r - l + 1;
                sum[rt] = count * num;
                update[rt] = true;
                change[rt] = num;
                lazy[rt] = 0;
                return;
            }

            //若无法完全覆盖则试图将任务下发到左右孩子
            int mid = (l+r)/2;

            //将任务下发，确保子节点更新成最新结果
            pushDown(rt,mid-l+1,r-mid);
            if(L <= mid){
                update(L,R,num,l,mid,rt << 1);
            }

            if(R > mid){
                update(L,R,num,mid+1,r,rt << 1|1);
            }

            //业务数据回溯,更新之前经过的节点
            recall(rt);
        }

        /**
         * 查询某个范围的累加和
         * @param L
         * @param R
         * @param l
         * @param r
         * @param rt
         * @return
         */
        public int query(int L, int R, int l, int r, int rt){
            if(l >= L && r <= R){
                return sum[rt];
            }

            int mid = (l+r) >>1;
            pushDown(rt,mid- l + 1, r - mid);

            int ans = 0;
            if(L <= mid){
                ans += query(L,R,l,mid,rt << 1);
            }

            if(R > mid){
                ans += query(L,R,mid+1,r,rt << 1 | 1);
            }
            return ans;
        }

        private void pushDown(int rt,int ln, int rn) {
            if(update[rt]){
                sum[rt << 1] = change[rt] * ln;
                sum[(rt << 1) + 1] = change[rt] * rn;
                change[rt<<1] = change[rt];
                change[rt<<1 | 1] = change[rt];
                lazy[rt<<1 | 1] = 0;
                lazy[rt<<1] = 0;
                update[rt] = false;
                update[rt << 1] = true;
                update[rt << 1 | 1] = true;
            }
            if(lazy[rt] != 0){
                sum[rt << 1] += lazy[rt] * ln;
                sum[(rt << 1) + 1] += lazy[rt] * rn;
                lazy[rt << 1] += lazy[rt];
                lazy[(rt << 1) + 1] += lazy[rt];
                lazy[rt] = 0;
            }
        }

        private void recall(int rt) {
            sum[rt] = sum[rt << 1] + sum[(rt << 1) + 1];
        }
    }
    public static class Right {
        public int[] arr;

        public Right(int[] origin) {
            arr = new int[origin.length + 1];
            for (int i = 0; i < origin.length; i++) {
                arr[i + 1] = origin[i];
            }
        }

        public void update(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] = C;
            }
        }

        public void add(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] += C;
            }
        }

        public long query(int L, int R) {
            long ans = 0;
            for (int i = L; i <= R; i++) {
                ans += arr[i];
            }
            return ans;
        }

    }

    public static int[] genarateRandomArray(int len, int max) {
        int size = (int) (Math.random() * len) + 1;
        int[] origin = new int[size];
        for (int i = 0; i < size; i++) {
            origin[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return origin;
    }

    public static boolean test() {
        int len = 100;
        int max = 1000;
        int testTimes = 5000;
        int addOrUpdateTimes = 1000;
        int queryTimes = 500;
        for (int i = 0; i < testTimes; i++) {
            int[] origin = genarateRandomArray(len, max);
            SegmentTree seg = new SegmentTree(origin);
            int S = 1;
            int N = origin.length;
            int root = 1;
            seg.build(S, N, root);
            Right rig = new Right(origin);
            for (int j = 0; j < addOrUpdateTimes; j++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                int C = (int) (Math.random() * max) - (int) (Math.random() * max);
                if (Math.random() < 0.5) {
                    seg.add(L, R, C, S, N, root);
                    rig.add(L, R, C);
                } else {
                    seg.update(L, R, C, S, N, root);
                    rig.update(L, R, C);
                }
            }
            for (int k = 0; k < queryTimes; k++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                long ans1 = seg.query(L, R, S, N, root);
                long ans2 = rig.query(L, R);
                if (ans1 != ans2) {
                    System.out.println(ans1 + "," + ans2);
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] origin = { 2, 1, 1, 2, 3, 4, 5 }; //{ 2, 5, 5, 6, 7, 4, 5 }
        SegmentTree seg = new SegmentTree(origin);
        Right right = new Right(origin);
        int S = 1; // 整个区间的开始位置，规定从1开始，不从0开始 -> 固定
        int N = origin.length; // 整个区间的结束位置，规定能到N，不是N-1 -> 固定
        int root = 1; // 整棵树的头节点位置，规定是1，不是0 -> 固定
        int L = 2; // 操作区间的开始位置 -> 可变
        int R = 5; // 操作区间的结束位置 -> 可变
        int C = 4; // 要加的数字或者要更新的数字 -> 可变
        // 区间生成，必须在[S,N]整个范围上build
        seg.build(S, N, root);
        System.out.println(seg.query(L, R, S, N, root));
        System.out.println(right.query(L,R));
        // 区间修改，可以改变L、R和C的值，其他值不可改变
        seg.add(L, R, C, S, N, root);
        right.add(L,R,C);
        // 区间更新，可以改变L、R和C的值，其他值不可改变
        seg.update(L, R, C, S, N, root);
        right.update(L,R,C);
        // 区间查询，可以改变L和R的值，其他值不可改变
        long sum = seg.query(L, R, S, N, root);
        System.out.println(sum);
        System.out.println(right.query(L,R));
        System.out.println("对数器测试开始...");
        System.out.println("测试结果 : " + (test() ? "通过" : "未通过"));

    }
}
