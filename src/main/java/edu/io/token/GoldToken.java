package edu.io.token;

public class GoldToken extends Token{
    private double amount = 1;

    //konstruktory
    public GoldToken(){
        super(Label.GOLD_TOKEN_LABEL);
    }

    public GoldToken(double amount){
        this();
        if(amount < 0) throw new IllegalArgumentException();
        this.amount = amount;
    }


    //gettery i settery
    public double amount(){return amount;}

}
