package edu.io;

import edu.io.strategy.PlacementStrategy;
import edu.io.token.EmptyToken;
import edu.io.token.Token;

public class Board {
    private final int size = 8;
    public Token[][] grid;
    
    public Board(){
        grid = new Token[size][size];
        this.clean();
    }

    public int size(){
        return this.size;
    }
    //bazaowo pierwsze wolne
    public Coords setPlacementStrategy(){
        for(int col = 0; col < size;col++){
            for(int row = 0; row < size;row++){
                if(grid[col][row] instanceof EmptyToken) return new Coords(col,row);
            }
        }
        throw new IllegalStateException();
    }
    //przeciążone z argumentem - losowe wolne
    public Coords setPlacementStrategy(PlacementStrategy strategy){
        return strategy.getStrategy(this);
    }

    public Token peekToken(int col, int row){
        return grid[col][row];
    }

    public Coords getAvailableSquare(){
        return setPlacementStrategy();
    }

    
    public void clean(){
        Token freeSpace = new EmptyToken();
        for(int col = 0; col < size;col++){
            for(int row = 0; row < size;row++){
                grid[col][row] = freeSpace;
            }
        }
    }
    public void placeToken(int col, int row, Token token){
        grid[col][row] = token;
    }

    public void display(){
        for(int col = 0; col < size;col++){
            for(int row = 0; row < size;row++){
                System.out.print(grid[row][col].label());
            }
            System.out.print("\n");
        }
    }

    public record Coords(int col, int row){}
}
