public class ExThread1 implements Runnable{
    private String text = "";

    public ExThread1(String _text) {
        text = _text;
    }

    @Override
    public void run(){
        for(int i=0; i<100;i++){
            System.out.print(text);
        }
    }
}
