package editor;

import engine.mapping.Tile;
import images.TextureClassifier.BackgroundTiles;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

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
    }

    public void programStart(){

        eMap = new EditorMap();

        //MAKE THIS PUBLIC STATIC CLASS VARIABLE
        gc = canvas.getGraphicsContext2D();

        new AnimationTimer() {


            @Override
            public void handle(long now){


            }
        };
    }

}
