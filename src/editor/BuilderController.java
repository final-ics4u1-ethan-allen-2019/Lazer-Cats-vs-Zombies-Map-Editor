package editor;

import engine.input.KeyboardInput;
import engine.mapping.Tile;
import engine.math.Vector2;
import images.TextureClassifier.BackgroundTiles;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

import java.security.Key;

public class BuilderController {

    public static Tile activeTile;
    private static double x;
    private static double y;

    @FXML
    ListView tileList;

    @FXML
    Canvas canvas;

    EditorMap eMap;

    public static GraphicsContext gc;
    public static Vector2 cameraPos;
    public static KeyboardInput keyboardInput;

    @FXML
    public void initialize(){
        tileList.getItems().addAll(BackgroundTiles.values());
        canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                BuilderController.x = event.getX();
                BuilderController.y = event.getX();
            }
        });
        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BuilderController.x = event.getX();
                BuilderController.y = event.getX();
            }
        });
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

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


        eMap.fill(BackgroundTiles.WATER_INVERSE_HOLE_BOTTOM_LEFT.getImage());

        new AnimationTimer() {


            @Override
            public void handle(long now){


                if (KeyboardInput.isKeyDown(KeyCode.W)){
                    cameraPos.y --;
                }
                else if (KeyboardInput.isKeyDown(KeyCode.S)){
                    cameraPos.y ++;
                }
                if (KeyboardInput.isKeyDown(KeyCode.A)){
                    cameraPos.x --;
                }
                else if (KeyboardInput.isKeyDown(KeyCode.D)){
                    cameraPos.x ++;
                }

                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                eMap.render();
            }
        }.start();
    }

}
