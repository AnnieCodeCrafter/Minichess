package Database;

import Database.PreparedStatements;
import Models.Player;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PreparedStatementsTest {
    PreparedStatements statements = new PreparedStatements();

    @Test
    public void getAllPlayers() {
        List<Player> players = statements.getAllPlayers();
        Assert.assertNotNull(players);

    }

    @Test
    public void GetPlayer() {
        Player player = statements.getPlayerByID(1);
        Assert.assertNotNull(player);
    }

    @Test
    public void checkPlayer() {
       boolean check =  statements.CheckPlayer("me", "neverguess");

       Assert.assertTrue(check);
    }

    @Test
    public void addPlayer() {
        statements.addPlayer("me", "neverguess");
        boolean check = statements.CheckPlayer("me", "neverguess");
        Assert.assertTrue(check);
    }

    @Test
    public void setWins() {
        statements.setWinsByPlayer(1, 3);

        boolean check;
        Player player = statements.getPlayerByID(1);

        if(player.getWins() == 3) {
            check = true;
        }

        else {
            check = false;
        }

        Assert.assertTrue(check);
    }
}
