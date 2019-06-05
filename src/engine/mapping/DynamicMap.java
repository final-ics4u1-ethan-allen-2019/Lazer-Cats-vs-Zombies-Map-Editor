package engine.mapping;

import engine.GameObject;

import engine.Rect;
import javafx.scene.image.*;
import java.util.ArrayList;

public class DynamicMap extends Map {
    //WIP
    protected ArrayList<ArrayList<Tile>> tiles;

    public DynamicMap(String url){
        super(url);
    }

    public DynamicMap(){
        super();
        tiles = null;
    }

    public void addTile(Tile tile, int x, int y){
        tiles.get(y).add(x, tile);
    }

    public void addTile(Image img){
        if (tiles == null){
            addRow();
            tiles.get(tiles.size() - 1).add(new Tile(img, 0, 0, tileWidth, tileHeight));
        } else{
            ArrayList<Tile> row = tiles.get(tiles.size() - 1);
            Rect lastTile = row.get(row.size() - 1).getRect();
            tiles.get(tiles.size() - 1).add(new Tile(img, (int)(lastTile.x + tileWidth), (int)(lastTile.y + tileHeight), tileWidth, tileHeight));
        }
    }

    public void addRow(){
        tiles.add(new ArrayList<Tile>());
    }

    @Override
    public void render(){  tiles.forEach( tileList -> { tileList.forEach(tile -> {tile.render();}); }); }
}
