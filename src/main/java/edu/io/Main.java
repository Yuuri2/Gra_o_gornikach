package edu.io;

import java.util.Scanner;

import edu.io.token.PlayerToken;

public class Main {
    public static void main(String[] args) {
        Scanner klawiatura = new Scanner(System.in);
        PlayerToken.Move ruch;
        Board plansza = new Board();
        PlayerToken gracz = new PlayerToken(plansza);
        do { 
            plansza.display();
            ruch =  PlayerToken.Move.valueOf(klawiatura.nextLine().toUpperCase());
            System.out.println("");
            gracz.move(ruch);
        } while (true);
        //klawiatura.close();
    }
}
