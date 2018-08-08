package wormgame.gui;

import wormgame.domain.Piece;
import wormgame.domain.Worm;
import wormgame.game.WormGame;

import javax.swing.*;
import java.awt.*;

public class DrawingBoard extends JPanel implements Updatable {

    private WormGame wormGame;
    //private Worm worm;
    private int pieceLength;
    private JLabel score;

    public DrawingBoard(WormGame wormGame, int pieceLength, JLabel score) {
        this.wormGame = wormGame;
        this.pieceLength = pieceLength;
        this.score = score;
        //this.worm = worm;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for (Piece p : wormGame.getWorm().getPieces()){
            g.fill3DRect(p.getX() * pieceLength,p.getY()  * pieceLength, pieceLength,  pieceLength, true);
        }

        g.setColor(Color.RED);
        g.fillOval(wormGame.getApple().getX() * pieceLength, wormGame.getApple().getY() * pieceLength, pieceLength, pieceLength);
    }

    @Override
    public void update() {
        super.repaint();
        score.setText(wormGame.getScore());
    }

    }
