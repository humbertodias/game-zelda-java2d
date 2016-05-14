package zelda.java2d;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public final class Game
        extends JPanel {

    private int moveTimer = 0;
    private final int moveTime = 8;
    private boolean isM;
    private String message = "";
    private final int playerSpeed = 3;
    private final int playerW = 28;
    private final int playerH = 32;
    private int playerX = 100;
    private int playerY = 100;
    private int Direction = 1;
    private int checkPointX = this.playerX;
    private int checkPointY = this.playerY;
    private int keys = 0;
    private int atkTimer = 0;

    private int swordCD;
    private int tempInv;
    private int pHealth = 10;
    private final int pMaxHealth = 10;

    private int sceneX;
    private int sceneY;
    private final int gridSizeX = 99;
    private final int gridSizeY = 99;
    private final Scene[][] sceneGrid = new Scene[this.gridSizeX][this.gridSizeY];

    private boolean loadImages;
    private final int TILE_SIZE = 32;

    private ControlPad controls = null;

    private final Image[] linkStates = new Image[8];
    private final Image[] linkAtkStates = new Image[8];

    public boolean swordLeft;
    public boolean swordRight;
    public boolean swordUp;
    public boolean swordDown;

    public Game(ControlPad a) {
        this.controls = a;
        loadLevel();
    }

    public void loadLevel() {
        this.linkStates[0] = ImageUtil.get("link-N.png");
        this.linkStates[1] = ImageUtil.get("link-S.png");
        this.linkStates[2] = ImageUtil.get("link-E.png");
        this.linkStates[3] = ImageUtil.get("link-W.png");
        this.linkStates[4] = ImageUtil.get("link-NM.png");
        this.linkStates[5] = ImageUtil.get("link-SM.png");
        this.linkStates[6] = ImageUtil.get("link-EM.png");
        this.linkStates[7] = ImageUtil.get("link-WM.png");

        this.linkAtkStates[0] = ImageUtil.get("link-attack1-N.png");
        this.linkAtkStates[1] = ImageUtil.get("link-attack1-S.png");
        this.linkAtkStates[2] = ImageUtil.get("link-attack1-E.png");
        this.linkAtkStates[3] = ImageUtil.get("link-attack1-W.png");
        this.linkAtkStates[4] = ImageUtil.get("link-attack2-N.png");
        this.linkAtkStates[5] = ImageUtil.get("link-attack2-S.png");
        this.linkAtkStates[6] = ImageUtil.get("link-attack2-E.png");
        this.linkAtkStates[7] = ImageUtil.get("link-attack2-W.png");

        Scene s1 = new Scene(new Color(13300910));
        Scene s2 = new Scene(new Color(13300910));
        Scene s3 = new Scene(new Color(13300910));
        Scene s4 = new Scene(new Color(13300910));
        Scene s5 = new Scene(new Color(13300910));
        Scene s6 = new Scene(new Color(13300910));
        this.sceneGrid[0][0] = s1;
        this.sceneGrid[1][0] = s2;
        this.sceneGrid[2][0] = s3;
        this.sceneGrid[3][0] = s4;
        this.sceneGrid[4][0] = s5;
        this.sceneGrid[5][0] = s6;

        TileSet boundaryTop = new TileSet(0, 0, 15, 1, "tileTree.png", true);
        TileSet boundaryLeft = new TileSet(0, 0, 1, 14, "tileTree.png", true);
        TileSet boundaryBottom = new TileSet(0, 416, 15, 1, "tileTree.png", true);
        TileSet boundaryRight = new TileSet(448, 0, 1, 14, "tileTree.png", true);

        s1.addShape(boundaryTop);
        s1.addShape(boundaryLeft);
        s1.addShape(boundaryBottom);
        s1.addShape(new TileSet(256, 224, 1, 1, "tileTree.png", true));
        s1.addShape(new TileSet(128, 288, 1, 1, "tileTree.png", true));
        s1.addShape(new TileSet(160, 96, 1, 1, "tileTree.png", true));

        s2.addShape(boundaryTop);
        s2.addShape(boundaryBottom);
        s2.addShape(new TileSet(64, 96, 1, 1, "tileTree.png", true));
        s2.addShape(new TileSet(96, 256, 1, 1, "tileTree.png", true));
        s2.addShape(new TileSet(128, 96, 1, 1, "tileTree.png", true));
        s2.addShape(new TileSet(128, 192, 1, 1, "tileTree.png", true));
        s2.addShape(new TileSet(160, 320, 1, 1, "tileTree.png", true));
        s2.addShape(new TileSet(128, 256, 1, 1, "tileTree.png", true));
        s2.addShape(new TileSet(288, 256, 1, 1, "tileTree.png", true));
        s2.addShape(new TileSet(384, TILE_SIZE, 1, 4, "tileTree.png", true));
        s2.addShape(new TileSet(384, 192, 1, 6, "tileTree.png", true));
        s2.addShape(new TileSet(352, 384, 1, 1, "tileTree.png", true));

        s2.addShape(new Enemy(100, 100, TILE_SIZE, TILE_SIZE, 3, "ghost", this));
        s2.addShape(new Door(384, 160, this));
        Enemy dropGhost = new Enemy(300, 200, TILE_SIZE, TILE_SIZE, 3, "ghost", this);
        dropGhost.setDrop(s2, 0, 0, 1);
        s2.addShape(dropGhost);

        s3.addShape(boundaryTop);
        s3.addShape(boundaryBottom);
        s3.addShape(new TileSet(64, TILE_SIZE, 2, 5, "tileTree.png", true));
        s3.addShape(new TileSet(64, 256, 2, 6, "tileTree.png", true));
        s3.addShape(new TileSet(160, TILE_SIZE, 2, 5, "tileTree.png", true));
        s3.addShape(new TileSet(160, 256, 2, 6, "tileTree.png", true));
        s3.addShape(new TileSet(256, TILE_SIZE, 2, 5, "tileTree.png", true));
        s3.addShape(new TileSet(256, 256, 2, 6, "tileTree.png", true));
        s3.addShape(new TileSet(352, TILE_SIZE, 2, 5, "tileTree.png", true));
        s3.addShape(new TileSet(352, 256, 2, 6, "tileTree.png", true));
        s3.addShape(new MovableObstacle(2, 64, 384, 192, TILE_SIZE, TILE_SIZE, Color.RED, "H", true, this, "tileFire.png"));
        s3.addShape(new MovableObstacle(2, 64, 384, 224, TILE_SIZE, TILE_SIZE, Color.RED, "H", true, this, "tileFire.png"));

        s4.addShape(boundaryTop);
        s4.addShape(boundaryBottom);
        s4.addShape(new TileSet(TILE_SIZE, TILE_SIZE, 2, 5, "tileTree.png", true));
        s4.addShape(new TileSet(TILE_SIZE, 224, 2, 6, "tileTree.png", true));
        s4.addShape(new TileSet(128, TILE_SIZE, 2, 5, "tileTree.png", true));
        s4.addShape(new TileSet(128, 224, 2, 6, "tileTree.png", true));
        s4.addShape(new TileSet(224, TILE_SIZE, 2, 5, "tileTree.png", true));
        s4.addShape(new TileSet(224, 224, 2, 6, "tileTree.png", true));
        s4.addShape(new TileSet(320, TILE_SIZE, 2, 5, "tileTree.png", true));
        s4.addShape(new TileSet(320, 224, 2, 6, "tileTree.png", true));
        s4.addShape(new MovableObstacle(2, 128, 256, 96, TILE_SIZE, TILE_SIZE, Color.RED, "V", true, this, "tileFire.png"));
        s4.addShape(new MovableObstacle(2, 128, 256, 192, TILE_SIZE, TILE_SIZE, Color.RED, "V", false, this, "tileFire.png"));
        s4.addShape(new MovableObstacle(2, 128, 256, 288, TILE_SIZE, TILE_SIZE, Color.RED, "V", true, this, "tileFire.png"));

        s5.addShape(boundaryTop);
        s5.addShape(boundaryBottom);
        s5.addShape(new TileSet(64, 96, 1, 1, "tileTree.png", true));
        s5.addShape(new TileSet(256, 256, 1, 1, "tileTree.png", true));
        s5.addShape(new TileSet(256, 96, 1, 1, "tileTree.png", true));
        s5.addShape(new TileSet(160, 192, 1, 1, "tileTree.png", true));
        s5.addShape(new TileSet(96, 320, 1, 1, "tileTree.png", true));
        s5.addShape(new TileSet(288, 256, 1, 1, "tileTree.png", true));
        s5.addShape(new TileSet(128, 256, 1, 1, "tileTree.png", true));
        s5.addShape(new TileSet(384, 96, 1, 1, "tileTree.png", true));
        s5.addShape(new TileSet(352, 192, 1, 1, "tileTree.png", true));
        s5.addShape(new TileSet(320, 288, 1, 1, "tileTree.png", true));
        s5.addShape(new Enemy(350, 250, TILE_SIZE, TILE_SIZE, 3, "ghost", this));
        s5.addShape(new Enemy(175, 100, TILE_SIZE, TILE_SIZE, 3, "ghost", this));
        s5.addShape(new Enemy(115, 300, TILE_SIZE, TILE_SIZE, 3, "ghost", this));

        s6.addShape(boundaryTop);
        s6.addShape(boundaryRight);
        s6.addShape(boundaryBottom);
        Shape a = new Shape(200, 200, 250, 50, true, Color.GREEN, true, this);
        a.setText("You got to the end of the demo! \nBut there's no cake for you.", Color.BLACK);
        s6.addShape(a);

        Shape b = new Shape(TILE_SIZE, 390, 300, 20, true, Color.GREEN, true, this);
        b.setText("ARROWS to Move and Attack with SPACEBAR", Color.BLACK);
        s1.addShape(b);

    }

    public void setKeys(int i) {
        if (i == 1) {
            this.keys += 1;
        } else {
            this.keys -= 1;
        }
    }

    public int getKeys() {
        return this.keys;
    }

    public void damagePlayer(int dmg) {
        if (this.tempInv == 0) {
            this.pHealth -= dmg;
            this.tempInv = 75;
        }

        if (this.pHealth < 0) {
            this.pHealth = 0;
        }
    }

    public void healPlayer(int heal) {
        this.pHealth += heal;

        if (this.pHealth > this.pMaxHealth) {
            this.pHealth = this.pMaxHealth;
        }
    }

    public boolean getSword(int direc) {
        switch (direc) {
            case 0:
                return this.swordUp;
            case 1:
                return this.swordDown;
            case 2:
                return this.swordRight;
            case 3:
                return this.swordLeft;
        }

        return false;
    }

    public void drawHealth(Graphics g) {
        Font font = new Font("Arial", 1, 20);
        g.setFont(font);
        g.setColor(new Color(255, 255, 255));
//        g.fillRect(TILE_SIZE, TILE_SIZE, this.pMaxHealth * 15, TILE_SIZE);

        for (int i = 0; i < this.pMaxHealth; i++) {
            if (i + 1 > this.pHealth) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.RED);
            }

            g.drawString("♥", TILE_SIZE + i * 15, TILE_SIZE + 15);
        }
    }

    public void tpToCheckPoint() {
        this.playerX = this.checkPointX;
        this.playerY = this.checkPointY;
    }

    public void setCheckPoint() {
        this.checkPointX = this.playerX;
        this.checkPointY = this.playerY;
    }

    public void setPlayerX(int value) {
        this.playerX = value;
    }

    public void setPlayerY(int value) {
        this.playerY = value;
    }

    public int getInt(String type) {
        if (type.equals("X")) {
            return this.playerX;
        }

        if (type.equals("Y")) {
            return this.playerY;
        }

        if (type.equals("W")) {
            return this.playerW;
        }

        if (type.equals("H")) {
            return this.playerH;
        }

        return 0;
    }

    public boolean collisionEngine(Shape objs, int x, int y, int w, int h) {
        if ((x > objs.getX() - w) && (x < objs.getX() + objs.getW())) {
            if ((y > objs.getY() - h) && (y < objs.getY() + objs.getH())) {
                return true;
            }
        }

        return false;
    }

    public void shapesLoopCollisions(int x, int y, int w, int h, Projectile projectile) {
        Shape[] objs = this.sceneGrid[this.sceneX][this.sceneY].getShapes();

        for (int i = 0; i < 99; i++) {
            if (objs[i] == null) {
                break;
            }

            if (collisionEngine(objs[i], x, y, w, h)) {
                if (projectile == null) {
                    objs[i].onTouch(this);
                    objs[i].onTouch();
                } else {
                    projectile.hitTarget();
                }
            }
        }
    }

    public boolean setSceneX(int id) {
        if ((id < 0) || (id > this.gridSizeX)) {
            return false;
        }

        if (this.sceneGrid[id][this.sceneY] == null) {
            return false;
        }

        this.sceneX = id;
        return true;
    }

    public boolean setSceneY(int id) {
        if ((id < 0) || (id > this.gridSizeY)) {
            return false;
        }

        if (this.sceneGrid[this.sceneX][id] == null) {
            return false;
        }

        this.sceneY = id;
        return true;
    }

    public void collideswith() {
        if (this.playerX > 450) {
            if (setSceneX(this.sceneX + 1)) {
                this.playerX = 0;
                setCheckPoint();
            } else {
                this.playerX = 450;
                setCheckPoint();
            }
        }

        if (this.playerX < 0) {
            if (setSceneX(this.sceneX - 1)) {
                this.playerX = 450;
                setCheckPoint();
            } else {
                this.playerX = 0;
                setCheckPoint();
            }
        }

        if (this.playerY > 410) {
            if (setSceneY(this.sceneY + 1)) {
                this.playerY = 0;
                setCheckPoint();
            } else {
                this.playerY = 410;
                setCheckPoint();
            }
        }

        if (this.playerY < 0) {
            if (setSceneY(this.sceneY - 1)) {
                this.playerY = 410;
                setCheckPoint();
            } else {
                this.playerY = 0;
                setCheckPoint();
            }
        }

        shapesLoopCollisions(this.playerX, this.playerY, this.playerW, this.playerH, null);
    }

    @Override
    public void paintComponent(Graphics g) {
        if (!this.loadImages) {

            for (int i = 0; i < 8; i++) {
                g.drawImage(this.linkStates[i], 0, 0, null);
                g.drawImage(this.linkAtkStates[i], 0, 0, null);
            }

            g.drawImage(ImageUtil.get("ghost-L.png"), 0, 0, null);
            g.drawImage(ImageUtil.get("ghost-R.png"), 0, 0, null);
            g.drawImage(ImageUtil.get("key.png"), 0, 0, null);

            this.loadImages = true;
        }

        if (!this.controls.isStopped()) {
            if (this.controls.getMovingLeft()) {
                this.Direction = 3;
                this.playerX -= this.playerSpeed;
            }

            if (this.controls.getMovingRight()) {
                this.Direction = 2;
                this.playerX += this.playerSpeed;
            }

            if (this.controls.getMovingUp()) {
                this.Direction = 0;
                this.playerY -= this.playerSpeed;
            }

            if (this.controls.getMovingDown()) {
                this.Direction = 1;
                this.playerY += this.playerSpeed;
            }
        }

        collideswith();

        int direc = this.Direction;

        if (direc > 3) {
            direc -= 4;
        }

        if ((this.isM) && (this.Direction < 4) && ((this.controls.getMovingLeft()) || (this.controls.getMovingRight()) || (this.controls.getMovingUp()) || (this.controls.getMovingDown()))) {
            this.Direction += 4;
        }

        g.setColor(this.sceneGrid[this.sceneX][this.sceneY].getBg());

        g.fillRect(0, 0, 500, 480);

        Shape[] objs = this.sceneGrid[this.sceneX][this.sceneY].getShapes();

        for (int i = 0; i < 99; i++) {
            if (objs[i] == null) {
                break;
            }

            if ((this.playerX > objs[i].getX() - this.playerW) && (this.playerX < objs[i].getX() + objs[i].getW())) {
                if ((this.playerY > objs[i].getY() - this.playerH) && (this.playerY < objs[i].getY() + objs[i].getH())) {
                    objs[i].onTouch();
                }
            }

            objs[i].renderShape(g);
        }

        if ((this.controls.getMovingLeft()) || (this.controls.getMovingRight()) || (this.controls.getMovingUp()) || (this.controls.getMovingDown())) {
            this.moveTimer += 1;
            this.message += this.moveTimer;

            if (this.moveTimer >= this.moveTime) {
                this.moveTimer = 0;

                this.isM = !this.isM;
            }
        }

        if (this.swordCD > 0) {
            this.swordCD -= 1;
        }

        if (this.tempInv > 0) {
            this.tempInv -= 1;
        }

        if ((this.controls.beginAttacking()) && (!this.controls.isStopped()) && (this.swordCD == 0)) {
            this.controls.setAttacking(false);
            this.controls.setStopped(true);
            this.atkTimer = 20;
            this.swordCD = 30;
        }

        if (!this.controls.isStopped()) {
            g.drawImage(this.linkStates[this.Direction], this.playerX, this.playerY, null);
            this.swordUp = false;
            this.swordDown = false;
            this.swordLeft = false;
            this.swordRight = false;
        } else {
            if (this.atkTimer > 0) {
                this.atkTimer -= 1;
            } else {
                this.controls.setStopped(false);
            }

            int xPos = this.playerX;
            int yPos = this.playerY;

            this.swordUp = false;
            this.swordDown = false;
            this.swordLeft = false;
            this.swordRight = false;

            switch (direc) {
                case 0:
                    this.swordUp = true;
                    break;
                case 1:
                    this.swordDown = true;
                    break;
                case 2:
                    this.swordRight = true;
                    break;
                case 3:
                    this.swordLeft = true;
            }

            if (this.atkTimer > 10) {
                g.drawImage(this.linkAtkStates[direc], xPos, yPos, null);

            } else {
                if (direc == 3) {
                    xPos -= 23;
                }

                if (direc == 0) {
                    yPos -= 23;
                }

                g.drawImage(this.linkAtkStates[(direc + 4)], xPos, yPos, null);
            }
        }

        drawHealth(g);
        g.finalize();
        try {
            Thread.sleep(10L);
        } catch (InterruptedException localInterruptedException) {
        }

        repaint();
    }
}
