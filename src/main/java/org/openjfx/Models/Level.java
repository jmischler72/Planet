package org.openjfx.Models;

import java.util.Random;

public class Level {
    protected String name;
    protected String description;
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
}
