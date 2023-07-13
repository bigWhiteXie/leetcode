package class06;

import java.util.*;

public class Code01_topK {
    static class Customer{
        public int id;
        public int buyer;
        public int enterTime;

        public Customer(int id, int buyer, int enterTime) {
            this.id = id;
            this.buyer = buyer;
            this.enterTime = enterTime;
        }
    }

    static class CandiatesAndDaddies{
        GreatHeap<Customer> daddies;
        GreatHeap<Customer> candiates;
        HashMap<Integer,Customer> customers;
        int limit;
        public CandiatesAndDaddies(int k) {
            daddies = new GreatHeap<>(new DaddyCom());
            candiates = new GreatHeap<>(new CandiateCom());
            customers = new HashMap<>();
            limit = k;
        }

        /**
         * 1.校验是否是新用户且是退款操作，是则直接返回
         * 2.根据id判断customer中是否含有此用户，没有则初始化用户,并添加到customer中
         * 3.根据id从customer中取出用户，根据isBuy对其buy进行加减
         *      加减完成后判断buyer是否为0，若为0则在customers、candiates、daddies中都移除该对象
         * 4.根据id判断candiate和daddies中是否包含此用户
         *      daddies包含此用户，重新调整该用户在daddies中的位置
         *      candiates包含此用户，重新调整该用户在candiates的位置
         *      都不包含此用户且该用户buyer>0：若daddies区没满则添加到daddies区，否则添加到candiates
         * 5.将candiates中符合条件的元素移动到daddies中：
         *      判断daddies.size() < k
         *      是
         *          小于k则从candiates中取出堆顶元素放到daddies中
         *      否
         *          从candiates和daddies中取出堆顶元素(设为c、d)
         *          若c.buyer>d.buyer 重新为c、d设置enterTime，将其互换区域
         *          若c.buyer<=d.buyer 直接返回
         * @param id
         * @param enterTime
         * @param isBuy
         */
        public void operation(int id,int enterTime, boolean isBuy){
            //1.校验是否是新用户且是退款操作，是则直接返回
            if(!customers.containsKey(id) && !isBuy){
                return;
            }
            //2.根据id判断customer中是否含有此用户，没有则初始化用户,并添加到customer中
            customers.putIfAbsent(id,new Customer(id,0,enterTime));
            //3.根据id从customer中取出用户，根据isBuy对其buy进行加减
            Customer customer = customers.get(id);
            if(isBuy){
                customer.buyer++;
            }else{
                customer.buyer--;
            }

            if(customer.buyer == 0){
                customers.remove(id);
                if (candiates.contains(customer)) {
                    candiates.remove(customer);
                }
                if (daddies.contains(customer)) {
                    daddies.remove(customer);
                }

            }
            //4.根据id判断candiate和daddies中是否包含此用户
            //     daddies包含此用户，重新调整该用户在daddies中的位置
            //     candiates包含此用户，重新调整该用户在candiates的位置
            //     都不包含此用户：若daddies区没满则添加到daddies区，否则添加到candiates
            if(daddies.contains(customer)){
                daddies.resign(customer);
            }else if (candiates.contains(customer)){
                candiates.resign(customer);
            }else if(customer.buyer != 0){
                if(daddies.size() < limit ){
                    daddies.push(customer);
                }else {
                    candiates.push(customer);
                }
            }

            //5.将candiates中符合条件的元素移动到daddies中：
            //     判断daddies.size() < k
            //     是
            //         小于k则从candiates中取出堆顶元素放到daddies中
            //     否
            //         若c.buyer>d.buyer 重新为c、d设置enterTime，将其互换区域
            //         若c.buyer<=d.buyer 直接返回
            if(!candiates.isEmpty()){
                // 候选区不为空
                if (daddies.size() < limit) {
                    Customer c = candiates.pop();
                    c.enterTime = enterTime;
                    daddies.push(c);
                } else { // 等奖区满了，候选区有东西
                    if (candiates.peek().buyer > daddies.peek().buyer) {
                        Customer oldDaddy = daddies.pop();
                        Customer newDaddy = candiates.pop();
                        newDaddy.enterTime = enterTime;
                        oldDaddy.enterTime = enterTime;
                        daddies.push(newDaddy);
                        candiates.push(oldDaddy);
                    }
                }
            }
        }

        public List<Integer> getCurAns(){
            List<Integer> list = new ArrayList<>();
            for(Customer c:daddies.getAllElements()){
                list.add(c.id);
            }
            return list;
        }
    }

    /**
     * 每次客户操作后返回一个当前购买力最强的K个人
     *
     * @param arr 按照操作顺序存储客户id，先操作的客户id在前，后操作的在后，arr中存在重复id
     * @param operations arr中客户对应操作，true表示下单，false表示退货
     * @param K 表示选出购买力最强的前K个客户
     * @return
     */
    public static List<List<Integer>> topK(int[] arr, boolean[] operations, int K){

        CandiatesAndDaddies candiatesAndDaddies = new CandiatesAndDaddies(K);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i<arr.length; i++){
            candiatesAndDaddies.operation(arr[i],i,operations[i]);
            List<Integer> ans = candiatesAndDaddies.getCurAns();
            res.add(ans);
        }
        return res;
    }

    static class CandiateCom implements Comparator<Customer>{

        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buyer != o2.buyer? o2.buyer - o1.buyer : o1.enterTime - o2.enterTime;
        }
    }


    static class DaddyCom implements Comparator<Customer>{

        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buyer != o2.buyer? o1.buyer - o2.buyer : o2.enterTime - o1.enterTime;
        }
    }

    // 干完所有的事，模拟，不优化
    public static List<List<Integer>> compare(int[] arr, boolean[] op, int k) {
        HashMap<Integer, Customer> map = new HashMap<>();
        ArrayList<Customer> cands = new ArrayList<>();
        ArrayList<Customer> daddy = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int id = arr[i];
            boolean buyOrRefund = op[i];
            if (!buyOrRefund && !map.containsKey(id)) {
                ans.add(getCurAns(daddy));
                continue;
            }
            // 没有发生：用户购买数为0并且又退货了
            // 用户之前购买数是0，此时买货事件
            // 用户之前购买数>0， 此时买货
            // 用户之前购买数>0, 此时退货
            if (!map.containsKey(id)) {
                map.put(id, new Customer(id, 0, 0));
            }
            // 买、卖
            Customer c = map.get(id);
            if (buyOrRefund) {
                c.buyer++;
            } else {
                c.buyer--;
            }
            if (c.buyer == 0) {
                map.remove(id);
            }
            // c
            // 下面做
            if (!cands.contains(c) && !daddy.contains(c)) {
                if (daddy.size() < k) {
                    c.enterTime = i;
                    daddy.add(c);
                } else {
                    c.enterTime = i;
                    cands.add(c);
                }
            }
            cleanZeroBuy(cands);
            cleanZeroBuy(daddy);
            cands.sort(new CandiateCom());
            daddy.sort(new DaddyCom());
            move(cands, daddy, k, i);
            ans.add(getCurAns(daddy));
        }
        return ans;
    }

    public static void move(ArrayList<Customer> cands, ArrayList<Customer> daddy, int k, int time) {
        if (cands.isEmpty()) {
            return;
        }
        // 候选区不为空
        if (daddy.size() < k) {
            Customer c = cands.get(0);
            c.enterTime = time;
            daddy.add(c);
            cands.remove(0);
        } else { // 等奖区满了，候选区有东西
            if (cands.get(0).buyer > daddy.get(0).buyer) {
                Customer oldDaddy = daddy.get(0);
                daddy.remove(0);
                Customer newDaddy = cands.get(0);
                cands.remove(0);
                newDaddy.enterTime = time;
                oldDaddy.enterTime = time;
                daddy.add(newDaddy);
                cands.add(oldDaddy);
            }
        }
    }

    public static void cleanZeroBuy(ArrayList<Customer> arr) {
        List<Customer> noZero = new ArrayList<Customer>();
        for (Customer c : arr) {
            if (c.buyer != 0) {
                noZero.add(c);
            }
        }
        arr.clear();
        for (Customer c : noZero) {
            arr.add(c);
        }
    }

    public static List<Integer> getCurAns(ArrayList<Customer> daddy) {
        List<Integer> ans = new ArrayList<>();
        for (Customer c : daddy) {
            ans.add(c.id);
        }
        return ans;
    }

    // 为了测试
    public static class Data {
        public int[] arr;
        public boolean[] op;

        public Data(int[] a, boolean[] o) {
            arr = a;
            op = o;
        }
    }

    // 为了测试
    public static Data randomData(int maxValue, int maxLen) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] arr = new int[len];
        boolean[] op = new boolean[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
            op[i] = Math.random() < 0.5 ? true : false;
        }
        return new Data(arr, op);
    }

    // 为了测试
    public static boolean sameAnswer(List<List<Integer>> ans1, List<List<Integer>> ans2) {
        if (ans1.size() != ans2.size()) {
            return false;
        }
        for (int i = 0; i < ans1.size(); i++) {
            List<Integer> cur1 = ans1.get(i);
            List<Integer> cur2 = ans2.get(i);
            if (cur1.size() != cur2.size()) {
                return false;
            }
            cur1.sort((a, b) -> a - b);
            cur2.sort((a, b) -> a - b);
            for (int j = 0; j < cur1.size(); j++) {
                if (!cur1.get(j).equals(cur2.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int maxValue = 10;
        int maxLen = 100;
        int maxK = 6;
        int testTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Data testData = randomData(maxValue, maxLen);
            int k = (int) (Math.random() * maxK) + 1;
            int[] arr = testData.arr;
            boolean[] op = testData.op;
            List<List<Integer>> ans1 = topK(arr, op, k);
            List<List<Integer>> ans2 = compare(arr, op, k);
            if (!sameAnswer(ans1, ans2)) {
                System.out.println("测试：" + i);
                for (int j = 0; j < arr.length; j++) {
                    System.out.println(arr[j] + " , " + op[j]);
                }
                System.out.println(k);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
