package editor;

/*
    Project title: Lazer Cats vs Zombies Map Editor
    -----------------------------------------------
    ClassName: Builder Controller
    -----------------------------------------------
    What it does: Controller for the UI.fxml
    -----------------------------------------------
    For: ICS4U1 - Holik
    -----------------------------------------------
    By: Ethan and Allen
    -----------------------------------------------
    Last Edited: June 18th 2019
 */

import engine.Rect;
import engine.input.KeyboardInput;
import engine.mapping.Tile;
import engine.math.Vector2;
import images.TextureClassifier.BackgroundTiles;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

/** Controller for the Map Builder
 *
 */
public class BuilderController {

    //mouse x and y
    private static double mouseX;
    private static double mouseY;

    @FXML
    ListView tileList;

    @FXML
    Canvas canvas;

    @FXML
    TextField fileName;

    @FXML
    TextField mapHeight;

    @FXML
    TextField mapWidth;

    @FXML
    TextField tileHeight;

    @FXML
    TextField tileWidth;

    @FXML
    MenuItem importFiles;

    @FXML
    MenuItem changeSavePath;

    private EditorMap eMap;


    /** Graphics context for the Canvas
     *
     */
    public static GraphicsContext gc;

    /** Camera Position
     *
     */
    public static Vector2 cameraPos;

    /** Camera Position
     *
     */
    public static KeyboardInput keyboardInput;

    //save path
    private String savePath;

    //selected tile
    private BackgroundTiles selected;

    /**
     *  Initializes Controller
     */
    @FXML
    public void initialize(){

        //Transfers the list of background enums to the tile list
        tileList.getItems().addAll(BackgroundTiles.values());

        //updates mouse x and y based on movement
        canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuilderController.mouseX = event.getX();
                BuilderController.mouseY = event.getY();
            }
        });

        //updates mouse x and y based on movement and changes tiles
        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuilderController.mouseX = event.getX();
                BuilderController.mouseY = event.getY();

                //gets index of clicked tile
                int[] tileIndex = eMap.getClickedTile(mouseX + cameraPos.x, mouseY + cameraPos.y);

                try {
                    if (tileIndex != null)
                        //sets tile
                        eMap.setTile(selected, tileIndex[0], tileIndex[1]);
                } catch (Exception e){

                }
            }
        });

        //sets tile when mouse clicked
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuilderController.mouseX = event.getX();
                BuilderController.mouseY = event.getY();

                //gets index of clicked tile
                int[] tileIndex = eMap.getClickedTile(mouseX + cameraPos.x, mouseY + cameraPos.y);

                try {
                    if (tileIndex != null)
                        //sets tile
                        eMap.setTile(selected, tileIndex[0], tileIndex[1]);
                } catch (Exception e){

                }
            }
        });

        //changes selected tile from the tile list
        tileList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selected = (BackgroundTiles) tileList.getSelectionModel().getSelectedItem();
            }
        });

        //opens file chooser to import a file
        importFiles.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                FileChooser fc = new FileChooser();

                //limites to .txt files
                fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt"));

                //opens files
                File file = fc.showOpenDialog(stage);

                //in case cancelled
                if (file != null) {

                    //generates map
                    eMap = (MapGenerator.generateEditorMap(file, 32, 32));

                    //gets file name and inputs it in the text field
                    fileName.setText(file.getName().replace(".txt", ""));
                }
            }
        });

        //changes default save path
        changeSavePath.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                FileChooser fc = new FileChooser();
                fc.setInitialFileName("example");

                //gets saved path
                File file = fc.showSaveDialog(stage);

                //in case cancelled
                if (file != null)

                    //gets file path without the file name
                    savePath = file.getPath().replace(file.getName(), "");
            }
        });

        //default file save path
        savePath = "maps/";
        keyboardInput = new KeyboardInput();
        canvas.setOnKeyPressed(keyboardInput);
        canvas.setOnKeyReleased(keyboardInput);
    }

    /**
     * Starts program
     */
    public void programStart(){

        //starts camera
        cameraPos = new Vector2(0, 0);
        eMap = new EditorMap();
        gc = canvas.getGraphicsContext2D();

        //fills map
        eMap.fill(BackgroundTiles.BLACK_TILE);

        //animation timer
        new AnimationTimer() {


            @Override
            public void handle(long now){


                //user input
                //up
                if (KeyboardInput.isKeyDown(KeyCode.W)){
                    cameraPos.y -= 2;
                }
                //down
                else if (KeyboardInput.isKeyDown(KeyCode.S)){
                    cameraPos.y += 2;
                }
                //left
                if (KeyboardInput.isKeyDown(KeyCode.A)){
                    cameraPos.x -= 2;
                }
                //right
                else if (KeyboardInput.isKeyDown(KeyCode.D)){
                    cameraPos.x += 2;
                }

                //clears screen
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                //Information
                gc.fillText((mouseX + cameraPos.x) + "     " + (mouseY + cameraPos.y), 100, 100);
                gc.fillText(("Camera X: " + (cameraPos.x) + "     Camera Y:" + (cameraPos.y)), 100, 150);
                gc.fillText(("Mouse X: " + (mouseX) + "     Mouse Y:" + mouseY), 100, 200);

                //renders map
                eMap.render();

                //gets hovering tile
                int[] tileIndex = eMap.getClickedTile(mouseX + cameraPos.x, mouseY + cameraPos.y);

                //if no tile is selected
                if(tileIndex != null) {

                    //draws black box around it
                    Rect rect = eMap.getTileRect(tileIndex[0], tileIndex[1]);
                    gc.setFill(Color.TRANSPARENT);
                    gc.strokeRect(rect.x - cameraPos.x, rect.y - cameraPos.y, rect.width, rect.height);
                }
            }
        }.start();
    }

    /** Menu Click Handler
     *
     * @param e action event
     */
    public void menuClickHandler(ActionEvent e){
        MenuItem target = (MenuItem) e.getTarget();
        switch(target.getText()){

            //saves file
            case "Save" :
                String name = fileName.getText();
                //if blank
                if (!fileName.getText().equals(""))
                    eMap.saveMap(fileName.getText(), savePath);
                else
                    System.out.print("Enter A File Name");
                break;

            //Wipes Canvas
            case "Clear":
                eMap.fill(BackgroundTiles.BLACK_TILE);
                break;

            //fills canvas
            case "Fill":
                eMap.fill(selected);
                break;
        }
    }

    /** Implements certain traits into the canvas
     *
     * @param k key event
     */
    public void fieldKeyHandler(KeyEvent k){
        TextField target = (TextField) k.getTarget();

        if (k.getCode() == KeyCode.ENTER) {
            switch (target.getId()) {

                //adjusts map width
                case "mapWidth":
                    if (!mapWidth.getText().equals("") && isParsable(mapWidth.getText())) {
                        eMap.setMapWidth(Integer.parseInt(mapWidth.getText()));
                        mapWidth.clear();
                    }
                    break;

                //adjusts map height
                case "mapHeight":
                    if (!mapHeight.getText().equals("") && isParsable(mapHeight.getText())) {
                        eMap.setMapHeight(Integer.parseInt(mapHeight.getText()));
                        mapHeight.clear();
                    }
                    break;

                //adjusts tile width
                case "tileWidth":
                    if (!tileWidth.getText().equals("") && isParsable(tileWidth.getText())) {
                        eMap.setTileWidth(Integer.parseInt(tileWidth.getText()));
                        tileWidth.clear();
                    }
                    break;

                //adjusts tile height
                case "tileHeight":
                    if (!tileHeight.getText().equals("") && isParsable(tileHeight.getText())) {
                        eMap.setTileHeight(Integer.parseInt(tileHeight.getText()));
                        tileHeight.clear();
                    }
                    break;
            }

            //fills screen
            eMap.fill(BackgroundTiles.BLACK_TILE);
        }
    }

    //checks if a string is parsable
    private boolean isParsable(String text){
        try{
            Integer.parseInt(text);
        } catch (NumberFormatException err){
            return false;
        } catch (Exception err){
            return false;
        }
        return true;
    }


}
