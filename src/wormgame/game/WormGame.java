package wormgame.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import wormgame.Direction;
import wormgame.domain.Apple;
import wormgame.domain.Piece;
import wormgame.domain.Worm;
import wormgame.gui.Updatable;

public class WormGame extends Timer implements ActionListener {

    private int width;
    private int height;
    private boolean continues;
    private Updatable updatable;

    private Worm worm;
    private Apple apple;
    private Random rng;

    private int[] boardSize;
    private int score = 0;

    public WormGame(int width, int height) {
        super(1000, null);

        this.width = width;
        this.height = height;
        this.continues = true;

        addActionListener(this);
        setInitialDelay(2000);



        boardSize = new int[2];

        boardSize[0] = this.width;
        boardSize[1] = this.height;

        worm = new Worm(this.width/2, this.height/2, Direction.DOWN, boardSize);
        rng = new Random();

        apple = new Apple(rng.nextInt(this.width), rng.nextInt(this.height));


    }


    public boolean continues() {
        return continues;
    }

    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Worm getWorm(){
        return this.worm;
    }

    public void setWorm (Worm worm){
        if (this.worm == null)
            this.worm = new Worm(this.width/2, this.height/2, Direction.DOWN, boardSize);
    }

    public Apple getApple(){
        return this.apple;
    }

    public void setApple(){
            this.apple = new Apple(rng.nextInt(this.width), rng.nextInt(this.height));
    }

    public void resetGame(){
        this.worm = new Worm(this.width/2, this.height/2, Direction.DOWN, boardSize);
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!continues) {
            return;
        }
        try {
            worm.move();

            //TODO: this print is for debugging purposes only.
            System.out.println("HEAD: " + this.worm.getHeadCoords() + " | APPLE: " + this.apple + "SCORE: " + getScore());
        }
        catch(IllegalArgumentException e){
            this.continues = false;
            System.out.println(e.getMessage());
        }
       ///////////////////
        //System.out.println(worm.runsInto(apple));
        //////////////////
        if (worm.runsInto(apple)){
            score++;

            try {
                worm.grow();
            }
            catch(IllegalArgumentException e){
                this.continues = false;
                System.out.println(e.getMessage());
            }

            setApple();
        }

        this.updatable.update();

        setDelay(1000 / worm.getLength());

    }

    public String getScore(){
        return "" + this.score;
    }
}
