package com.kodilla.battleships;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class BattleShips extends Application implements EventHandler<ActionEvent> {
    private final Image imageback = new Image("file:src/main/resources/ships/s3.gif");
    private final Image rules = new Image("file:src/main/resources/ships/Zasady.png");
    private final Image five = new Image("file:src/main/resources/ships/5.png");
    private final Image fiveD = new Image("file:src/main/resources/ships/5d.png");
    private final Image four = new Image("file:src/main/resources/ships/4.png");
    private final Image fourD = new Image("file:src/main/resources/ships/4d.png");
    private final Image three = new Image("file:src/main/resources/ships/3.png");
    private final Image threeD = new Image("file:src/main/resources/ships/3d.png");
    private final Image two = new Image("file:src/main/resources/ships/2.png");
    private final Image twoD = new Image("file:src/main/resources/ships/2d.png");
    private final Image one = new Image("file:src/main/resources/ships/1.png");
    private final  Image oneD = new Image("file:src/main/resources/ships/1d.png");
    private final  Image computerWon = new Image("file:src/main/resources/ships/ComputerWon.png");
    private final  Image playerWon = new Image("file:src/main/resources/ships/PlayerWon.png");
    private final  Image easy = new Image("file:src/main/resources/ships/Easy.png");
    private final  Image normal = new Image("file:src/main/resources/ships/Normal.png");
    private final  Image high = new Image("file:src/main/resources/ships/High.png");
    private final  Image diffLevel = new Image("file:src/main/resources/ships/difficultLevel.png");
    private final Image resetImageButton = new Image("file:src/main/resources/ships/reset1.png");

    private BorderPane boards = new BorderPane();
    private final Label playerBoardLabel = new Label("Player board");
    private final Label computerBoardLabel = new Label("Computer board");
    private final Button startB = new Button("START");

    private final Button resetShipPlacement = new Button();
    private final Button end = new Button("END");
    private final Button info = new Button("Rules");
    private final Font font = Font.font("Courier New", FontWeight.BOLD, 20);

    private String gameStage;  //Stages "Start", "Deployment", "Game"
    public Set<Integer> listOfPlayerShipLocation = new HashSet<>();
    public Set<Integer> listOfComputerShipLocation = new HashSet<>();
    private Set<Integer> tempShipLocation = new HashSet<>();
    public List<Integer> playerPotentialShipLocationList = new ArrayList<>();
    public Set<Integer> listOfAllowedLocation = new HashSet<>();
    private String difficultyMode = "Normal"; // "Easy" , "Normal", "Hard"
    public boolean firstCell = true;
    private boolean firstShoot = false;
    private boolean GameOver = false;
    private boolean wasHit = false;
    public Fleet playerFleet = new Fleet("Player fleet");
    public Fleet computerFleet = new Fleet("Computer fleet");
    public List<Integer> listOfShipToDeploy = new ArrayList<>();
    private GameButton randomButton = new GameButton();
    public PotentialPlayerShipLocationList playerPotentialShipLocationClass = new PotentialPlayerShipLocationList();
    private Integer randomShoot;
    private List<Integer> listOfTargets = new ArrayList<>();
    private Set<Integer> targetLockedlist = new HashSet<>();
    private Set<Integer> hitCellsList = new HashSet<>();
    private int shipsBeforeShot;
    private int shipsAfterShot;
    private Set<Integer> tempToRemove = new HashSet<>();
    HashSet<Integer> computerGridHit = new HashSet<>();
    HashSet<Integer> computerGridMiss = new HashSet<>();
    HashSet<Integer> computerGridSink = new HashSet<>();
    HashSet<Integer> playerGridHit = new HashSet<>();
    HashSet<Integer> playerGridMiss = new HashSet<>();
    HashSet<Integer> playerGridSink = new HashSet<>();
    GameState saveGame = new GameState();
    private VerifyNeighbors verificator = new VerifyNeighbors();
    private GridPane playerGridBoard = new GridPane();
    private GridPane computerGridBoard = new GridPane();
    private BorderPane centrum = new BorderPane();
    GridPane playerGridBoardAndLabel = new GridPane();
    GridPane computerGridBoardAndLabel = new GridPane();
    GridPane shipsToDeploy = new GridPane();
    private ImageView view5 = new ImageView();
    private Button fiveCells = new Button();
    private Button fourCells = new Button();
    private Button threeCells = new Button();
    private Button twoCells = new Button();
    private Button oneCells = new Button();
    private Button easyMode = new Button("Easy");
    private Button normalMode = new Button("Normal");
    private Button highMode = new Button("High");
    private Button diffLevelButton = new Button();
    private Button reset = new Button("Reset");
    private Button save = new Button("Save");
    private Button Load = new Button("Load");
    FileChooser fileChooser = new FileChooser();
    private File file;
    private Desktop desktop = Desktop.getDesktop();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        BackgroundImage rulesImage = new BackgroundImage(rules, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background rulesBackground = new Background(rulesImage);

        Deployment deploymentSetup = new Deployment();
        deploymentSetup.createListOfShipTODeploy(listOfShipToDeploy);

        BorderPane grid = new BorderPane();
        gameStage = "Start";

        playerGridBoard.setHgap(5);
        playerGridBoard.setVgap(5);
        playerGridBoard.setPadding(new Insets(10, 20, 10, 20));
        playerGridBoard.setAlignment(Pos.CENTER);

        computerGridBoard.setHgap(5);
        computerGridBoard.setVgap(5);
        computerGridBoard.setPadding(new Insets(10, 20, 10, 10));
        computerGridBoard.setAlignment(Pos.CENTER);

        for (int i = 0; i<10; i++) {
            for (int j = 0; j < 10; j++) {
                GameButton bb = createNumberButton(i, j);
                computerGridBoard.add(bb, i, j, 1, 1);
                bb.setDisable(true);
            }
        }

        view5 = new ImageView(five);
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

        shipsToDeploy.add(fiveCells,2,0,1,1);
        shipsToDeploy.add(fourCells,2,1,1,1);
        shipsToDeploy.add(threeCells,2,2,1,1);
        shipsToDeploy.add(twoCells,2,3,1,1);
        shipsToDeploy.add(oneCells,2,4,1,1);

        grid.setCenter(centrum);
        end.setPrefHeight(100);
        end.setPrefWidth(100);
        end.setStyle("-fx-background-color: #FF4500;");
        end.setFont(font);
        end.setOnAction(e->closeProgram(primaryStage));
        primaryStage.setOnCloseRequest(e->closeProgram(primaryStage));

        startB.autosize();
        startB.setPrefHeight(100);
        startB.setStyle("-fx-background-color: #228B22;");
        startB.setFont(font);
        startB.setDisable(true);

        resetShipPlacement.autosize();
        resetShipPlacement.setPrefHeight(100);

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
        BorderPane topBoard = new BorderPane();
        topBoard.setPadding(new Insets(12, 12, 12, 12));
        topBoard.setStyle("-fx-background-color: #336699;");
        FlowPane topLeftPane = new FlowPane();
        topLeftPane.setHgap(40);
        topLeftPane.getChildren().add(startB);
        resetShipPlacement.setVisible(false);
        topLeftPane.getChildren().add(resetShipPlacement);
        BorderPane difficultLevel = new BorderPane();

        ImageView viewEasy = new ImageView(easy);
        viewEasy.setFitHeight(50);
        viewEasy.setPreserveRatio(true);
        easyMode.setGraphic(viewEasy);
        ImageView viewNormal = new ImageView(normal);
        viewNormal.setFitHeight(50);
        viewNormal.setPreserveRatio(true);
        normalMode.setGraphic(viewNormal);
        ImageView viewHigh = new ImageView(high);
        viewHigh.setFitHeight(50);
        viewHigh.setPreserveRatio(true);
        highMode.setGraphic(viewHigh);
        ImageView viewLvl = new ImageView(diffLevel);
        viewLvl.setFitHeight(80);
        viewLvl.setFitWidth(325);
        diffLevelButton.setGraphic(viewLvl);

        FlowPane flowDifficult = new FlowPane();
        flowDifficult.getChildren().add(easyMode);
        flowDifficult.getChildren().add(normalMode);
        flowDifficult.getChildren().add(highMode);
        flowDifficult.getChildren().add(diffLevelButton);

        easyMode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                difficultyMode = "Easy";
                easyMode.setDisable(true);
                normalMode.setDisable(false);
                highMode.setDisable(false);
                startB.setDisable(false);
            }
        });
        normalMode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                difficultyMode = "Normal";
                easyMode.setDisable(false);
                normalMode.setDisable(true);
                highMode.setDisable(false);
                startB.setDisable(false);
            }
        });

        highMode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                difficultyMode = "High";
                easyMode.setDisable(false);
                normalMode.setDisable(false);
                highMode.setDisable(true);
                startB.setDisable(false);
            }
        });

        Label difficultLabel = new Label("Difficulty level");
        difficultLabel.setFont(font);
        difficultLevel.setTop(difficultLabel);
        difficultLevel.setCenter(flowDifficult);
        topBoard.setRight(difficultLevel);
        playerBoardLabel.setFont(font);
        HBox hbox = new HBox(80);
        hbox.setPrefHeight(200);
        playerGridBoardAndLabel.add(playerBoardLabel,0,0,1,1);
        playerGridBoardAndLabel.add(hbox,0,1,10,10);
        playerGridBoardAndLabel.add(playerGridBoard,0,2,1,1);
        grid.setLeft(playerGridBoardAndLabel);

        computerBoardLabel.setFont(font);
        computerGridBoardAndLabel.add(computerBoardLabel,0,0,1,1);
        computerGridBoardAndLabel.add(computerGridBoard,0,1,1,1);
        grid.setRight(computerGridBoardAndLabel);

        centrum.setPadding(new Insets(30, 20, 20, 180));
        centrum.setCenter(shipsToDeploy);
        centrum.setVisible(false);

        playerGridBoardAndLabel.setVisible(false);
        computerGridBoardAndLabel.setVisible(false);

        for (int i = 0; i<10; i++) {
            for (int j = 0; j < 10; j++) {
                playerGridBoard.add(createNumberButton(i, j), i, j, 1, 1);
            }
        }
        createListToShoot();

        startB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                easyMode.setDisable(true);
                normalMode.setDisable(true);
                highMode.setDisable(true);
                playerGridBoardAndLabel.setVisible(true);
                computerGridBoardAndLabel.setVisible(true);
                resetShipPlacement.setVisible(true);
                gameStage = "Deployment";
                centrum.setVisible(true);
                if(difficultyMode.equals("Easy")) {
                    deploymentSetup.randomComputerDeployment(listOfComputerShipLocation, computerGridBoard, computerFleet, 1,
                            1, 2, 1, 0);
                } else if(difficultyMode.equals("High")) {
                    deploymentSetup.randomComputerDeployment(listOfComputerShipLocation, computerGridBoard, computerFleet, 1,
                            0, 1, 2, 1);
                } else {
                    deploymentSetup.randomComputerDeployment(listOfComputerShipLocation, computerGridBoard, computerFleet, 1,
                            1, 1, 1, 1);
                }
            }
        });
        topBoard.setLeft(topLeftPane);
        boards.setTop(topBoard);
        BorderPane bottomBoard = new BorderPane();
        bottomBoard.setStyle("-fx-background-color: #336699;");
        bottomBoard.setPadding(new Insets(12, 12, 12, 12));
        bottomBoard.setRight(end);

        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                reset();
            }
        });
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!GameOver) {

                    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text file", "*.txt"));
                    file=fileChooser.showOpenDialog(primaryStage);
                    if(file != null){
                        System.out.println(file.getName());
                    }
                    saveClass(file);
                }else{
                    System.out.println("Unable to save");
                }
            }
        });
        Load.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text file", "*.txt"));
                file=fileChooser.showOpenDialog(primaryStage);
                if(file != null){
                    System.out.println(file.getName());
                    loadClass(file);
                }

            }
        });

        FlowPane loadReset = new FlowPane();
        loadReset.getChildren().add(reset);
        loadReset.getChildren().add(save);
        loadReset.getChildren().add(Load);
        save.setDisable(true);

        bottomBoard.setLeft(loadReset);
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
                StackPane rulesLayout = new StackPane();
                rulesLayout.setBackground(rulesBackground);
                Scene ruleScene = new Scene(rulesLayout, 340, 600);
                Stage ruleStage = new Stage();
                ruleStage.setTitle("Rules for game");
                ruleStage.setScene(ruleScene);
                ruleStage.show();
            }
        });

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
        if (!GameOver){
            GameButton przycisk = (GameButton) event.getTarget();
        if (gameStage.equals("Deployment") && listOfShipToDeploy.size() != 0 || (tempShipLocation.size() == 0 && listOfShipToDeploy.size() > 0)) {
            if (!verificator.checkIfAnyNeighbors(przycisk.returnCellNumber(), playerGridBoard, listOfPlayerShipLocation)) {
                if (tempShipLocation.size() == 0 || verificator.checkIfAllowed(przycisk.returnCellNumber(), listOfAllowedLocation)) {
                    przycisk.setStyle("-fx-background-color: #00FF00;");
                    tempShipLocation.add(przycisk.returnCellNumber());
                    przycisk.setDisable(true);
                    verificator.createAllowedCellList(tempShipLocation, listOfAllowedLocation);
                    if (tempShipLocation.size() == listOfShipToDeploy.get(0)) {
                        Ships ship = new Ships(4, tempShipLocation, playerGridBoard, playerPotentialShipLocationClass);
                        addToFleet(playerFleet, ship, listOfPlayerShipLocation, tempShipLocation);
                        if (listOfShipToDeploy.size() == 5) {
                            view5 = new ImageView(fiveD);
                            view5.setFitHeight(60);
                            view5.setPreserveRatio(true);
                            fiveCells.setGraphic(view5);
                        } else if (listOfShipToDeploy.size() == 4) {
                            view5 = new ImageView(fourD);
                            view5.setFitHeight(60);
                            view5.setPreserveRatio(true);
                            fourCells.setGraphic(view5);
                        } else if (listOfShipToDeploy.size() == 3) {
                            view5 = new ImageView(threeD);
                            view5.setFitHeight(60);
                            view5.setPreserveRatio(true);
                            threeCells.setGraphic(view5);
                        } else if (listOfShipToDeploy.size() == 2) {
                            view5 = new ImageView(twoD);
                            view5.setFitHeight(60);
                            view5.setPreserveRatio(true);
                            twoCells.setGraphic(view5);
                        } else if (listOfShipToDeploy.size() == 1) {
                            view5 = new ImageView(oneD);
                            view5.setFitHeight(60);
                            view5.setPreserveRatio(true);
                            oneCells.setGraphic(view5);
                        }
                        listOfShipToDeploy.remove(0);
                        firstCell = true;
                        tempShipLocation.clear();
                        if (listOfShipToDeploy.size() == 0 && gameStage.equals("Deployment")) {
                            gameStage = "Game";
                            save.setDisable(false);
                            disableButtons(playerGridBoard);
                            resetShipPlacement.setDisable(true);
                            enableButtons(computerGridBoard);
                        }
                    }
                }
            }
        }
        if (gameStage.equals("Game")) {
            if (firstShoot) {
                boolean kolejnyStrzal = false;
                boolean againComputerShoot = false;
                przycisk.setDisable(true);
                for (Ships temp : computerFleet.fleetList) {
                    if (temp.checkIfHit(przycisk.returnCellNumber(), przycisk)) {
                        kolejnyStrzal = true;
                        if (computerFleet.checkIfFleetDestroyed()) {
                            System.out.println("Gratulacje wygrales");
                            playerWonShow();
                            GameOver = true;
                            break;
                        }
                        break;
                    } else {
                        kolejnyStrzal = false;
                    }
                }
                if (kolejnyStrzal) {
                } else {
                    do {
                        if(GameOver){break;}
                        playerPotentialShipLocationList.clear();
                        for (Integer newList : playerPotentialShipLocationClass.getPlayerPotentialShipLocation()) {
                            playerPotentialShipLocationList.add(newList);
                        }
                        Random rn = new Random();
                        if(targetLockedlist.isEmpty()) {
                            //calkowicie losowy strzal
                            randomShoot = playerPotentialShipLocationList.get(rn.nextInt(playerPotentialShipLocationList.size()));
                        } else {

                            listOfTargets.clear();
                            for(Integer copyInt: targetLockedlist){
                                listOfTargets.add(copyInt);
                            }
                            randomShoot = listOfTargets.get(rn.nextInt(listOfTargets.size()));
                            targetLockedlist.remove(randomShoot);
                        }
                        playerPotentialShipLocationClass.remove(randomShoot);
                        try {
                            randomButton = getNodeByRowColumnIndex(verificator.getRowLocation(randomShoot), verificator.getColumnLocation(randomShoot), playerGridBoard);
                        } catch (Exception e) {
                            System.out.println("There is no ship to shoot at");
                        }
                        shipsBeforeShot = playerFleet.fleetList.size();
                        for (Ships temp : playerFleet.fleetList) {
                            if (temp.checkIfHit(randomButton.returnCellNumber(), randomButton)) {
                                againComputerShoot = true;
                                wasHit = true;
                                if (playerFleet.checkIfFleetDestroyed()) {
                                    System.out.println("Niestety przegrales");
                                    GameOver = true;
                                    computerWonShow();
                                    break;
                                }
                                break;
                            } else {
                                if (!GameOver) {
                                    againComputerShoot = false;
                                }
                                wasHit = false;
                            }
                        }
                            shipsAfterShot = playerFleet.fleetList.size();
                            if (!(shipsBeforeShot == shipsAfterShot)) {
                                //trafiony zatopiony
                                System.out.println("Trafiony Zatopiony.");
                                hitCellsList.clear();
                                targetLockedlist.clear();
                            } else if(wasHit){
                                wasHit = false;
                                hitCellsList.add(randomShoot);
                                verificator.createAllowedCellList(hitCellsList, targetLockedlist);
                                for(Integer tempToValidate: targetLockedlist) {
                                    if(!(playerPotentialShipLocationClass.getPlayerPotentialShipLocation().contains(tempToValidate))){
                                        tempToRemove.add(tempToValidate);
                                    }
                                }
                                targetLockedlist.removeAll(tempToRemove);
                            }
                    } while (againComputerShoot);
                }
            } else {
                firstShoot = true;
            }
        }
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
    private void computerWonShow(){
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(computerWon, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        StackPane rulesLayout = new StackPane();
        Background rulesBackground = new Background(backgroundImage);
        rulesLayout.setBackground(rulesBackground);
        Scene ruleScene = new Scene(rulesLayout, 800, 400);
        Stage ruleStage = new Stage();
        ruleStage.setTitle("Computer WON");
        ruleStage.setScene(ruleScene);
        ruleStage.show();
    }

    private void playerWonShow(){
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(playerWon, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        StackPane rulesLayout = new StackPane();
        Background rulesBackground = new Background(backgroundImage);
        rulesLayout.setBackground(rulesBackground);
        Scene ruleScene = new Scene(rulesLayout, 800, 400);
        Stage ruleStage = new Stage();
        ruleStage.setTitle("Player WON");
        ruleStage.setScene(ruleScene);
        ruleStage.show();
    }

    public void saveClass(File file){
        computerGridHit.clear();
        computerGridMiss.clear();
        computerGridSink.clear();
        playerGridHit.clear();
        playerGridMiss.clear();
        playerGridSink.clear();
        GameButton playerButton;
        GameButton computerButton;
        for(int i=0;i<10;i++){
            for(int j=0; j<10; j++){
                playerButton = getNodeByRowColumnIndex(i, j, playerGridBoard);
                computerButton =getNodeByRowColumnIndex(i, j, computerGridBoard);
                if(playerButton.getStyle().equals("-fx-background-color: #000000;")){
                    playerGridMiss.add(playerButton.returnCellNumber());
                } else if (playerButton.getStyle().equals("-fx-background-color: #FFD700;")){
                    playerGridHit.add(playerButton.returnCellNumber());
                } else if (playerButton.getStyle().equals("-fx-background-color: #FF0000;")){
                    playerGridSink.add(playerButton.returnCellNumber());
                }
                if(computerButton.getStyle().equals("-fx-background-color: #000000;")){
                    computerGridMiss.add(computerButton.returnCellNumber());
                } else if (computerButton.getStyle().equals("-fx-background-color: #FFD700;")){
                    computerGridHit.add(computerButton.returnCellNumber());
                } else if (computerButton.getStyle().equals("-fx-background-color: #FF0000;")){
                    computerGridSink.add(computerButton.returnCellNumber());
                }
            }
        }
        saveGame.GameStateSave(computerFleet, playerFleet, computerGridMiss, computerGridHit, computerGridSink, playerGridMiss,  playerGridHit, playerGridSink, gameStage, listOfPlayerShipLocation, listOfComputerShipLocation, firstShoot, playerPotentialShipLocationClass, targetLockedlist, hitCellsList, shipsBeforeShot, shipsAfterShot);

        try {

            FileOutputStream f = new FileOutputStream(file);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(saveGame);
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("" + e.getStackTrace());
            System.out.println(""+ e.getMessage());
            System.out.println("File not found");
            return;
        } catch (IOException e) {
            System.out.println("Error initializing stream");
            System.out.println(""+ e.getMessage());
            return;
        } catch (NullPointerException e){
            System.out.println("No file selected");
            return;
        }
    }

    public void loadClass(File fileLoad){

        try {
            FileInputStream fi = new FileInputStream(fileLoad);
            ObjectInputStream oi = new ObjectInputStream(fi);
            saveGame = (GameState)oi.readObject();
            oi.close();
            fi.close();

        } catch (FileNotFoundException e) {
            System.out.println("" + e.getStackTrace());
            System.out.println(""+ e.getMessage());
            System.out.println("File not found");
            return;
        } catch (IOException e) {
            System.out.println("Error initializing stream");
            System.out.println(""+ e.getMessage());
            return;
        }catch (ClassNotFoundException e) {
            System.out.println("Error initializing stream");
            System.out.println(""+ e.getMessage());
            return;
        }

        reset();
        playerFleet = saveGame.getPlayerFleet();
        computerFleet = saveGame.getComputerFleet();
        for(Ships temp: playerFleet.fleetList) {
            temp.setGridPaneAssigned(playerGridBoard);
        }
        for(Ships temp: computerFleet.fleetList) {
            temp.setGridPaneAssigned(computerGridBoard);
        }
        easyMode.setDisable(true);
        normalMode.setDisable(true);
        highMode.setDisable(true);
        playerGridBoardAndLabel.setVisible(true);
        computerGridBoardAndLabel.setVisible(true);
        disableButtons(playerGridBoard);
        enableButtons(computerGridBoard);
        gameStage = saveGame.getGameStage();
        listOfPlayerShipLocation = saveGame.getListOfPlayerShipLocation();
        listOfComputerShipLocation = saveGame.getListOfComputerShipLocation();
        firstShoot = saveGame.isFirstShoot() ;
        playerPotentialShipLocationClass = saveGame.getPlayerPotentialShipLocationClass();
        targetLockedlist = saveGame.getTargetLockedlist();
        hitCellsList = saveGame.getHitCellsList();
        shipsBeforeShot = saveGame.getShipsBeforeShot();
        shipsAfterShot = saveGame.getShipsAfterShot();
        view5 = new ImageView(fiveD);
        view5.setFitHeight(60);
        view5.setPreserveRatio(true);
        fiveCells.setStyle("-fx-background-color: #336699;");
        fiveCells.setGraphic(view5);

        view5 = new ImageView(fourD);
        view5.setFitHeight(60);
        view5.setPreserveRatio(true);
        fourCells.setStyle("-fx-background-color: #336699;");
        fourCells.setGraphic(view5);

        view5 = new ImageView(threeD);
        view5.setFitHeight(60);
        view5.setPreserveRatio(true);
        threeCells.setStyle("-fx-background-color: #336699;");
        threeCells.setGraphic(view5);

        view5 = new ImageView(twoD);
        view5.setFitHeight(60);
        view5.setPreserveRatio(true);
        twoCells.setStyle("-fx-background-color: #336699;");
        twoCells.setGraphic(view5);

        view5 = new ImageView(oneD);
        view5.setFitHeight(60);
        view5.setPreserveRatio(true);
        oneCells.setStyle("-fx-background-color: #336699;");
        oneCells.setGraphic(view5);

        centrum.setVisible(true);
        GameButton tempButton = new GameButton();
        for(Ships temp: playerFleet.fleetList) {
            for(Map.Entry<Integer, Integer> entry: temp.shipLocation.entrySet()){
                try {
                    tempButton = verificator.getNodeByRowColumnIndex(verificator.getRowLocation(entry.getKey()), verificator.getColumnLocation(entry.getKey()), playerGridBoard);
                } catch (Exception e) {
                    System.out.println("Problem with ship cells colors during load");
                }
                tempButton.setStyle("-fx-background-color: #00FF00;");
            }
        }
        for(Integer tempPlayer: saveGame.getPlayerGridHit()){
            try {
                tempButton = verificator.getNodeByRowColumnIndex(verificator.getRowLocation(tempPlayer), verificator.getColumnLocation(tempPlayer), playerGridBoard);
                tempButton.setStyle("-fx-background-color: #FFD700;");
            } catch (Exception e) {
                System.out.println("Problem with colors for player Hit load");
            }
        }
        for(Integer tempPlayer: saveGame.getPlayerGridMiss()){
            try {
                tempButton = verificator.getNodeByRowColumnIndex(verificator.getRowLocation(tempPlayer), verificator.getColumnLocation(tempPlayer), playerGridBoard);
                tempButton.setStyle("-fx-background-color: #000000;");
            } catch (Exception e) {
                System.out.println("Problem with colors for player Miss load");
            }
        }
        for(Integer tempPlayer: saveGame.getPlayerGridSink()){
            try {
                tempButton = verificator.getNodeByRowColumnIndex(verificator.getRowLocation(tempPlayer), verificator.getColumnLocation(tempPlayer), playerGridBoard);
                tempButton.setStyle("-fx-background-color: #FF0000;");
            } catch (Exception e) {
                System.out.println("Problem with colors for player Sink load");
            }
        }
        for(Integer tempPlayer: saveGame.getComputerGridHit()){
            try {
                tempButton = verificator.getNodeByRowColumnIndex(verificator.getRowLocation(tempPlayer), verificator.getColumnLocation(tempPlayer), computerGridBoard);
                tempButton.setStyle("-fx-background-color: #FFD700;");
                tempButton.setDisable(true);
            } catch (Exception e) {
                System.out.println("Problem with colors for Computer Hit load");
            }
        }
        for(Integer tempPlayer: saveGame.getComputerGridMiss()){
            try {
                tempButton = verificator.getNodeByRowColumnIndex(verificator.getRowLocation(tempPlayer), verificator.getColumnLocation(tempPlayer), computerGridBoard);
                tempButton.setStyle("-fx-background-color: #000000;");
                tempButton.setDisable(true);
            } catch (Exception e) {
                System.out.println("Problem with colors for Computer Miss load");
            }
        }
        for(Integer tempPlayer: saveGame.getComputerGridSink()){
            try {
                tempButton = verificator.getNodeByRowColumnIndex(verificator.getRowLocation(tempPlayer), verificator.getColumnLocation(tempPlayer), computerGridBoard);
                tempButton.setStyle("-fx-background-color: #FF0000;");
                tempButton.setDisable(true);
            } catch (Exception e) {
                System.out.println("Problem with colors for Computer Sink load");
            }

        }
        save.setDisable(false);


    }
    public void reset(){
        playerFleet.fleetList.clear();
        computerFleet.fleetList.clear();
        easyMode.setDisable(false);
        normalMode.setDisable(false);
        highMode.setDisable(false);
        resetShipPlacement.setVisible(false);
        startB.setDisable(true);
        save.setDisable(true);
        view5 = new ImageView(five);
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
        centrum.setVisible(false);

        for (int i=0; i<10; i++) {
            for(int j = 0; j<10;j++) {
                verificator.getNodeByRowColumnIndex(i, j, playerGridBoard).setStyle("");
            }
        }
        for (int i=0; i<10; i++) {
            for(int j = 0; j<10;j++) {
                verificator.getNodeByRowColumnIndex(i, j, computerGridBoard).setStyle(""); //"-fx-background-color: #D3D3D3;");
            }
        }
        disableButtons(computerGridBoard);
        enableButtons(playerGridBoard);
        gameStage = "Deployment";
        Deployment deploymentSetup = new Deployment();
        listOfShipToDeploy.clear();
        deploymentSetup.createListOfShipTODeploy(listOfShipToDeploy);
        listOfPlayerShipLocation.clear();
        listOfComputerShipLocation.clear();
        tempShipLocation.clear();
        GameOver = false;
        resetShipPlacement.setVisible(false);
        playerGridBoardAndLabel.setVisible(false);
        computerGridBoardAndLabel.setVisible(false);
        firstShoot = false;
        listOfAllowedLocation.clear();
        hitCellsList.clear();
        targetLockedlist.clear();
        playerPotentialShipLocationClass.clear();
        tempToRemove.clear();
        wasHit = false;
        createListToShoot();
    }
public void createListToShoot(){
    for (int i = 0; i<10; i++) {
        for (int j = 0; j < 10; j++) {
            playerPotentialShipLocationClass.addPlayerPotentialShipLocation(i*10+j);
        }
    }
}
}