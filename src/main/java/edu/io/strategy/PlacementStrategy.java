package edu.io.strategy;

import edu.io.Board;

public interface PlacementStrategy {
    public Board.Coords getStrategy(Board board);
}
