import java.awt.*;

public class Chicken {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public String name;               //name of the hero
    public int xpos;                  //the x position
    public int ypos;                  //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;                 //the width of the hero image
    public int height;                //the height of the hero image
    public boolean isAlive;           //a boolean to denote if the hero is alive or dead
    public Rectangle rect;

    //This is a constructor that takes 3 parameters.
    // This allows us to specify the hero's name and position when we build it.
    public Chicken(String pName, int pXpos, int pYpos) {
        name = pName;
        xpos = pXpos;
        ypos = pYpos;
        dx = 9;
        dy = 9;
        width = 20;
        height = 20;
        isAlive = true;
        rect = new Rectangle(xpos, ypos, width, height);

    }

    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;
        rect = new Rectangle(xpos, ypos, width, height);

        if(xpos >= BasicGameApp.worldWidth - width ){
            dx = -dx;
        }
        if (ypos >= BasicGameApp.worldHeight - height){;
            dy = -dy;
        }
        if(xpos <= 0 ){
            dx = -dx;
        }
        if (ypos <= 0){
            dy = -dy;
        }

    }

    public void runAway(int targetX, int targetY, double speed) {
        double dxSide = xpos - targetX;
        double dySide = ypos - targetY;

        double dist = Math.sqrt(dxSide * dxSide + dySide * dySide);

        if (dist != 0) {
            dx = (int)(speed * dxSide / dist);
            dy = (int)(speed * dySide / dist);
        }

        int wallDistance = 100;

        if (xpos < wallDistance) {
            dx += 2;
        }
        if (xpos > BasicGameApp.worldWidth - wallDistance) {
            dx -=2;
        }
        if (ypos < wallDistance) {
            dy += 2;
        }
        if (ypos > BasicGameApp.worldHeight-wallDistance) {
            dy -=2;
        }

        xpos += dx;
        ypos += dy;
        rect.setBounds(xpos, ypos, width, height);
    }

}