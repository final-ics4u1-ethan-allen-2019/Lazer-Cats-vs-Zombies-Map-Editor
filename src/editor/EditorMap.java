package editor;

/*
    Project title: Lazer Cats vs Zombies Map Editor
    -----------------------------------------------
    ClassName: EditorMap
    -----------------------------------------------
    What it does: it is a subclass of the Dyanmic Map class with saving capabilities with it's special editor tile class
    -----------------------------------------------
    For: ICS4U1 - Holik
    -----------------------------------------------
    By: Ethan and Allen
    -----------------------------------------------
    Last Edited: June 18th 2019
 */

import engine.Rect;
import engine.mapping.DynamicMap;
import images.TextureClassifier;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

import java.util.ArrayList;

/**
 * Map class used especially for map editing
 */
public class EditorMap extends DynamicMap {

    //tile map with Editor tiles
    protected ArrayList<ArrayList<EditorTile>> tileMap;

    /** Empty Constructor, makes an empty map
     *
     */
    EditorMap(){
        super();
    }

    /** Map constructor with dimensions
     *
     * @param tileWidth how many pixels wide a tile is
     * @param tileHeight how many pixels high a tile is
     * @param mapWidth how many tiles wide the map is
     * @param mapHeight how many tiles high the map is
     */
    EditorMap(int tileWidth, int tileHeight, int mapWidth, int mapHeight){
        super(tileWidth, tileHeight, mapWidth, mapHeight);
        tileMap = new ArrayList<>();
    }


    /** Fills the tile class
     *
     * @param tile desired tile type to change the map with
     */
    public void fill(TextureClassifier.BackgroundTiles tile){
        tileMap = new ArrayList<>();

        //checks for each tile in the map
        for (int y = 0; y < mapHeight; y++){
            addRow();
            for (int x = 0; x < mapWidth; x++){

                //adds tile
                addTile(new EditorTile(tile, x* tileWidth, y *tileHeight, tileWidth, tileHeight ));
            }
        }
    }

    //Gets clicked tile or tile interacted with
    public int[] getClickedTile(double x, double y){

        //checks each tile to see if one collides with the coordinates
        for (int row = 0; row < tileMap.size(); row ++){
            for (int col = 0; col < tileMap.get(row).size(); col++){

                //checks collision
                if (tileMap.get(row).get(col).click(x, y)){
                    return new int[] {col, row};
                }
            }
        }
        return null;
    }

    //adds tile at a desired location
    private void addTile(EditorTile tile){

        //checks if tile map is empty
        checkEmpty();

        //adds tile
        tileMap.get(tileMap.size() - 1).add(tile);
    }

    /** Adds new row to the tile map
     *
     */
    @Override
    public void addRow(){
        this.tileMap.add(new ArrayList<EditorTile>());
    }

    /** Sets the desired tile
     *
     * @param tile tile you want to set it as
     * @param x tile column
     * @param y tile row
     */
    public void setTile(TextureClassifier.BackgroundTiles tile, int x , int y){
        tileMap.get(y).get(x).setTile(tile);
    }

    /** Gets the rectangle of a tile
     *
     * @param x tile column
     * @param y tile row
     * @return rect of the tile
     */
    public Rect getTileRect(int x, int y){
        return tileMap.get(y).get(x).getRect();
    }

    /** Saves the map onto a text file for the game to read
     *
     * @param name
     * @param savePath
     */
    public void saveMap(String name, String savePath){
        //makes a new string
        String text = new String();

        //iterates through the tile map
        for (int y = 0; y < tileMap.size(); y++){

            //makes a new row
            String[] row = new String[tileMap.get(y).size()];

            //iterates through the row
            for (int x = 0; x < tileMap.get(y).size(); x++){

                //gets the row's id
                row[x] = (Integer.toString(tileMap.get(y).get(x).getId()));
            }

            //adds to the "text" string
            if (y != tileMap.size() - 1) {
                text += (String.format((String.join(" ", row) + "%n")));
            } else {
                text += (String.format((String.join(" ", row))));
            }
        }

        //saving file
        try {

            //stage for file explorer
            Stage stage = new Stage();
            FileChooser fc = new FileChooser();

            //sets initial paths
            fc.setInitialDirectory(new File(savePath));
            fc.setInitialFileName(name);
            File file = fc.showSaveDialog(stage);

            //writes file
            FileWriter writer = new FileWriter(file.getPath() + ".txt");
            writer.write(text);
            writer.close();

        } catch (Exception e){
            System.out.print("file could not be written");
        }
    }

    /** Adds tile with images
     *
     * @param img desired image
     */
    @Override
    public void addTile(Image img){
        //checks if time map is empty
        checkEmpty();

        //checks if row is empty
        if (tileMap.get(tileMap.size() - 1).size() == 0) {
            tileMap.get(tileMap.size() - 1).add(new EditorTile(img, 0, tileHeight * (tileMap.size() - 1), tileWidth, tileHeight));
        }

        //adds tiles
        else {
            ArrayList<EditorTile> row = tileMap.get(tileMap.size() - 1);
            Rect lastTile = row.get(row.size() - 1).getRect();
            tileMap.get(tileMap.size() - 1).add(new EditorTile(img, (int)(lastTile.x + tileWidth), (int)(lastTile.y), tileWidth, tileHeight));
        }
    }

    /** Adds tile with the background tile emun
     *
     * @param tile Enum value from TextureClassifier.Background Tiles
     */
    public void addTile(TextureClassifier.BackgroundTiles tile){
        checkEmpty();

        //see the other add tile
        if (tileMap.get(tileMap.size() - 1).size() == 0) {
            tileMap.get(tileMap.size() - 1).add(new EditorTile(tile, 0, tileHeight * (tileMap.size() - 1), tileWidth, tileHeight));
        } else{
            ArrayList<EditorTile> row = tileMap.get(tileMap.size() - 1);
            Rect lastTile = row.get(row.size() - 1).getRect();
            tileMap.get(tileMap.size() - 1).add(new EditorTile(tile, (int)(lastTile.x + tileWidth), (int)(lastTile.y), tileWidth, tileHeight));
        }
    }

    /** Renders all tiles
     *
     */
    @Override
    public void render(){  tileMap.forEach(tileList -> { tileList.forEach(tile -> {tile.render();}); }); }

    //checks if tile map is empty or uninitialized
    private void checkEmpty(){

        //if not initialized
        if (tileMap == null){
            tileMap = new ArrayList<>();
            addRow();
        }

        //if empty
        else if (tileMap.size() < 1){
            addRow();
        }
    }

}
