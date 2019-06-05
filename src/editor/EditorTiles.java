package editor;

import engine.mapping.Tile;
import javafx.scene.canvas.GraphicsContext;
import editor.BuilderController;

public class EditorTiles extends Tile {

    public EditorTiles(){
        super();
    }

    public void click(){

    }

    @Override
    public void render(){
        BuilderController.gc.drawImage(img , rect.x, rect.y, rect.width, rect.height);
    }

}
