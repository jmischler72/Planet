package org.openjfx.Models;

import org.openjfx.Models.Enemy.Boss;
import org.openjfx.Models.Enemy.Enemy;
import org.openjfx.Models.Level.*;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

public class Planet {

    private double size = 0;
    private PlanetType type;
    private File planet_file;
    private ArrayList<Level> levels = new ArrayList<Level>();

    public Planet() {
        generatePlanetType(new ArrayList<PlanetType>(){{add(PlanetType.Galaxy);}});
        fetchPlanetImage();

        for(int i=0; i< 6; i++){

            LevelType levelType;
            Level level;

            if((i+1)%5 == 0) levelType = LevelType.Shop;
            else if((i+1)%6 == 0) levelType = LevelType.Boss;
            else levelType = LevelType.Enemy;

            switch (levelType) {
                case Boss:
                    Boss boss = new Boss();
                    level = new LevelBoss(boss);
                    break;
                case Shop:
                    level = new LevelShop();
                    break;
                case Enemy:
                    Enemy enemy = new Enemy();
                    level = new LevelEnemy(enemy);
                    break;
                default:
                    Enemy defaultEnnemy = new Enemy();
                    level = new LevelEnemy(defaultEnnemy);
                    System.out.println("Level type not found");
            }

            levels.add(level);
        }

    }

    private void generatePlanetType(ArrayList<PlanetType> excludedTypes) {
        PlanetType[] planetTypes = PlanetType.values();

        Random rand = new Random();
        do{
            type = planetTypes[rand.nextInt(planetTypes.length)];
        }while(excludedTypes.contains(type));
    }

    private void fetchPlanetImage(){

        if (type != null){
            File[] planets_files = new File[0];
            try {
                planets_files = (new File(getClass().getResource("Planets/" + type.toString()).toURI())).listFiles();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            Random rand = new Random();
            planet_file = planets_files[rand.nextInt(planets_files.length)];

        }else{
            System.out.println("This planet has no type");
        }
    }

    public File getPlanet_file(){
        return this.planet_file;
    }

    public ArrayList<Level> getLevels(){
        return levels;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }


//    public void changeColor(){
//        for (int col = 0; col <  width; col++) {
//            for (int row = 0; row < height; row++) {
//                Color color = picture.get(col, row);
//                int r = color.getRed();
//                int g = color.getGreen();
//                int b = color.getBlue();
//                pictureR.set(col, row, new Color(r, 0, 0));
//
//            }
//        }
//    }
}
