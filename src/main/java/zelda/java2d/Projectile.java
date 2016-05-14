package zelda.java2d;

public class Projectile {

    private final int x;
    private final int y;
    private int h;
    private int w;
    private final int direction;
    private Game parent = null;
    private boolean inactive;

    public Projectile(int x, int y, int direction, Game game) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.parent = game;
    }

    public int getDirection() {
        return this.direction;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getW() {
        return this.w;
    }

    public int getH() {
        return this.h;
    }

    public boolean getActivity() {
        return this.inactive;
    }

    public Game getParent() {
        return this.parent;
    }

    public void hitTarget() {
        this.inactive = true;
    }
}
