public class RunEx2 {
    public static void main(String[] args) throws InterruptedException{
//        run1();
        run2();
    }

    public static void run1(){
        ExThread1 et1 = new ExThread1("-");
        ExThread1 et2 = new ExThread1("|");

        Thread t1 = new Thread(et1);
        Thread t2 = new Thread(et2);

        t1.start();
        t2.start();
    }

    public static void run2(){
        Tag tag = new Tag();

        ExThread2 et1 = new ExThread2(tag, "-");
        ExThread2 et2 = new ExThread2(tag, "|");

        et1.start();
        et2.start();

    }
}
