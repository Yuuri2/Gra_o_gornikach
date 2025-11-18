package edu.io.player;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;

public class Vitals {
    private int hydration;

    public Vitals(){
        hydration = 100;
        setOnDeathHandler(() -> {});
    }

    private Runnable onDeathCallback;

    public void setOnDeathHandler(@NotNull Runnable callback){
            onDeathCallback = Objects.requireNonNull(callback, "callback cannot be null");
    }

    public void hydrate(int amount){
        if(!(amount >= 0)) throw new IllegalArgumentException("adding a negative number");
        if(hydration + amount > 100) hydration = 100;
        else hydration+=amount;
        
        
    }

    public void dehydrate(int amount){
        if(!(amount >= 0)) throw new IllegalArgumentException("decreasing number by a negative number will increase value");
        if(hydration - amount <= 0){
            hydration = 0;
            onDeathCallback.run();
        }
        else hydration-=amount;
        
    }


    public boolean isAlive(){
        return hydration>0;
    }

    public int hydration(){
        return hydration;
    }
}
