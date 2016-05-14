package zelda.java2d;

import java.awt.Graphics;
import java.awt.Image;

public class Key extends Shape {

    public Key(int x, int y, Game game) {
        super(x, y, game);
        setW(14);
        setH(26);
        setActive(false);
    }

    @Override
    public void onTouch() {
        setRendering(false);
        getParent().setKeys(1);
    }

    @Override
    public void renderShape(Graphics g) {
        if (isRenderable()) {
            Image myDoor = ImageUtil.get("key.png");
            g.drawImage(myDoor, getX(), getY(), null);
        }
    }
}
