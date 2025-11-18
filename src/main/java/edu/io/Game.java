package edu.io;

import java.util.Scanner;

import edu.io.player.NoTool;
import edu.io.player.Player;
import edu.io.strategy.RandomSpawnStrategy;
import edu.io.token.AnvilToken;
import edu.io.token.GoldToken;
import edu.io.token.PickaxeToken;
import edu.io.token.PlayerToken;
import edu.io.token.PyriteToken;
import edu.io.token.SluiceBoxToken;

public class Game {
    private final Board board;
    private Player player;

    public Game(){
        //Przykładowa inicjalizacja gry
        this.board = new Board();
        Board.Coords freeSpace;
        GoldToken gold = new GoldToken();
        PyriteToken pyrite = new PyriteToken();
        PickaxeToken pickaxe = new PickaxeToken();
        AnvilToken anvil = new AnvilToken();
        SluiceBoxToken sluiceBox = new SluiceBoxToken();

        for(int goldOre = 0; goldOre < 4; goldOre++){
            freeSpace = board.getAvailableSquare(new RandomSpawnStrategy());
            if(goldOre == 1) board.placeToken(freeSpace.col(), freeSpace.row(), new GoldToken(5));
            else board.placeToken(freeSpace.col(), freeSpace.row(), gold);
        }
        for(int pyriteOre = 0; pyriteOre < 2; pyriteOre++){
            freeSpace = board.getAvailableSquare(new RandomSpawnStrategy());
            board.placeToken(freeSpace.col(), freeSpace.row(), pyrite);
        }
        freeSpace = board.getAvailableSquare(new RandomSpawnStrategy());
        board.placeToken(freeSpace.col(), freeSpace.row(), pickaxe);

        freeSpace = board.getAvailableSquare(new RandomSpawnStrategy());
        board.placeToken(freeSpace.col(), freeSpace.row(), anvil);

        freeSpace = board.getAvailableSquare(new RandomSpawnStrategy());
        board.placeToken(freeSpace.col(), freeSpace.row(), sluiceBox);
    }

    public void join(Player player){
        this.player = player;
        PlayerToken token = new PlayerToken(player, board);
        player.assignToken(token);
        player.vitals.setOnDeathHandler(() -> {
            System.out.println("Player died of thirst");
        });
    }
    public void start(){
        Scanner input = new Scanner(System.in);
        PlayerToken.Move move;
        do { 
            board.display();
            System.out.println("Gold pices: " + player.gold.amount());
            System.out.println("Hydration level: " + player.vitals.hydration() + "%");
            System.out.println("");
            if(!(player.getPlayerShed().getTool() instanceof NoTool)){
                System.out.println("Name: " + player.getToolToken() + " Durability: " + player.getToolToken().durability());
                System.out.println("");
            }
            move =  PlayerToken.Move.valueOf(input.nextLine().toUpperCase());
            try {
                player.token().move(move);
            } catch (IllegalStateException e) {
                System.out.println("Player is dead [*]");
            }
            
        } while (move != PlayerToken.Move.NONE);
        input.close();
    }
}
