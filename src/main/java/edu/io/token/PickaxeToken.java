package edu.io.token;

import edu.io.player.Repairable;
import edu.io.player.Tool;

public class PickaxeToken extends Token implements Tool, Repairable{
    private double gainFactor = 1.5;
    private int durability = 3;
    private int initalDurability = 3;
    
    private Token withToken;

    //gettery i settery
    public PickaxeToken() {
        super(Label.PICKAXE_TOKEN_LABEL);
    }
    public PickaxeToken(double gainFactor){
        this();
        if(gainFactor > 0) this.gainFactor = gainFactor;
        else throw new IllegalArgumentException();
    }
    public PickaxeToken(double gainFactor, int durability){
        this(gainFactor);
        if(durability > 0){
            this.durability = durability;
            this.initalDurability = durability;
        } 
        else throw new IllegalArgumentException();
    }

    //metody
    @Override
    public boolean isBroken(){
        return durability <= 0;
    }

    @Override
    public void repair(){
        durability = initalDurability;
    }

    @Override
    public PickaxeToken ifBroken(Runnable action){
        if(isBroken()) action.run();
        return this;
    }

    @Override
    public PickaxeToken ifWorking(Runnable action){
        if(!isBroken() && withToken instanceof GoldToken) action.run();
        use();
        return this;
    }

    @Override
    public PickaxeToken ifIdle(Runnable action){
        if(!isBroken() && withToken instanceof EmptyToken) action.run();
        return this;
    }

    @Override
    public PickaxeToken useWith(Token token){
        withToken = token;
        return this;
    }
    
    //gettery i settery
    public void use(){
        durability--;
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
