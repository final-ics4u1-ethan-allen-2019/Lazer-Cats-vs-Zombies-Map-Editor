package engine.mapping;

import engine.GameObject;

public class Map extends GameObject {

    private Tile[][] tileMap;
    protected String mapData;
    protected int tileWidth;
    protected int tileHeight;
    protected int mapWidth;
    protected int mapHeight;

    public Map(Tile[][] map, String mapData){
        if (map != null) {
            tileMap = map;
            this.mapData = mapData;
            tileMap[0][0].getRect().width = tileWidth;
        }
    }

    public Map(){
        tileMap = null;
        mapData = null;
    }

    public Map(int mapWidth, int mapHeight){
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.tileWidth = 32;
        this.tileHeight = 32;
    }

    public Map(String mapData){
        this.mapData = mapData;
    }

    @Override
    public void render(){
        for (Tile[] row : tileMap) {
            for (Tile tile : row){
                tile.render();
            }
        }
    }
}
