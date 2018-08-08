package wormgame;

/**
 * Each direction has the opposite direction accesible by getOpposite()
 */
public enum Direction {



    UP("DOWN", "UP"), RIGHT("LEFT", "RIGHT"), DOWN("UP", "DOWN"), LEFT("RIGHT", "LEFT");

    private String oppositeTo, name;

    Direction (String oppositeTo, String name){
        this.oppositeTo = oppositeTo;
        this.name = name;
    }

    public String getOpposite(){
        return this.oppositeTo;
    }

    public String getName(){
        return this.name;
    }
    public boolean isOpposite(Direction dir){
        if(this.oppositeTo == dir.name)
            return true;
        return false;
    }



}
