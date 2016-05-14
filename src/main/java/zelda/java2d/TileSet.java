package zelda.java2d;

import java.awt.Graphics;

public class TileSet extends Shape {

    private final int xRepeat;
    private final int yRepeat;
    private java.awt.Image tileImg = null;

    public TileSet(int x, int y, int repeatX, int repeatY, String img, boolean isActive) {
        super(x, y);
        this.tileImg = ImageUtil.get(img);
        setW(32 * repeatX);
        setH(32 * repeatY);
        setActive(isActive);
        this.xRepeat = repeatX;
        this.yRepeat = repeatY;
    }

    @Override
    public void renderShape(Graphics g) {
        if (isRenderable()) {
            for (int i = 0; i < this.xRepeat; i++) {
                for (int z = 0; z < this.yRepeat; z++) {
                    g.drawImage(this.tileImg, getX() + i * 32, getY() + z * 32, null);
                }
            }
        }
    }
}
