package class13;

import class04.Code02_quickSort;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 问题：问至少多少栈灯才能把路全照亮
 * X表示墙，会隔断灯的范围，i位置上的一盏灯能照亮i-1,i,i+1三个位置
 */
public class Code01_Light {

	public static int minLight1(String road) {
		if (road == null || road.length() == 0) {
			return 0;
		}
		return process(road.toCharArray(), 0, new HashSet<>());
	}

	// str[index....]位置，自由选择放灯还是不放灯
	// str[0..index-1]位置呢？已经做完决定了，那些放了灯的位置，存在lights里
	// 要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯
	public static int process(char[] str, int index, HashSet<Integer> lights) {
		if (index == str.length) { // 结束的时候
			for (int i = 0; i < str.length; i++) {
				if (str[i] != 'X') { // 当前位置是点的话
					if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
						return Integer.MAX_VALUE;
					}
				}
			}
			return lights.size();
		} else { // str还没结束
			// i X .
			int no = process(str, index + 1, lights);
			int yes = Integer.MAX_VALUE;
			if (str[index] == '.') {
				lights.add(index);
				yes = process(str, index + 1, lights);
				lights.remove(index);
			}
			return Math.min(no, yes);
		}
	}

	public static int minLight3(String road){
		char[] arr = road.toCharArray();
		HashMap<Integer, Integer> map = new HashMap<>();
		return process2(arr,0,new HashSet<Integer>(),map);
	}
	//[. . . x]
	private static int process2(char[] arr, int index, HashSet<Integer> lights, Map<Integer,Integer> map) {
//		if(map.containsKey(index)){
//			return map.get(index);
//		}
		int ans = 0;
		if(index >= arr.length){
			boolean flag = true;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] != 'X') { // 当前位置是点的话
					if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
						ans = Integer.MAX_VALUE;
						flag = false;
						break;
					}
				}
			}

			ans = flag ? lights.size() : Integer.MAX_VALUE;
		}else{
			//不在index位置点灯
			int p1 = process2(arr,index+1,lights,map);
			//在index位置点灯
			int p2 = Integer.MAX_VALUE;
			//若index为'.'则可以点灯
			if(arr[index] == '.'){
				lights.add(index);
				if(lights.contains(index - 1) || lights.contains(index + 1)){
					lights.remove(index-1);
					lights.remove(index+1);
				}
				p2 = process2(arr,index+2,lights,map);
			}
			ans = Math.min(p1,p2);
		}
		map.put(index,ans);
		return ans;
	}

	/**
	 * 若i位置是墙，则直接跳过
	 * i位置是路,count+1
	 * 		i+1是墙，则照亮i,i=i+1
	 * 		i+1是路，i+2是墙i=i+1
	 * 		i+1也是路，i+2也是路，则照亮i+1，i=i+4
	 * @param road
	 * @return
	 */
	public static int minLight2(String road) {
		char[] array = road.toCharArray();
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			if(array[i] == 'X'){
				continue;
			}else {
				count++;
				if(i+2 >= array.length){
					break;
				}
				if( array[i+1] == 'X'){
					i += 1;
				}else if( array[i+1] == '.' && array[i+2] == 'X'){
					i += 2;
				}else if( array[i+1] == '.' && array[i+2] == '.'){
					i += 2;
				}
			}
		}
		return count;
	}

	// for test
	public static String randomString(int len) {
		char[] res = new char[(int) (Math.random() * len) + 1];
		for (int i = 0; i < res.length; i++) {
			res[i] = Math.random() < 0.5 ? 'X' : '.';
		}
		return String.valueOf(res);
	}

	public static void main(String[] args) {
		int len = 20;
		int testTime = 100000;
		int ops = 0;
		int nice = 0;
		for (int i = 0; i < testTime; i++) {
			String test = randomString(len);
			int ans1 = minLight1(test);
			int ans2 = minLight3(test);
			if (ans1 != ans2) {
				ops++;
			}else {
				nice++;
			}
		}
		System.out.println("oops: " + ops);
		System.out.println("nice: " + nice);
		System.out.println("finish!");
	}
}
