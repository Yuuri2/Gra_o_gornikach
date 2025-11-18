package edu.io.player;

import edu.io.token.AnvilToken;
import edu.io.token.EmptyToken;
import edu.io.token.GoldToken;
import edu.io.token.PickaxeToken;
import edu.io.token.PlayerToken;
import edu.io.token.SluiceBoxToken;
import edu.io.token.Token;
import edu.io.token.WaterToken;

public class Player{

    private PlayerToken token;
    public final Gold gold = new Gold();
    public final Vitals vitals = new Vitals();

    //shed umieściłem w playerze, bo każdy gracz będzie miał swoją prywatną szopę
    private final Shed shed = new Shed();


    public void interactWithToken(Token token){
        if(!vitals.isAlive()) throw new IllegalStateException("Player is dead");
        switch (token) {
            case GoldToken goldToken ->{
                usePickaxeOnGold(goldToken);
                vitals.dehydrate(VitalsValues.DEHYDRATION_GOLD);
            }
            case PickaxeToken pickaxe ->{
                shed.add(pickaxe);
            }
            case SluiceBoxToken sluiceBox ->{
                shed.add(sluiceBox);
            }
            case AnvilToken anvil->{
                vitals.dehydrate(VitalsValues.DEHYDRATION_ANVIL);
                if(shed.getTool() instanceof Repairable tool) tool.repair();
            }
            case WaterToken water ->{
                vitals.hydrate(water.amount());
            }
            case EmptyToken freeToken ->{
                vitals.dehydrate(VitalsValues.DEHYDRATION_MOVE);
            }
            default ->{
                System.out.println("Token not recognised");
            }
        }
    }

    private void usePickaxeOnGold(GoldToken goldToken){
        double amount = goldToken.amount();
        if(shed.getTool() instanceof Tool tool && !(shed.getTool() instanceof NoTool)){
            tool.useWith(goldToken)
            .ifWorking(() -> {
                gold.gain(amount * tool.gainFactor());
            })
            .ifBroken(() -> {
                gold.gain(amount);
                shed.dropTool();
            });
        }
        else gold.gain(amount);
    }

    //gettery i settery
    public PlayerToken token(){
        return token;
    }
    public Tool getToolToken(){
        return shed.getTool();
    }

    public void assignToken(PlayerToken token){
        this.token = token;
        token.player = this;
    }

    public Shed getPlayerShed(){
        return shed;
    }

}
