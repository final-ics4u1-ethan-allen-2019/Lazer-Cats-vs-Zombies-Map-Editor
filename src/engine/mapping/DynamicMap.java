package engine.mapping;

/*
    Project title: Lazer Cats vs Zombies Map Editor
    -----------------------------------------------
    ClassName: Dynamic Map
    -----------------------------------------------
    What it does: Dynamic Map
    -----------------------------------------------
    For: ICS4U1 - Holik
    -----------------------------------------------
    By: Ethan and Allen
    -----------------------------------------------
    Last Edited: June 18th 2019
 */

import engine.Rect;
import javafx.scene.image.*;
import java.util.ArrayList;

/** Map subclass that can add rows
 *
 */
public class DynamicMap extends Map {


    protected ArrayList<ArrayList<Tile>> tileMap;


    /**
     *
     * @param tileWidth
     * @param tileHeight
     * @param mapWidth
     * @param mapHeight
     */
    public DynamicMap( int tileWidth, int tileHeight, int mapWidth, int mapHeight){
        super(tileWidth, tileHeight, mapWidth, mapHeight);
        tileMap = new ArrayList<>();
    }

    /** Empty constructor
     *
     */
    public DynamicMap(){
        super();
        tileMap = new ArrayList<>();
    }

    /** Adds tile
     *
     * @param tile tile desired
     */
    public void addTile(Tile tile){
        if (tileMap == null){
            tileMap = new ArrayList<>();
            addRow();
        } else if (tileMap.size() < 1){
            addRow();
        }
        tileMap.get(tileMap.size() - 1).add(tile);
    }

    /** Adds tile with image
     *
     * @param img image desired
     */
    public void addTile(Image img){
        if (tileMap == null){
            tileMap = new ArrayList<>();
            addRow();
        } else if (tileMap.size() < 1){
            addRow();
        }

        //if row is empty
        if (tileMap.get(tileMap.size() - 1).size() == 0) {
            tileMap.get(tileMap.size() - 1).add(new Tile(img, 0, tileHeight * (tileMap.size() - 1), tileWidth, tileHeight));
        }

        //otherwise
        else{
            //gets last tile
            ArrayList<Tile> row = tileMap.get(tileMap.size() - 1);
            Rect lastTile = row.get(row.size() - 1).getRect();
            tileMap.get(tileMap.size() - 1).add(new Tile(img, (int)(lastTile.x + tileWidth), (int)(lastTile.y), tileWidth, tileHeight));
        }
    }


    /** Fills the tile map
     *
     * @param img image
     */
    @Override
    public void fill(Image img){
        tileMap = new ArrayList<>();
        for (int y = 0; y < tileHeight; y++){
            addRow();
            for (int x = 0; x < tileWidth; x++){
                addTile(new Tile(img, x* tileWidth, y *tileHeight, tileWidth, tileHeight ));
            }
        }
    }

    /** Adds a row
     *
     */
    public void addRow(){
        tileMap.add(new ArrayList<Tile>());
    }

    /** Renders each tile
     *
     */
    @Override
    public void render(){  tileMap.forEach(tileList -> { tileList.forEach(tile -> {tile.render();}); }); }
}
