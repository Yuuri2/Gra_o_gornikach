package edu.io.player;

public class Gold {
    private double amount;

    //Konstruktory
    public Gold(){
        amount = 0;
    }
    public Gold(double amount){
        if(amount >= 0) this.amount = amount;
        else throw new IllegalArgumentException();
    }

    //Metody
    public void gain(double amount){
        if(amount >= 0) this.amount+=amount;
        else throw new IllegalArgumentException();
    }
    public void lose(double amount){
        if(amount >= 0 && this.amount > amount) this.amount-=amount;
        else throw new IllegalArgumentException();
    }
    
    //gettery i settery
    public double amount(){
        return amount;
    }
}
