package org.openjfx.Models.Level;

public abstract class Level {
    protected String name;
    protected String description;
    private double[] position;
    protected LevelType type;

    public Level(String name, LevelType type) {
        this.name = name;
        this.type = type;
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

    public LevelType getType() {
        return type;
    }
  
    protected void setName(String name){
        this.name = name;
    }
}
