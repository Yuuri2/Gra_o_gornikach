package edu.io.strategy;

import edu.io.Board;

public interface PlacementStrategy {
    public Board.Coords getStrategy(Board board);
    //spróbowałem zmienić sposób implementacji strategii
    //ta metoda wydaje mi się lepsza - pierwsze wolne jest defaultowo
    //jeśli chcę inną metodę to podaję ją argumentem
}
