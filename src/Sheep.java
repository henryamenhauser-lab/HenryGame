import java.awt.*;

public class Sheep {

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
    public Sheep(String pName, int pXpos, int pYpos) {
        name = pName;
        xpos = pXpos;
        ypos = pYpos;
        dx = 2;
        dy = 2;
        width = 40;
        height = 40;
        isAlive = true;
        rect = new Rectangle(xpos, ypos, width, height);

    }

    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;
        rect = new Rectangle(xpos, ypos, width, height);
        if (xpos > 1000-width){
            xpos = 1000-width;
        }
        if (xpos <0){
            xpos = 0;
        }
        if (ypos > 700-height){
            ypos = 700-height;
        }
        if (ypos <0){
            ypos = 0;
        }
    }
}
