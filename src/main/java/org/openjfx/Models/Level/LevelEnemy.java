package org.openjfx.Models.Level;

import org.openjfx.Models.Enemy.Enemy;
import org.openjfx.Models.PlanetType;

import java.util.ArrayList;
import java.util.Random;

public class LevelEnemy extends Level{

    private ArrayList<Enemy> enemies;

    public LevelEnemy(Enemy enemy) {
        super("Enemy", LevelType.Enemy);
        enemies = new ArrayList<Enemy>();
        generateEnemies();
        this.setName(generateName());

    }

    private void generateEnemies(){
        Random random = new Random();
        for(int i = 0; i< random.nextInt(3)+1; i++ ){
            System.out.println(i);
            enemies.add(new Enemy());
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
