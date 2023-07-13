package class13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 会议问题
 * 题目给出会议数组，每个会议包含开始和结束时间，给出这个数组中最多能安排的会议数量，两个会议时间不能有重叠
 */
public class Code03_BestArrange {

	public static class Program {
		public int start;
		public int end;

		public Program(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	// 暴力！所有情况都尝试！
	public static int bestArrange1(Program[] programs) {
		if (programs == null || programs.length == 0) {
			return 0;
		}
		return process(programs, 0, 0);
	}

	// 还剩下的会议都放在programs里
	// done之前已经安排了多少会议的数量
	// timeLine目前来到的时间点是什么
	
	// 目前来到timeLine的时间点，已经安排了done多的会议，剩下的会议programs可以自由安排
	// 返回能安排的最多会议数量
	public static int process(Program[] programs, int done, int timeLine) {
		if (programs.length == 0) {
			return done;
		}
		// 还剩下会议
		int max = done;
		// 当前安排的会议是什么会，每一个都枚举
		for (int i = 0; i < programs.length; i++) {
			if (programs[i].start >= timeLine) {
				Program[] next = copyButExcept(programs, i);
				max = Math.max(max, process(next, done + 1, programs[i].end));
			}
		}
		return max;
	}

	public static Program[] copyButExcept(Program[] programs, int i) {
		Program[] ans = new Program[programs.length - 1];
		int index = 0;
		for (int k = 0; k < programs.length; k++) {
			if (k != i) {
				ans[index++] = programs[k];
			}
		}
		return ans;
	}

	// 会议的开始时间和结束时间，都是数值，不会 < 0
	public static int bestArrange2(Program[] programs) {
		//1.按照结束时间从小到大排好序
		Arrays.sort(programs,new ProgramComparator());

		int count = 0;
		int endLine = -1;
		for (Program program : programs) {
			if(endLine <= program.start){
				endLine = program.end;
				count++;
			}
		}
		return count;
	}

	public static int bestArrange3(Program[] programs){
		PriorityQueue<Program> heap = new PriorityQueue<>((a, b) ->  (a.end - b.end));
		for (Program program : programs) {
			heap.add(program);
		}

		int count = 0;
		int endline = -1;
		while (!heap.isEmpty()){
			Program program = heap.poll();
			if(program.start >= endline){
				count++;
				endline = program.end;
			}
		}
		return count;
	}

	public static class ProgramComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.end - o2.end;
		}

	}

	// for test
	public static Program[] generatePrograms(int programSize, int timeMax) {
		Program[] ans = new Program[(int) (Math.random() * (programSize + 1))];
		for (int i = 0; i < ans.length; i++) {
			int r1 = (int) (Math.random() * (timeMax + 1));
			int r2 = (int) (Math.random() * (timeMax + 1));
			if (r1 == r2) {
				ans[i] = new Program(r1, r1 + 1);
			} else {
				ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		int programSize = 12;
		int timeMax = 20;
		int timeTimes = 1000000;
		for (int i = 0; i < timeTimes; i++) {
			Program[] programs = generatePrograms(programSize, timeMax);
			if (bestArrange1(programs) != bestArrange3(programs)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
