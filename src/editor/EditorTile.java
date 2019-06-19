package editor;

/*
    Project title: Lazer Cats vs Zombies Map Editor
    -----------------------------------------------
    ClassName: Editor Tile
    -----------------------------------------------
    What it does: Sub class of the tile class that takes the BackgroundTiles enum
    -----------------------------------------------
    For: ICS4U1 - Holik
    -----------------------------------------------
    By: Ethan and Allen
    -----------------------------------------------
    Last Edited: June 18th 2019
 */

import engine.mapping.Tile;
import images.TextureClassifier;
import javafx.scene.image.Image;

/** Tile subclass just for Editor Map
 *
 */
public class EditorTile extends Tile {


    private TextureClassifier.BackgroundTiles tile;

    /**
     *  Empty Constructor
     */
    public EditorTile(){
        super();
    }

    /** Regular Constuctor
     *
     * @param tile tile enum value
     * @param x x position on canvas
     * @param y y position on canvas
     * @param width width on canvas
     * @param length length on canvas
     */
    public EditorTile(TextureClassifier.BackgroundTiles tile, double x, double y, double width, double length){
        super(tile.getImage(), x, y, width, length);
        this.tile = tile;
    }

    /** Regular Constructor with images instead of tile
     *
     * @param img image
     * @param x x position on canvas
     * @param y y position on canvas
     * @param tileWidth tile width
     * @param tileHeight tile height
     */
    public EditorTile(Image img, double x, double y, double tileWidth, double tileHeight){
        super(img, x, y, tileWidth, tileHeight);
    }

    /** Checks if the given parameters collide with the tile
     *
     * @param x target x
     * @param y target y
     * @return
     */
    public boolean click(double x, double y){
        if (rect.isIn(x, y)){
            return true;
        }
        return false;
    }

    /** Renders the image of the tile
     *
     */
    @Override
    public void render(){
        BuilderController.gc.drawImage(img , rect.x - BuilderController.cameraPos.x, rect.y - BuilderController.cameraPos.y, rect.width, rect.height);
    }

    /** Sets the BackgroundTiles enum value
     *
     * @param tile enum value
     */
    public void setTile(TextureClassifier.BackgroundTiles tile){

        //checks if tile is null
        if(tile != null) {
            this.tile = tile;
            this.img = tile.getImage();
        }
    }

    /** Gets id of the tile
     *
     * @return tile id
     */
    public int getId(){
        return tile.getId();
    }



}
