package class16;

import java.util.Stack;

public class Code02_Hanoi {

	public static void hanoi1(int n) {
		if(n > 0){
			fun(n,"left","to","middle");
		}
	}


	public static void fun(int n,String from, String to, String other){
		if(n == 1){
			System.out.println("move " + n + " from " + from + " to " + to);
			return;
		}

		fun(n-1,from,other,to);
		System.out.println("move " + n + " from " + from + " to " + to);
		fun(n-1,other,to,from);
	}



	public static void main(String[] args) {
		int n = 10;
		hanoi1(n);
		System.out.println("============");

	}

}
