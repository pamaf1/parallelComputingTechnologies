import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

class Ball {
    private Component canvas;
    private static final int XSIZE = 20;
    private static final int YSIZE = 20;
    private int x = 0;
    private int y = 0;
    private int dx = 2;
    private int dy = 2;
    private boolean colorRed;
    
    
    public Ball(Component c, boolean _colorRed){
        this.canvas = c;
        colorRed = _colorRed;
       
//        if(Math.random()<0.5){
//             x = new Random().nextInt(this.canvas.getWidth());
//             y = 0;
//        }else{
//            x = 0;
//            y = new Random().nextInt(this.canvas.getHeight());
//        }
        x = 120;
        y = 120;
    }

    
    public void draw (Graphics2D g2){
        if(colorRed) {
            g2.setColor(Color.red);
        }
        else {
            g2.setColor(Color.blue);
        }
        g2.fill(new Ellipse2D.Double(x,y,XSIZE,YSIZE));
    }
    
    public boolean move(){
        x+=dx;
        y+=dy;
        if(x<0){
            x = 0;
            dx = -dx;
        }
        if(x+XSIZE>=this.canvas.getWidth()){
            x = this.canvas.getWidth()-XSIZE;
            dx = -dx;
        }
        if(y<0){
            y=0;
            dy = -dy;
        }
        if(y+YSIZE>=this.canvas.getHeight()){
            y = this.canvas.getHeight()-YSIZE;
            dy = -dy;
        }
        this.canvas.repaint();

        if(x >= 200 && x<= 225 && y >= 0 && y <= 25){
            return false;
        }

        return true;
    }
}
