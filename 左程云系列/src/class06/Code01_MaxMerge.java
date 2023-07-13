package class06;

import java.util.*;

/**
 * 题目：给出一些线段，求一个线段最多能与多少线段有交集
 */
public class Code01_MaxMerge {
    static class Line{
        int start;
        int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * 1.按照线段起点从小到大排好
     * 2.从list中依次选取队列添加到堆中
     *      每次添加要将堆中end<此元素start的线段移除
     * @param m
     * @return
     */
    public static int maxMerge(int[][] m){
        List<Line> list = new ArrayList<>();
        List<Line> heap = new ArrayList<>();
        int heapsize =0;
        for(int i=0; i<m.length; i++){
            list.add(new Line(m[i][0],m[i][1]));
        }
        list.sort(new LineComp());
        int max = 0;

        for(int i=0; i<list.size(); i++){
            Line line = list.get(i);
            while(heapsize > 0 && heap.get(0).end <= line.start){
                swap(heap,0,--heapsize);
                heap.remove(heapsize);
                heapdify(heap,0,heapsize);

            }
            heap.add(line);
            heapInsert(heap,heapsize++);
            max = Math.max(heapsize,max);
        }


        return max;
    }


    public static void heapInsert(List<Line> list,int index){
        HeapComp comp = new HeapComp();
        while(comp.compare(list.get(index),list.get((index-1)/2)) < 0){
            swap(list,index,(index-1)/2);
            index = (index-1) / 2;
        }
    }

    public static void heapdify(List<Line> list,int index, int heapsize){
        HeapComp comp = new HeapComp();
        int left = index * 2 + 1;
        while(left<heapsize){
            int best = left + 1< heapsize && comp.compare(list.get(left+1),list.get(left)) < 0? left+1 : left;
            if(comp.compare(list.get(best),list.get(index)) < 0){
                swap(list,index,best);
                index = best;
                left = index * 2 + 1;
            }else {
                break;
            }
        }
    }

    public static void swap(List<Line> list,int i, int j){
        Line line = list.get(i);
        list.set(i,list.get(j));
        list.set(j,line);
    }
    static class LineComp implements Comparator<Line>{

        @Override
        public int compare(Line o1, Line o2) {
            return o1.start  - o2.start;
        }
    }

    static class HeapComp implements Comparator<Line>{

        @Override
        public int compare(Line o1, Line o2) {
            return o1.end  - o2.end;
        }
    }

    public static int maxCover1(int[][] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }
        int cover = 0;
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            for (int i = 0; i < lines.length; i++) {
                if (lines[i][0] < p && lines[i][1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }

    // for test
    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static void main(String[] args) {

        Line l1 = new Line(4, 9);
        Line l2 = new Line(1, 4);
        Line l3 = new Line(7, 15);
        Line l4 = new Line(2, 4);
        Line l5 = new Line(4, 6);
        Line l6 = new Line(3, 7);

        // 底层堆结构，heap
        PriorityQueue<Line> heap = new PriorityQueue<>(new LineComp());
        heap.add(l1);
        heap.add(l2);
        heap.add(l3);
        heap.add(l4);
        heap.add(l5);
        heap.add(l6);

        while (!heap.isEmpty()) {
            Line cur = heap.poll();
            System.out.println(cur.start + "," + cur.end);
        }

        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = maxCover1(lines);
            int ans2 = maxMerge(lines);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }
}
