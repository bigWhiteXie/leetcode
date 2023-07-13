package class28;

public class Code01_Manacher {

	public static int manacher(String s) {
		if(s == null || s.length() == 0){
			return -1;
		}
		char[] str = manacherString(s);
		int[] help = new int[str.length];
		int max = Integer.MIN_VALUE;
		int c = 0, r = -1;

		for(int i=0; i<str.length; i++){
			help[i] = i < r ? Math.min(help[2*c - i],r-i) : 1;

			while(i+help[i] < str.length && i-help[i] >= 0){
				if(str[i+help[i]] == str[i-help[i]]){
					help[i]++;
					continue;
				}
				break;
			}

			if(i+help[i] > r){
				c = i;
				r = i+help[i];
				max = Math.max(max,help[i] - 1);
			}

		}
		return max;
	}

	public static char[] manacherString(String str) {
		char[] strArr = new char[str.length() * 2 + 1];
		int j = 0;
		for (int i = 0; i < strArr.length; i++) {
			strArr[i] = (i & 1) == 0 ? '#':str.charAt(j++);
		}
		return strArr;
	}

	// for test
	public static int right(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = manacherString(s);
		int max = 0;
		for (int i = 0; i < str.length; i++) {
			int L = i - 1;
			int R = i + 1;
			while (L >= 0 && R < str.length && str[L] == str[R]) {
				L--;
				R++;
			}
			max = Math.max(max, R - L - 1);
		}
		return max / 2;
	}

	// for test
	public static String getRandomString(int possibilities, int size) {
		char[] ans = new char[(int) (Math.random() * size) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
		}
		return String.valueOf(ans);
	}

	public static void main(String[] args) {
		int possibilities = 5;
		int strSize = 20;
		int testTimes = 5000000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			String str = getRandomString(possibilities, strSize);
			if (manacher(str) != right(str)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");
	}

}
