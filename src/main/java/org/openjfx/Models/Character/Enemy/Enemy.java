package org.openjfx.Models.Character.Enemy;

import org.openjfx.Models.Character.Character;
import org.openjfx.Models.Character.Player;

public class Enemy extends Character {

    private final EnemyType type;

    private final int health = 0;


    public Enemy(String name, EnemyType type, int health, int shield, int damage , double dodge){
        super(name,health,shield, damage, dodge);
        this.type = type;
    }

    public EnemyType getType() {
        return type;
    }



}
