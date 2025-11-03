package edu.io.player;

import edu.io.token.AnvilToken;
import edu.io.token.EmptyToken;
import edu.io.token.GoldToken;
import edu.io.token.PickaxeToken;
import edu.io.token.PlayerToken;
import edu.io.token.SluiceBoxToken;
import edu.io.token.Token;

public class Player{

    private PlayerToken token;
    public final Gold gold = new Gold();
    //shed umieściłem w playerze, bo każdy gracz będzie miał swoją prywatną szopę
    private final Shed shed = new Shed();

    public void interactWithToken(Token token){
        switch (token) {
            case GoldToken goldToken ->{
                //Zwiększyłem nieco funkcjonalność metody o sluiceBoxa więc zmienił bym nazwę
                //na useToolOnGold, ale tak było w treści zadania to zostawiam na razie
                usePickaxeOnGold(goldToken);
            }
            case PickaxeToken pickaxe ->{
                shed.add(pickaxe);
            }
            case SluiceBoxToken sluiceBox ->{
                shed.add(sluiceBox);
            }
            case AnvilToken anvil->{
                if(shed.getTool() instanceof Repairable tool) tool.repair();
            }
            case EmptyToken freeToken ->{
                break;
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
            //nie mam tu ifIdle bo to by zakładało że jestem nie jestem na złocie
            //a wiem że jestem
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
