package REST;
import Models.Player;
import PlayerDeets.PlayerDTO;
import RESTHelper.RestResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RestResponseHelper {

    private static final Gson gson = new Gson();

    public static String getErrorResponseString()
    {
        RestResponse response = new RestResponse();
        response.setSuccess(false);
        String output = gson.toJson(response);
        System.out.println("[Server response] " + output);
        return output;
    }


    public static String getSuccessResponse(boolean success)
    {
        RestResponse response = new RestResponse();
        response.setSuccess(success);
        String output = gson.toJson(response);
        System.out.println("[Server response] " + output);
        return output;
    }

    public static List<PlayerDTO> getPlayerDTOList(List<Player> Players)
    {
        List<PlayerDTO> allPlayers = new ArrayList<>();
        for (Player u : Players) {
            PlayerDTO player = u.createDTO();
            allPlayers.add(player);
        }
        return allPlayers;
    }

    public static String getAllPlayersResponse(List<PlayerDTO> allPlayers)
    {
        RestResponse response = new RestResponse();
        response.setSuccess(true);
        response.setPlayers(allPlayers);
        String output = gson.toJson(response);
        System.out.println("[Server response] " + output);
        return output;
    }


    public static String getSinglePlayerResponse(Player player)
    {
        RestResponse response = new RestResponse();
        response.setSuccess(true);
        List<PlayerDTO> Players = new ArrayList<>();
        PlayerDTO Player = player.createDTO();
        Players.add(Player);
        response.setPlayers(Players);
        String output = gson.toJson(response);
        System.out.println("[Server response] " + output);
        return output;
    }


}
