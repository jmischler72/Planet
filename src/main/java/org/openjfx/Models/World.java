package org.openjfx.Models;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Random;

public class World {

    private File world_image;

    public World() {
        File[] files = new File[0];
        try {
            files = (new File(getClass().getResource("Planets").toURI())).listFiles();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Random rand = new Random();

        this.world_image = files[rand.nextInt(files.length)];

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
