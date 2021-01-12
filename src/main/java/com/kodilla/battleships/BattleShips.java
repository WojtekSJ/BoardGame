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

public class BattleShips extends Application implements EventHandler<ActionEvent> {


    private Image imageback = new Image("file:src/main/resources/ships/s3.gif");
    private Image rules = new Image("file:src/main/resources/ships/Zasady.png");
    private Image playerBoard = new Image("file:src/main/resources/ships/PlayerShips.gif");
    private Image computerBoard = new Image("file:src/main/resources/ships/ComputerShips.gif");

    private Image s1 = new Image("file:src/main/resources/ships/s1.gif");
    private BorderPane boards = new BorderPane();
    private Label playerBoardLabel = new Label("Players board");
    private Label computerBoardLabel = new Label("Computer board");
    private Button startB = new Button("START");

    private Button resetShipPlacement = new Button();
    private Button end = new Button("END");
    private Button info = new Button("Rules");
    private Font font = Font.font("Courier New", FontWeight.BOLD, 20);
    private GridPane computerGridBoard = new GridPane();
   private String gameStage;  //Stages "Start", "Deployment", "Game"
    private Button ok = new Button();

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

        BorderPane grid = new BorderPane();
        //GridPane grid = new GridPane();
        gameStage = "Start";


        GridPane playerGrid = new GridPane();
        GridPane computerGrid = new GridPane();

        ImageView img1 = new ImageView(playerBoard);
        ImageView img2 = new ImageView(computerBoard);


        GridPane playerGridBoard = new GridPane();
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


               Button bb = createNumberButton(i, j);
                computerGridBoard.add(bb, i, j, 1, 1);
                bb.setDisable(true);
            }
        }





        Button bb1 = new Button("1");
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
        centrum.setCenter(ok);
        /*centrum.add(bb2, 1, 0,1,1);
        centrum.add(bb3,2,0,1,1);
        centrum.add(bb4, 3,0,1,1);*/

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
    System.out.println(event.getTarget());
    Button przycisk = (Button)event.getTarget();
        if(!przycisk.equals(startB)) {
            System.out.println(przycisk.getAlignment());
            System.out.println(przycisk.getText());
            przycisk.setStyle("-fx-background-color: #FF0000;");
            przycisk.setDisable(true);
        }
    }

    private Button createNumberButton(int i, int j) {
        Button button = new Button(Integer.toString(i) + Integer.toString(j));
        button.setOnAction(this);
        return button ;
    }

    public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                result = node;
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
