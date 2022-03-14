package org.openjfx.Models;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Random;

public class World {

    private String type;
    private File planet_file;

    public World() {
        File[] planets_type = new File[0];
        try {
            planets_type = (new File(getClass().getResource("Planets").toURI())).listFiles();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Random rand = new Random();

        String[] path= planets_type[rand.nextInt(planets_type.length)].toString().split("\\\\");
        this.type = path[path.length-1];

        File[] planets_files = new File[0];
        try {
            planets_files = (new File(getClass().getResource("Planets/" + this.type).toURI())).listFiles();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        this.planet_file = planets_files[rand.nextInt(planets_files.length)];

        System.out.println(planet_file);

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
