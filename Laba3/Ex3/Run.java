package Ex3;
import java.util.Arrays;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        Journal journal = new Journal();

        Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        (new Thread(new Teacher("Lecturer 1", Arrays.asList("ІT-92", "ІT-93", "ІT-94"), journal))).start();
                        (new Thread(new Teacher("Assistant 1", Arrays.asList("ІT-92", "ІT-93", "ІT-94"), journal))).start();
                        (new Thread(new Teacher("Assistant 2", Arrays.asList("ІT-92", "ІT-93", "ІT-94"), journal))).start();
                        (new Thread(new Teacher("Assistant 3", Arrays.asList("ІT-92", "ІT-93", "ІT-94"), journal))).start();
                    }
                };

        Thread t = new Thread(r);
        t.start();
        t.join();

        journal.show();
    }
}
