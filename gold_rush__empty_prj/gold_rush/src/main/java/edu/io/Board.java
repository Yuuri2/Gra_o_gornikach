package edu.io;

public class Board {
    public int size = 8;
    public Token[][] grid;
    
    public Board(){
        grid = new Token[size][size];
        this.clean();
    }

    
    public void clean(){
        for(int i = 0; i < size;i++){
            for(int j = 0; j < size;j++){
                grid[i][j] = new Token("・");
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

    }
}
