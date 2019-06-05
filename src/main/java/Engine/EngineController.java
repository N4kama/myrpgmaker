package Engine;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EngineController {
    private EngineModel model_;
    private EngineView view_;

    public EngineController(EngineModel engineModel, EngineView engineView) {
        this.model_ = engineModel;
        this.view_ = engineView;
    }

    public void set_controls() {
        this.view_.startButton.addActionListener(start_action());
        this.view_.exitButton.addActionListener(exit_action());
        this.view_.addKeyListener(input_listener());

    }

    private ActionListener start_action() {
        return actionEvent -> {
            this.view_.startGame();
        };
    }

    private ActionListener exit_action() {
        return actionEvent -> {
            this.view_.exitGame();
        };
    }

    private KeyListener input_listener() {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {
                if(ke.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    view_.map_view.curAnim = view_.map_view.walkRight;
                    view_.map_view.curAnim.start();
                    model_.move(Direction.RIGHT);
                }
                else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
                    view_.map_view.curAnim = view_.map_view.walkLeft;
                    view_.map_view.curAnim.start();
                    model_.move(Direction.LEFT);
                }
                else if (ke.getKeyCode() == KeyEvent.VK_UP) {
                    view_.map_view.curAnim = view_.map_view.walkUp;
                    view_.map_view.curAnim.start();
                    model_.move(Direction.UP);
                }
                else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
                    view_.map_view.curAnim = view_.map_view.walkDown;
                    view_.map_view.curAnim.start();
                    model_.move(Direction.DOWN);
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                if(ke.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    view_.map_view.curAnim.stop();
                    view_.map_view.curAnim.reset();
                    view_.map_view.curAnim = view_.map_view.standRight;
                    model_.stand();
                }
                else if(ke.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    view_.map_view.curAnim.stop();
                    view_.map_view.curAnim.reset();
                    view_.map_view.curAnim = view_.map_view.standLeft;
                    model_.stand();
                }
                else if(ke.getKeyCode() == KeyEvent.VK_UP)
                {
                    view_.map_view.curAnim.stop();
                    view_.map_view.curAnim.reset();
                    view_.map_view.curAnim = view_.map_view.standUp;
                    model_.stand();
                }
                else if(ke.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    view_.map_view.curAnim.stop();
                    view_.map_view.curAnim.reset();
                    view_.map_view.curAnim = view_.map_view.standDown;
                    model_.stand();
                }
            }
        };
    }
}
