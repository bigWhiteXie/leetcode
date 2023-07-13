package class19;

// 测试链接：https://leetcode.com/problems/longest-palindromic-subsequence/

/**
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 *
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 *
 * 输入：s = "bbbab"
 * 输出：4
 * 解释：一个可能的最长回文子序列为 "bbbb" 。
 *
 * 范围尝试模型：分为L和R，它们相遇时结束递归
 */
public class Code01_PalindromeSubsequence {



	public static int longestPalindromeSubseq1(String s){
		if(s == null || s.length() == 0){
			return 0;
		}
		char[] str = s.toCharArray();

		return process(str,0,str.length - 1);
	}

	private static int process(char[] str, int start, int end) {
		if(start > end){
			return 0;
		}
		if(start == end){
			return 1;
		}

		int p1 = process(str,start+1,end);
		int p2 = process(str,start,end-1);
		if(str[start] == str[end]){
			return 2 + process(str,start+1,end-1);
		}
		return Math.max(p1,p2);
	}

	public static int longestPalindromeSubseq2(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = s.toCharArray();
		int[][] dp = new int[s.length()][s.length()];
		for(int i=0;i<s.length(); i++){
			dp[i][i] = 1;
		}

		for(int i = s.length()-2; i>=0; i--){
			for(int j=i+1; j < s.length(); j++){
				if(str[i] == str[j]){
					dp[i][j] = 2 + dp[i+1][j-1];
					continue;
				}
				dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
			}
		}

		return dp[0][s.length() - 1];
	}


	public static void main(String[] args) {
		String s = "bbbab";
		System.out.println(longestPalindromeSubseq1(s));
		System.out.println(longestPalindromeSubseq2(s));
	}

}
