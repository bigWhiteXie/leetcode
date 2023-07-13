package class18;

import javax.swing.plaf.BorderUIResource;
import java.util.HashMap;
import java.util.Map;

// 本题测试链接：https://leetcode.com/problems/stickers-to-spell-word

/**
 * 我们有 n 种不同的贴纸。每个贴纸上都有一个小写的英文单词。
 *
 * 您想要拼写出给定的字符串 target，方法是从收集的贴纸中切割单个字母并重新排列它们。如果你愿意，你可以多次使用每个贴纸，每个贴纸的数量是无限的。
 *
 * 返回你需要拼出 target的最小贴纸数量。如果任务不可能，则返回 -1 。
 *
 * 输入： stickers = ["with","example","science"], target = "thehat"
 * 输出：3
 * 解释：
 * 我们可以使用 2 个 "with" 贴纸，和 1 个 "example" 贴纸。
 * 把贴纸上的字母剪下来并重新排列后，就可以形成目标 “thehat“ 了。
 * 此外，这是形成目标字符串所需的最小贴纸数量。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/stickers-to-spell-word
 *
 * 业务限制模型：当target被完全消减后结束
 */
public class Code03_StickersToSpellWord {
	public static int minStickers(String[] stickers, String target){


		return process(stickers,target,0);
	}

	//返回stickers[i]及之后的stickers拼凑target的最小张数
	//同时我们发现此题的状态变量是target和i，并不适合创建矩阵左动态规划
	private static int process(String[] stickers, String target, int i) {
		if(target == null || target.equals("")){ // target为空直接返回0张
			return 0;
		}else if(i == stickers.length){ //target不为空，但stickers已经遍历完
			return -1;
		}

		int min = process(stickers,target,i+1);
		int count = 0;

		if(min == -1){
			min = Integer.MAX_VALUE;
		}

		String s = stickers[i];
		while(hasRest(target,s)){
			count++;
			target = minus2(target, s);
			int p = process(stickers, target, i + 1);
			if(p!=-1){
				min = Math.min(min,count+p);
			}
		}
		return min == Integer.MAX_VALUE ? -1 : min;
	}

	private static boolean hasRest(String s1, String s2) {
		HashMap<Character, Integer> map = new HashMap<>();
		char[] a1 = s1.toCharArray();
		char[] a2 = s2.toCharArray();


		for (int i = 0; i < a1.length; i++) {
			Integer absent = map.putIfAbsent(a1[i], 1);
			if(absent != null){
				map.put(a1[i],map.get(a1[i]) + 1);
			}
		}

		for (int i = 0; i < a2.length; i++) {
			if(map.get(a2[i]) != null){
				return true;
			}
		}

		return false;
	}

	public static String minus2(String s1,String s2){
		HashMap<Character, Integer> map = new HashMap<>();
		char[] a1 = s1.toCharArray();
		char[] a2 = s2.toCharArray();

		for (int i = 0; i < a1.length; i++) {
			Integer absent = map.putIfAbsent(a1[i], 1);
			if(absent != null){
				map.put(a1[i],map.get(a1[i]) + 1);
			}
		}

		for (int i = 0; i < a2.length; i++) {
			Integer res;
			if((res = map.get(a2[i])) != null && res > 0){
				if(res == 1){
					map.remove(a2[i]);
				}else {
					map.put(a2[i],res-1);
				}
			}
		}
		StringBuilder str = new StringBuilder();
		for (Character key : map.keySet()) {
			int num = map.get(key);
			while(num-- > 0){
				str.append(key);
			}
		}

		return str.toString();



	}

	public static int minStickers1(String[] stickers, String target)
	{
		HashMap<String, Integer> map = new HashMap<>();
		return process1(stickers,target,map);
	}

	private static int process1(String[] stickers, String target, Map<String,Integer> map) {
		if(map.containsKey(target)){
			return map.get(target);
		}
		if(target == null || target.length() == 0){
			return 0;
		}

		int min = Integer.MAX_VALUE;
		for(String sticker:stickers){
			String rest = minus(target,sticker);
			if(!rest.equals(target)){
				int p = process1(stickers, rest, map);
				if(p == -1){
					map.put(target,-1);
					return -1;
				}
				min = Math.min(min,p);
			}
		}
		map.put(target,min == Integer.MAX_VALUE ? -1 : min+1);
		return map.get(target);

	}


	public static int minStickers4(String[] stickers, String target)
	{

		return process4(stickers,0,target);
	}

	/**
	 * 从stickers的index开始，填充target最少的张数
	 * 分别计算用[0,业务限制]张stickers[index]去填充target时需要的最少张数，再求出最小值
	 * @param stickers
	 * @param index
	 * @param target
	 * @return
	 */
	private static int process4(String[] stickers, int index,String target) {
		if(index == stickers.length){
			return target == null || target.length() == 0 ? 0 : -1;
		}
		if(target == null || target.length() == 0){
			return 0;
		}

		int min = Integer.MAX_VALUE;
		int count = 0;

		while(true){
			int i = process4(stickers, index + 1, target);
			if( i!= -1){
				min = Math.min(min,i+count);
				count++;
				String res = minus(target,stickers[index]);
				if(res.equals(target)){
					break;
				}
				target =res;
			}else {
				count++;
				String res = minus(target,stickers[index]);
				if(res.equals(target)){
					break;
				}
				target =res;
			}
		}

		return min == Integer.MAX_VALUE ? process4(stickers,index+1,target) : min;
	}

	private static String minus(String target, String sticker) {
		char[] chars = sticker.toCharArray();
		char[] tarArr = target.toCharArray();
		int[] count = new int[26];
		for(char c : tarArr){
			count[c - 'a']++;
		}
		for(char c:chars){
			count[c - 'a']--;
		}

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < count.length; i++) {
			while(count[i] > 0){
				builder.append((char) ('a' + i));
				count[i]--;
			}
		}

		return builder.toString();
	}


	public static int minStickers2(String[] stickers, String target) {
		int N = stickers.length;
		// 关键优化(用词频表替代贴纸数组)
		int[][] counts = new int[N][26];
		for (int i = 0; i < N; i++) {
			char[] str = stickers[i].toCharArray();
			for (char cha : str) {
				counts[i][cha - 'a']++;
			}
		}
		int ans = process2(counts, target);
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	// stickers[i] 数组，当初i号贴纸的字符统计 int[][] stickers -> 所有的贴纸
	// 每一种贴纸都有无穷张
	// 返回搞定target的最少张数
	// 最少张数
	public static int process2(int[][] stickers, String t) {
		if (t.length() == 0) {
			return 0;
		}
		// target做出词频统计
		// target  aabbc  2 2 1..
		//                0 1 2..
		char[] target = t.toCharArray();
		int[] tcounts = new int[26];
		for (char cha : target) {
			tcounts[cha - 'a']++;
		}
		int N = stickers.length;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			// 尝试第一张贴纸是谁
			int[] sticker = stickers[i];
			// 最关键的优化(重要的剪枝!这一步也是贪心!)
			if (sticker[target[0] - 'a'] > 0) {
				StringBuilder builder = new StringBuilder();
				for (int j = 0; j < 26; j++) {
					if (tcounts[j] > 0) {
						int nums = tcounts[j] - sticker[j];
						for (int k = 0; k < nums; k++) {
							builder.append((char) (j + 'a'));
						}
					}
				}
				String rest = builder.toString();
				min = Math.min(min, process2(stickers, rest));
			}
		}
		return min + (min == Integer.MAX_VALUE ? 0 : 1);
	}

	public static int minStickers3(String[] stickers, String target) {
		int N = stickers.length;
		int[][] counts = new int[N][26];
		for (int i = 0; i < N; i++) {
			char[] str = stickers[i].toCharArray();
			for (char cha : str) {
				counts[i][cha - 'a']++;
			}
		}
		HashMap<String, Integer> dp = new HashMap<>();
		dp.put("", 0);
		int ans = process3(counts, target, dp);
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	public static int process3(int[][] stickers, String t, HashMap<String, Integer> dp) {
		if (dp.containsKey(t)) {
			return dp.get(t);
		}
		char[] target = t.toCharArray();
		int[] tcounts = new int[26];
		for (char cha : target) {
			tcounts[cha - 'a']++;
		}
		int N = stickers.length;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			int[] sticker = stickers[i];
			if (sticker[target[0] - 'a'] > 0) {
				StringBuilder builder = new StringBuilder();
				for (int j = 0; j < 26; j++) {
					if (tcounts[j] > 0) {
						int nums = tcounts[j] - sticker[j];
						for (int k = 0; k < nums; k++) {
							builder.append((char) (j + 'a'));
						}
					}
				}
				String rest = builder.toString();
				min = Math.min(min, process3(stickers, rest, dp));
			}
		}
		int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
		dp.put(t, ans);
		return ans;
	}

	public static void main(String[] args) {
		String[] stickers = {"with","example","science","qoy","girl"};
		String target = "thehatooqyggl";
		System.out.println(minStickers1(stickers, target));
		System.out.println(minStickers4(stickers, target));
		System.out.println(minStickers(stickers, target));
	}

}
