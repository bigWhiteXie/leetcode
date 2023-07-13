package class16;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的所有排序
 * abc--->abc bac cba acb bca cab
 */
public class Code04_PrintAllPermutations {

	public static List<String> permutation1(String s) {
		char[] array = s.toCharArray();
		ArrayList<Character> res = new ArrayList<>();
		for (char c : array) {
			res.add(c);
		}

		ArrayList<String> ans = new ArrayList<>();
		String path = "";
		process(res,path,ans);
		return ans;
	}

	/**
	 * 每个元素轮流来充当当前递归第一个元素
	 * @param res
	 * @param path
	 * @param ans
	 */
	private static void process(ArrayList<Character> res, String path, ArrayList<String> ans) {
		if(res.isEmpty()){
			ans.add(path);
		}
		boolean[] visited = new boolean[256];
		for(int i=0; i<res.size(); i++){
			Character cur = res.remove(i);
			if(!visited[cur]){
				visited[cur] = true;
				process(res,path+cur,ans);
			}
			res.add(i,cur);
		}
	}

	public static List<String> permutation2(String s) {
		char[] array = s.toCharArray();


		ArrayList<String> ans = new ArrayList<>();

		process2(array,0,ans);
		return ans;
	}

	/**
	 * abc-->
	 * 		  abc acb
	 * 		  bac bca
	 * 		  cba cab
	 * @param res
	 * @param ans
	 */
	private static void process2(char[] res, int index, ArrayList<String> ans) {
		if(index == res.length){
			ans.add(String.valueOf(res));
		}
		boolean[] visited = new boolean[256];
		for(int i=index; i < res.length; i++){
			if(!visited[res[i]]) {
				visited[res[i]] = true;
				swap(res, index, i);
				process2(res, index + 1, ans);
				swap(res, index, i);
			}
		}

	}


	public static void swap(char[] chs, int i, int j) {
		char tmp = chs[i];
		chs[i] = chs[j];
		chs[j] = tmp;
	}



	public static void main(String[] args) {
		String s = "acc";
		List<String> ans1 = permutation1(s);
		for (String str : ans1) {
			System.out.println(str);
		}
		System.out.println("=======");
		List<String> ans2 = permutation2(s);
		for (String str : ans2) {
			System.out.println(str);
		}
//		System.out.println("=======");
//		List<String> ans3 = permutation3(s);
//		for (String str : ans3) {
//			System.out.println(str);
//		}

	}

}
