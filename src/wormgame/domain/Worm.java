package wormgame.domain;

import wormgame.Direction;

import java.util.ArrayList;
import java.util.List;

public class Worm {

    private int originalX, originalY;
    private Direction originalDirection, direction;
    private List<Piece> body;

    public Worm(int originalX, int originalY, Direction originalDirection) {
        this.originalX = originalX;
        this.originalY = originalY;
        direction = originalDirection;

        body = new ArrayList<>();
        body.add(new Piece(originalX, originalY));
    }

    public Direction getDirection(){
        return this.direction;
    }

    public void setDirection ( Direction dir ) {
        this.direction = dir;
    }

    public int getLength() {
        return getPieces().size();
    }

    public List<Piece> getPieces () {
        return this.body;
    }

    public void move() {
        if (getPieces().size() < 3){
            grow();
        }
        else{
            this.body.remove(0);
            grow();
        }
    }

    public void grow() {
        if (getDirection() == Direction.LEFT) {
            body.add(new Piece(getHeadCoords().getX() - 1, getHeadCoords().getY()));
        }
        if (getDirection() == Direction.RIGHT) {
            body.add(new Piece(getHeadCoords().getX() + 1, getHeadCoords().getY()));
        }
        if (getDirection() == Direction.UP) {
            body.add(new Piece(getHeadCoords().getX(), getHeadCoords().getY() - 1));
        }
        if (getDirection() == Direction.DOWN) {
            body.add(new Piece(getHeadCoords().getX(), getHeadCoords().getY() + 1));
        }

    }

    public Piece getHeadCoords() {
        return this.body.get(body.size() - 1);
    }

    public boolean runsInto (Piece piece) {
        if( getHeadCoords().getY() == piece.getY() || getHeadCoords().getX() == piece.getX())
            return true;
        return false;
    }

   // public boolean runsIntoItself() {
    //    for (Piece p : getPieces())
   // }


}
