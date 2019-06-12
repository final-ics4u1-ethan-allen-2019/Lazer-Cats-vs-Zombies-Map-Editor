package editor;

import engine.Rect;
import engine.mapping.DynamicMap;
import engine.mapping.Tile;
import images.TextureClassifier;
import javafx.scene.image.Image;
import java.io.*;

import java.util.ArrayList;

public class EditorMap extends DynamicMap {

    protected ArrayList<ArrayList<EditorTile>> tileMap;

    EditorMap(){
        super();
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
                    System.out.print(col);
                    return new int[] {row, col};
                }
            }
        }
        return null;
    }


    public void addTile(EditorTile tile){
        if (tileMap.size() <= 1){
            addRow();
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
        tileMap.add(new ArrayList<EditorTile>());
        System.out.print(tileMap.size() - 1);
    }

    public void setTile(TextureClassifier.BackgroundTiles tile, int x , int y){
        tileMap.get(y).get(x).setTile(tile);
    }

    public Rect getTileRect(int x, int y){
        return tileMap.get(y).get(x).getRect();
    }

    public void saveMap(String name){
        String text = new String();
        for (int y = 0; y < tileMap.size(); y++){
            String[] row = new String[tileMap.get(y).size()];
            for (int x = 0; x < tileMap.get(y).size(); x++){
                row[x] = (Integer.toString(tileMap.get(y).get(x).getId()));
            }
            text += (String.format((String.join(" ", row) + "%n")));
        }
        try {
            FileWriter writer = new FileWriter("maps/" + name + ".txt");
            writer.write(text);
            writer.close();

        } catch (Exception e){
            System.out.print("file could not");
        }
    }



    @Override
    public void render(){  tileMap.forEach(tileList -> { tileList.forEach(tile -> {tile.render();}); }); }

}
