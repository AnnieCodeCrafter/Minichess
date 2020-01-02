package Models;


import PlayerDeets.PlayerDTO;

public class Player {
    private int id;
    private String username;
    private String password;
    private String sessionID;
    private int wins;


    public Player() {

    }

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Player(int id, String username, String password, int wins) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.wins = wins;
    }

    public Player(String username, String pass, String id) {
        this.username = username;
        this.password = pass;
        this.sessionID = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public PlayerDTO createDTO() {
        return new PlayerDTO(username, password, id);
    }
}
