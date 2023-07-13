package class13;

import java.util.*;

public class Code05_UnionFind {
    static class UnionFind<T>{
        Map<T,T> parentMap = new HashMap<>();
        Map<T,Integer> sizeMap = new HashMap<>();

        public UnionFind(T[] arr){
            for (T t : arr) {
                parentMap.put(t,t);
                sizeMap.put(t,1);
            }
        }

        public boolean isSameUnion(T a, T b){
            if(parentMap.containsKey(a) && parentMap.containsKey(b)){
                a = findFather(a);
                b = findFather(b);

                if(a == b){
                    return true;
                }

            }
            return false;
        }

        private T findFather(T a) {
            if(parentMap.containsKey(a)) {
                Stack<T> stack = new Stack<>();
                //找到a的父节点
                while (parentMap.get(a) != a) {
                    stack.push(a);
                    a = parentMap.get(a);
                }

                //将栈中的元素父节点修改为a，方便之后查找
                while (!stack.isEmpty()) {
                    T pop = stack.pop();
                    parentMap.put(pop, a);
                }
                return a;
            }
            return null;
        }

        public void union(T a, T b){
            if(parentMap.containsKey(a) && parentMap.containsKey(b)){
                T headA = findFather(a);
                T headB = findFather(b);

                if(headA != headB) {
                    Integer sizeA = sizeMap.get(headA);
                    Integer sizeB = sizeMap.get(headB);
                    T more = sizeA > sizeB ? headA : headB;
                    T less = sizeA > sizeB ? headB : headA;

                    parentMap.put(less, more);
                    sizeMap.put(more, sizeA + sizeB);
                    sizeMap.remove(less);
                }
            }
        }

        public int size(){
            return sizeMap.size();
        }
    }

    static class UnionArray{
        List<Object> list;
        int[] parent;
        int[] size;
        int[] help;
        int sets;

        public UnionArray(Object[] arr) {
            int len = arr.length;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
            sets = len;
            for (int i = 0; i < parent.length; i++) {
                list.add(arr[i]);
                parent[i] = i;
                size[i] = 1;
            }
        }

        public boolean isSameUnion(Object a, Object b){
            int i = list.indexOf(a);
            int i2 = list.indexOf(b);
            if(i != -1 && i2 != -1){
                int f1 = findFather(i);
                int f2 = findFather(i2);
                if(f1 == f2){
                    return true;
                }
            }

            return false;
        }

        public void union(Object a, Object b){
            int i1 = list.indexOf(a);
            int i2 = list.indexOf(b);

            if(i1 != -1 && i2 != -1){
                int f1 = findFather(i1);
                int f2 = findFather(i2);
                if(f1 != f2){
                    int more = size[f1] > size[f2] ? f1 : f2;
                    int less = more == f1 ? f2 : f1;

                    parent[less] = more;
                    size[more] += size[less];
                    size[less] = 0;
                    sets--;
                }
            }
        }

        private int findFather(int index){
            int helpIndex = 0;
            while(parent[index] != index){
                help[helpIndex++] = parent[index];
                index = parent[index];
            }

            while(helpIndex >= 0){
                parent[help[helpIndex--]] = index;
            }

            return index;
        }
    }
}
