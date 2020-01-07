package REST;



import Database.PreparedStatements;
import Models.Player;
import PlayerDeets.PlayerDTO;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

//import Database.PreparedStatements;

//TODO: yeah take this down once you finished modifying the server cuz i think the problems there
@Path("/authentication")
public class RestEndpoints {

   PreparedStatements statements = new PreparedStatements();

   private Gson gson = new Gson();


    @GET
    @Path("/player/{playerid}")
    @Produces("application/json")
    public Response getPlayer(@PathParam("playerid") String playerIdString) {

        System.out.println("[Server getPlayer]");


        //Find player

        Player player = statements.getPlayerByID(Integer.parseInt(playerIdString));

        // Check whether player exists
        if (player == null) {
            // Client error 400 - Bad Request
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
        }

        // Define response
        return Response.status(200).entity(RestResponseHelper.getSinglePlayerResponse(player)).build();
    }


    @GET
    @Path("/player/all")
    @Produces("application/json")
    public Response getAllPlayers() {

        System.out.println("[Server getAllPlayers]");

        // Get all players from the db
        List<Player> allPlayers = statements.getAllPlayers();

        // Define response
        return Response.status(200).entity(RestResponseHelper.getAllPlayersResponse(RestResponseHelper.getPlayerDTOList(allPlayers))).build();
    }




    @POST
    @Path("/player")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addPlayer(String requestBody ) {



        PlayerDTO playerRequest = new PlayerDTO();

        try {
            playerRequest = gson.fromJson(requestBody, PlayerDTO.class);
        }

        catch(Exception e)  {

        }
        System.out.println("[Server addPlayer]");

        // Check request
        if (playerRequest == null) {
            // Client error 400 - Bad Request
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
        }

        // Add player
        String playerName = playerRequest.getName();
        String playerPass = playerRequest.getPlayerPass();
        Player player = new Player(playerName, playerPass);
        statements.addPlayer(playerName, playerPass);

        // Define response
        return Response.status(200).entity(RestResponseHelper.getSinglePlayerResponse(player)).build();
    }


    @POST
    @Path("/player/login")
    @Consumes("application/json")
    @Produces("application/json")
    public Response loginPlayer(String requestBody) {

        PlayerDTO playerRequest = new PlayerDTO();

        try {
            playerRequest = gson.fromJson(requestBody, PlayerDTO.class);
        }

        catch(Exception e)  {

        }
        System.out.println("Server [loginPlayer]");

        // Check request
        if (playerRequest == null) {
            // Client error 400 - Bad Request
            System.out.println("No player given");
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();

        }
        // see if player exists

        System.out.println(playerRequest.getName() + " " + playerRequest.getPlayerPass());

        if(!statements.CheckPlayer(playerRequest.getName(), playerRequest.getPlayerPass())) {
            System.out.println("Wrong login");
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
        }

        Player player = new Player(playerRequest.getName(), playerRequest.getPlayerPass());

        //TODO: FIGURE OUT WHY THIS WAS HERE
    //    ClientLauncher.startClient(ActiveClientEndpoint.class);

        return Response.status(200).entity(RestResponseHelper.getSinglePlayerResponse(player)).build();
    }

    @POST
    @Path("/player/signup")
    @Consumes("application/json")
    @Produces("application/json")
    public Response playerSignup(String requestBody) {
        PlayerDTO playerDTO = new PlayerDTO();

        try {
            playerDTO = gson.fromJson(requestBody, PlayerDTO.class);
        } catch (Exception e) {

        }

        if (playerDTO == null) {

            //no player given
            System.out.println("No player");
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();

        }

        if (playerDTO.getName() == null || playerDTO.getPlayerPass() == null) {
            //serialization shizzle
            System.out.println("Player missing values");
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
        }

        // check if it already exists
        if (statements.CheckPlayer(playerDTO.getName(), playerDTO.getPlayerPass())) {

            System.out.println("Player already exists");
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
        }

        else{
            Player player = new Player();
            player = new Player(player.getUsername(), player.getPassword());
            statements.addPlayer(playerDTO.getName(), playerDTO.getPlayerPass());
            return Response.status(200).entity(RestResponseHelper.getSinglePlayerResponse(player)).build();
        }


    }


}
