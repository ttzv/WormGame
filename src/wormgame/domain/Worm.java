package wormgame.domain;

import wormgame.Direction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Worm {

    private int originalX, originalY;
    private Direction originalDirection, direction;
    private ArrayList<Piece> body;
    boolean intersects;

    private int[] boundaries;

    public Worm(int originalX, int originalY, Direction originalDirection, int[] boundaries) {
        this.originalX = originalX;
        this.originalY = originalY;
        direction = originalDirection;
        body = new ArrayList<>();
        body.add(new Piece(originalX, originalY));
        intersects = false;

        this.boundaries = boundaries;
    }

    public Direction getDirection(){
        return this.direction;
    }

    public void setNewWorm(){
        body = new ArrayList<>();
        body.add(new Piece(originalX, originalY));
    }

    public void setDirection ( Direction dir )
    {
        if (!this.direction.isOpposite(dir))
            this.direction = dir;
        else throw new IllegalAccessError("Worm cannot move inside itself");
    }

    public int getLength() {
        return getPieces().size();
    }

    public ArrayList<Piece> getPieces () {
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
        Piece newPiece;

        newPiece = calculateNext();

        if(!_runsIntoItself(newPiece))
            body.add(newPiece);
        else {
            intersects = true;
            throw new IllegalArgumentException("Collision");
        }
    }

    public Piece calculateNext() {

        Piece head = getHeadCoords();

        Piece piece = null;

        if (getDirection() == Direction.UP) {
            if (head.getY() != 0) {
                piece = new Piece(head.getX(), head.getY() - 1);
            } else {
                piece = new Piece(head.getX(), boundaries[0]);
            }
        }

        if (getDirection() == Direction.LEFT) {
            if (head.getX() != 0) {
                piece = new Piece(head.getX() - 1, head.getY());
            } else {
                piece = new Piece(boundaries[1], head.getX());
            }
        }

        if (getDirection() == Direction.RIGHT) {
            if (head.getX() != boundaries[1]) {
                piece = new Piece(head.getX() + 1, head.getY());
            } else {
                piece = new Piece(0, head.getY());
            }
        }

        if (getDirection() == Direction.DOWN) {
            if (head.getY() != boundaries[0]){
                piece = new Piece( head.getX(), head.getY() + 1);
            }
            else{
                piece = new Piece(head.getX(), 0);
            }
        }

        return piece;
    }

    public Piece getHeadCoords() {
        return this.body.get(body.size() - 1);
    }

    /**
     *
     * @param piece part of body
     * @return true if piece would move in the way of another piece, false if the way is clear
     */
    public boolean runsInto (Piece piece) {
       return piece.equals(getHeadCoords());
    }

    public boolean _runsIntoItself(Piece piece) {
        if (this.body.contains(piece))
            return true;
        return false;
    }

    public boolean runsIntoItself(){
        return intersects;
    }





}
