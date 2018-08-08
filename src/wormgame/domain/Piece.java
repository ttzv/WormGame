package wormgame.domain;

import java.util.Objects;

public class Piece {

    private int x;
    private int y;

    public Piece(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    public boolean runsInto(Piece piece){
        if (this.equals(piece)){
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "(" +
                 x +
                ", " + y +
                ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Piece piece = (Piece) o;
        return x == piece.x &&
                y == piece.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


}
