package edu.io;

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

    public Token peekToken(int col, int row){
        return grid[col][row];
    }
    
    public void clean(){
        Token freeSpace = new EmptyToken();
        for(int i = 0; i < size;i++){
            for(int j = 0; j < size;j++){
                grid[i][j] = freeSpace;
            }
        }
    }
    public void placeToken(int col, int row, Token token){
        grid[col][row] = token;
    }

    public Token square(int col, int row){
        return grid[col][row];
    }

    public void display(){
        for(int i = 0; i < size;i++){
            for(int j = 0; j < size;j++){
                System.out.print(grid[i][j].label());
            }
            System.out.print("\n");
        }
    }

    public record Coords(int col, int row){

    }
}
