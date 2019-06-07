package editor;

import engine.mapping.Tile;
import javafx.scene.canvas.GraphicsContext;
import editor.BuilderController;
import javafx.scene.image.Image;

public class EditorTile extends Tile {

    public EditorTile(){
        super();
    }

    public EditorTile(Image img, double x, double y, double tileWidth, double tileHeight){
        super(img, x, y, tileWidth, tileHeight);
    }

    public void click(){

    }

    @Override
    public void render(){
        BuilderController.gc.drawImage(img , rect.x - BuilderController.cameraPos.x, rect.y - BuilderController.cameraPos.y, rect.width, rect.height);
    }


}
