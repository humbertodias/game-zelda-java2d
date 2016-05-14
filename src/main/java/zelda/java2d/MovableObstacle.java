package zelda.java2d;

import java.awt.Graphics;

public class MovableObstacle extends Shape {

    private final int speed;
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
    private final String axis;
    private boolean direction;
    private final java.awt.Color color;
    private final String img;

    public MovableObstacle(int speed, int min, int max, int relativePos, int w, int h, java.awt.Color color, String axis, boolean direction, Game game, String img) {
        super(0, 0, game);

        this.speed = speed;
        this.img = img;

        if (axis.equals("H")) {
            this.minX = min;
            this.maxX = max;

            if (direction) {
                setX(min);
            } else {
                setX(max);
            }

            setY(relativePos);
        }

        if (axis.equals("V")) {
            this.minY = min;
            this.maxY = max;

            if (direction) {
                setY(min);
            } else {
                setY(max);
            }

            setX(relativePos);
        }

        this.axis = axis;
        setW(w);
        setH(h);
        this.direction = direction;
        this.color = color;
        setActive(false);
    }

    @Override
    public void renderShape(Graphics g) {
        if (isRenderable()) {
            if (this.img.equals("")) {
                g.setColor(this.color);
                g.fillRect(getX(), getY(), getW(), getH());
            } else {
                java.awt.Image myTile = ImageUtil.get(this.img);
                g.drawImage(myTile, getX(), getY(), null);
            }

            move();
        }
    }

    @Override
    public void onTouch() {
        getParent().damagePlayer(1);
        getParent().tpToCheckPoint();
    }

    public void move() {
        if (this.axis.equals("H")) {
            if (this.direction) {
                setX(getX() + this.speed);

                if (getX() >= this.maxX) {
                    this.direction = false;
                }

            } else {
                setX(getX() - this.speed);

                if (getX() <= this.minX) {
                    this.direction = true;
                }

            }

        } else if (this.axis.equals("V")) {
            if (this.direction) {
                setY(getY() + this.speed);

                if (getY() >= this.maxY) {
                    this.direction = false;
                }

            } else {
                setY(getY() - this.speed);

                if (getY() <= this.minY) {
                    this.direction = true;
                }
            }
        }
    }
}
