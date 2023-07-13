package 笔试.赛码网风格;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private int n;

    private Lock lock = new ReentrantLock();

    //轮到哪个线程执行
    private volatile int flag = 0;

    //发放完积分后是发放优惠券还是贡献值
    private volatile boolean isCouponTurn = true;

    private Condition bonus = lock.newCondition();

    private Condition coupon = lock.newCondition();

    private Condition contribution = lock.newCondition();

    public Main(int n) {
        this.n = n;
    }

    //校验代码，无需考生实现
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("");
        String number = scanner.nextLine();
        final PrizePool prizePool = new PrizePool();
        final Main reviewEncourage = new Main(Integer.valueOf(number));
        Thread bonusThread = new Thread(new Runnable() {
            public void run() {
                try {
                    reviewEncourage.bonus(prizePool);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread couponThread = new Thread(new Runnable() {
            public void run() {
                try {
                    reviewEncourage.coupon(prizePool);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread contributionThread = new Thread(new Runnable() {
            public void run() {
                try {
                    reviewEncourage.contribution(prizePool);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        bonusThread.start();
        couponThread.start();
        contributionThread.start();
    }

    public void bonus(PrizePool prizePool) throws InterruptedException {
        if (n < 1) {
            return;
        }
        try {
            lock.lock();
            for (int i = 1; i <= n; i += 2) {
                if (i != 1 && flag != 0) {
                    bonus.await();
                }
                prizePool.send("A");
                if (isCouponTurn) {
                    flag = 1;
                    coupon.signal();
                } else {
                    flag = 2;
                    contribution.signal();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void coupon(PrizePool prizePool) throws InterruptedException {
        if (n < 2) {
            return;
        }
        try {
            lock.lock();
            for (int i = 2; i <= n; i += 4) {
                if (flag != 1) {
                    coupon.await();
                }
                prizePool.send("B");
                bonus.signal();
                flag = 0;
                isCouponTurn = false;
            }
        } finally {
            lock.unlock();
        }
    }

    public void contribution(PrizePool prizePool) throws InterruptedException {
        if (n < 4) {
            return;
        }
        try {
            lock.lock();
            for (int i = 4; i <= n; i += 4) {
                if (flag != 2) {
                    contribution.await();
                }
                prizePool.send("C");
                bonus.signal();
                flag = 0;
                isCouponTurn = true;
            }
        } finally {
            lock.unlock();
        }
    }

    public static class PrizePool {

        void send(String input) {
            System.out.print(input);
        }

    }
}