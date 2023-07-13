package 哈希表;

import java.util.*;

public class 两个数组的交际 {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for(int i:nums1){
            set1.add(i);;
        }
        for(int i:nums2){
            if(set1.contains(i)){
                set2.add(i);
            }
        }
        int[] arr = new int[set2.size()];
        int index = 0;
        for(int i:set2){
            arr[index++] = i;
        }
        return arr;
    }

    public int[] intersection2(int[] nums1, int[] nums2){
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        for(int i:nums1){
            Integer res = map.putIfAbsent(i, 1);
            if(res != null){
                map.put(i,res+1);
            }
        }
        for(int i:nums2){
            if(map.get(i) != null){
                Integer res = map2.putIfAbsent(i, 1);
                if(res != null){
                    map2.put(i,res+1);
                }
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        Set<Integer> set = map2.keySet();
        for(int i:set){
            int num = Math.min(map.get(i),map2.get(i));
            for(int j=0;j<num; j++){
                list.add(i);
            }
        }
        int[] nums = new int[list.size()];
        int index = 0;
        for(int i=0; i<list.size();i++){
            nums[index++] = list.get(i);
        }
        return nums;
    }
}
