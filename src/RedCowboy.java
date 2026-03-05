import java.awt.*;
/**
 * Created by chales on 11/6/2017.
 * Edits by mblair on 10/27/2025
 */
public class RedCowboy {

    public String name;
    public double xpos;
    public double ypos;
    public double dx;
    public double dy;
    public int width;
    public int height;
    public boolean isAlive;
    public Rectangle rect;

    public RedCowboy(String pName, int pXpos, int pYpos) {
        name = pName;
        xpos = pXpos;
        ypos = pYpos;
        dx = 0;
        dy = 0;
        width = 30;
        height = 60;
        isAlive = true;
        rect = new Rectangle((int)xpos, (int)ypos, width, height);
    }

    public void move() {
        xpos += dx;
        ypos += dy;

        if(xpos < 0) xpos = 0;
        if(ypos < 0) ypos = 0;
        if(xpos > BasicGameApp.worldWidth - width) xpos = BasicGameApp.worldWidth - width;
        if(ypos > BasicGameApp.worldHeight - height) ypos = BasicGameApp.worldHeight - height;

        rect.setBounds((int)xpos, (int)ypos, width, height);
    }
}