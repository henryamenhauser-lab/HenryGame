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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

//*******************************************************************************

public class BasicGameApp implements Runnable, KeyListener, MouseListener {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    public final int WIDTH = 1000;
    public final int HEIGHT = 700;

    public static final int worldWidth = 2000;
    public static final int worldHeight = 1000;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    public int score = 0;
    public int highScore = 0;
    public int healthBarRed = 5;
    public int healthBarBlue = 5;
    public int redDashTime = 0;
    public int blueDashTime = 0;

    public double cameraX;
    public double cameraY;

    Bull[] bulls;

    Image bullImage;

    RedCowboy redCowboy;
    Image redcowboyImage;

    BlueCowboy blueCowboy;
    Image bluecowboyImage;

    Sheep sheep;
    Image sheepImage;

    Chicken[] chickens;
    Image chickenImage;

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

        bulls[0] = new Bull("bull", 10, 10);
        bullImage = Toolkit.getDefaultToolkit().getImage("Bull.png");

        bulls[1] = new Bull("bull", 100, 10);
        bulls[2] = new Bull("bull", 500, 100);
        bulls[3] = new Bull("bull", 250, 250);
        bulls[4] = new Bull("bull",880,580);
        bulls[5] = new Bull("bull",880,580);

        redCowboy = new RedCowboy("RedCowboy", 263, 561);
        redcowboyImage = Toolkit.getDefaultToolkit().getImage("CowboyImage1.png");

        blueCowboy = new BlueCowboy("BlueCowboy", 132, 236);
        bluecowboyImage = Toolkit.getDefaultToolkit().getImage("CowboyImage2.png");

        sheep = new Sheep("SheepItem",40,40);
        sheepImage = Toolkit.getDefaultToolkit().getImage("Sheep.png");

        chickens[0] = new Chicken("Chicken1",900,650);
        chickens[1] = new Chicken("Chicken2",880,630);
        chickens[2] = new Chicken("Chicken3",860,610);

        chickenImage = Toolkit.getDefaultToolkit().getImage("Chicken.png");

    }

    public void run() {
        //for the moment we will loop things forever.
        while (true) {
            moveThings();//move all the game objects\
            render();  // paint the graphics
            pause(20);// sleep for 10 ms

        }
    }

    public void moveThings() {

        double halfScreenW = WIDTH / 2.0;
        double halfScreenH = HEIGHT / 2.0;

        bulls[0].chase((int) redCowboy.xpos, (int) redCowboy.ypos,3);
        bulls[1].chase(blueCowboy.xpos, blueCowboy.ypos, 3);
        bulls[5].chase(sheep.xpos, sheep.ypos,2.5);

        for (Bull b : bulls) {
            b.move(worldWidth,worldHeight);
        }

        blueCowboy.move();
        redCowboy.move();

        for (Chicken c : chickens) {
            c.move();
        }

        checkCrashAndToss();
        collectSheep();
        collectChicken();
        dash();

        if (score >10) {
            sheep.move();
        }

        double targetX = redCowboy.xpos - halfScreenW;
        double targetY = redCowboy.ypos - halfScreenH;

        cameraX += (targetX - cameraX) * 0.1;
        cameraY += (targetY - cameraY) * 0.1;

        if (cameraX < 0) cameraX = 0;
        if (cameraY < 0) cameraY = 0;
        if (cameraX > worldWidth - WIDTH) cameraX = worldWidth - WIDTH;
        if (cameraY > worldHeight - HEIGHT) cameraY = worldHeight - HEIGHT;

    }

    public void checkCrashAndToss() {
        int pushBack = 100;


        for (Bull b : bulls) {

            if (score < 10) {
                bulls[4].isAlive = false;
                bulls[5].isAlive = false;
            } else {
                bulls[4].isAlive = true;
                bulls[5].isAlive = true;
            }

            if (b.rect.intersects(redCowboy.rect)) {
                if (b.isAlive) {
                    if (b.xpos < redCowboy.xpos) redCowboy.xpos += pushBack;
                    else redCowboy.xpos -= pushBack;
                    if (b.ypos < redCowboy.ypos) redCowboy.ypos += pushBack;
                    else redCowboy.ypos -= pushBack;

                    if (redCowboy.xpos < 0) redCowboy.xpos = 0;
                    if (redCowboy.xpos + redCowboy.width > worldWidth) redCowboy.xpos = worldWidth - redCowboy.width;
                    if (redCowboy.ypos < 0) redCowboy.ypos = 0;
                    if (redCowboy.ypos + redCowboy.height > worldHeight) redCowboy.ypos = worldHeight - redCowboy.height;

                    if (healthBarRed > 0) healthBarRed--;
                    pushBack =100;
                }
            }

            if (b.rect.intersects(blueCowboy.rect)) {
                if (b.isAlive) {
                    if (b.xpos < blueCowboy.xpos) blueCowboy.xpos += pushBack;
                    else blueCowboy.xpos -= pushBack;
                    if (b.ypos < blueCowboy.ypos) blueCowboy.ypos += pushBack;
                    else blueCowboy.ypos -= pushBack;

                    if (blueCowboy.xpos < 0) blueCowboy.xpos = 0;
                    if (blueCowboy.xpos + blueCowboy.width > worldWidth) blueCowboy.xpos = worldWidth - blueCowboy.width;
                    if (blueCowboy.ypos < 0) blueCowboy.ypos = 0;
                    if (blueCowboy.ypos + blueCowboy.height > worldHeight) blueCowboy.ypos = worldHeight - blueCowboy.height;

                    if (healthBarBlue > 0) healthBarBlue--;
                    pushBack =100;
                }
            }

        }

    }

    public void collectSheep() {

        if (sheep.isAlive) {
            if (redCowboy.rect.intersects(sheep.rect) && healthBarRed > 0 || blueCowboy.rect.intersects(sheep.rect) && healthBarBlue > 0) {
                sheep.isAlive = false;
                score++;
                sheep.xpos = (int) (Math.random() * (worldWidth - sheep.width));
                sheep.ypos = (int) (Math.random() * (worldHeight - sheep.height));
                sheep.isAlive = true;
                sheep.rect.setBounds(sheep.xpos, sheep.ypos, sheep.width, sheep.height);

            }
        }
    }
        public void collectChicken() {

            for (Chicken c : chickens) {
                if (!c.isAlive) continue;

                boolean redHit = redCowboy.rect.intersects(c.rect) && healthBarRed > 0;
                boolean blueHit = blueCowboy.rect.intersects(c.rect) && healthBarBlue > 0;

                if (redHit || blueHit) {
                    score += 2;

                    c.xpos = (int)(Math.random() * (worldWidth - c.width));
                    c.ypos = (int)(Math.random() * (worldHeight - c.height));
                    c.rect.setBounds(c.xpos, c.ypos, c.width, c.height);
                }
            }
        }

    public void stopBull() {
        if (healthBarBlue <1) {
            bulls[1].dx = 0;
            bulls[1].dy = 0;
        }
        if (healthBarRed <1) {
            bulls[0].dx = 0;
            bulls[0].dy = 0;
        }
        if (healthBarBlue <1 && healthBarRed <1) {
            bulls[2].dx = 0;
            bulls[2].dy = 0;
            bulls[3].dx = 0;
            bulls[3].dy = 0;
        }
    }

    public void dash() {

        if (redDashTime > 0) {
            redCowboy.dx *= 2;
            redCowboy.dy *= 2;
            redDashTime--;
        }
        if (blueDashTime > 0) {
            blueCowboy.dx *= 2;blueCowboy.dy *= 2;
            blueDashTime--;
        }
    }

    public void menu() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Menu F&Q", 500, 350);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        g.drawString("Sheep = 1", 400, 400);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        g.drawString("Chicken = 2", 600, 400);

        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.PLAIN, 10));
        g.drawString("Player 1 WASD  || Dash = shift", 400, 500);

        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial", Font.PLAIN, 10));
        g.drawString("Player 2 < ^ >  ||  Dash = space", 600, 500);

    }



    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        g.drawImage(rodeo, - (int) cameraX, - (int) cameraY, worldWidth, worldHeight, null);

        for (int i = 0; i < bulls.length; i++) {

            if (i < 4 || score > 10) {
                g.drawImage(bullImage,
                        (int)(bulls[i].xpos - cameraX),
                        (int)(bulls[i].ypos - cameraY),
                        bulls[i].width,
                        bulls[i].height,
                        null);
            }
        }

        int drawX = (int)(redCowboy.xpos - cameraX);
        int drawY = (int)(redCowboy.ypos - cameraY);
        g.drawImage(redcowboyImage, drawX, drawY, redCowboy.width, redCowboy.height, null);


        g.drawImage(bluecowboyImage, (int) (blueCowboy.xpos -cameraX), (int) (blueCowboy.ypos - cameraY), blueCowboy.width, blueCowboy.height, null);

        if (sheep.isAlive) {
            g.drawImage(sheepImage, (int) (sheep.xpos -cameraX), (int) (sheep.ypos -cameraY), sheep.width, sheep.height, null);
        }
        for (Chicken c : chickens) {
            if (c.isAlive) {
                g.drawImage(chickenImage,(int)(c.xpos-cameraX),(int)(c.ypos - cameraY),c.width,c.height,null );
            }
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Score: " + score, 20, 40);

        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Health: " + healthBarRed, 220, 40);

        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Health: " + healthBarBlue, 420, 40);

        if (healthBarRed<1 && healthBarBlue<1) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 150));
            g.drawString("Game Over",80,350);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Score:" + score,200,450);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.drawString("Press R to Restart",500,450);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.drawString("Highscore:" + highScore,500,650);
            if (score > highScore) {
                highScore = score;
            }


        }

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
            if (healthBarRed >0) {
                if (e.getKeyCode() == 38) { //this is up
                    redCowboy.dy = -10;
                }
                if (e.getKeyCode() == 40) { //this is down
                    redCowboy.dy = 10;
                }
                if (e.getKeyCode() == 37) { //this is left
                    redCowboy.dx = -10;
                }
                if (e.getKeyCode() == 39) {//this is right
                    redCowboy.dx = 10;
                }
            }

            if (healthBarBlue > 0) {
                if (e.getKeyCode() == 87) { //this is up
                    blueCowboy.dy = -10;
                }
                if (e.getKeyCode() == 83) { //this is down
                    blueCowboy.dy = 10;
                }
                if (e.getKeyCode() == 65) { //this is left
                    blueCowboy.dx = -10;
                }
                if (e.getKeyCode() == 68) {//this is right
                    blueCowboy.dx = 10;
                }
            }
            if (e.getKeyCode() == 82) { //this is (R)
                bulls[0] = new Bull("bull", 10, 10);
                bulls[1] = new Bull("bull", 100, 10);
                bulls[2] = new Bull("bull", 500, 100);
                bulls[3] = new Bull("bull", 250, 250);
                bulls[4] = new Bull("bull", 880, 580);
                bulls[5] = new Bull("bull", 880, 580);

                redCowboy = new RedCowboy("RedCowboy", 263, 561);
                blueCowboy = new BlueCowboy("BlueCowboy", 132, 236);
                sheep = new Sheep("SheepItem", 40, 40);
                chickens[0] = new Chicken("Chicken", 910, 650);
                chickens[1] = new Chicken("Chicken", 920, 650);
                chickens[2] = new Chicken("Chicken", 930, 650);





                healthBarBlue = 5;
                healthBarRed = 5;
                score = 0;

                cameraX = redCowboy.xpos - WIDTH / 2.0;
                cameraY = redCowboy.ypos - HEIGHT / 2.0;
            }



        if (e.getKeyCode() == 16) { // SHIFT
            redDashTime = 10;
        }

        if (e.getKeyCode() == 32) { // SPACE
            blueDashTime = 10;
        }

        if (e.getKeyCode() == 77) {//this is (m)
            menu();
        }



    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 38) { //this is up
            redCowboy.dy = 0;
        }
        if (e.getKeyCode() == 40) { //this is down
            redCowboy.dy = 0;
        }
        if (e.getKeyCode() == 37) { //this is left
            redCowboy.dx = 0;
        }
        if (e.getKeyCode() == 39) {//this is right
            redCowboy.dx = 0;
        }


        if (e.getKeyCode() == 87) { //this is up
            blueCowboy.dy = 0;
        }
        if (e.getKeyCode() == 83) { //this is down
            blueCowboy.dy = 0;
        }
        if (e.getKeyCode() == 65) { //this is left
            blueCowboy.dx = 0;
        }
        if (e.getKeyCode() == 68) {//this is right
            blueCowboy.dx = 0;
        }


    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // List of stuff I want to add.
    // Bandages to gain health back
    // Add more bulls
}
