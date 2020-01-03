package Starts;

import Models.Player;
import PlayerDeets.PlayerDTO;
import RESTclient.RestClient;
import WebsocketsClient.ActiveClientEndpoint;
import WebsocketsClient.ClientLauncher;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;

public class ClientUI extends Application {

    private Rectangle testSquare;
    private ClientLauncher clientLauncher = new ClientLauncher();

    //todo: just puttin this here to see what happens
    private final int BORDERSIZE = 10; // Size of borders in pixels
    private final int AREAWIDTH = 400; // Width of area in pixels
    private final int AREAHEIGHT = AREAWIDTH; // Height of area in pixels
    private final int SQUAREWIDTH = 97; // Width of single square in pixels
    private final int SQUAREHEIGHT = 78; // Height of single square in pixels


    // Constants to define number of squares horizontal and vertical
    private final int NRSQUARESHORIZONTAL = 4;
    private final int NRSQUARESVERTICAL = 5;

    // Target area, a 10 x 10 grid where the opponent's ships are placed
    private Rectangle targetArea;

    // Squares for the target area
    private Rectangle[][] squaresTargetArea;

    private TextField userText;
    private TextField passText;

    private Button buttonUp;
    private Button buttonIn;


    @Override
    public void start(Stage stage) throws Exception {

        GridPane grid;
        grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(25, 25, 25, 25));


        //TODO: im just puttin this here to see what happens.




        //textfield username
       userText = new TextField();
        grid.add(userText, 47, 2);

        //label username
        Label labelUsername = new Label();
        labelUsername.setText("Username: ");
        grid.add(labelUsername, 47,1);

        //textfield password
        passText = new TextField();
        grid.add(passText, 47, 4);

        //label password
        Label labelPass = new Label();
        labelPass.setText("Password: ");
        grid.add(labelPass, 47, 3);

        //button signup
         buttonUp = new Button("Sign up");
        buttonUp.setOnAction(  (EventHandler) event -> {
            try {
                // TODO: signup
            } catch (Exception e) {
               System.out.println("Register Player error: " + e.getMessage());
            }
        });
        grid.add(buttonUp, 45, 1);

        //button signin
        buttonIn.setOnAction(  (EventHandler) event -> {
            try {
                // TODO: signin
            } catch (Exception e) {
                System.out.println("Login Player error: " + e.getMessage());
            }
        });

        grid.add(buttonIn, 45, 2);

        // For debug purposes
        // Make de grid lines visible
        // grid.setGridLinesVisible(true);

        // Create the scene and add the grid pane
        Group root = new Group();
        Scene scene = new Scene(root, 1400, 750);
        root.getChildren().add(grid);


        targetArea = new Rectangle(BORDERSIZE,3*BORDERSIZE,AREAWIDTH,AREAHEIGHT);
        targetArea.setFill(Color.WHITE);
        root.getChildren().add(targetArea);

        // Create 10 x 10 squares for the target area
        squaresTargetArea = new Rectangle[NRSQUARESHORIZONTAL][NRSQUARESVERTICAL];
        for (int i = 0; i < NRSQUARESHORIZONTAL; i++) {
            for (int j = 0; j < NRSQUARESVERTICAL; j++) {
                double x = targetArea.getX() + i * (AREAWIDTH/NRSQUARESHORIZONTAL) + 2;
                double y = targetArea.getY() + j * (AREAHEIGHT/NRSQUARESVERTICAL) + 2;
                Rectangle rectangle = new Rectangle(x,y,SQUAREWIDTH,SQUAREHEIGHT);
                rectangle.setArcWidth(5);
                rectangle.setArcHeight(5);
                rectangle.setStroke(Color.BLACK);

                if(i % 2 ==0) {
                    if(j % 2 == 0) {
                        rectangle.setFill(Color.WHITE);
                    }
                    else{
                        rectangle.setFill(Color.BLACK);
                    }
                }

                else {
                    if(j % 2 == 0) {
                        rectangle.setFill(Color.BLACK);
                    }
                    else{
                        rectangle.setFill(Color.WHITE);
                    }
                }





                rectangle.setVisible(true);
                final int xpos = i;
                final int ypos = j;
                rectangle.addEventHandler(MouseEvent.MOUSE_PRESSED,
                        new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
//                                rectangleTargetAreaMousePressed(event,xpos,ypos);
                                //TODO: SET EVENT
                            }
                        });
                squaresTargetArea[i][j] = rectangle;
                root.getChildren().add(rectangle);
            }
        }

        stage.setTitle("Minichess");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
       //ClientLauncher.startClient(ActiveClientEndpoint.class);

    }
//    private void IntoRandomLobby(ActionEvent event) throws InvocationTargetException {
//
//        int pl = 0;
//
//        // get the amount of players and put them in the labels
//        playerIsConnected.setText("true");
//        playerAmount.setText(Integer.toString(pl));
//
//
//    }

    //method login
    private void LoginButtonEvent(ActionEvent event) throws InvocationTargetException {
        String loginUserText = userText.getText();
        String loginPassText = passText.getText();
        System.out.println(loginUserText + " " + loginPassText);
        RestClient client = new RestClient();
       Player player = new Player(loginUserText, loginPassText);
        PlayerDTO playerDTO = player.createDTO();

        System.out.println(player.getUsername() + " " + player.getPassword());

        if(client.loginPlayer(playerDTO)) {
            buttonIn.setDisable(true);
            buttonUp.setDisable(true);
          //  buttonEnterRandom.setDisable(false);

        }

        else {
            System.out.println("You are not logged in.");
        }




    }

    private void SignupButtonEvent(ActionEvent event) throws InvocationTargetException {
        String signupUserText = userText.getText();
        String signupPassText = passText.getText();
       Player player = new Player(signupUserText, signupPassText);
        PlayerDTO playerDTO = player.createDTO();
        RestClient client  = new RestClient();
        if(client.signupPlayer(playerDTO)) {
            System.out.println("You are now signed up");
        }

        else  {
            System.out.println("You are not signed up.");
        }

    }
}
