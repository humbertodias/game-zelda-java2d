package zelda.java2d;

import java.awt.Color;

public class Scene {

    private final Shape[] shapes = new Shape[99];
    private final Color bg;

    public Scene(Color bg) {
        this.bg = bg;
    }

    public Color getBg() {
        return this.bg;
    }

    public void addShape(Shape shape) {
        for (int i = 0; i < this.shapes.length; i++) {
            if (this.shapes[i] == null) {
                this.shapes[i] = shape;
                break;
            }
        }
    }

    public Shape[] getShapes() {
        return this.shapes;
    }
}
