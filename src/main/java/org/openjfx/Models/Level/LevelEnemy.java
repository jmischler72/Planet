package org.openjfx.Models.Level;

import org.openjfx.Models.Enemy.Enemy;
import org.openjfx.Models.PlanetType;

import java.util.ArrayList;

public class LevelEnemy extends Level{

    private ArrayList<Enemy> enemy;

    public LevelEnemy(PlanetType planetType) {
        super(planetType);
        generateName();
    }

    private void generateName() {

        while (name == null){
            name = EnemyName.getRandomName().name();

        }
    }
}
