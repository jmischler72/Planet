package org.openjfx.Models;

import org.openjfx.Models.Level.Level;
import org.openjfx.Models.Personage.Player;

public class Game {
    private final Player player;
    private final Planet currentPlanet;
    private Level currentLevel = null;

    public Game() {
        player = new Player("Moi");
        currentPlanet = new Planet();
    }

    public Player getPlayer() {
        return player;
    }

    public Planet getCurrentPlanet() {
        return currentPlanet;
    }

    public Level getCurrentLevel(){
        return currentLevel;
    }

    public void setCurrentLevel(Level level){
        this.currentLevel = level;
    }


}
