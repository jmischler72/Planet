package org.openjfx.Models;

public class LevelEnemy extends Level{

    public LevelEnemy(PlanetType planetType) {
        super(planetType);
        generateName();
    }

    private void generateName() {
        while (name == null) name = EnemyName.getRandomName().name();
    }
}
