import java.awt.*;

/**
 * Created by chales on 11/6/2017.
 * Edits by mblair on 10/27/2025
 */
public class Bull {

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
    public Bull(String pName, int pXpos, int pYpos) {
        name = pName;
        xpos = pXpos;
        ypos = pYpos;
        dx = 4;
        dy = 4;
        width = 120;
        height = 70;
        isAlive = true;
        rect = new Rectangle(xpos, ypos, width, height);

    }


    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;
        rect = new Rectangle(xpos, ypos, width, height);


        if(xpos >= 1000 - width ){
            dx = -dx;
        }
        if (ypos >= 700 - height){;
            dy = -dy;
        }
        if(xpos <= 0 ){
            dx = -dx;
        }
        if (ypos <= 0){
            dy = -dy;
        }
    }

    public void wrap() {
        xpos = xpos + dx;
        ypos = ypos + dy;
        rect = new Rectangle(xpos, ypos, width, height);

        if (xpos > 1000){
            xpos = 0;
        }
        if (ypos > 700){
            ypos = 0;
        }
        if (xpos < 0 ){
            xpos = 1000;
        }
        if (ypos < 0){
            ypos = 700;
        }
    }

    public void chase(int targetX, int targetY, double speed) {
        double dxSide = targetX - xpos;
        double dySide = targetY - ypos;

        double dist = Math.sqrt(dxSide * dxSide + dySide * dySide);

        if (dist != 0) {
            dx = (int)(speed * dxSide / dist);
            dy = (int)(speed * dySide / dist);
        }
    }

    
}






