package engine.mapping;

/*
    Project title: Lazer Cats vs Zombies Map Editor
    -----------------------------------------------
    ClassName: Tile
    -----------------------------------------------
    What it does: Displays a background image
    -----------------------------------------------
    For: ICS4U1 - Holik
    -----------------------------------------------
    By: Ethan and Allen
    -----------------------------------------------
    Last Edited: June 18th 2019
 */

import engine.Draw;
import engine.GameObject;
import engine.Rect;
import javafx.scene.image.Image;

/** Tile object
 *
 */
public class Tile extends GameObject {


    protected Image img;
    protected Rect rect;

    /** Empty Constructor
     *
     */
    public Tile(){
        super();
        img = null;
        rect = new Rect();
    }

    /** Constructor with image
     *
     * @param img desired image
     */
    public Tile(Image img){
        super();
        this.img = img;
        rect = new Rect(0,0, this.img.getWidth(), this.img.getHeight());
    }

    /** Constructor with image and rect
     *
     * @param img desired image
     * @param rect desired rect
     */
    public Tile(Image img, Rect rect){
        super();
        this.img = img;
        this.rect = rect;
    }

    /** Tile constuctor
     *
     * @param img desired image
     * @param x x position on the canvas
     * @param y y position on the canvas
     * @param width width in pixels
     * @param height height in pixels
     */
    public Tile(Image img, double x, double y, double width, double height){
        super();
        this.img = img;
        this.rect = new Rect(x, y, width, height);
    }

    /** Renders tile
     *
     */
    @Override
    public void render(){
        Draw.drawImage(img, rect.x, rect.y, rect.width, rect.height);
    }

    /** setter for image
     *
     * @param img desired image
     */
    public void setImg(Image img) {
        this.img = img;
    }

    /** returns rect
     *
     * @return rect
     */
    public Rect getRect(){
        return this.rect;
    }

}
