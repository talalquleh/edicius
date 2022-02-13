package worlds;

import entities.Entity;
import helpers.CollisionBox;
import helpers.Size;
import state.State;

import java.awt.*;

public class Maps {

    private int[][] map1;
    private int[][] map2;
    private int[][] map3;
    private CollisionBox[][] wallsCollisionBox;
    private Size size;
    private String mapType;

    private int[][] getMap1() {
        for (int i = 0; i < (int) size.getWidth(); i++) {
            for (int j = 0; j < (int) size.getHeight(); j++) {
                if ((i % 2 == 0 && (j < 3 || (j > 7 && j < 14) || j > (int) size.getHeight() - 4)) || i == 0 || j == 0
                        || i == (int) size.getWidth() - 1
                        || j == (int) size.getHeight() - 1) {
                    map1[i][j] = 1;
                } else {
                    map1[i][j] = 0;
                }
                wallsCollisionBox[i][j] = new CollisionBox(new Rectangle(i, j, 62, 62));
            }
        }
        return map1;
    }

    private int[][] getMap2() {
        for (int i = 0; i < (int) size.getWidth(); i++) {
            for (int j = 0; j < (int) size.getHeight(); j++) {
                if ((i % 2 == 0 && (i < 3 || (i > 7 && i < 14) || i > (int) size.getHeight() - 4 )) || i == 0 || j == 0
                        || i == (int) size.getWidth() -1
                        || j == (int) size.getHeight() -1){
                    map2[i][j] = 1;
                }else{
                    map2[i][j] = 0;
                }
                wallsCollisionBox[i][j] = new CollisionBox(new Rectangle(i, j, 62, 62));
            }
        }
        return map2;
    }

    private int[][] getMap3() {
        for (int i = 0; i < (int) size.getWidth(); i++) {
            for (int j = 0; j < (int) size.getHeight(); j++) {
                if ((i % 2 == 0 && (i < 3 || (i > 7 && j < 14 || i < 14 && j > 3) || j > (int) size.getHeight() - 4 )) || i == 0 || j == 0
                        || i == (int) size.getWidth() -1
                        || j == (int) size.getHeight() -1){
                    map3[i][j] = 1;
                }else{
                    map3[i][j] = 0;
                }
                wallsCollisionBox[i][j] = new CollisionBox(new Rectangle(i, j, 62, 62));
            }
        }
        return map3;
    }

    public Maps(String mapType, Size size) {
        this.mapType = mapType;
        this.size = size;
        map1 = new int[(int) size.getWidth()][(int) size.getHeight()];
        map2 = new int[(int) size.getWidth()][(int) size.getHeight()];
        map3 = new int[(int) size.getWidth()][(int) size.getHeight()];
    }

    public int[][] getMap(){
        if(mapType == "map1"){
            return getMap1();
        }else if(mapType == "map2"){
            return getMap2();
        }else if(mapType == "map3"){
            return getMap3();
        }
        return getMap3();
    }

}
