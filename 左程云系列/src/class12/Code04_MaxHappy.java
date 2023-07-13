package class12;

import javax.xml.stream.FactoryConfigurationError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 求maxHappy，满足以下两个条件
 * 1.若节点x去了，则x的直系子节点就不能去
 * 2.若x没去，则x的直系子节点可以去可以不去
 */
public class Code04_MaxHappy {

	public static class Employee {
		public int happy;
		public List<Employee> nexts;

		public Employee(int h) {
			happy = h;
			nexts = new ArrayList<>();
		}

	}

	public static int maxHappy1(Employee boss) {
		if (boss == null) {
			return 0;
		}

		HashMap<Employee, Integer> upMap = new HashMap<>();
		HashMap<Employee, Integer> noMap = new HashMap<>();
		return process1(boss, false,upMap,noMap);
	}

	// 当前来到的节点叫cur，
	// up表示cur的上级是否来，
	// 该函数含义：
	// 如果up为true，表示在cur上级已经确定来，的情况下，cur整棵树能够提供最大的快乐值是多少？
	// 如果up为false，表示在cur上级已经确定不来，的情况下，cur整棵树能够提供最大的快乐值是多少？
	public static int process1(Employee cur, boolean up, Map<Employee,Integer> map1,Map<Employee,Integer> map2) {

		if (up) { // 如果cur的上级来的话，cur没得选，只能不来
			if(map1.containsKey(cur)){
				return map1.get(cur);
			}
			int ans = 0;
			for (Employee next : cur.nexts) {
				ans += process1(next, false,map1,map2);
			}
			map1.put(cur,ans);
			return ans;
		} else { // 如果cur的上级不来的话，cur可以选，可以来也可以不来
			if(map2.containsKey(cur)){
				return map2.get(cur);
			}
			int p1 = cur.happy;
			int p2 = 0;
			for (Employee next : cur.nexts) {
				p1 += process1(next, true,map1,map2);
				p2 += process1(next, false,map1,map2);
			}
			int ans = Math.max(p1, p2);
			map2.put(cur,ans);
			return ans;
		}
	}

	public static int maxHappy2(Employee head) {
		Info allInfo = process(head);
		return Math.max(allInfo.no, allInfo.yes);
	}

	static class Info{
		int no;
		int yes;

		public Info(int no, int yes) {
			this.no = no;
			this.yes = yes;
		}
	}

	public static Info process(Employee x) {
		if (x == null) {
			return new Info(0, 0);
		}
		List<Info> list = new ArrayList<>();
		for (Employee next : x.nexts) {
			list.add(process(next));
		}

		int yes = x.happy;
		int no = 0;

		for (Info info : list) {
			yes += info.no;
			no += Math.max(info.yes,info.no);
		}

		return new Info(no,yes);

	}

	public static int maxHappy3(Employee employee){
		return process2(employee,false);
	}

	private static int process2(Employee employee,boolean up) {
		if(employee.nexts == null || employee.nexts.isEmpty()){
			return employee.happy;
		}

		if(up){
			int ans = employee.happy;
			for (Employee e : employee.nexts) {
				ans += process2(e,false);
			}
			int p1 = employee.happy + process2(employee,up);

		}

		return 0;
	}

	// for test
	public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
		if (Math.random() < 0.02) {
			return null;
		}
		Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
		genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
		return boss;
	}

	// for test
	public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
		if (level > maxLevel) {
			return;
		}
		int nextsSize = (int) (Math.random() * (maxNexts + 1));
		for (int i = 0; i < nextsSize; i++) {
			Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
			e.nexts.add(next);
			genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
		}
	}

	public static void main(String[] args) {
		int maxLevel = 4;
		int maxNexts = 7;
		int maxHappy = 100;
		int testTimes = 100000;
		for (int i = 0; i < testTimes; i++) {
			Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
			if (maxHappy1(boss) != maxHappy2(boss)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
