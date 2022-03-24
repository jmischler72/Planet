package org.openjfx.Models;

public class LevelEnemy extends Level{

    public LevelEnemy(PlanetType planetType) {
        super(planetType);
        generateName();
        type = LevelType.Enemy;
    }

    private void generateName() {
        while (name == null) name = EnemyName.getRandomName().name();
    }
}
