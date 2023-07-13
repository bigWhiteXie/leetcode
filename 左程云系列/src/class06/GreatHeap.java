package class06;

import java.security.Principal;
import java.util.*;

/*
 * T一定要是非基础类型，有基础类型需求包一层
 * 功能：push、pop、contains、resign、remove、allElements
 */
public class GreatHeap<T> {
    private List<T> heap;
    private int heapsize = 0;
    private Map<T,Integer> indexMap;
    private Comparator<T> comp;

    public GreatHeap(Comparator<T> comp) {
        this.comp = comp;
        heap = new ArrayList<>();
        indexMap = new HashMap<>();

    }

    public void push(T o){
        heap.add(o);
        indexMap.put(o,heapsize);

        heapInsert(heapsize++);
    }

    public T pop(){
        if(heapsize>0) {
            T obj = heap.get(0);
            swap(0, --heapsize);
            T remove = heap.remove(heapsize);
            indexMap.remove(remove);
            heapdify(0);
            return obj;
        }
        return null;
    }

    public void remove(T obj){
        Integer index = indexMap.get(obj);
        if(index == null){
            return;
        }
        T replace = heap.get(heapsize - 1);

        swap(index,--heapsize);

        heap.remove(heapsize);
        indexMap.remove(obj);

        resign(replace);
    }

    public boolean contains(T obj){
        return indexMap.containsKey(obj);
    }

    public T peek(){
        return heap.get(0);
    }

    public List<T> getAllElements(){
        ArrayList<T> list = new ArrayList<>();
        for (T t : heap) {
            list.add(t);
        }
        return list;
    }

    public boolean isEmpty(){
        return heapsize ==0;
    }

    public int size(){
        return heapsize;
    }

    public void resign(T obj) {
        Integer index = indexMap.get(obj);
        if(index != null) {
            heapdify(index);
            heapInsert(index);
        }
    }

    private void heapdify(int index) {
        int left = index * 2 + 1;
        while(left < heapsize){
            int best = left + 1 < heapsize && comp.compare(heap.get(left+1),heap.get(left)) < 0 ? left+1 : left;
            if(comp.compare(heap.get(best),heap.get(index)) < 0){
                swap(index,best);
                index = best;
                left = index * 2 + 1;
            }else{
                return ;
            }
        }
    }

    /**
     * 对index上的元素向上调整
     * @param index
     */
    private void heapInsert(int index){
        while (comp.compare(heap.get(index),heap.get((index-1) / 2)) < 0){
            swap(index,(index-1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void swap(int i, int j){
        T o1 = heap.get(i);
        T o2 = heap.get(j);

        heap.set(j,o1);
        heap.set(i,o2);
        indexMap.put(o1,j);
        indexMap.put(o2,i);
    }
}
