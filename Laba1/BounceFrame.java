import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BounceFrame extends JFrame {

    private BallCanvas canvas;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;
    public int counter = 0;
    public boolean blueColor;
    public JTextField text = new JTextField("Balls gone: 0", 10);

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce programm");

        this.canvas = new BallCanvas();
        System.out.println("In Frame Thread name = " + Thread.currentThread().getName());

        Container content = this.getContentPane();

        content.add(this.canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton buttonRed = new JButton("Red");
        JButton buttonBlue = new JButton("Blue");
        JButton buttonJoin = new JButton("Join");
        JButton buttonStop = new JButton("Stop");

        BounceFrame bounceFrame = this;

        buttonRed.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Ball b = new Ball(canvas, true);
                canvas.add(b);

                BallThread thread = new BallThread(b);
                thread.setPriority(Thread.MAX_PRIORITY);
                thread.start();

                System.out.println("Thread name = " + thread.getName());
            }
        });

        buttonBlue.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 500; i++) {
                    Ball b = new Ball(canvas, false);
                    canvas.add(b);

                    BallThread thread = new BallThread(b);
                    thread.setPriority(Thread.MIN_PRIORITY);
                    thread.start();

                    System.out.println("Thread name = " + thread.getName());
                }
            }
        });

        buttonJoin.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Runnable ex4 =
                                new Runnable() {
                                    public void run() {

                                        for (int i = 0; i < 5; i++) {

                                            if (i % 2 == 0) {
                                                blueColor = false;
                                            } else {
                                                blueColor = true;
                                            }
                                            Ball b = new Ball(canvas, blueColor);
                                            canvas.add(b);

                                            BallThread thread = new BallThread(b);

                                            thread.start();

                                            try {
                                                thread.join();
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }
                                };

                        Thread thread = new Thread(ex4);
                        thread.start();
                }
                });


        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }

        });


        buttonPanel.add(buttonRed);
        buttonPanel.add(buttonBlue);
        buttonPanel.add(buttonJoin);
        buttonPanel.add(buttonStop);
        buttonPanel.add(text);
        content.add(buttonPanel, BorderLayout.SOUTH);

    }

    public void changeCount() {
        counter++;
        text.setText("Gone: " + counter);
    }
}
