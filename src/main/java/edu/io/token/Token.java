package edu.io.token;

public abstract class Token {
    private final String label;

    public Token(String l){
        label = l;
    }

    public String label(){
        return this.label;
    }
}
