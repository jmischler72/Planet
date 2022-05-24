package org.openjfx.Models.Level;

import org.openjfx.Models.Enemy.Alien;
import org.openjfx.Models.Enemy.Enemy;
import org.openjfx.Models.Enemy.Octopus;
import org.openjfx.Models.PlanetType;

import java.util.ArrayList;
import java.util.Random;

public class LevelEnemy extends Level{

    private ArrayList<Enemy> enemies;

    public LevelEnemy() {
        super("Enemy", LevelType.Enemy);
        enemies = new ArrayList<Enemy>();
        generateEnemies();
        this.setName(generateName());

    }

    private void generateEnemies(){
        Random random = new Random();
        for(int i = 0; i< random.nextInt(3)+1; i++ ){
            System.out.println(i);
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

        if(enemies.size() ==2){
            return enemies.get(0).getName() + " et " + enemies.get(1).getName();
        }
        if(enemies.size()>2){
            return enemies.get(0).getName() + " et autres";
        }

        return null;
    }

    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }

}
