package src.gfx;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class SpriteSheet {

    private Map<String, SpriteSet> units;
    private Map<String, Image> tiles;
    public static Image cursor;


    public SpriteSheet() {
        units = new HashMap<>();
        tiles = new HashMap<>();
        loadSpritesFromDisk();
    }

    private void loadSpritesFromDisk() {
        loadUnits("/sprites/units");
        loadTiles("/sprites/tiles");
      
    }

    /**
     * Loads the tiles of the game according the specified path!
     * @param path
     */
    private void loadTiles(String path) {
        String [] imagesInFolder = getImagesInFolder(path);

        for(String fileName : imagesInFolder) {
            tiles.put(
                    fileName.substring(0, fileName.length() - 4),
                    Assets.loadImage(path + "/" + fileName)
            );
        }
    }

    /**
     * Loading the entity units using the path specified.
     * @param path
     */
    private void loadUnits(String path){
        String[] folderNames = getFolderNames(path);

        for(String folderName : folderNames) {
            SpriteSet spriteSet = new SpriteSet();
            String pathToFolder = path + "/" + folderName;
            String [] sheetsInFolder = getImagesInFolder(pathToFolder);

            for(String sheetName : sheetsInFolder) {
                spriteSet.addSheets(
                        sheetName.substring(0, sheetName.length() - 4),
                        Assets.loadImage(pathToFolder + "/" + sheetName)
                );
            }
            units.put(folderName , spriteSet);
        }
    }


    /**
     * Getting all the folder names listed on a specified directory (basePath) by using the java.net.URL
     * @param basePath
     * @return a list of folder names.
     */
    private String[] getFolderNames(String basePath) {
        URL resource = SpriteSheet.class.getResource(basePath);
        File file ;
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        }else {
            file = new File(resource.getFile());
        }
        return file.list((current, name ) -> new File(current ,name).isDirectory());
    }

    /**
     * Getting all the file names listed on a specified directory (basePath) by using the java.net.URL
     * @param basePath
     * @return a list of file names.
     */
    private String[] getImagesInFolder(String basePath) {
        URL resource = SpriteSheet.class.getResource(basePath);
        File file;
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        }else {
            file = new File(resource.getFile());
        }
        return file.list((current, name ) -> new File(current ,name).isFile());
    }

    public SpriteSet getUnit(String name) {
        return units.get(name);
    }

    public Image getTiles(String name){
        return tiles.get(name);
    }
}
