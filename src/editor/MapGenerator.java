package editor;

/*
    Project title: Lazer Cats vs Zombies Map Editor
    -----------------------------------------------
    ClassName: Map Generator
    -----------------------------------------------
    What it does: Used for importing maps, generates maps based on file
    -----------------------------------------------
    For: ICS4U1 - Holik
    -----------------------------------------------
    By: Ethan and Allen
    -----------------------------------------------
    Last Edited: June 18th 2019
 */

import images.TextureClassifier.BackgroundTiles;

import java.io.*;

/** Generates maps
 *
 */
public class MapGenerator {

    /** Generates editor map from textfile
     *
     * @param file the desired file
     * @param tileWidth tile width for rendering
     * @param tileHeight tile height for rendering
     * @return EditorMap with all tiles initialized
     */
    public static EditorMap generateEditorMap(File file, int tileWidth, int tileHeight){
        EditorMap map = null;

        try {
            FileReader fileReader = new FileReader(file);
            String line;
            int[] mapDimensions = getMapDimensions(fileReader);

           //inits the map
            map = new EditorMap(tileWidth, tileHeight, mapDimensions[0], mapDimensions[1]);

            //resets the file reader
            fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            //for all lines
            while ((line = reader.readLine()) != null){

                //if empty line
                if (line.equals("/n"))
                    continue;

                //splits all the id's
                String[] txt = line.split(" ");

                //for each id
                for (String s : txt){

                    //checks for each enum
                    for (BackgroundTiles b : BackgroundTiles.values()){
                        if (b.getId() == Integer.parseInt(s)){
                            map.addTile(b);
                        }
                    }
                }

                //adds row to map
                map.addRow();
            }
        }

        //error
        catch (Exception e){
            System.out.println("Load Failed");
            e.printStackTrace();
        }


        return map;
    }


    //gets map dimensions
    private static int[] getMapDimensions(FileReader reader){
        int length = 0;
        int width = 0;
        try {
            //reader inits
            BufferedReader bReader = new BufferedReader(reader);

            //gets width
            width = (bReader.readLine()).split(" ").length;

            //adds one because of readline
            length++;
            while (bReader.readLine() != null)
                length++;

        } catch (Exception e){

        }

        return new int[] {width, length};
    }
}
