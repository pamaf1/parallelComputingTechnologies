import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class RunEx3 {
    public static void main(String[] args) throws InterruptedException{
        Counter counter = new Counter();

//        run1(counter);
//        run2(counter);
//        run3(counter);
        run4(counter);

        System.out.println(counter.getCount());
    }

    public static void run1(Counter counter) throws InterruptedException{
        int repeat = 100000;

        Runnable part1 = () -> {
            for(int i=0; i<repeat;i++) {
                counter.incrementNotSynchron();
            }
        };
        Thread threadPart1 = new Thread(part1);

        Runnable part2 = () -> {
            for(int i=0; i<repeat;i++) {
                counter.decrementNotSynchron();
            }
        };
        Thread threadPart2 = new Thread(part2);

        threadPart1.start();
        threadPart2.start();

        threadPart1.join();
        threadPart2.join();

    }

    public static void run2(Counter counter) throws InterruptedException{
        int repeat = 100000;

        Runnable part1 = () -> {
            for(int i=0; i<repeat;i++) {
                counter.incrementSynchronMethod();
            }
        };
        Thread threadPart1 = new Thread(part1);

        Runnable part2 = () -> {
            for(int i=0; i<repeat;i++) {
                counter.decrementSynchronMethod();
            }
        };
        Thread threadPart2 = new Thread(part2);

        threadPart1.start();
        threadPart2.start();

        threadPart1.join();
        threadPart2.join();
    }

    public static void run3(Counter counter) throws InterruptedException{
        int repeat = 100000;

        Runnable part1 = () -> {
            for(int i=0; i<repeat;i++) {
                counter.incrementSynchronBlock();
            }
        };
        Thread threadPart1 = new Thread(part1);

        Runnable part2 = () -> {
            for(int i=0; i<repeat;i++) {
                counter.decrementSynchronBlock();
            }
        };
        Thread threadPart2 = new Thread(part2);

        threadPart1.start();
        threadPart2.start();

        threadPart1.join();
        threadPart2.join();
    }

    public static void run4(Counter counter) throws InterruptedException{
        int repeat = 100000;

        ReentrantLock lock = new ReentrantLock();

        Runnable part1 = () -> {
            for(int i=0; i<repeat;i++) {
                lock.lock();
                counter.incrementNotSynchron();
                lock.unlock();
            }
        };
        Thread threadPart1 = new Thread(part1);

        Runnable part2 = () -> {
            for(int i=0; i<repeat;i++) {
                lock.lock();
                counter.decrementNotSynchron();
                lock.unlock();
            }
        };
        Thread threadPart2 = new Thread(part2);

        threadPart1.start();
        threadPart2.start();

        threadPart1.join();
        threadPart2.join();
    }
}
