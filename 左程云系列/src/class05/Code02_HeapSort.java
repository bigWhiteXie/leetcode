package class05;

import java.util.PriorityQueue;

public class Code02_HeapSort {
    public static void heapSort(int[] arr){
        int[] heap = new int[arr.length];
        int heapsize = 0;
        for(int i=0; i<arr.length; i++){
            heap[heapsize] = arr[i];
            heapInsert(heap,heapsize++);
        }

        while(heapsize > 0){
            swap(heap,0,--heapsize);
            heapdify(heap,0,heapsize);
        }

        for(int i=0; i<heap.length;i++){
            arr[i] = heap[i];
        }
    }

    public static void heapdify(int[] arr, int index,int heapsize){
        int left = index*2 + 1;
        //每换完一次，heapsize位置上的元素都是当前最大值，不能算在下沉范围中
        while(left < heapsize){
            int largest = left + 1 < heapsize && arr[left] < arr[left+1] ? left+1:left;
            if(arr[largest] > arr[index]){
                swap(arr,largest,index);
                index = largest;
                left = index*2 + 1;
            }else{
                break;
            }
        }
    }

    public static void heapInsert(int[] arr, int index){
        int parent = (index - 1) / 2;
        while(arr[index] > arr[parent]){
            swap(arr,index,parent);
            index = parent;
            parent = (index-1) / 2;
        }
    }

    public static void swap(int[] arr, int i1, int i2){
        int t = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = t;
    }


    public static void main(String[] args) {
        int[] ints = {5, 3, 1, 2, 6, 4, 7,10,8,9};
        heapSort(ints);
        for(int i=0; i<ints.length; i++){
            System.out.print(ints[i] + " ");
        }
    }
}
