package class03;

/**
 * 归并排序的特点：会将数组按照顺序划分成若干和有序数组进行合并，我们可以利用有序数组的性质解决问题
 */
public class 归并排序 {
    /**
     * 将数组arr中[L,M] [M+1,R]这两个有序部分合并成一个有序部分
     * @param arr
     * @param L
     * @param M
     * @param R
     * @return
     */
    public static void merge(int[] arr, int L, int M, int R) {
      int[] help = new int[R-L+1];
      int s1 = L, s2 = M + 1;
      int i = 0;
      while(s1<=M && s2 <= R){
          if(arr[s1] <= arr[s2]){
              help[i++] = arr[s1++];
          }else{
              help[i++] = arr[s2++];
          }
      }

      while(s1 <= M){
          help[i++] = arr[s1++];
      }

      while(s2 <= R){
          help[i++] = arr[s2++];
      }

      for (int j = 0; j < help.length; j++) {
          arr[L+j] = help[j];
      }

    }
    public static void process(int[] arr,int L, int R){
        if(L == R){
            return;
        }

        int mid = L + ((R-L) >> 1);
        process(arr,L,mid);
        process(arr,mid+1,R);

        merge(arr,L,mid,R);
    }
    public static void sort(int[] arr){
        process(arr,0,arr.length-1);
    }
    public static void main(String[] args) {
        int[] arr = new int[]{2,3,4,1,3,4,2,5,6};
        sort(arr);
        for(int i:arr){
            System.out.print(i + " ");
        }
    }
}
