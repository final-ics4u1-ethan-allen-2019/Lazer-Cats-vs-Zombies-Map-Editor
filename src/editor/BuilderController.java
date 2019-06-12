package editor;

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

import java.security.Key;

public class BuilderController {

    public static Tile activeTile;
    private static double x;
    private static double y;

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

    EditorMap eMap;



    public static GraphicsContext gc;
    public static Vector2 cameraPos;
    public static KeyboardInput keyboardInput;
    private BackgroundTiles selected;

    @FXML
    public void initialize(){
        tileList.getItems().addAll(BackgroundTiles.values());
        canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                BuilderController.x = event.getX();
                BuilderController.y = event.getY();
            }
        });
        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuilderController.x = event.getX();
                BuilderController.y = event.getY();
                int[] tileIndex = eMap.getClickedTile(x + cameraPos.x, y + cameraPos.y);
                eMap.setTile(selected, tileIndex[1], tileIndex[0]);
            }
        });
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuilderController.x = event.getX();
                BuilderController.y = event.getY();
                int[] tileIndex = eMap.getClickedTile(x + cameraPos.x, y + cameraPos.y);
                eMap.setTile(selected, tileIndex[1], tileIndex[0]);
            }
        });

        tileList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                selected = (BackgroundTiles) tileList.getSelectionModel().getSelectedItem();
            }
        });

        keyboardInput = new KeyboardInput();
        canvas.setOnKeyPressed(keyboardInput);
        canvas.setOnKeyReleased(keyboardInput);
    }

    public void programStart(){

        cameraPos = new Vector2(400, 600);

        eMap = new EditorMap();

        //MAKE THIS PUBLIC STATIC CLASS VARIABLE
        gc = canvas.getGraphicsContext2D();


        eMap.fill(BackgroundTiles.WATER_TILE_0);

        new AnimationTimer() {


            @Override
            public void handle(long now){


                if (KeyboardInput.isKeyDown(KeyCode.W)){
                    cameraPos.y -= 2;
                }
                else if (KeyboardInput.isKeyDown(KeyCode.S)){
                    cameraPos.y += 2;
                }
                if (KeyboardInput.isKeyDown(KeyCode.A)){
                    cameraPos.x -= 2;
                }
                else if (KeyboardInput.isKeyDown(KeyCode.D)){
                    cameraPos.x += 2;
                }

                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                eMap.render();
                gc.fillText((x + cameraPos.x) + "     " + (y + cameraPos.y), 100, 100);
                gc.fillText(("Camera X: " + (cameraPos.x) + "     Camera Y:" + (cameraPos.y)), 150, 150);
                gc.fillText(("Mouse X: " + (x) + "     Mouse Y:" + y), 200, 200);
                int[] tileIndex = eMap.getClickedTile(x + cameraPos.x, y + cameraPos.y);
                if(tileIndex != null) {
                    Rect rect = eMap.getTileRect(tileIndex[1], tileIndex[0]);
                    gc.setFill(Color.TRANSPARENT);
                    gc.strokeRect(rect.x - cameraPos.x, rect.y - cameraPos.y, rect.width, rect.height);
                }
            }
        }.start();
    }

    public void menuClickHandler(ActionEvent e){
        MenuItem target= (MenuItem) e.getTarget();
        switch(target.getText()){
            case "Save" :
                String name = fileName.getText();
                if (fileName.getText() != "")
                    eMap.saveMap(fileName.getText());
                else
                    System.out.print("Enter A File Name");
                break;
            case "Clear":
                eMap.fill(BackgroundTiles.WATER_TILE_0);
                break;
        }
    }

    public void fieldKeyHandler(KeyEvent k){
        TextField target = (TextField) k.getTarget();

        if (k.getCode() == KeyCode.ENTER) {
            switch (target.getId()) {
                case "mapWidth":
                    if (!mapWidth.getText().equals("")) {
                        eMap.setMapWidth(Integer.parseInt(mapWidth.getText()));
                        mapWidth.clear();
                    }
                    break;
                case "mapHeight":
                    if (!mapHeight.getText().equals("")) {
                        eMap.setMapHeight(Integer.parseInt(mapHeight.getText()));
                        mapHeight.clear();
                    }
                    break;
                case "tileWidth":
                    if (!tileWidth.getText().equals("")) {
                        eMap.setTileWidth(Integer.parseInt(tileWidth.getText()));
                        tileWidth.clear();
                    }
                    break;
                case "tileHeight":
                    if (!tileHeight.getText().equals("")) {
                        eMap.setTileHeight(Integer.parseInt(tileHeight.getText()));
                        tileHeight.clear();
                    }
                    break;
            }
            eMap.fill(BackgroundTiles.LAVA_TILE_0);
        }
    }
}
