package org.openjfx.Models;

import org.openjfx.Models.Character.Player;
import org.openjfx.Models.Level.Level;

public class Game {
    private final Player player;
    private Planet currentPlanet;
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

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Level level) {
        this.currentLevel = level;
    }

    public void setCurrentPlanet(Planet currentPlanet) {
        this.currentPlanet = currentPlanet;
    }
}
