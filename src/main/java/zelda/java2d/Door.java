package zelda.java2d;

import java.awt.Graphics;
import java.awt.Image;

/**
 * Door
 */
public class Door extends Shape {

    public Door(int x, int y, Game game) {
        super(x, y, game);

        setW(32);
        setH(32);
    }

    @Override
    public void onTouch() {
        if (getParent().getKeys() > 0) {
            setActive(false);
            setRendering(false);
            getParent().setKeys(0);
        }
    }

    @Override
    public void renderShape(Graphics g) {
        if (isRenderable()) {
            Image myDoor = ImageUtil.get("lockedDoor.png");
            g.drawImage(myDoor, getX(), getY(), null);
        }
    }
}
