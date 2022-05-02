package Ex1;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    public static final int NTEST = 10000;
    private final int[] accounts;
//     private final AtomicIntegerArray accounts;
    private long nTransacts = 0;
//     private final AtomicLong nTransacts = new AtomicLong(0);
    private final ReentrantLock lock = new ReentrantLock();

    public Bank(int n, int initialBalance) {
        accounts = new int[n];
//         accounts = new AtomicIntegerArray(new int[n]);
        int i;
        for (i = 0; i < accounts.length; i++) accounts[i] = initialBalance;
        nTransacts = 0;
//         for (i = 0; i < accounts.length(); i++) accounts.set(i, initialBalance);
//         nTransacts.set(0);
    }

    public void transfer(int from, int to, int amount) throws InterruptedException {
//    accounts.addAndGet(from, -amount);
//    accounts.addAndGet(to, amount);
//    nTransacts.incrementAndGet();
//    if (nTransacts.get() % NTEST == 0) test();

        accounts[from] -= amount;
        accounts[to] += amount;
        nTransacts++;
        if (nTransacts % NTEST == 0) test();
    }

//  public void transfer(int from, int to, int amount) throws InterruptedException {
//    lock.lock();
//    accounts[from] -= amount;
//    accounts[to] += amount;
//    nTransacts++;
//    if (nTransacts % NTEST == 0) test();
//    lock.unlock();
//  }

//  public synchronized void transfer(int from, int to, int amount) throws InterruptedException {
//    accounts[from] -= amount;
//    accounts[to] += amount;
//    nTransacts++;
//    if (nTransacts % NTEST == 0) test();
//  }

    public void test() {
//    AtomicInteger sum = new AtomicInteger(0);
//    for (int i = 0; i < accounts.length(); i++) sum.addAndGet(accounts.get(i));
//    System.out.println("Transactions:" + nTransacts.get() + " Sum: " + sum.get());

        int sum = 0;
        for (int account : accounts) sum += account;
        System.out.println("Transactions:" + nTransacts + " Sum: " + sum);
    }

    public int size() {
        return accounts.length;
//        return accounts.length();
    }
}
