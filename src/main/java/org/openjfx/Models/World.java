package org.openjfx.Models;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class World {

    private PlanetType type;
    private File planet_file;

    public World() {
        generatePlanetType(new ArrayList<PlanetType>(){{add(PlanetType.Galaxy);}});
        fetchPlanetImage();
    }

    private void generatePlanetType(ArrayList<PlanetType> excludedTypes) {
        PlanetType[] planetTypes = PlanetType.values();

        Random rand = new Random();

        do{
            type = planetTypes[rand.nextInt(planetTypes.length)];
            System.out.println(type);
        }while(excludedTypes.contains(type));
    }

    private void fetchPlanetImage(){

        if (this.type != null){
            File[] planets_files = new File[0];
            try {
                planets_files = (new File(getClass().getResource("Planets/" + this.type.toString()).toURI())).listFiles();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            Random rand = new Random();
            this.planet_file = planets_files[rand.nextInt(planets_files.length)];

        }else{
            System.out.println("This world has no type");
        }
    }

    public File getPlanet_file(){
        return this.planet_file;
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
