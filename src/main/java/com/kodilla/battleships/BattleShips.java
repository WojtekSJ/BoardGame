package com.kodilla.battleships;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class BattleShips extends Application implements EventHandler<ActionEvent> {


    private Image imageback = new Image("file:src/main/resources/ships/s3.gif");
    private Image rules = new Image("file:src/main/resources/ships/Zasady.png");
    private Image playerBoard = new Image("file:src/main/resources/ships/PlayerShips.gif");
    private Image computerBoard = new Image("file:src/main/resources/ships/ComputerShips.gif");
    private Image five = new Image("file:src/main/resources/ships/5.png");
    private Image fiveD = new Image("file:src/main/resources/ships/5d.png");
    private Image four = new Image("file:src/main/resources/ships/4.png");
    private Image fourD = new Image("file:src/main/resources/ships/4d.png");
    private Image three = new Image("file:src/main/resources/ships/3.png");
    private Image threeD = new Image("file:src/main/resources/ships/3d.png");
    private Image two = new Image("file:src/main/resources/ships/2.png");
    private Image twoD = new Image("file:src/main/resources/ships/2d.png");
    private Image one = new Image("file:src/main/resources/ships/1.png");
    private Image oneD = new Image("file:src/main/resources/ships/1d.png");


    private Image s1 = new Image("file:src/main/resources/ships/s1.gif");
    private BorderPane boards = new BorderPane();
    private Label playerBoardLabel = new Label("Players board");
    private Label computerBoardLabel = new Label("Computer board");
    private Button startB = new Button("START");

    private Button resetShipPlacement = new Button();
    private Button end = new Button("END");
    private Button info = new Button("Rules");
    private Font font = Font.font("Courier New", FontWeight.BOLD, 20);

    private String gameStage;  //Stages "Start", "Deployment", "Game"
    private Button ok = new Button();
    private Set<Integer> listOfPlayerShipLocation = new HashSet<>();
    private Set<Integer> listOfComputerShipLocation = new HashSet<>();
    private Set<Integer> tempShipLocation = new HashSet<>();
    public Set<Integer> listOfAllowedLocation = new HashSet<>();
    private boolean firstCell = true;
    private Fleet playerFleet = new Fleet("Player fleet");
    private Fleet computerFleet = new Fleet("Computer fleet");
    public List<Integer> listOfShipToDeploy = new ArrayList<>();

    private VerifyNeighbors verificator = new VerifyNeighbors();
    private GridPane playerGridBoard = new GridPane();
    private GridPane computerGridBoard = new GridPane();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        BackgroundImage rulesImage = new BackgroundImage(rules, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background rulesBackground = new Background(rulesImage);


        Deployment deploymentSetup = new Deployment();
        deploymentSetup.createListOfShipTODeploy(listOfShipToDeploy);



        BorderPane grid = new BorderPane();
        //GridPane grid = new GridPane();
        gameStage = "Start";


        GridPane playerGrid = new GridPane();
        GridPane computerGrid = new GridPane();

        ImageView img1 = new ImageView(playerBoard);
        ImageView img2 = new ImageView(computerBoard);



        playerGridBoard.setHgap(5);
        playerGridBoard.setVgap(5);
        playerGridBoard.setAlignment(Pos.CENTER);
        //Button[] button = new Button[100];
        for (int i = 0; i<10; i++) {
            for (int j = 0; j < 10; j++) {

                //Button button = createNumberButton(i, j);

                playerGridBoard.add(createNumberButton(i, j), i, j, 1, 1);
                 //playerGridBoard.add(new Button(i + "," +j), i, j, 1, 1);
            }
        }


        computerGridBoard.setHgap(5);
        computerGridBoard.setVgap(5);
        computerGridBoard.setAlignment(Pos.CENTER);
        for (int i = 0; i<10; i++) {
            for (int j = 0; j < 10; j++) {

                /*Cell cc = new Cell(i, j);
                cc.setOnMouseClicked(event -> System.out.println("Clicked Cell"));
                computerGridBoard.add(cc, i, j, 1, 1);*/


                GameButton bb = createNumberButton(i, j);
                computerGridBoard.add(bb, i, j, 1, 1);
                bb.setDisable(true);
            }
        }





        GameButton bb1 = new GameButton("Cos");
        Button bb2 = new Button("2");
        Button bb3 = new Button("3");
        Button bb4 = new Button("4");
        bb1.setOnAction(this);
        bb2.setOnAction(this);
        bb3.setOnAction(this);
        bb4.setOnAction(this);







        //grid.setCenter(playerGrid);
        Image okImageButton = new Image("file:src/main/resources/ships/ok.png");
        ok.setPrefHeight(100);
        ok.setPrefWidth(100);
        ok.setStyle("-fx-background-color: #336699;");
        ImageView view = new ImageView(okImageButton);
        view.setFitHeight(80);
        view.setPreserveRatio(true);
        ok.setGraphic(view);
        BorderPane centrum = new BorderPane();
        ok.setVisible(false);
        GridPane shipsToDeploy = new GridPane();
        Button fiveCells = new Button();
        Button fourCells = new Button();
        Button threeCells = new Button();
        Button twoCells = new Button();
        Button oneCells = new Button();



        ImageView view5 = new ImageView(five);
        view5.setFitHeight(60);
        view5.setPreserveRatio(true);
        fiveCells.setStyle("-fx-background-color: #336699;");
        fiveCells.setGraphic(view5);

        view5 = new ImageView(four);
        view5.setFitHeight(60);
        view5.setPreserveRatio(true);
        fourCells.setStyle("-fx-background-color: #336699;");
        fourCells.setGraphic(view5);

        view5 = new ImageView(three);
        view5.setFitHeight(60);
        view5.setPreserveRatio(true);
        threeCells.setStyle("-fx-background-color: #336699;");
        threeCells.setGraphic(view5);

        view5 = new ImageView(two);
        view5.setFitHeight(60);
        view5.setPreserveRatio(true);
        twoCells.setStyle("-fx-background-color: #336699;");
        twoCells.setGraphic(view5);

        view5 = new ImageView(one);
        view5.setFitHeight(60);
        view5.setPreserveRatio(true);
        oneCells.setStyle("-fx-background-color: #336699;");
        oneCells.setGraphic(view5);


        shipsToDeploy.add(fiveCells,3,0,1,1);
        shipsToDeploy.add(fourCells,3,1,1,1);
        shipsToDeploy.add(threeCells,3,2,1,1);
        shipsToDeploy.add(twoCells,3,3,1,1);
        shipsToDeploy.add(oneCells,3,4,1,1);
        //centrum.setCenter(ok);

        /*centrum.add(bb2, 1, 0,1,1);
        centrum.add(bb3,2,0,1,1);
        centrum.add(bb4, 3,0,1,1);*/
        ok.setOnAction(new EventHandler<ActionEvent>() {
                           @Override
                           public void handle(ActionEvent event) {
                               GameButton temp = getNodeByRowColumnIndex(0, 0, computerGridBoard);
                               temp.setDisable(false);
                           }
                       });


        grid.setCenter(centrum);


            end.setPrefHeight(100);
            end.setPrefWidth(100);
            end.setStyle("-fx-background-color: #FF4500;");

            end.setFont(font);
            end.setOnAction(e->closeProgram(primaryStage));
            primaryStage.setOnCloseRequest(e->closeProgram(primaryStage));

            //end.setStyle("-fx-font-weight: BOLD");
        startB.autosize();
        startB.setPrefHeight(100);
        //start.setPrefWidth(100);
        startB.setStyle("-fx-background-color: #228B22;");
        startB.setFont(font);

        resetShipPlacement.autosize();
        resetShipPlacement.setPrefHeight(100);

        Image resetImageButton = new Image("file:src/main/resources/ships/reset1.png");
        ImageView viewReset = new ImageView(resetImageButton);
        viewReset.setFitHeight(80);
        viewReset.setPreserveRatio(true);
        resetShipPlacement.setGraphic(viewReset);
        resetShipPlacement.setStyle("-fx-background-color: #336699;");
        resetShipPlacement.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int column = 0;
                int row = 0;
                for(Integer temp: tempShipLocation){

                    try {
                        column = verificator.getColumnLocation(temp);
                    } catch (Exception e) {
                        System.out.println("There is no ship to set-up restart");
                    }
                    try {
                    row = verificator.getRowLocation(temp);
                    } catch (Exception e) {
                        System.out.println("There is no ship to set-up restart");
                    }

                    getNodeByRowColumnIndex(row,column,playerGridBoard).setDisable(false);
                    getNodeByRowColumnIndex(row,column,playerGridBoard).setStyle(null);
                }

                tempShipLocation.clear();
                listOfAllowedLocation.clear();
            }
        });






        info.setPrefHeight(100);
        //info.setPrefWidth(100);

        BorderPane topBoard = new BorderPane();
        topBoard.setPadding(new Insets(12, 12, 12, 12));
        topBoard.setStyle("-fx-background-color: #336699;");
        //hbox.getChildren().add(img3);
        //hbox.getChildren().add(img4);
        //hbox.getChildren().add(b1);
        FlowPane topLeftPane = new FlowPane();
        topLeftPane.setHgap(40);
        topLeftPane.getChildren().add(startB);
        resetShipPlacement.setVisible(false);
        topLeftPane.getChildren().add(resetShipPlacement);

        startB.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                grid.setLeft(playerGridBoard);
                grid.setRight(computerGridBoard);
                resetShipPlacement.setVisible(true);
                gameStage = "Deployment";
                ok.setVisible(true);
                centrum.setPadding(new Insets(80, 20, 20, 200));
                centrum.setCenter(shipsToDeploy);


            }
        });

        topBoard.setLeft(topLeftPane);
        boards.setTop(topBoard);

        /*HBox hbox2 = new HBox();
        hbox2.setPadding(new Insets(15, 12, 100, 12));
        hbox2.setSpacing(100);
        hbox2.setStyle("-fx-background-color: #336699;");
        boards.setBottom(hbox2);*/

        BorderPane bottomBoard = new BorderPane();
        bottomBoard.setStyle("-fx-background-color: #336699;");
        bottomBoard.setPadding(new Insets(12, 12, 12, 12));
        bottomBoard.setRight(end);
        boards.setBottom(bottomBoard);

        VBox vbox1 = new VBox();
        vbox1.setPadding(new Insets(15, 120, 100, 12));
        vbox1.setSpacing(100);
        vbox1.setStyle("-fx-background-color: #336699;");
        boards.setLeft(vbox1);


        BorderPane rightBoard = new BorderPane();
        rightBoard.setStyle("-fx-background-color: #336699;");
        rightBoard.setPadding(new Insets(12, 12, 12, 12));
        info.setFont(font);
        info.setStyle("-fx-background-color: #228B22;");
        info.autosize();
        rightBoard.setTop(info);
        boards.setRight(rightBoard);
        info.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Akcja Info");
                Label infoLabel = new Label("Rules content \n cos jeszcze");
                StackPane rulesLayout = new StackPane();
                //rulesLayout.getChildren().add(infoLabel);
                rulesLayout.setBackground(rulesBackground);
                Scene ruleScene = new Scene(rulesLayout, 225, 400);
                Stage ruleStage = new Stage();
                ruleStage.setTitle("Rules for game");
                ruleStage.setScene(ruleScene);
                ruleStage.show();

            }
        });

        /*VBox vbox2 = new VBox();
        vbox2.setPadding(new Insets(15, 120, 100, 12));
        vbox2.setSpacing(100);
        vbox2.setStyle("-fx-background-color: #336699;");
        boards.setRight(vbox2);*/



        grid.setBackground(background);

        boards.setCenter(grid);
        Scene scene = new Scene(boards, 1300, 700, Color.BLACK);

        primaryStage.setTitle("Game of Battle Ships");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void closeProgram(Stage primaryStage) {
          System.out.println("Zamykanie");
        primaryStage.close();
    }


    @Override
    public void handle(ActionEvent event) {
    //System.out.println(event.getTarget());
    GameButton przycisk = (GameButton)event.getTarget();

        if(gameStage.equals("Deployment")&&listOfShipToDeploy.size()!=0||(tempShipLocation.size()==0&&listOfShipToDeploy.size()>0)) {

            if (!verificator.checkIfAnyNeighbors(przycisk.returnCellNumber(), playerGridBoard, listOfPlayerShipLocation)) {
                if (tempShipLocation.size() == 0 || verificator.checkIfAllowed(przycisk.returnCellNumber(), listOfAllowedLocation)) {
                    //System.out.println(listOfShipToDeploy);
                    //System.out.println(listOfShipToDeploy.size());
                    przycisk.setStyle("-fx-background-color: #FF0000;");
                    tempShipLocation.add(przycisk.returnCellNumber());
                    przycisk.setDisable(true);
                    verificator.createAllowedCellList(tempShipLocation, listOfAllowedLocation);


                    if (tempShipLocation.size() == listOfShipToDeploy.get(0)) {

                        Ships ship = new Ships(4, tempShipLocation);
               /* for(Integer temp: tempShipLocation){
                    System.out.println(temp);
                }*/
                        //System.out.println("Lista localizacji przed dodaniem: " + listOfPlayerShipLocation);
                        // System.out.println("Ship name: " + ship.name);
                        //System.out.println("Ship health: " + ship.health);
                        //System.out.println("Ship location: " + ship.shipLocation);
                        //System.out.println("Ship czy zniszczony?: " + ship.getDestroyed());
                        addToFleet(playerFleet, ship, listOfPlayerShipLocation, tempShipLocation);
                        //System.out.println("Lista localizacji po dodaniu: " + listOfPlayerShipLocation);
                        //System.out.println(playerFleet.fleetList.size());
                        listOfShipToDeploy.remove(0);
                        firstCell = true;
                        tempShipLocation.clear();

                        if(listOfShipToDeploy.size()==0&&gameStage.equals("Deployment")) {
                            gameStage="Game";
                            disableButtons(playerGridBoard);
                            resetShipPlacement.setDisable(true);
                        }
                    }
                }
            }else {
                System.out.println(listOfPlayerShipLocation);
                System.out.println(przycisk.returnCellNumber());
                System.out.println("Jest sasiad tu nie mozesz rozstawic.");

            }
        }
        if(gameStage.equals("Game")) {
            System.out.println("Gramy :) !!!");
        }


    }
    private void addToFleet(Fleet fleet, Ships ship, Set<Integer> listOfShipLocation, Set<Integer> tempShipLocation){
        fleet.addToFleet(ship);
        for(Integer temp: tempShipLocation){
            listOfShipLocation.add(temp);
        }


    }
    private GameButton createNumberButton(int i, int j) {
        GameButton button = new GameButton(Integer.toString(i) + Integer.toString(j));
        button.setOnAction(this);
        return button ;
    }

    public GameButton getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        GameButton result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = (GameButton)node;
                break;
            }
        }

        return result;
    }

    public int getInt(String test){
        try{
            return Integer.parseInt(test.trim());
        }catch(Exception e){
            return -5;
        }
    }

    public void disableButtons(GridPane matrix){
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                GameButton temp = getNodeByRowColumnIndex(i, j, matrix);
                temp.setDisable(true);
            }
        }
    }

    public void enableButtons(GridPane matrix){
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                GameButton temp = getNodeByRowColumnIndex(i, j, matrix);
                temp.setDisable(false);
            }
        }
    }
}










/*

package com.kodilla.blackjack;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.Color;

public class BlackJack extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Group root1 = new Group();
        Scene scene = new Scene(root, 300, 300, Paint.valueOf("#77C2BB"));
       // Scene test = new Scene(root1, Paint.valueOf("#77C2BB"));

        Rectangle r = new Rectangle(25,25,250,250);

        r.setFill(Paint.valueOf("#4285f4"));

        root.getChildren().add(r);
        primaryStage.setTitle("BlackJack");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
*/
