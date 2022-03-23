package org.openjfx.Models.Level;

import org.openjfx.Models.PlanetType;

import java.util.Random;

public class Level {
    protected String name;
    protected String description;
    private double[] position;
    private PlanetType planetType;

    public Level(PlanetType planetType){
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
