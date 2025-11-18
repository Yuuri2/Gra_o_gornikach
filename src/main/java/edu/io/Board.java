package edu.io;

import java.util.Objects;

import edu.io.strategy.PlacementStrategy;
import edu.io.token.EmptyToken;
import edu.io.token.Token;

public final class Board {
    private int size;
    public Token[][] grid;
    
    //konstruktory
    public Board(){
        size = 8;
        grid = new Token[size][size];
        clean();
    }
    public Board(int size){
        this();
        this.size = size;
    }

    //Metody
    //bazaowo pierwsze wolne
    public Coords setPlacementStrategy(){
        for(int col = 0; col < size;col++){
            for(int row = 0; row < size;row++){
                if(grid[col][row] instanceof EmptyToken) return new Coords(col,row);
            }
        }
        throw new IllegalStateException();
    }
    public Coords getAvailableSquare(){
        return setPlacementStrategy();
    }

    //metoda przeciażona - umożliwia ustawienie dowolnej strategii
    public Coords setPlacementStrategy(PlacementStrategy strategy){
        return strategy.getStrategy(this);
    }
    public Coords getAvailableSquare(PlacementStrategy strategy){
        return setPlacementStrategy(strategy);
    }

    public void clean(){
        Token freeSpace = new EmptyToken();
        for(int col = 0; col < size;col++){
            for(int row = 0; row < size;row++){
                grid[col][row] = freeSpace;
            }
        }
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

    //gettery i settery
    public int size(){
        return this.size;
    }

    public Token peekToken(int col, int row){
        return grid[col][row];
    }

    public void placeToken(int col, int row, Token token){
        //if(token == null) throw new NullPointerException("Token cannot be null");
        Objects.requireNonNull(token, "Token cannot be null");
        grid[col][row] = token;
    }
}
