package class01;

import java.util.*;

public class Code02_EvenTimesOddTimes {

	// arr中，只有一种数，出现奇数次
	public static int printOddTimesNum1(int[] arr) {
		int eor = 0;
		for(int i=0; i<arr.length; i++){
			eor ^= arr[i];
		}
		System.out.println(eor);
		return eor;
	}

	// arr中，有两种数，出现奇数次
	public static List<Integer> printOddTimesNum2(int[] arr) {

		int eor = 0;
		//得到a^b的结果
		for (int i : arr) {
			eor ^= i;
		}
		//a^b的首个1
		int firstOne = eor & (~eor + 1);
		int a = 0;
		//得到两数其中一个
		for (int i : arr) {
			if((i & firstOne) != 0 ){
				a ^= i;
			}
		}

		int b = eor ^ a;
		ArrayList<Integer> list = new ArrayList<>();
		list.add(a);
		list.add(b);
		return list;
	}

	public static List comparator(int[] arr) {
		HashMap<Integer, Integer> map = new HashMap<>();
		ArrayList<Integer> list = new ArrayList<>();
		for (int num : arr) {
			if (map.containsKey(num)) {
				map.put(num, map.get(num) + 1);
			} else {
				map.put(num, 1);
			}
		}
		for (int num : map.keySet()) {
			if (map.get(num) % 2 == 1) {
				list.add(num);
			}
		}
		return list;
	}

	public static boolean equals(List<Integer> list2, List<Integer> list){
		if(list.size() != list2.size()){
			return false;
		}

		for(int i=0; i < list.size();){
			for(int j=0 ;j<list2.size(); ){
				if(list.get(i) == list2.get(j)){
					list.remove(i);
					list2.remove(j);

					i--;
					break;
				}
				j++;
			}
			i++;
		}

		if(list.size() == 0 && list2.size() == 0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 构造一个随机长度、oddNums个数是奇数，其余数均是偶数的数组
	 * @param range 随机数的范围[0,range]
	 * @param oddNums
	 * @return
	 */
	public static int[] randomArr(int range,int oddNums){
		ArrayList<Integer> list = new ArrayList<>();
		//偶数个数 [0,range]
		int evenNums = (int)(Math.random() * (range+1));

		//随机生成偶数并添加至列表
		while(evenNums > 0){
			//[0,2*range - 2]
			int evenTime = (int)((Math.random()) * range) * 2;  //偶数出现次数
			int evenNum = randomNum(range) ;   //随机生成偶数
			while(evenTime-- > 0){
				list.add(evenNum);
			}
			evenNums--;
		}

		//用set保存奇数次数的数，避免重复
		HashSet<Integer> set = new HashSet<>();
		//随机生成奇数次的数
		while(oddNums-- > 0){
			//[1,2*range - 1]
			int oddTime = (int)((Math.random()) * range) * 2 + 1;
			boolean flag = false;
			Integer oddNum = randomNum(range);

			//如果
			if(set.contains(oddNum)){
				oddNums++;
				continue;
			}

			set.add(oddNum);
			while(oddTime-- > 0){
				list.add(oddNum);
			}
		}


		int[] arr = new int[list.size()];

		int i = 0;
		while(list.size() > 0){
			int index = (int) (Math.random() * list.size());
			arr[i++] = list.remove(index);
		}
		return arr;
	}
	//[-range,range]
	public static int randomNum(int range){
		return ((int) (Math.random()*range) + 1) - ((int) (Math.random()*range) + 1);
	}


	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void staticArr(int[] arr){
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int item : arr){
			Integer ifAbsent = map.putIfAbsent(item, 1);
			if(ifAbsent == null){
				continue;
			}
			map.computeIfPresent(item,(key,oldValue)->{
				return oldValue + 1;
			});
		}

		Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
		for(Map.Entry entry : entries){
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
	public static void swap(int[] arr, int i, int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	
	public static void main(String[] args) {
		int testTime = 100000;
		while(testTime-- > 0){

			int[] ints = randomArr(5, 2);
			List list = comparator(ints);
			List<Integer> list2 = printOddTimesNum2(ints);
			if (!equals(list,list2)){
				System.out.println(list);
				System.out.println(list2);
				throw new RuntimeException("测试错误");
			}


		}

//		int[] ints = randomArr(5, 1);
//		printArray(ints);
		System.out.println("nice");


	}

}
