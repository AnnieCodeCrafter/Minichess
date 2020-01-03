package Database;

import Models.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PreparedStatements implements IPreparedStatements {


    static PreparedStatement PrepareStat = null;
    static MysqlCon con = new MysqlCon();
    static Connection Conn;

    @Override
    public List<Player> getAllPlayers() {
        List<Player> getPlayers = new ArrayList<>();
        try {

            Conn = con.getConnection();

            String getQueryStatement = "SELECT * FROM player";

            PrepareStat = Conn.prepareStatement(getQueryStatement);

            // Execute the Query, and get a java ResultSet
            ResultSet rs = PrepareStat.executeQuery();

            // Let's iterate through the java ResultSet
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Username");
                String pword = rs.getString("Password");
                int wins = rs.getInt("wins");

                getPlayers.add(new Player(id, name, pword, wins ));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getPlayers;
    }

    @Override
    public Player getPlayerByID(int id) {
        Conn = con.getConnection();
        Player player = new Player();
        try {

            String getQueryStatement = "SELECT * FROM player WHERE ID = ?";

            PrepareStat = Conn.prepareStatement(getQueryStatement);
            PrepareStat.setObject(1, id);

            // Execute the Query, and get a java ResultSet
            ResultSet rs = PrepareStat.executeQuery();

            // Let's iterate through the java ResultSet
            while (rs.next()) {
                String name = rs.getString("uname");
                String pword = rs.getString("pword");
                int wins = rs.getInt("wins");
                player = new Player(id, name, pword, wins);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return player;
    }

    @Override
    public void addPlayer(String username, String password) {
        Conn = con.getConnection();
        try {
            String insertQueryStatement = "INSERT  INTO  player (uname, pword, wins)  VALUES  (?,?, 0)";

            PrepareStat = Conn.prepareStatement(insertQueryStatement);
            PrepareStat.setString(1, username);
            PrepareStat.setString(2, password);

            // execute insert SQL statement
            PrepareStat.executeUpdate();

        } catch (

                SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean CheckPlayer(String username, String password) {
        Conn = con.getConnection();
        Player player = new Player();
        List<Player> userList = new ArrayList<>();
        boolean playerExists = false;

        try {
            String getQueryStatement = "SELECT * FROM player WHERE username = ? AND password = ?";
            PrepareStat = Conn.prepareStatement(getQueryStatement);
            PrepareStat.setString(1, username);
            PrepareStat.setString(2, password);


            ResultSet rs = PrepareStat.executeQuery();

            // Let's iterate through the java ResultSet
            while (rs.next()) {
                String name = rs.getString("username");
                String pword = rs.getString("password");
                player = new Player(name, pword);
                userList.add(player);
            }

            if(userList.size() < 1) {
                playerExists = false;
            }

            if(userList.size() >= 1) {
                playerExists = true;
                System.out.println("there were more players with that name.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerExists;
    }



    @Override
    public void setWinsByPlayer(int id, int wins) {
        Conn = con.getConnection();

        try {
            String getQueryStatement = "UPDATE player SET wins = ? WHERE id = ?";
            PrepareStat = Conn.prepareStatement(getQueryStatement);
            PrepareStat.setInt(1, wins);
            PrepareStat.setInt(2, id);
            PrepareStat.executeUpdate();
        }

         catch (SQLException e) {
        e.printStackTrace();
        }


    }

    //maybe someday but whatever

    @Override
    public List<Player> getPlayersByWins(int wins) {
        return null;
    }

}
