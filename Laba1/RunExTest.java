import java.util.concurrent.locks.ReentrantLock;

public class RunExTest {
    public static void main(String[] args) throws InterruptedException {
        Counter counter1 = new Counter();

        Runnable part61 =
                () -> {
                    try {
                        run61(counter1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };
        Thread part61Thread = new Thread(part61);

        part61Thread.start();
        part61Thread.join();

        System.out.println("6.1 = " + counter1.getCount());

        Counter counter2 = new Counter();

        Runnable part62 =
                () -> {
                    try {
                        run62(counter2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };
        Thread part62Thread = new Thread(part62);

        part62Thread.start();
        part62Thread.join();

        System.out.println("6.2 = " + counter2.getCount());

        Counter counter3 = new Counter();

        Runnable part63 =
                () -> {
                    try {
                        run63(counter3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };
        Thread part63Thread = new Thread(part63);

        part63Thread.start();
        part63Thread.join();

        System.out.println("6.3 = " + counter3.getCount());

        Counter counter4 = new Counter();

        Runnable part64 =
                () -> {
                    try {
                        run64(counter4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };
        Thread part64Thread = new Thread(part64);

        part64Thread.start();
        part64Thread.join();

        System.out.println("6.4 = " + counter4.getCount());
    }

    public static void run61(Counter counter) throws InterruptedException {
        int n_times = 1_000_000;

        Runnable task1 =
                () -> {
                    for (int i = 0; i < n_times; i++) counter.incrementNotSynchron();
                };
        Thread taskThread1 = new Thread(task1);

        Runnable task2 =
                () -> {
                    for (int i = 0; i < n_times; i++) counter.decrementNotSynchron();
                };
        Thread taskThread2 = new Thread(task2);

        taskThread1.start();
        taskThread2.start();

        taskThread1.join();
        taskThread2.join();
    }

    public static void run62(Counter counter) throws InterruptedException {
        int n_times = 1_000_000;

        Runnable task1 =
                () -> {
                    for (int i = 0; i < n_times; i++) counter.incrementSynchronMethod();
                };
        Thread taskThread1 = new Thread(task1);

        Runnable task2 =
                () -> {
                    for (int i = 0; i < n_times; i++) counter.decrementSynchronMethod();
                };
        Thread taskThread2 = new Thread(task2);

        taskThread1.start();
        taskThread2.start();

        taskThread1.join();
        taskThread2.join();
    }

    public static void run63(Counter counter) throws InterruptedException {
        int n_times = 1_000_000;

        Runnable task1 =
                () -> {
                    for (int i = 0; i < n_times; i++) counter.incrementSynchronBlock();
                };
        Thread taskThread1 = new Thread(task1);

        Runnable task2 =
                () -> {
                    for (int i = 0; i < n_times; i++) counter.decrementSynchronBlock();
                };
        Thread taskThread2 = new Thread(task2);

        taskThread1.start();
        taskThread2.start();

        taskThread1.join();
        taskThread2.join();
    }

    public static void run64(Counter counter) throws InterruptedException {
        int n_times = 1_000_000;

        ReentrantLock lock = new ReentrantLock();

        Runnable task1 =
                () -> {
                    for (int i = 0; i < n_times; i++) {
                        lock.lock();
                        counter.incrementNotSynchron();
                        lock.unlock();
                    }
                };
        Thread taskThread1 = new Thread(task1);

        Runnable task2 =
                () -> {
                    for (int i = 0; i < n_times; i++) {
                        lock.lock();
                        counter.decrementNotSynchron();
                        lock.unlock();
                    }
                };
        Thread taskThread2 = new Thread(task2);

        taskThread1.start();
        taskThread2.start();

        taskThread1.join();
        taskThread2.join();
    }

}
