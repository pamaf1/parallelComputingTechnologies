public class ExThread2 extends Thread{
    private String text = "";
    private final Tag tag;

    public ExThread2(Tag tag, String text){
        this.text = text;
        this.tag = tag;
    }

    @Override
    public void run(){
        synchronized (tag) {
            for (int i = 0; i < 100; i++) {
                while (tag.mark && this.text.equals("-")) {
                    try {
                        tag.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                while (!tag.mark && this.text.equals("|")) {
                    try {
                        tag.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.print(this.text);
                tag.mark = !tag.mark;
                tag.notifyAll();
            }
        }
    }
}
