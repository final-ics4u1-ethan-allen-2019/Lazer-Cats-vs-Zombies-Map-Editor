package editor;

import engine.Rect;
import engine.mapping.DynamicMap;
import engine.mapping.Tile;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class EditorMap extends DynamicMap {


    EditorMap(){
        super();
    }


    //IDEALLY OVERRIDE WITH Tile Parameter

    @Override()
    public void fill(Image img){
        tileMap = new ArrayList<>();
        for (int y = 0; y < tileHeight; y++){
            addRow();
            for (int x = 0; x < tileWidth; x++){
                addTile(new EditorTile(img, x* tileWidth, y *tileHeight, tileWidth, tileHeight ));
            }
        }
    }


}
