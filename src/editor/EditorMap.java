package editor;

import engine.Rect;
import engine.mapping.DynamicMap;
import engine.mapping.Tile;
import images.TextureClassifier;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

import java.util.ArrayList;

public class EditorMap extends DynamicMap {

    protected ArrayList<ArrayList<EditorTile>> tileMap;

    EditorMap(){
        super();
    }

    EditorMap(int tileWidth, int tileHeight, int mapWidth, int mapHeight){
        super(tileWidth, tileHeight, mapWidth, mapHeight);
        tileMap = new ArrayList<>();
    }


    //IDEALLY OVERRIDE WITH Tile Parameter


    public void fill(TextureClassifier.BackgroundTiles tile){
        tileMap = new ArrayList<>();
        for (int y = 0; y < mapHeight; y++){
            addRow();
            for (int x = 0; x < mapWidth; x++){
                addTile(new EditorTile(tile, x* tileWidth, y *tileHeight, tileWidth, tileHeight ));
            }
        }
    }

    public int[] getClickedTile(double x, double y){
        for (int row = 0; row < tileMap.size(); row ++){
            for (int col = 0; col < tileMap.get(row).size(); col++){
                if (tileMap.get(row).get(col).click(x, y)){
                    return new int[] {row, col};
                }
            }
        }
        return null;
    }


    public void addTile(EditorTile tile){
        if (tileMap.size() <= 1){
            this.addRow();
        }
        if (tileMap.get(tileMap.size() - 1).size() == 0) {
            tileMap.get(tileMap.size() - 1).add(tile);
        } else{
            ArrayList<EditorTile> row = tileMap.get(tileMap.size() - 1);
            Rect lastTile = row.get(row.size() - 1).getRect();
            tileMap.get(tileMap.size() - 1).add(tile);
        }
    }

    @Override
    public void addRow(){
        this.tileMap.add(new ArrayList<EditorTile>());
    }

    public void setTile(TextureClassifier.BackgroundTiles tile, int x , int y){
        tileMap.get(y).get(x).setTile(tile);
    }

    public Rect getTileRect(int x, int y){
        return tileMap.get(y).get(x).getRect();
    }

    public void saveMap(String name){
        String text = new String();
        text.replace("%n" , "");
        for (int y = 0; y < tileMap.size(); y++){
            String[] row = new String[tileMap.get(y).size()];
            for (int x = 0; x < tileMap.get(y).size(); x++){
                row[x] = (Integer.toString(tileMap.get(y).get(x).getId()));
            }
            if (y == tileMap.size() - 1) {
                text += String.format((String.join(" ", row)));
            } else {
                text += (String.format((String.join(" ", row) + "%n")));
            }
        }
        try {
            FileWriter writer = new FileWriter(BuilderController.savePath +".txt");
            writer.write(text);
            writer.close();

        } catch (Exception e){
            System.out.print("file could not");
        }
    }

    @Override
    public void addTile(Image img){
        if (tileMap == null){
            tileMap = new ArrayList<>();
            addRow();
        } else if (tileMap.size() < 1){
            addRow();
        }
        if (tileMap.get(tileMap.size() - 1).size() == 0) {
            tileMap.get(tileMap.size() - 1).add(new EditorTile(img, 0, tileHeight * (tileMap.size() - 1), tileWidth, tileHeight));
        } else{
            ArrayList<EditorTile> row = tileMap.get(tileMap.size() - 1);
            Rect lastTile = row.get(row.size() - 1).getRect();
            tileMap.get(tileMap.size() - 1).add(new EditorTile(img, (int)(lastTile.x + tileWidth), (int)(lastTile.y), tileWidth, tileHeight));
        }
    }

    @Override
    public void render(){  tileMap.forEach(tileList -> { tileList.forEach(tile -> {tile.render();}); }); }

}
