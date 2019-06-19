package engine.mapping;

/*
    Project title: Lazer Cats vs Zombies Map Editor
    -----------------------------------------------
    ClassName: Map
    -----------------------------------------------
    What it does: Super for all map classes, holds all the tiles
    -----------------------------------------------
    For: ICS4U1 - Holik
    -----------------------------------------------
    By: Ethan and Allen
    -----------------------------------------------
    Last Edited: June 18th 2019
 */

import engine.GameObject;
import javafx.scene.image.Image;

/** Map class
 *
 */
public class Map extends GameObject {

    private Tile[][] tileMap;
    protected String mapData;
    protected int tileWidth;
    protected int tileHeight;
    protected int mapWidth;
    protected int mapHeight;

    /**
     *
     * @param map
     * @param mapData
     */
    public Map(Tile[][] map, String mapData){

        // if map is not empty
        if (map != null) {
            tileMap = map;
            this.mapData = mapData;
            tileWidth = (int)tileMap[0][0].getRect().width;
            tileHeight = (int)tileMap[0][0].getRect().height;
            mapWidth = tileMap[0].length;
            mapHeight = tileMap.length;
        }
    }

    /** Empty constructor
     *
     */
    public Map(){
        tileMap = null;
        mapData = null;
        tileWidth = 32;
        tileHeight = 32;
        mapHeight = 32;
        mapWidth = 32;
    }

    /** Constructor
     *
     * @param mapWidth map width
     * @param mapHeight map height
     */
    public Map(int mapWidth, int mapHeight){
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.tileWidth = 32;
        this.tileHeight = 32;
    }

    /** Constructor
     *
     * @param tileWidth tile width
     * @param tileHeight tile height
     * @param mapWidth map width
     * @param mapHeight map height
     */
    public Map(int tileWidth, int tileHeight, int mapWidth, int mapHeight){
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    /** Constructor with map url
     *
     * @param mapData map url
     */
    public Map(String mapData){
        this.mapData = mapData;
    }

    /** Fills the map
     *
     * @param img images
     */
    public void fill(Image img){
        tileMap = new Tile[tileHeight][tileWidth];
        for (int y = 0; y < tileMap.length; y++){
            for (int x = 0; x < tileMap[y].length; x++){
                tileMap[y][x] = new Tile(img, tileWidth * x, tileHeight * y, tileWidth, tileHeight);
            }
        }
    }

    /** Renders all tiles
     *
     */
    @Override
    public void render(){
        for (Tile[] row : tileMap) {
            for (Tile tile : row){
                tile.render();
            }
        }
    }

    /** Sets map height
     *
     * @param mapHeight desired map height
     */
    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    /** Sets map width
     *
     * @param mapWidth desired map width
     */
    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    /** Sets tile height
     *
     * @param tileHeight desired tile height
     */
    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    /** Sets tile width
     *
     * @param tileWidth desired tile width
     */
    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }
}
