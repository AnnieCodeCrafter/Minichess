package Starts;

import javafx.application.Application;
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

public class ClientUI extends Application {

    private Rectangle testSquare;

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


    @Override
    public void start(Stage stage) throws Exception {

        GridPane grid;
        grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(25, 25, 25, 25));


        //TODO: im just puttin this here to see what happens.




        //textfield username
       TextField textUsername = new TextField();
        grid.add(textUsername, 47, 2);

        //label username
        Label labelUsername = new Label();
        labelUsername.setText("Username: ");
        grid.add(labelUsername, 47,1);

        //textfield password
        TextField textPass = new TextField();
        grid.add(textPass, 47, 4);

        //label password
        Label labelPass = new Label();
        labelPass.setText("Password: ");
        grid.add(labelPass, 47, 3);

        //button signup
        Button buttonUp = new Button("Sign up");
        buttonUp.setOnAction(  (EventHandler) event -> {
            try {
                // TODO: signup
            } catch (Exception e) {
               System.out.println("Register Player error: " + e.getMessage());
            }
        });
        grid.add(buttonUp, 45, 1);

        //button signin
        Button buttonIn = new Button("Sign in");
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

    }

}
