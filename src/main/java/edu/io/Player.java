package edu.io;

import edu.io.token.GoldToken;
import edu.io.token.PlayerToken;
import edu.io.token.Token;

public  class Player{
    private  PlayerToken token;
    private double gold;

    public PlayerToken getPlayerToken(){
        return token;
    }

    public void assignToken(PlayerToken token){
        this.token = token;
        token.player = this;
    }

    public void interactWithToken(Token token){
        if(token instanceof GoldToken gold) this.gainGold(gold.amount());
    }
    public void gainGold(double amount){
        if(amount >= 0) gold+=amount;
        else throw new IllegalArgumentException();
    }

    public void loseGold(double amount){
        if(gold-amount >= 0 && amount >=0) gold-=amount;
        else throw new IllegalArgumentException();
    }

    //gettery

    public PlayerToken token(){
        return  this.token;
    }

    public double gold(){
        return gold;
    }
}
