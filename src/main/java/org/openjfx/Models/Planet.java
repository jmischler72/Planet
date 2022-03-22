package org.openjfx.Models;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

public class Planet {

    private PlanetType type;
    private File planet_file;

    public Planet() {
        generatePlanetType(new ArrayList<PlanetType>(){{add(PlanetType.Galaxy);}});
        fetchPlanetImage();
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

    public PlanetType getType() {
        return type;
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
