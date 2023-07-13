package class16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 打印字符串的所有子串
 *  例：abc-->a b c ab ac bc abc
 */
public class Code03_PrintAllSubsquences {

	// s -> "abc" ->
	public static List<String> subs(String s) {
		char[] array = s.toCharArray();
		ArrayList<String> ans = new ArrayList<>();
		process(array,0,ans,"");
		return ans;
	}

	/**
	 * 用path保存上一层递归的结果并传入到下一层
	 * @param str
	 * @param index
	 * @param ans
	 * @param path
	 */
	public static void process(char[] str, int index, List<String> ans, String path){
		if(index == str.length){
			ans.add(path);
			return;
		}
		process(str,index+1,ans,path+str[index]);
		process(str,index+1,ans,path);
	}




	public static List<String> subsNoRepeat(String s) {
		char[] str = s.toCharArray();
		String path = "";
		HashSet<String> set = new HashSet<>();
		process2(str, 0, set, path);
		List<String> ans = new ArrayList<>();
		for (String cur : set) {
			ans.add(cur);
		}
		return ans;
	}

	public static void process2(char[] str, int index, HashSet<String> set, String path) {
		if (index == str.length) {
			set.add(path);
			return;
		}
		String no = path;
		process2(str, index + 1, set, no);
		String yes = path + String.valueOf(str[index]);
		process2(str, index + 1, set, yes);
	}

	public static void main(String[] args) {
		String test = "abcd";
		List<String> ans1 = subs(test);
//		List<String> ans2 = subsNoRepeat(test);

		for (String str : ans1) {
			System.out.println(str);
		}
		System.out.println("=================");
//		for (String str : ans2) {
//			System.out.println(str);
//		}
		System.out.println("=================");

	}

}
