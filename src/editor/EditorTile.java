package editor;

import engine.mapping.Tile;
import images.TextureClassifier;
import javafx.scene.image.Image;

public class EditorTile extends Tile {

    private TextureClassifier.BackgroundTiles tile;

    public EditorTile(){
        super();
    }

    public EditorTile(TextureClassifier.BackgroundTiles tile, double x, double y, double width, double length){
        super(tile.getImage(), x, y, width, length);
        this.tile = tile;
    }

    public EditorTile(Image img, double x, double y, double tileWidth, double tileHeight){
        super(img, x, y, tileWidth, tileHeight);
    }

    public boolean click(double x, double y){
        if (rect.isIn(x, y)){
            return true;
        }
        return false;
    }

    @Override
    public void render(){
        BuilderController.gc.drawImage(img , rect.x - BuilderController.cameraPos.x, rect.y - BuilderController.cameraPos.y, rect.width, rect.height);
    }

    public void setTile(TextureClassifier.BackgroundTiles tile){
        if(tile != null) {
            this.tile = tile;
            this.img = tile.getImage();
        }
    }

    public int getId(){
        return tile.getId();
    }



}
