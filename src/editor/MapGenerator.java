package editor;

import engine.mapping.DynamicMap;
import engine.mapping.Map;
import engine.mapping.Tile;
import images.TextureClassifier.BackgroundTiles;

import java.io.*;

public class MapGenerator {

    public static EditorMap generateEditorMap(File file, int tileWidth, int tileHeight){
        EditorMap map = null;
        try {
            FileReader fileReader = new FileReader(file);
            String line;
            int[] mapDimensions = getMapDimensions(fileReader);
            map = new EditorMap(tileWidth, tileHeight, mapDimensions[0], mapDimensions[1]);
            fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            while ((line = reader.readLine()) != null){
                String[] txt = line.split(" ");
                for (String s : txt){

                    for (BackgroundTiles b : BackgroundTiles.values()){
                        if (b.getId() == Integer.parseInt(s)){
                            System.out.print(s + " ");
                            map.addTile(b.getImage());
                        }
                    }
                }
                map.addRow();
            }
        } catch (Exception e){
            System.out.println("Load Failed");
            e.printStackTrace();
        }


        return map;
    }


    public static int[] getMapDimensions(FileReader reader){
        int length = 0;
        int width = 0;
        try {
            BufferedReader bReader = new BufferedReader(reader);
            width = (bReader.readLine()).split(" ").length;
            length++;
            while (bReader.readLine() != null) {
                length++;
            }
        } catch (Exception e){

        }

        return new int[] {width, length};
    }
}
