public class BallThread extends Thread {
    private Ball b;
    public BounceFrame bounceFrame;
    
    public BallThread(Ball ball){
        b = ball;
    }
    @Override
    public void run(){
        try{
            for(int i=1; (i<1500); i++) {
                b.move();
//                if(b.move() == false){
//                    bounceFrame.changeCount();
//                    break;
//                }
                Thread.sleep(5);

            }
        } catch(InterruptedException ex){

        }
    }

}
