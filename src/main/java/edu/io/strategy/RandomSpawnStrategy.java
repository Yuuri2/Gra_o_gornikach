package edu.io.strategy;

import edu.io.Board;
import edu.io.token.EmptyToken;

public class RandomSpawnStrategy implements PlacementStrategy{
    @Override
    public Board.Coords getStrategy(Board board){
        int randomCol, randomRow, tries = 0;
        do {
            randomCol = (int)(Math.random() * board.size());
            randomRow = (int)(Math.random() * board.size());
            tries++;
            //Przetestowane przy całej planszy złota throwuje error
            //jeśli do tego czasu nie znajdzie miejsca jest dobra szansa że plansza jest pełna więc przechodzi do metody szukającej pierwszego wolnego
            if(tries > Math.pow(board.size(), 2)){
                return board.setPlacementStrategy();
            }
        } while (!(board.grid[randomCol][randomRow] instanceof EmptyToken));
        return new Board.Coords(randomCol,randomRow);
    }
}
