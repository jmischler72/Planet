package org.openjfx.Models.Level;

import org.openjfx.Models.Character.Enemy.Alien;
import org.openjfx.Models.Character.Enemy.Enemy;
import org.openjfx.Models.Character.Enemy.Octopus;

import java.util.ArrayList;
import java.util.Random;

public class LevelEnemy extends Level{

    private final ArrayList<Enemy> enemies;
    private boolean done = false;

    public LevelEnemy() {
        super("Enemy", LevelType.Enemy);
        enemies = new ArrayList<Enemy>();
        generateEnemies();
        this.setName(generateName());
    }

    private void generateEnemies(){
        Random random = new Random();
        for(int i = 0; i< random.nextInt(3)+1; i++ ){
            switch (random.nextInt(2)){
                case 0:
                    enemies.add(new Alien());
                    break;
                case 1:
                    enemies.add(new Octopus());
                    break;
            }
        }
    }

    private String generateName(){
        if (enemies.size() ==1){
            return enemies.get(0).getName();
        }

        if (enemies.size() == 2) {
            return enemies.get(0).getName() + " et " + enemies.get(1).getName();
        }
        if (enemies.size() > 2) {
            return enemies.get(0).getName() + " et autres";
        }

        return null;
    }

    public void remove(Enemy e) {
        this.enemies.remove(e);
        if (this.enemies.size() < 1) {
            this.done = true;
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public boolean isDone() {
        return done;
    }
}
