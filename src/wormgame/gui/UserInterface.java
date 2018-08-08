package wormgame.gui;

import java.awt.*;
import javax.swing.*;

import wormgame.Direction;
import wormgame.game.WormGame;

public class UserInterface implements Runnable {

    private JFrame frame;
    private WormGame game;
    private int sideLength;
    private DrawingBoard board;
    int width, height;

    public UserInterface(WormGame game, int sideLength) {
        this.game = game;
        this.sideLength = sideLength;
    }

    @Override
    public void run() {
        frame = new JFrame("Worm Game");
        frame.setLayout(new BorderLayout());
        width = (game.getWidth() + 1) * sideLength + 10;
        height = (game.getHeight() + 1) * sideLength + 10;


        frame.setPreferredSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void createComponents(Container container) {
        JLabel score = new JLabel("SCORE: " + game.getScore());
        //JButton start = new JButton("START");
        this.board = new DrawingBoard(game, sideLength, score);

        //frame.addKeyListener(new KeyboardListener(game.getWorm()));


        container.add(score, BorderLayout.PAGE_START);
        //container.add(start, BorderLayout.PAGE_END);
        container.add(board, BorderLayout.CENTER);

        MovingAction movingAction = new MovingAction(board, game.getWorm());

        movingAction.addAction("UP", Direction.UP);
        movingAction.addAction("DOWN", Direction.DOWN);
        movingAction.addAction("LEFT", Direction.LEFT);
        movingAction.addAction("RIGHT", Direction.RIGHT);
    }

    public Updatable getUpdatable(){
        return this.board;
    }


    public JFrame getFrame() {
        return frame;
    }
}
