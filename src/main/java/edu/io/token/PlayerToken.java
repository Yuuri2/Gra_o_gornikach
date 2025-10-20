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
        switch(dir){
            case LEFT -> {
                if(col > 0){
                    this.board.placeToken(col, row, new EmptyToken());
                    this.col -= 1;
                }
                else throw new IllegalArgumentException("Cannot go out of bounds");
            }
            case RIGHT -> {
                if(col < board.size() - 1){
                    this.board.placeToken(col, row, new EmptyToken());
                    this.col += 1;
                }
                else throw new IllegalArgumentException("Cannot go out of bounds");
            }
            case UP -> {
                if(row > 0){
                    this.board.placeToken(col, row, new EmptyToken());
                    this.row -= 1;
                }
                else throw new IllegalArgumentException("Cannot go out of bounds");
            }
            case DOWN -> {
                if(row < board.size() - 1){
                    this.board.placeToken(col, row, new EmptyToken());
                    this.row += 1;
                }
                else throw new IllegalArgumentException("Cannot go out of bounds");
            }
            case NONE -> System.out.println("Nothing ever happens");

            default -> {
                throw new IllegalArgumentException("UNKNOWN DIRECTION PASSED!");
            }
        }
        this.board.placeToken(col, row, this);
    }
    public Board.Coords pos(){
        return new Board.Coords(this.col, this.row);
    }
}
