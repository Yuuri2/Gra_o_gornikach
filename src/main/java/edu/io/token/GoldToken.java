package edu.io.token;

public class GoldToken extends Token{
    private double amount = 1;

    public GoldToken(){
        super(Label.GOLD_TOKEN_LABEL);
    }

    public GoldToken(double amount){
        super(Label.GOLD_TOKEN_LABEL);
        if(amount < 0) throw new IllegalArgumentException();
        this.amount = amount;
    }

    public double amount(){return amount;}

}
