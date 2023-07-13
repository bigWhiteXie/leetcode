package class18;

// 这个问题leetcode上可以直接测
// 链接：https://leetcode.com/problems/longest-common-subsequence/

/**
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none)
 * deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 *
 * 多样本对应尝试模型：str1与str2相对应，每个str对应一个index
 */
public class Code04_LongestCommonSubsequence {

	public static int longestCommonSubsequence1(String s1, String s2) {
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();

		return process(c1,c2,0,0);

	}

	private static int process(char[] c1, char[] c2, int i, int i1) {
		if(i == c1.length - 1 && i1 == c2.length - 1){
			if(c1[i] == c2[i1]){
				return 1;
			}else {
				return 0;
			}
		}

		if(i == c1.length-1){
			if(c1[i] == c2[i1]){
				return 1;
			}else {
				return process(c1,c2,i,i1+1);
			}
		}

		if(i1 == c2.length - 1){
			if(c1[i] == c2[i1]){
				return 1;
			}else {
				return process(c1,c2,i+1,i1);
			}
		}

		int r1 = 0;
		if(c1[i] == c2[i1]){
			return r1 = 1 + process(c1,c2,i+1,i1+1);
		}

		int r2 = process(c1,c2,i+1,i1);
		int r3 = process(c1,c2,i,i1+1);

		return Math.max(r2,r3);
	}


	public static int longestCommonSubsequence2(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
			return 0;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int N = str1.length;
		int M = str2.length;
		int[][] dp = new int[N][M];
		if(str1[N-1] == str2[M-1]){
			dp[N-1][M-1] = 1;
		}else {
			dp[N-1][M-1] = 0;
		}

		for(int i=N - 2; i >= 0; i--){
			if(str1[i] == str2[M-1]){
				dp[i][M-1] = 1;
			}else {
				dp[i][M-1] = dp[i+1][M-1];
			}
		}

		for(int j = M - 2; j >= 0; j--){
			if(str1[N-1] == str2[j]){
				dp[N-1][j] = 1;
			}else{
				dp[N-1][j] = dp[N-1][j+1];
			}
		}

		for(int i=N-2; i>=0; i--){
			for(int j = M-2; j>=0; j--){
				if(str1[i] == str2[j]){
					dp[i][j] = 1 + dp[i+1][j+1];
				}else {
					dp[i][j] = Math.max(dp[i][j+1],dp[i+1][j]);
				}
			}
		}
		return dp[0][0];
	}

	public static void main(String[] args) {
		String text1 = "abc", text2 = "abc" ;
		System.out.println(longestCommonSubsequence1(text1, text2));
		System.out.println(longestCommonSubsequence2(text1, text2));
	}

}
