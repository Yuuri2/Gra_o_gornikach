package edu.io;

import java.util.Scanner;

import edu.io.token.GoldToken;
import edu.io.token.PlayerToken;
import edu.io.token.PyriteToken;

public class Game {
    private final Board board;
    private Player player;

    public Game(){
        this.board = new Board();
        //złoto jest poprawnie zbierane
        board.placeToken(4, 4, new GoldToken());
        //piryt pomyślnie imituje złoto i nie dodaje wartości
        board.placeToken(2, 5, new PyriteToken());
    }
    public void join(Player player){
        this.player = player;
        PlayerToken token = new PlayerToken(player, board);
        player.assignToken(token);
    }
    public void start(){
        Scanner klawiatura = new Scanner(System.in);
        PlayerToken.Move ruch;
        do { 
            board.display();
            System.out.println("Ilosc zlota: "+player.gold());
            System.out.println("");
            ruch =  PlayerToken.Move.valueOf(klawiatura.nextLine().toUpperCase());
            player.getPlayerToken().move(ruch);
        } while (ruch != PlayerToken.Move.NONE);
        klawiatura.close();
    }
}
