package class18;

/**
 * 给定一个字符串比如"123",其中1代表a，2代表b，依此类推，问"123"能被转化为多少种不同的字符串？
 * "123"能够被转化成 "abc"、"lc"、"aw"三种
 *
 * 从左到右尝试模型
 */
public class Code02_ConvertToLetterString {
	public static int number(String str){
		if(str == null || str.length() == 0){
			return 0;
		}

		char[] array = str.toCharArray();

		return process(array,0);
	}

	/**
	 * 从0-i共有多少种不同的转换方法
	 * @param array
	 * @param i
	 * @return
	 */
	private static int process(char[] array, int i) {
		//当i走到尽头时返回1
		if(i >= array.length){
			return 1;
		}else if(array[i] == '0'){ //若arr[i]='0'则此路不通直接返回0
			return 0;
		}

		int r1 = 0;
		if(i<array.length-1) {
			//当范围小于26时走两步
			if ((array[i] - '0') * 10 + (array[i + 1] - '0') <= 26){
				r1 = process(array,i+2);
			}
		}

		int r2 = process(array,i+1);

		return r1 + r2;
	}
	public static int dp2(String s){
		char[] array = s.toCharArray();
		int[] info = new int[s.length()+1];
		info[s.length()] = 1;
		for(int i=s.length()-1; i>=0; i--){
			if(array[i] == '0'){
				continue;
			}
			int r1 = 0;
			if(i<array.length-1) {
				//当范围小于26时走两步
				if ((array[i] - '0') * 10 + (array[i + 1] - '0') <= 26){
					r1 = info[i+2];
				}
			}

			int r2 = info[i+1];
			info[i] = r1+r2;
		}
		return info[0];
	}

	public static int dp(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] array = s.toCharArray();
		int[] dp = new int[s.length()+1];
		dp[s.length()] = 1;
		for(int i=s.length() - 1; i>=0; i--){
			int r1 = 0;
			if(array[i] == '0'){
				dp[i] = 0;
				continue;
			}
			if(i<array.length-1) {
				if ((array[i] - '0') * 10 + (array[i + 1] - '0') <= 26){
					r1 = dp[i+2];
				}
			}

			int r2 = dp[i+1];
			dp[i] = r1 + r2;
		}

		return dp[0];

	}


	public static void main(String[] args) {
		System.out.println(number("7210231231232031203123"));
		System.out.println(dp("7210231231232031203123"));
		System.out.println(dp2("7210231231232031203123"));
	}

}
