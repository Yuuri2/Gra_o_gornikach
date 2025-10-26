import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.io.Board;
import edu.io.Player;
import edu.io.token.EmptyToken;
import edu.io.token.GoldToken;
import edu.io.token.PlayerToken;
import edu.io.token.Token;

class BoardTest {
    Board board;
    Player player;

    @BeforeEach
    void setUp() {
        board = new Board();
        player = new Player();
    }

    @Test
    void new_board_is_clean() {
        Assertions.assertTrue(_is_board_clean());
    }

    @Test
    void can_place_token() {
        Token t = new PlayerToken(player, board);
        board.placeToken(1, 2, t);
        Assertions.assertEquals(t, board.peekToken(1, 2));
    }

    @Test
    void clean_method_works() {
        board.placeToken(1, 2, new GoldToken());
        board.placeToken(board.size()-1, board.size()-1,
                new GoldToken());
        board.clean();
        Assertions.assertTrue(_is_board_clean());
    }

    @Test
    void display_method_exists() {
        Assertions.assertDoesNotThrow(() -> Board.class.getMethod("display"));
    }

    @Test
    void getAvailableSquare_returns_position_of_empty_square() {
        Board.Coords c = board.getAvailableSquare();
        Assertions.assertInstanceOf(
                EmptyToken.class,
                board.peekToken(c.col(), c.row()));
        board.placeToken(c.col(), c.row(), new GoldToken());
        c = board.getAvailableSquare();
        Assertions.assertInstanceOf(
                EmptyToken.class,
                board.peekToken(c.col(), c.row())
        );
    }

    @Test
    void getAvailableSquare_throws_when_board_is_full() {
        int n = board.size() * board.size();
        Token token = new GoldToken();
        for (int i=0; i<n; i++) {
            Board.Coords c = board.getAvailableSquare();
            board.placeToken(c.col(), c.row(), token);
        }
        Assertions.assertThrows(
                IllegalStateException.class,
                () -> board.getAvailableSquare());
    }

    //=======Mój Test========
    @Test
    void setPlacementStrategy_throws_when_board_is_full_on_random_strategy() {
        int n = board.size() * board.size();
        Token token = new GoldToken();
        for (int i=0; i<n; i++) {
            Board.Coords c = board.setPlacementStrategy(1);
            board.placeToken(c.col(), c.row(), token);
        }
        Assertions.assertThrows(
                IllegalStateException.class,
                () -> board.setPlacementStrategy(1));
    }

    //=======Mój Test========
    @Test
    void does_random_placement_strategy_work(){
        Board board = new Board();
        Token token = new PlayerToken(player, board);
        Board.Coords c1 = board.setPlacementStrategy(1);
        Assertions.assertTrue(board.peekToken(c1.col(), c1.row()) instanceof EmptyToken);
        board.placeToken(c1.col(), c1.row(), token);
        Board.Coords c2 = board.setPlacementStrategy(1);
        Assertions.assertNotEquals(c1, c2);
    }

    // -- utils
    boolean _is_board_clean() {
        int size = board.size();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (!(board.peekToken(col, row) instanceof EmptyToken)) {
                    return false;
                }
            }
        }
        return true;
    }

    
}
