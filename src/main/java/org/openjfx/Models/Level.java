package org.openjfx.Models;

import java.util.Random;

public class Level {
    private String name;
    private String description;
    private int[] position;

    public Level(String name){
        this.name = name;
    }
    public Level(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int[] getPosition(){
        return position;
    }

    public void setPosition(int[] position){
        this.position = position;
    }



}
