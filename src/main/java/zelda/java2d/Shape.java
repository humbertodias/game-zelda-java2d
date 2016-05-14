package zelda.java2d;

import java.awt.Color;

public class Shape {

    private int x;
    private int y;
    private int w;
    private int h;
    private Color c;
    private boolean active;
    private boolean doRender = true;
    private String text = "";
    private Color textColor = Color.BLACK;
    private Game parent = null;

    public Shape(int x, int y, int w, int h, boolean block, Color c, boolean isActive, Game rpg) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.c = c;
        this.active = isActive;
        this.parent = rpg;
    }

    public int getX() {
        return this.x;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setRendering(boolean active) {
        this.doRender = active;
    }

    public Game getParent() {
        return this.parent;
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

    public void setX(int value) {
        this.x = value;
    }

    public void setY(int value) {
        this.y = value;
    }

    public void setH(int value) {
        this.h = value;
    }

    public void setW(int value) {
        this.w = value;
    }

    public void onTouch(Game game) {
        if (isActive()) {
            if (getX() - game.getInt("W") > game.getInt("X") - 4) {
                game.setPlayerX(getX() - game.getInt("W"));
            }

            if (getY() - game.getInt("H") > game.getInt("Y") - 4) {
                game.setPlayerY(getY() - game.getInt("H"));
            }

            if (getX() + getW() < game.getInt("X") + 4) {
                game.setPlayerX(getX() + getW());
            }

            if (getY() + getH() < game.getInt("Y") + 4) {
                game.setPlayerY(getY() + getH());
            }
        }
    }

    public void onTouch() {
    }

    public Shape(int x, int y, Game rpg) {
        this.x = x;
        this.y = y;
        this.parent = rpg;
        this.active = true;
    }

    public Shape(int x, int y) {
        this.x = x;
        this.y = y;
        this.active = true;
    }

    public void setAsCheckPoint() {
        this.active = false;
    }

    public void setText(String text, Color c) {
        this.text = text;
        this.textColor = c;
    }

    public boolean isActive() {
        return this.active;
    }

    public boolean isRenderable() {
        return this.doRender;
    }

    public void touchEngine() {
        setRendering(false);
    }

    public void renderShape(java.awt.Graphics g) {
        if (this.doRender) {
            if (this.c == null) {
                this.c = Color.RED;
            }

            g.setColor(this.c);
            g.fillRect(this.x, this.y, this.w, this.h);
        }

        if (!this.text.equals("")) {
            g.setColor(this.textColor);

            int lineCounter = getY() + getH() / 20;
            String[] arrayOfString;
            int j = (arrayOfString = this.text.split("\n")).length;
            for (int i = 0; i < j; i++) {
                String line = arrayOfString[i];
                lineCounter += 15;
                g.drawString(line, getX() + getW() / 20, lineCounter);
            }
        }
    }
}
