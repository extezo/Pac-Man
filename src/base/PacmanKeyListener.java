package base;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PacmanKeyListener implements KeyListener {
    private String direction = "down";
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyChar());
        switch (e.getKeyChar()) {
            case 'a':
                direction = "left";
                break;
            case 'd':
                direction = "right";
                break;
            case 'w':
                direction = "up";
                break;
            case 's':
                direction = "down";
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public String getDirection() {
        return direction;
    }
}
