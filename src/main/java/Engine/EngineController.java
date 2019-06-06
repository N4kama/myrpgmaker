package Engine;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Engine.Character.Animation;
import Engine.Character.EngineObj;
import Game.Map;
import Game.World;

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
        this.view_.continueButton.addActionListener(continue_action());
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

    private ActionListener continue_action() {
        return actionEvent -> {
            this.view_.displayGame();
        };
    }

    private KeyListener input_listener() {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                    model_.move(Direction.RIGHT);
                } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
                    model_.move(Direction.LEFT);
                } else if (ke.getKeyCode() == KeyEvent.VK_UP) {
                    model_.move(Direction.UP);
                } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
                    model_.move(Direction.DOWN);
                } else if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    model_.pause();
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                Animation cur = model_.getGameWorld().player_.getEs().getCurAnim();
                if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                    cur.stop();
                    cur.reset();
                    model_.getGameWorld().player_.getEs()
                            .setCurAnim(model_.getGameWorld().player_.getEs().getStandRight());
                    model_.stand();
                } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
                    cur.stop();
                    cur.reset();
                    model_.getGameWorld().player_.getEs()
                            .setCurAnim(model_.getGameWorld().player_.getEs().getStandLeft());
                    model_.stand();
                } else if (ke.getKeyCode() == KeyEvent.VK_UP) {
                    cur.stop();
                    cur.reset();
                    model_.getGameWorld().player_.getEs()
                            .setCurAnim(model_.getGameWorld().player_.getEs().getStandUp());
                    model_.stand();
                } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
                    cur.stop();
                    cur.reset();
                    model_.getGameWorld().player_.getEs()
                            .setCurAnim(model_.getGameWorld().player_.getEs().getStandDown());
                    model_.stand();
                }
            }
        };
    }
}
