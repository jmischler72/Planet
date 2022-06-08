package org.openjfx.Models.Enemy;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.openjfx.Models.Planet;
import org.openjfx.Models.Player;

public class Enemy {
    private final String name;

    private final EnemyType type;


    private int health = 0;
    private int max_health = 0;


    public Enemy(String name, EnemyType type){
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public EnemyType getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return max_health;
    }

    public void setHealth(int health){
        this.health = health;
        this.max_health = health;
    }

    public void getAttacked(Player player) {
        this.health -= player.getDamage();
    }

}
