package class05;

import java.awt.image.renderable.RenderableImage;
import java.util.Comparator;

public class Code01_MaxHeap {
    static class MaxHeap{
         int[] heap;
         int limit;
         int heapsize = 0;
         MaxHeap(int limit){
             this.limit = limit;
             heap = new int[limit];
         }

         public void push(int element){
             heap[heapsize] = element;
             heapInsert(heap,heapsize++);

         }

         public int peek(){
             return heap[0];
         }

         public int pop(){
             int t = heap[0];
             swap(heap,0,--heapsize);
             heapdify(heap,0);
             return t;
         }


         public void heapdify(int[] arr, int index){
             int left = index*2 + 1;
             int largest;
             while(left < heapsize){
                 largest = left + 1 < heapsize && arr[left] < arr[left + 1] ? left+1:left;
                 if(arr[index] < arr[largest]){
                     swap(arr,index,largest);
                     index = largest;
                     left = index*2 + 1;
                 }else{
                    break;
                 }
             }
         }

         public void heapInsert(int[] arr, int heapsize){
             int index = heapsize;
             int parent = (index - 1) / 2;
             while(arr[index] > arr[parent]){
                 swap(arr,index,parent);
                 index = parent;
                 parent = (index - 1) / 2;
             }
         }

         private void swap(int[] arr, int i1, int i2){
             int t = arr[i1];
             arr[i1] = arr[i2];
             arr[i2] = t;
         }
    }




    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(6);
        maxHeap.push(5);
        maxHeap.push(3);
        maxHeap.push(6);
        maxHeap.push(1);
        maxHeap.push(4);
        maxHeap.push(2);
        System.out.println(maxHeap.peek());
        System.out.println(maxHeap.pop());
        System.out.println(maxHeap.pop());
        System.out.println(maxHeap.pop());
        maxHeap.push(7);
        System.out.println(maxHeap.pop());

    }
}
