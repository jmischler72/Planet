package org.openjfx.Models;

import java.util.Random;

public class Level {
    private String name;
    private String description;
    private double[] position;
    private PlanetType planetType;

    public Level(String name, String description, PlanetType planetType){
        this.name = name;
        this.description = description;
        this.planetType = planetType;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double[] getPosition(){
        return position;
    }

    public void setPosition(double[] position){
        this.position = position;
    }



}
