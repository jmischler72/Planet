package org.openjfx.Models;

import org.openjfx.Models.Character.Player;
import org.openjfx.Models.Level.Level;

import java.util.ArrayList;

public class Game {
    private final Player player;
    private Planet currentPlanet;
    private ArrayList<Planet> planets = new ArrayList<Planet>();
    private Level currentLevel = null;

    public Game() {
        player = new Player("Moi");
        currentPlanet = new Planet(new ArrayList<PlanetType>(){{add(PlanetType.DryTerran);}});
        planets.add(currentPlanet);
    }

    public Player getPlayer() {
        return player;
    }

    public Planet getCurrentPlanet() {
        return currentPlanet;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level level) {
        this.currentLevel = level;
    }

    public void setCurrentPlanet(Planet currentPlanet) {
        this.currentPlanet = currentPlanet;
    }

    public void addPlanet(Planet newPlanet){
        planets.add(newPlanet);
    }

    public ArrayList<Planet> getPlanets(){
        return planets;
    }
}
