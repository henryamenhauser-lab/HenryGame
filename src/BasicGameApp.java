//Basic Game Application
// Basic Object, Image, Movement
// Threaded

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

//*******************************************************************************

public class BasicGameApp implements Runnable, KeyListener{

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    public int score = 0;


    Bull bull1;
    Bull bull2;
    Bull bull3;
    Bull bull4;
    Image bullImage;

    RedCowboy redCowboy;
    Image redcowboyImage;

    BlueCowboy blueCowboy;
    Image bluecowboyImage;

    Sheep sheep;
    Image sheepImage;

    Image rodeo = Toolkit.getDefaultToolkit().getImage("RodeoArena.png");

    public boolean firstCrashRed;
    public boolean firstCrashBlue;
    public boolean firstCrashCowboys;

    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }

    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.

    public BasicGameApp() { // BasicGameApp constructor

        setUpGraphics();

        bull1 = new Bull("bull", 10, 10);
        bullImage = Toolkit.getDefaultToolkit().getImage("Bull.png");

        bull2 = new Bull("bull", 100, 10);
        bull3 = new Bull("bull", 200, 200);
        bull4 = new Bull("bull", 250, 250);

        redCowboy = new RedCowboy("RedCowboy", 263, 561);
        redcowboyImage = Toolkit.getDefaultToolkit().getImage("CowboyImage1.png");

        blueCowboy = new BlueCowboy("BlueCowboy", 132, 236);
        bluecowboyImage = Toolkit.getDefaultToolkit().getImage("CowboyImage2.png");

        sheep = new Sheep("SheepItem",40,40);
        sheepImage = Toolkit.getDefaultToolkit().getImage("Sheep.png");

    }

    public void run() {
        //for the moment we will loop things forever.
        while (true) {
            moveThings();//move all the game objects\
            collectSheep();
            render();  // paint the graphics
            pause(20);// sleep for 10 ms

        }
    }

    public void moveThings() {
        bull1.chase(redCowboy.xpos, redCowboy.ypos,3);
        bull2.chase(blueCowboy.xpos, blueCowboy.ypos, 3);
        bull3.chase(blueCowboy.xpos, blueCowboy.ypos, 0);

        bull1.move();
        bull2.move();
        bull3.move();
        bull4.move();

        blueCowboy.move();
        redCowboy.move();

        checkCrashAndToss();
    }

    public void checkCrashAndToss() {
        if (bull1.rect.intersects(redCowboy.rect)) {

            if (bull1.xpos < redCowboy.xpos) {
                redCowboy.xpos += 50;
            } else {
                redCowboy.xpos -= 50;
            }

            if (bull1.ypos < redCowboy.ypos) {
                redCowboy.ypos += 50;
            } else {
                redCowboy.ypos -= 50;
            }

            if (redCowboy.xpos < 0) redCowboy.xpos = 0;
            if (redCowboy.xpos + redCowboy.width > WIDTH) redCowboy.xpos = WIDTH - redCowboy.width;
            if (redCowboy.ypos < 0) redCowboy.ypos = 0;
            if (redCowboy.ypos + redCowboy.height > HEIGHT) redCowboy.ypos = HEIGHT - redCowboy.height;
        }

        if (bull2.rect.intersects(blueCowboy.rect)) {
            if (bull2.xpos < blueCowboy.xpos) {
                blueCowboy.xpos += 50;

            } else {
                blueCowboy.xpos -= 50;
            }

            if (bull2.ypos < blueCowboy.ypos) {
                blueCowboy.ypos += 50;
            } else {
                blueCowboy.ypos -= 50;
            }

            if (blueCowboy.xpos < 0) blueCowboy.xpos = 0;
            if (blueCowboy.xpos + blueCowboy.width > WIDTH) blueCowboy.xpos = WIDTH - blueCowboy.width;
            if (blueCowboy.ypos < 0) blueCowboy.ypos = 0;
            if (blueCowboy.ypos + blueCowboy.height > HEIGHT) blueCowboy.ypos = HEIGHT - blueCowboy.height;
        }
        if (bull3.rect.intersects(blueCowboy.rect)) {
            if (bull3.xpos < blueCowboy.xpos) {
                blueCowboy.xpos += 50;

            } else {
                blueCowboy.xpos -= 50;
            }

            if (bull3.ypos < blueCowboy.ypos) {
                blueCowboy.ypos += 50;
            } else {
                blueCowboy.ypos -= 50;
            }

            if (blueCowboy.xpos < 0) blueCowboy.xpos = 0;
            if (blueCowboy.xpos + blueCowboy.width > WIDTH) blueCowboy.xpos = WIDTH - blueCowboy.width;
            if (blueCowboy.ypos < 0) blueCowboy.ypos = 0;
            if (blueCowboy.ypos + blueCowboy.height > HEIGHT) blueCowboy.ypos = HEIGHT - blueCowboy.height;
        }
        if (bull4.rect.intersects(blueCowboy.rect)) {
            if (bull4.xpos < blueCowboy.xpos) {
                blueCowboy.xpos += 50;

            } else {
                blueCowboy.xpos -= 50;
            }

            if (bull4.ypos < blueCowboy.ypos) {
                blueCowboy.ypos += 50;
            } else {
                blueCowboy.ypos -= 50;
            }

            if (blueCowboy.xpos < 0) blueCowboy.xpos = 0;
            if (blueCowboy.xpos + blueCowboy.width > WIDTH) blueCowboy.xpos = WIDTH - blueCowboy.width;
            if (blueCowboy.ypos < 0) blueCowboy.ypos = 0;
            if (blueCowboy.ypos + blueCowboy.height > HEIGHT) blueCowboy.ypos = HEIGHT - blueCowboy.height;
        }
        if (bull4.rect.intersects(redCowboy.rect)) {

            if (bull4.xpos < redCowboy.xpos) {
                redCowboy.xpos += 50;
            } else {
                redCowboy.xpos -= 50;
            }

            if (bull4.ypos < redCowboy.ypos) {
                redCowboy.ypos += 50;
            } else {
                redCowboy.ypos -= 50;
            }


            if (redCowboy.xpos < 0) redCowboy.xpos = 0;
            if (redCowboy.xpos + redCowboy.width > WIDTH) redCowboy.xpos = WIDTH - redCowboy.width;
            if (redCowboy.ypos < 0) redCowboy.ypos = 0;
            if (redCowboy.ypos + redCowboy.height > HEIGHT) redCowboy.ypos = HEIGHT - redCowboy.height;
        }
    }

    public void collectSheep() {
        if (sheep.isAlive) {
            if (redCowboy.rect.intersects(sheep.rect) || blueCowboy.rect.intersects(sheep.rect)) {
                sheep.isAlive = false;
                score++;
                sheep.xpos = (int)(Math.random() * (WIDTH - sheep.width));
                sheep.ypos = (int)(Math.random() * (HEIGHT - sheep.height));
                sheep.isAlive = true;
                sheep.rect = new Rectangle(sheep.xpos, sheep.ypos, sheep.width, sheep.height);
            }
        }
    }

    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        g.drawImage(rodeo, 0, 0, WIDTH, HEIGHT, null);
        g.drawImage(bullImage, bull1.xpos, bull1.ypos, bull1.width, bull1.height, null);
        g.drawImage(bullImage, bull2.xpos, bull2.ypos, bull2.width, bull2.height, null);
        g.drawImage(bullImage, bull3.xpos, bull3.ypos, bull3.width, bull3.height, null);
        g.drawImage(bullImage, bull4.xpos, bull4.ypos, bull4.width, bull4.height, null);
        g.drawImage(redcowboyImage, redCowboy.xpos, redCowboy.ypos, redCowboy.width, redCowboy.height, null);
        g.drawImage(bluecowboyImage, blueCowboy.xpos, blueCowboy.ypos, blueCowboy.width, blueCowboy.height, null);

        if (sheep.isAlive) {
            g.drawImage(sheepImage, sheep.xpos, sheep.ypos, sheep.width, sheep.height, null);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Score: " + score, 20, 40);

        g.dispose();
        bufferStrategy.show();
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time ) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        canvas.addKeyListener(this);
        System.out.println("DONE graphic setup");
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());

            if (e.getKeyCode() == 38) { //this is up
                redCowboy.dy = -10;
                redCowboy.dx = 0;

            }
            if (e.getKeyCode() == 40) { //this is down
                redCowboy.dy = 10;
                redCowboy.dx = 0;
            }
            if (e.getKeyCode() == 37) { //this is left
                redCowboy.dy = 0;
                redCowboy.dx = -10;
            }
            if (e.getKeyCode() == 39) {//this is right
                redCowboy.dy = 0;
                redCowboy.dx = 10;

            }


            if (e.getKeyCode() == 87) { //this is up
            blueCowboy.dy = -10;
            blueCowboy.dx = 0;
            }
            if (e.getKeyCode() == 83) { //this is down
            blueCowboy.dy = 10;
            blueCowboy.dx = 0;
            }
            if (e.getKeyCode() == 65) { //this is left
            blueCowboy.dy = 0;
            blueCowboy.dx = -10;
            }
            if (e.getKeyCode() == 68) {//this is right
            blueCowboy.dy = 0;
            blueCowboy.dx = 10;
            }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 38) { //this is up
            redCowboy.dy = 0;
            redCowboy.dx = 0;
        }
        if (e.getKeyCode() == 40) { //this is down
            redCowboy.dy = 0;
            redCowboy.dx = 0;
        }
        if (e.getKeyCode() == 37) { //this is left
            redCowboy.dy = 0;
            redCowboy.dx = 0;
        }
        if (e.getKeyCode() == 39) {//this is right
            redCowboy.dy = 0;
            redCowboy.dx = 0;
        }


        if (e.getKeyCode() == 87) { //this is up
            blueCowboy.dy = 0;
            blueCowboy.dx = 0;
        }
        if (e.getKeyCode() == 83) { //this is down
            blueCowboy.dy = 0;
            blueCowboy.dx = 0;
        }
        if (e.getKeyCode() == 65) { //this is left
            blueCowboy.dy = 0;
            blueCowboy.dx = 0;
        }
        if (e.getKeyCode() == 68) {//this is right
            blueCowboy.dy = 0;
            blueCowboy.dx = 0;
        }


    }
}





//    public void checkCrashRed(){
//        if (bull.rect.intersects(redCowboy.rect) && firstCrashRed){
//            redCowboy.dy = -redCowboy.dy;
//            firstCrashRed = false;
//        }
//
//        if (!bull.rect.intersects(redCowboy.rect)) {
//            firstCrashRed = true;
//        }
//    }
//    public void checkCrashBlue(){
//        if (bull.rect.intersects(blueCowboy.rect) && firstCrashBlue){
//            blueCowboy.dx = -blueCowboy.dx;
//            firstCrashBlue = false;
//        }
//        if (!bull.rect.intersects(blueCowboy.rect)) {
//            firstCrashBlue = true;
//        }
//    }
//    public void checkCrashCowboys() {
//        if (redCowboy.rect.intersects(blueCowboy.rect) && firstCrashCowboys) {
//
//            redCowboy.dx = -redCowboy.dx;
//            redCowboy.dy = -redCowboy.dy;
//            blueCowboy.dx = -blueCowboy.dx;
//            blueCowboy.dy = -blueCowboy.dy;
//
//            firstCrashCowboys = false;
//        }
//
//        if (!redCowboy.rect.intersects(blueCowboy.rect)) {
//            firstCrashCowboys = true;
//        }
//    }



//    public void checkCrash(){
//        if (bull.rect.intersects(redCowboy.rect) && firstCrashRed){
//            redCowboy.dy = -redCowboy.dy;
//            firstCrashRed = false;
//        }
//
//        if (!bull.rect.intersects(redCowboy.rect)) {
//            firstCrashRed = true;
//        }
//        if (bull.rect.intersects(blueCowboy.rect) && firstCrashBlue){
//            blueCowboy.dx = -blueCowboy.dx;
//            firstCrashBlue = false;
//        }
//        if (!bull.rect.intersects(blueCowboy.rect)) {
//            firstCrashBlue = true;
//        }
//        if (!redCowboy.rect.intersects(blueCowboy.rect)) {
//            redCowboy.dx = -redCowboy.dx;
//            redCowboy.dy = -redCowboy.dy;
//            blueCowboy.dx = -blueCowboy.dx;
//            blueCowboy.dy = -blueCowboy.dy;
//        }
//            firstCrashCowboy = false;
//        if (!redCowboy.rect.intersects(blueCowboy.rect)) {
//            firstCrashCowboy = true;
//        }
//    }