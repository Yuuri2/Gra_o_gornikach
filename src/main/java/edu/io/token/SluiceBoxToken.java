package edu.io.token;

import edu.io.player.Tool;

public class SluiceBoxToken extends Token implements Tool{

    private int durability = 5;
    private double gainFactor = 1.2;
    Token withToken;
    //konstruktory
    public SluiceBoxToken(){
        super(Label.SLUICE_BOX_TOKEN_LABEL);
    }
    public SluiceBoxToken(int durability){
        this();
        this.durability = durability;
    }
    public SluiceBoxToken(double gainFactor){
        this();
        this.gainFactor = gainFactor;
    }
    public SluiceBoxToken(double gainFactor, int durability){
        this();
        this.gainFactor = gainFactor;
        this.durability = durability;
    }

     //metody
    @Override
    public boolean isBroken(){
        return durability <= 0;
    }

    @Override
    public SluiceBoxToken ifBroken(Runnable action){
        if(isBroken()) action.run();
        return this;
    }

    @Override
    public SluiceBoxToken ifWorking(Runnable action){
        if(!isBroken() && withToken instanceof GoldToken) action.run();
        use();
        return this;
    }

    @Override
    public SluiceBoxToken ifIdle(Runnable action){
        if(!isBroken() && withToken instanceof EmptyToken) action.run();
        return this;
    }

    @Override
    public SluiceBoxToken useWith(Token token){
        withToken = token;
        return this;
    }

    public void use(){
        durability--;
        gainFactor-=0.04;
    }

    @Override
    public double gainFactor(){
        return gainFactor;
    }
    @Override
    public int durability(){
        return durability;
    }
}
