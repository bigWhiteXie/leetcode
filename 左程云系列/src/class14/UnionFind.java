package class14;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class UnionFind {
    static class UnionMap<T>{
        Map<T,T> parent;
        Map<T,Integer> size;

        public UnionMap(T[] arr) {
            parent = new HashMap<>();
            size = new HashMap<>();
            for (T t : arr) {
                parent.put(t,t);
                size.put(t,1);
            }
        }

        private T findFather(T a){
            Stack<T> stack = new Stack<>();
            while (parent.get(a) != a){
                stack.push(a);
                a = parent.get(a);
            }

            while(!stack.isEmpty()){
                T pop = stack.pop();
                parent.put(pop,a);
            }

            return a;
        }

        public void union(T a, T b){
            if(parent.containsKey(a) && parent.containsKey(b)){
                T fa = findFather(a);
                T fb = findFather(b);

                if(fa != fb){
                    Integer sa = size.get(fa);
                    Integer sb = size.get(fb);

                    T more = sa >= sb ? fa : fb;
                    T less = more == fa ? fb : fa;

                    parent.put(less,more);
                    size.put(more,sa+sb);
                    size.remove(less);
                }
            }
        }

        public boolean isSameUnion(T a, T b){
            if(parent.containsKey(a) && parent.containsKey(b)){
                return findFather(a) == findFather(b);
            }
            return false;
        }

        public int sets(){
            return size.size();
        }


    }

    static class UnionArr<T>{
        int[] parent;
        int[] size;
        int[] help;
        int sets;

        public UnionArr(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            sets = n;
            for(int i=0; i< n; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }

        private int findFather(int index){
            int count = 0;
            while(parent[index] != index){
                help[count++] = index;
                index = parent[index];
            }

            count--;

            while(count >= 0){
                parent[help[count--]] = index;
            }
            return index;
        }

        public void union(int i1, int i2){
            int f1 = findFather(i1);
            int f2 = findFather(i2);

            if(f1 != f2){
                int s1 = size[f1];
                int s2 = size[f2];

                int more = s1 >= s2 ? f1 : f2;
                int less = more == f1 ? f2 : f1;

                parent[less] = parent[more];
                size[more] = s1 + s2;
                size[less] = 0;
                sets--;
            }
        }

        public int sets(){
            return sets;
        }
    }
}
