package edu.io.token;

import edu.io.Board;
import edu.io.player.Player;

public class PlayerToken extends Token{
    private int col, row;
    private final Board board;
    public Player player;

    public enum Move{
        NONE,
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    //metody
    public PlayerToken(Player player, Board board){
        super(Label.PLAYER_TOKEN_LABEL);
        this.board = board;
        this.player = player;

        Board.Coords freeSpace= board.getAvailableSquare();
        col = freeSpace.col();
        row = freeSpace.row();

        board.placeToken(col, row, this);
    }
    public void move(Move dir){
        if(dir == null) return;
        board.placeToken(col, row, new EmptyToken());
        if(isMoveLegal(dir)){
            switch (dir) {
                case UP -> {this.row--;}
                case DOWN -> {this.row++;}
                case LEFT -> {this.col--;}
                case RIGHT -> {this.col++;}
                case NONE ->{}
            }
            player.interactWithToken(board.peekToken(col, row));
        }
        else{
            throw new IllegalArgumentException();
        }
        board.placeToken(col, row, this);
    }

    private boolean isMoveLegal(Move direction){
        switch(direction){
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
        return new Board.Coords(col, row);
    }
}
