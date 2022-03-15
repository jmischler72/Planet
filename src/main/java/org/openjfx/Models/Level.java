package org.openjfx.Models;

import java.util.Random;

public class Level {
    private String name;
    private String description;

    public Level(){
        Random rand = new Random();
        name = Integer.toString(rand.nextInt(600));
        description = Integer.toString(rand.nextInt(600));
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
