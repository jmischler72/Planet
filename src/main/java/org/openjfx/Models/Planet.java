package org.openjfx.Models;

import org.openjfx.Models.Character.Enemy.Boss;
import org.openjfx.Models.Level.*;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

public class Planet {

    private double size = 0;
    private PlanetType type;
    private File planet_file;
    private final ArrayList<Level> levels = new ArrayList<Level>();
    private ArrayList<Level> doneLevels = new ArrayList<Level>();

    public Planet(ArrayList<PlanetType> desiredTypes) {

        generatePlanetType(desiredTypes);
        fetchPlanetImage();

        for(int i=0; i< 6; i++){
            if((i+1)%5 == 0){
                levels.add(new LevelShop());
            }else if((i+1)%6 == 0) {
                levels.add(new LevelEnemy());
            }
            else {
                levels.add(new LevelEnemy());
            }
        }

    }

    public ArrayList<Level> getDoneLevels() {
        return doneLevels;
    }

    public void addDoneLevel(Level level) {
        doneLevels.add(level);
    }

    private void generatePlanetType(ArrayList<PlanetType> desiredTypes) {
        PlanetType[] planetTypes = PlanetType.values();

        Random rand = new Random();
        do {
            type = planetTypes[rand.nextInt(planetTypes.length)];
        } while (!desiredTypes.contains(type));
    }

    private void fetchPlanetImage(){

        if (type != null){
            File[] planets_files = new File[0];
            try {
                planets_files = (new File(getClass().getResource("Planets/" + type).toURI())).listFiles();
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

    public ArrayList<Level> getLevels(){
        return levels;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public PlanetType getType() {
        return type;
    }

}
