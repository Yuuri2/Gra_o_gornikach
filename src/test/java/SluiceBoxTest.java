import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.io.player.Player;
import edu.io.token.EmptyToken;
import edu.io.token.GoldToken;
import edu.io.token.SluiceBoxToken;

public class SluiceBoxTest {

    @Test
    void sluicebox_gainFactor_affects_collected_gold() {
        var player = new Player();
        player.interactWithToken(new SluiceBoxToken());
        player.interactWithToken(new GoldToken(2.0));
        Assertions.assertEquals(1.2 * 2.0, player.gold.amount());
    }

    @Test
    void default_sluicebox_durability_is_5() {
        Assertions.assertEquals(5, new SluiceBoxToken().durability());
    }

    @Test
    void sluicebox_use_decrement_durability() {
        var n = 3;
        var t = new SluiceBoxToken();
        for (var i=0; i<5; i++) {
            Assertions.assertTrue(t.durability() > 0);
            t.use();
        }
        Assertions.assertEquals(0, t.durability());
    }

    @Test
    void broken_sluicebox_is_unusable() {
        // Arrange
        var player = new Player();
        var goldToken = new GoldToken(2.0);
        var sluiceBox = new SluiceBoxToken();
        player.interactWithToken(sluiceBox);
        sluiceBox.use();
        // Act
        for (var i=0; i<5; i++){
            player.interactWithToken(goldToken);
        }
        // Assert
        Assertions.assertTrue(sluiceBox.isBroken());
    }

    @Test
    void can_use_sluicebox_with_gold() {
        new SluiceBoxToken().useWith(new GoldToken())
               .ifWorking(() -> {
                   Assertions.assertTrue(true);
               })
               .ifBroken(Assertions::fail)
               .ifIdle(Assertions::fail);
    }

    @Test
    void can_use_sluicebox_with_other_than_gold_but_no_effect() {
        new SluiceBoxToken().useWith(new EmptyToken())
                .ifWorking(Assertions::fail)
                .ifBroken(Assertions::fail)
                .ifIdle(() -> {
                    Assertions.assertTrue(true);
                });
    }

    @Test
    void broken_sluicebox_doesnt_work() {
        var pickaxeToken = new SluiceBoxToken(1);
        pickaxeToken.useWith(new GoldToken()).ifWorking(()->{});
        pickaxeToken.useWith(new GoldToken())
                .ifWorking(Assertions::fail)
                .ifBroken(() -> {
                    Assertions.assertTrue(true);
                });
    }

}
