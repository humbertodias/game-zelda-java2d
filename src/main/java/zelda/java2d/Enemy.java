package zelda.java2d;

import java.awt.Graphics;

public class Enemy extends Shape {

    private int health;
    private int tempInv;
    private boolean isknockBack;
    private int knockBackTimer;
    private final int knockBackAmt = 60;
    private int knockBackDirec;
    private final String img;
    private String currentImg;
    private final int h, w;
    private Scene parentScene;
    private int dropX;
    private int dropY;
    private int dropType;
    private boolean dropped;

    public Enemy(int x, int y, int w, int h, int hp, String img, Game rpg) {
        super(x, y, rpg);
        setH(20);
        setW(20);
        this.health = hp;
        this.img = img;
        this.w = w;
        this.h = h;
    }

    public void setDrop(Scene parent, int dropX, int dropY, int dropType) {
        this.parentScene = parent;
        this.dropX = dropX;
        this.dropY = dropY;
        this.dropType = dropType;
    }

    public void drop() {
        if ((this.parentScene != null) && (!this.dropped)) {

            if (this.dropType == 1) {
                if ((this.dropX == 0) && (this.dropY == 0)) {
                    this.parentScene.addShape(new Key(getX(), getY(), getParent()));
                } else {
                    this.parentScene.addShape(new Key(this.dropX, this.dropY, getParent()));
                }
            }

            this.dropped = true;
        }
    }

    public void damageEnemy(int damage) {
        if (this.tempInv == 0) {
            this.health -= damage;
            this.tempInv = 15;

            if (this.health < 1) {
                setRendering(false);
                setActive(false);
                drop();
            }
        }
    }

    public void knockBack() {
        if (this.isknockBack) {
            switch (this.knockBackDirec) {
                case 0:
                    setY(getY() - 4);
                    break;
                case 1:
                    setY(getY() + 4);
                    break;
                case 2:
                    setX(getX() + 4);
                    break;
                case 3:
                    setX(getX() - 4);
            }

            this.knockBackTimer += 4;

            if (this.knockBackTimer >= this.knockBackAmt) {
                this.knockBackTimer = 0;
                this.isknockBack = false;
            }
        }
    }

    @Override
    public void onTouch() {
        if ((isActive()) && (isRenderable())) {
            getParent().damagePlayer(1);
        }
    }

    @Override
    public void renderShape(Graphics g) {
        knockBack();

        if (isRenderable()) {
            if (!this.isknockBack) {
                if (this.tempInv > 0) {
                    this.tempInv -= 1;
                }

                if (getX() > getParent().getInt("X")) {
                    setX(getX() - 1);
                    this.currentImg = (this.img + "-L.png");

                } else if (getX() < getParent().getInt("X")) {
                    setX(getX() + 1);
                    this.currentImg = (this.img + "-R.png");
                }

                if (getX() == getParent().getInt("X")) {
                    setX(getParent().getInt("X"));
                }

                if (getY() > getParent().getInt("Y")) {
                    setY(getY() - 1);
                }

                if (getY() < getParent().getInt("Y")) {
                    setY(getY() + 1);
                }

                if (getY() == getParent().getInt("Y")) {
                    setY(getParent().getInt("Y"));
                }

                if ((getParent().getSword(0)) && (getY() < getParent().getInt("Y")) && (getY() > getParent().getInt("Y") - this.h - 22)) {
                    if ((getX() <= getParent().getInt("W") + getParent().getInt("X") - 10) && (getX() + this.w >= getParent().getInt("X") + 16)) {
                        damageEnemy(1);
                        this.isknockBack = true;
                        this.knockBackDirec = 0;
                    }
                }

                if ((getParent().getSword(1)) && (getY() > getParent().getInt("Y")) && (getY() < getParent().getInt("Y") + 22 + getParent().getInt("H"))) {
                    if ((getX() <= getParent().getInt("W") + getParent().getInt("X") - 6) && (getX() + this.w >= getParent().getInt("X") + 12)) {
                        damageEnemy(1);
                        this.isknockBack = true;
                        this.knockBackDirec = 1;
                    }
                }

                if ((getParent().getSword(2)) && (getX() > getParent().getInt("X")) && (getX() < getParent().getInt("X") + 22 + getParent().getInt("W"))) {
                    if ((getY() <= getParent().getInt("H") + getParent().getInt("Y") - 10) && (getY() + this.h >= getParent().getInt("Y") + 14)) {
                        damageEnemy(1);
                        this.isknockBack = true;
                        this.knockBackDirec = 2;
                    }
                }

                if ((getParent().getSword(3)) && (getX() < getParent().getInt("X")) && (getX() > getParent().getInt("X") - this.w - 22)) {
                    if ((getY() <= getParent().getInt("H") + getParent().getInt("Y") - 10) && (getY() + this.h >= getParent().getInt("Y") + 14)) {
                        damageEnemy(1);
                        this.isknockBack = true;
                        this.knockBackDirec = 3;
                    }
                }
            }

            g.drawImage(ImageUtil.get(this.currentImg), getX(), getY(), null);
        }
    }
}
