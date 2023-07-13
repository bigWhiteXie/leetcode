package class07;

import java.util.HashMap;
import java.util.Map;


/**
 * 前缀树：根据字符串在路径上进行赋值
 * [abc,bcd,abd,ace]
 */
public class Code01_TireTree {
    static class Node{
        int pass;
        int end;
        Map<Integer,Node> childs = new HashMap<>();
    }

    static class TireTree{
        Node root = new Node();

        public void insert(String str){
            char[] array = str.toCharArray();
            Node cur = root;
            for(int i=0; i<array.length; i++){
                int path = array[i];
                if(cur.childs.get(path) == null){
                    cur.childs.put(path,new Node());
                }
                cur =cur.childs.get(path);
                cur.pass++;
            }

            cur.end++;
        }

        /**
         * 查找str出现过几次
         * @param str
         * @return
         */
        public int search(String str){
            Node cur = root;
            char[] array = str.toCharArray();
            for(int i=0; i<array.length; i++){
                int path = array[i];
                if(!cur.childs.containsKey(path)){
                    return  0;
                }
                cur = cur.childs.get(path);
            }
            return cur.end;
        }


        public void remove(String str){
            if(search(str) != 0){
                Node cur = root;
                cur.pass--;
                char[] array = str.toCharArray();
                for(int i=0; i<array.length; i++){
                    int path = array[i];
                    Node next = cur.childs.get(path);
                    next.pass--;
                    if(next.pass == 0){
                        cur.childs.remove(path);
                        return;
                    }
                    cur = next;
                }

                cur.end--;

            }
        }

        public int prefixNumber(String pre) {
            char[] array = pre.toCharArray();
            Node cur = root;
            for (int i = 0; i < array.length; i++) {
                int path = array[i];

                if(!cur.childs.containsKey(path)){
                    return 0;
                }
                cur = cur.childs.get(path);
            }
            return cur.pass;
        }

    }

    public static class Right {

        private HashMap<String, Integer> box;

        public Right() {
            box = new HashMap<>();
        }

        public void insert(String word) {
            if (!box.containsKey(word)) {
                box.put(word, 1);
            } else {
                box.put(word, box.get(word) + 1);
            }
        }

        public void delete(String word) {
            if (box.containsKey(word)) {
                if (box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            if (!box.containsKey(word)) {
                return 0;
            } else {
                return box.get(word);
            }
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String cur : box.keySet()) {
                if (cur.startsWith(pre)) {
                    count += box.get(cur);
                }
            }
            return count;
        }
    }

    public static void printNode(Node root){

    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            TireTree trie1 = new TireTree();

            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);

                    right.insert(arr[j]);

                } else if (decide < 0.5) {
                    trie1.remove(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 1) {
                    int ans1 = trie1.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    if (ans1 != ans3) {
                        System.out.println("search Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(arr[j]);
                    int ans3 = right.prefixNumber(arr[j]);
                    if (ans1 != ans3) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");
    }
}
