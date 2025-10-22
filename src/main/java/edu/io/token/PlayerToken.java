package edu.io.token;

import edu.io.Board;

public class PlayerToken extends Token{
    private int col, row;
    private final Board board;

    public enum Move{
        NONE,
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    public PlayerToken(Board board){
        super(Label.PLAYER_TOKEN_LABEL);
        this.board = board;
        this.row = 0;
        this.col = 0;
        board.placeToken(col, row, this);
    }
    public void move(Move dir){
        if(dir == null) return;
        this.board.placeToken(col, row, new EmptyToken());
        if(isMoveLegal(dir)){
            switch (dir) {
                case UP -> {this.row--;}
                case DOWN -> {this.row++;}
                case LEFT -> {this.col--;}
                case RIGHT -> {this.col++;}
                case NONE ->{}
            }
        }
        else{
            throw new IllegalArgumentException();
        }
        this.board.placeToken(col, row, this);
    }

    private boolean isMoveLegal(Move Direction){
        switch(Direction){
            case UP -> {if(row > 0) return true;}
            case DOWN -> {if(row < board.size() - 1) return true;}
            case LEFT -> {if(col > 0) return true;}
            case RIGHT -> {if(col < board.size() - 1) return true;}
            case NONE -> {return true;}
            default -> {return false;}
        }
        
        return false;
    }

    public Board.Coords pos(){
        return new Board.Coords(this.col, this.row);
    }
}
