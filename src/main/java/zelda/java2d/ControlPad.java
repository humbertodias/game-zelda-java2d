package zelda.java2d;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Class which controls movements of the character
 */
public class ControlPad
        extends KeyAdapter {

    private boolean movingLeft;
    private boolean movingRight;
    private boolean movingUp;
    private boolean movingDown;
    private boolean stopped;
    private boolean beginAttack;
    public String msg;

    public boolean getMovingLeft() {
        return this.movingLeft;
    }

    public boolean beginAttacking() {
        return this.beginAttack;
    }

    public void setAttacking(boolean exp) {
        this.beginAttack = exp;
    }

    public boolean isStopped() {
        return this.stopped;
    }

    public void setStopped(boolean exp) {
        this.stopped = exp;
    }

    public boolean getMovingRight() {
        return this.movingRight;
    }

    public boolean getMovingUp() {
        return this.movingUp;
    }

    public boolean getMovingDown() {
        return this.movingDown;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();

        if (keycode == KeyEvent.VK_LEFT) {
            this.movingLeft = true;
        }

        if (keycode == KeyEvent.VK_RIGHT) {
            this.movingRight = true;
        }

        if (keycode == KeyEvent.VK_UP) {
            this.movingUp = true;
        }

        if (keycode == KeyEvent.VK_DOWN) {
            this.movingDown = true;
        }

        if (keycode == KeyEvent.VK_SPACE) {
            this.beginAttack = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keycode = e.getKeyCode();

        if (keycode == KeyEvent.VK_LEFT) {
            this.movingLeft = false;
        }

        if (keycode == KeyEvent.VK_RIGHT) {
            this.movingRight = false;
        }

        if (keycode == KeyEvent.VK_UP) {
            this.movingUp = false;
        }

        if (keycode == KeyEvent.VK_DOWN) {
            this.movingDown = false;
        }
    }
}
