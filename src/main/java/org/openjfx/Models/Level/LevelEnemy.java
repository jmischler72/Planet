package org.openjfx.Models.Level;

import org.openjfx.Models.Enemy.Enemy;
import org.openjfx.Models.PlanetType;

import java.util.ArrayList;
import java.util.Random;

public class LevelEnemy extends Level{

    private ArrayList<Enemy> enemies;

    public LevelEnemy(Enemy enemy) {
        super(enemy.getName(), LevelType.Enemy);

    }


}
