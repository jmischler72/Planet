package org.openjfx.Models;

import java.util.ArrayList;

public class Level {
    protected String name;
    protected String description;
    protected LevelType type;
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

    public LevelType getType() {
        return type;
    }
}
