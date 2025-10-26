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

    public Coords setPlacementStrategy(int placementStrategy){
        switch (placementStrategy) {
            case 1 ->{
                int randomCol, randomRow, tries = 0;
                do {
                    randomCol = (int)(Math.random() * 8);
                    randomRow = (int)(Math.random() * 8);
                    tries++;
                    //Przetestowane przy całej planszy złota throwuje error
                    //jeśli do tego czasu nie znajdzie miejsca jest dobra szansa że plansza jest pełna więc przechodzi do metody szukającej pierwszego wolnego
                    if(tries > size*size){
                        return setPlacementStrategy(2);
                    }
                } while (!(grid[randomCol][randomRow] instanceof EmptyToken));
                return new Coords(randomCol,randomRow);
            }
            case 2 ->{
                for(int col = 0; col < size;col++){
                    for(int row = 0; row < size;row++){
                        if(grid[col][row] instanceof EmptyToken) return new Coords(col,row);
                    }
                }
                throw new IllegalStateException();
            }
            default ->{
                throw new AssertionError();
            }
                
        }
        
    }

    public Token peekToken(int col, int row){
        return grid[col][row];
    }

    public Coords getAvailableSquare(){
        // W programie normalnie użyłbym tego ale testy nie przejdą w takiej konfiguracji więc zostawiam jako komentarz
        
        // System.out.println("Select Spawn Strategy(1-2):");
        // System.out.println("1 - Random Spawn");
        // System.out.println("2 - First Free Space");
        // System.out.println("Your Choice: ");
        // try {
        //     Scanner klawiatura = new Scanner(System.in);
        //     int placementStrategy = klawiatura.nextInt();
        //     klawiatura.close();
        //     if(placementStrategy > 2 || placementStrategy < 1) throw new IllegalArgumentException("No SpawnStrategy of this index");
        //     return setPlacementStrategy(placementStrategy);
        // } catch (Exception e) {
        //     throw new IllegalArgumentException();
        // }
        return setPlacementStrategy(2);
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
