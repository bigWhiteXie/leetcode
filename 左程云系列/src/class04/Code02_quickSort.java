package class04;

import java.util.Arrays;
import java.util.Stack;

public class Code02_quickSort {
    public static class Op{
        private int left;
        private int right;

        public Op(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getRight() {
            return right;
        }

        public void setRight(int right) {
            this.right = right;
        }
    }
    public static void quickSort(int[] arr){
        procee(arr,0,arr.length-1);
    }

    public static void procee(int[] arr,int l, int r) {
        if (l >= r) {
            return;
        }
//
//        int l_bound = l - 1, r_bound = r;
//        int target = arr[r];
//        int left = l, right = r;
//        while(left <= right){
//            if(arr[left] > target){
//                swap(arr,left,right--);
//                r_bound--;
//            }else if(arr[left] == target){
//                left++;
//            }else{
//                swap(arr,left++,++l_bound);
//            }
//        }

//        swap(arr,right,r);
        int l_bound = l - 1, r_bound = r;
        int left = l, right = r;
        int tar = arr[right];
        while(l <= r){
            if(arr[left] > tar){
                swap(arr,left,right--);
            }else if(arr[left] == tar){
                left++;
            }else{
                swap(arr,left++,++l_bound);
            }
        }
        procee(arr,l,l_bound);
        procee(arr,r_bound+1,r);

    }
    public static void swap(int[] arr, int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] =t;
    }

    public static void quickSort2(int arr[],int left, int right) {
        if (left >= right) {
            return;
        }
        int L = left, R = right;
        int temp = arr[left];
        while (right > left) {
            // 先判断基准数和后面的数依次比较
            while (temp <= arr[right] && left < right) {
                --right;
            }
            // 当基准数大于了 arr[right]，则填坑
            if (left < right) {
                arr[left] = arr[right];
                ++left;
            }
            // 现在是 arr[right] 需要填坑了
            while (temp > arr[left] && left < right) {
                ++left;
            }
            if (left < right) {
                arr[right] = arr[left];
                --right;
            }
        }
        arr[left] = temp;


        quickSort2(arr,L,left-1);
        quickSort2(arr,left+1,R);
    }

    //根据pivot对arr进行划分,pivot左边的数都小于它，右边的都大于它
    public static int[] partition(int[] arr, int L, int R, int pivot) {

        int less = L-1,more = R+1;
        int cur = L;
        int[] ans = new int[2];

        while (cur < more){
            if(arr[cur] < pivot){
                swap(arr,cur++,++less);
            }else if(arr[cur] > pivot){
                swap(arr,cur,--more);
            }else {
                cur++;
            }
        }

        ans[0] = less+1;
        ans[1] = more-1;
        return ans;
    }

    public static void quickSort(int[] arr, int L, int R){
        if(L >= R){
            return;
        }
        int size = R - L ;
        int pivot = arr[L + ((int) (Math.random()*size))];

        int[] pos = partition(arr, L, R, pivot);
        quickSort(arr,L,pos[0]-1);
        quickSort(arr,pos[1]+1,R);
    }

        // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }

        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void quickSort2(int[] arr){
        if(arr.length == 0){
            return ;
        }
        int left = 0, right =arr.length - 1;
        int random = (int) (Math.random()*arr.length);
        swap(arr,random,right);

        Stack<Op> stack = new Stack<>();
        Op op = netherlandFlag(arr, left, right);
        stack.push(new Op(0,op.getLeft()));
        stack.push(new Op(op.getRight(),right));
        while(!stack.isEmpty()){

            Op pop = stack.pop();
            left = pop.getLeft();
            right = pop.getRight();
            if(left < right) {
                Op new_op = netherlandFlag(arr, pop.getLeft(), pop.getRight());

                stack.push(new Op(left,new_op.getLeft()));
                stack.push(new Op(new_op.getRight(), right));
            }
        }

    }

    public static Op netherlandFlag(int[] arr,int l, int r) {
        if (l == r) {
            return new Op(l,l);
        }

        if(l > r){
            return new Op(-1,-1);
        }

        int l_bound = l - 1, r_bound = r;
        int target = arr[r];
        int left = l, right = r;
        while(left <= right){
            if(arr[left] > target){
                swap(arr,left,right--);
                r_bound--;
            }else if(arr[left] == target){
                left++;
            }else{
                swap(arr,left++,++l_bound);
            }
        }


        return new Op(l_bound,r_bound+1);
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);

            quickSort(arr1,0,arr1.length-1);
            Arrays.sort(arr2);

            if (!isEqual(arr1, arr2) ) {
                succeed = false;

                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
    }
}
