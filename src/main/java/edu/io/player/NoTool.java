package edu.io.player;

import edu.io.token.Token;

public class NoTool implements Tool{
    //metody
    @Override
    public Tool useWith(Token withToken){
        return this;
    }

    @Override
    public Tool ifWorking(Runnable action){
        return this;
    }

    @Override
    public Tool ifBroken(Runnable action){
        return this;
    }

    @Override
    public Tool ifIdle(Runnable action){
        return this;
    }

    @Override
    public boolean isBroken(){
        return false;
    }
    @Override
    public double gainFactor(){
        return 0;
    }
    @Override
    public int durability(){
        return 0;
    }
}
