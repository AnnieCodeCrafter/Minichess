package Database;

import Models.Player;

import java.util.List;

public interface IPreparedStatements {

    // Get list of users.
    List<Player> getAllPlayers();

    //Get user by id.
    Player getPlayerByID(int id);

    // Add user.
    void addPlayer(String username, String password);

    boolean CheckPlayer(String username, String password);

    void setWinsByPlayer(int id, int wins);

    List<Player> getPlayersByWins(int wins);


}
