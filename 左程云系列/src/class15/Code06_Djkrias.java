package class15;

import java.util.*;

public class Code06_Djkrias {
    static class Record implements Comparable<Record> {
        Node node;
        int distance;

        public Record(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Record record = (Record) o;
            return  Objects.equals(node, record.node);
        }

        @Override
        public int hashCode() {
            return Objects.hash(node, distance);
        }

        @Override
        public int compareTo(Record o) {
            return distance - o.distance;
        }
    }

    static class NodeHeap{
        Record[] records;
        Map<Node,Integer> nodeMap;
        int size;

        public NodeHeap(int size) {
            records = new Record[size];

            nodeMap = new HashMap<>();
        }

        public boolean isEmpty(){
            return size == 0;
        }

        private void heapdifyInsert(int index){
            while(records[index].compareTo(records[(index-1) / 2]) < 0){
                swap(index,(index-1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void headfify(int index){
            int left = index * 2 + 1;
            while (left < size){
                int best = left + 1 < size && records[left+1].compareTo(records[left]) < 0 ? left+1 : left;
                if(records[best].compareTo(records[index]) < 0){
                    swap(index,best);
                    index = best;
                }else {
                    break;
                }
            }
        }



        private void swap(int i1, int i2) {
            Record r1 = records[i1];
            Record r2 = records[i2];

            nodeMap.put(r1.node,i2);
            nodeMap.put(r2.node,i1);


            Record t = records[i1];
            records[i1] = records[i2];
            records[i2] = t;
        }


        public void push(Node start,int distance) {
            Record record = new Record(start, distance);
            records[size++] = record;

            nodeMap.put(record.node,size - 1);
            heapdifyInsert(size - 1);
        }

        public Record pop() {
            Record record = records[0];
            swap(0,--size);

            headfify(0);
            nodeMap.put(record.node, -1);

            return record;
        }

        public void addOrUpdateOrIgnore(Node to, int distance) {
            //如果该节点第一次出现则直接加入堆中
            if(!nodeMap.containsKey(to)){
                push(to,distance);
            }else if(nodeMap.get(to) != -1){
                Integer index = nodeMap.get(to);
                Record oldRecord = records[index];
                oldRecord.distance = Math.min(oldRecord.distance,distance);
                heapdifyInsert(index);
            }else {
                System.out.println("什么也不做");
            }
        }

        public Record peek() {
            return records[0];
        }
    }


    static class NodeHeap2{
        Record[] records;
        Map<Node,Integer> nodeMap;
        Comparator<Record> comp = (a,b)->a.distance - b.distance;
        int size = 0;
        public NodeHeap2(int size) {
            records = new Record[size];
            nodeMap = new HashMap<>();
        }
        public void push(Record record){
            records[size++] = record;
            nodeMap.put(record.node, size-1);
            heapInsert(size-1);
        }

        public Record pop(){
            Record record = records[0];

            swap(0,--size);
            nodeMap.put(record.node, -1);
            heapdify(0);
            return record;
        }

        public void addOrUpdateOrIgnore(Node node, int dis){
            if(!nodeMap.containsKey(node)){
                push(new Record(node,dis));
            }else if(nodeMap.get(node) != -1){
                Integer index = nodeMap.get(node);
                Record old = records[index];
                old.distance = Math.min(old.distance,dis);
                heapInsert(index);
            }
        }

        public boolean isEmpty(){
            return size == 0;
        }
        private void heapInsert(int index){
            int parent = (index - 1) / 2;
            while(comp.compare(records[index],records[parent]) < 0){
                swap(index,parent);
                index = parent;
                parent = (index - 1) / 2;
            }
        }

        private void heapdify(int index){
            int left = index*2 + 1;
            while (left < size){
                int best = left + 1 < size && comp.compare(records[left+1],records[left]) < 0 ? left+1 : left;
                if(comp.compare(records[best],records[index]) < 0){
                    swap(index,best);
                    index = best;
                    left = index*2 + 1;
                }else {
                    break;
                }
            }
        }

        private void swap(int i, int j){
            Record r1 = records[i];
            Record r2 = records[j];

            Integer i1 = nodeMap.get(r1.node);
            Integer i2 = nodeMap.get(r2.node);

            records[i] = r2;
            nodeMap.put(r1.node,i2);

            records[j] = r1;
            nodeMap.put(r2.node,i1);
        }
    }

    public static Map<Node,Integer> djkrisa(Node start,int size){
        Map<Node,Integer> ans = new HashMap<>();
        NodeHeap2 heap = new NodeHeap2(size);

        heap.push(new Record(start,0));

        while(!heap.isEmpty()){
            Record record = heap.pop();
            Node node = record.node;

            for (Edge edeg : node.edegs) {
                Node to = edeg.to;
                heap.addOrUpdateOrIgnore(to, record.distance + edeg.weight);
            }
            ans.put(record.node,record.distance);
        }

        return ans;

    }

    public static Map<Node,Integer> djkrisa2(Node start,int size){
        Map<Node,Integer> ans = new HashMap<>();
        PriorityQueue<Record> heap = new PriorityQueue<>();
        HashSet<Node> set = new HashSet<>();
        heap.add(new Record(start,0));

        while(!heap.isEmpty()){
            Record record = heap.poll();
            Node node = record.node;

            for (Edge edeg : node.edegs) {
                Node to = edeg.to;
                if(!set.contains(to)) {
                    Record r = new Record(to, record.distance + edeg.weight);
                    heap.add(r);
                }
            }
            set.add(node);
            ans.put(record.node,record.distance);
        }

        return ans;

    }

    public static void main(String[] args) {
        Node start = new Node(0);
        Node o1 = new Node(1);
        Node o2 = new Node(2);
        Node o3 = new Node(3);
        start.nexts.add(o1);
        start.nexts.add(o2);
        start.nexts.add(o3);

        Node o4 = new Node(4);
        Node o5 = new Node(5);
        Node o6 = new Node(6);
        o1.nexts.add(o4);
        o3.nexts.add(o5);
        o5.nexts.add(o6);

        Edge e1 = new Edge(2, start, o1);
        Edge e2 = new Edge(1, start, o2);
        Edge e3 = new Edge(3, start, o3);
        start.edegs.add(e1);
        start.edegs.add(e2);
        start.edegs.add(e3);

        Edge e4 = new Edge(2, o1, o4);
        Edge e5 = new Edge(6, o2, o5);
        Edge e7 = new Edge(1, o1, o5);
        o1.edegs.add(e4);
        o2.edegs.add(e5);
        o1.edegs.add(e7);

        Edge e6 = new Edge(7, o5, o6);
        o5.edegs.add(e6);

        Map<Node, Integer> map = djkrisa(start, 7);
        Set<Node> keySet = map.keySet();
        for (Node node : keySet) {
            System.out.println(node.val + ":" + map.get(node));
        }
        System.out.println("--------------------------------");
        Map<Node, Integer> map1 = djkrisa2(start, 7);
        Set<Node> keySet1 = map.keySet();
        for (Node node : keySet1) {
            System.out.println(node.val + ":" + map.get(node));
        }
    }
}
