package auto.chincho;

import chincho.merinde.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

class InputListener implements KeyListener {

    // Set no permite elementos duplicados
    public Set<Integer> pressedKeys = new HashSet<>();
    MainWindow parent;

    InputListener(MainWindow parent) {
        this.parent = parent;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
        checkInputs();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }

    private void checkInputs() {

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
