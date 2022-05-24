package org.openjfx.Models.Enemy;

public class Enemy {
    private String name;

    private EnemyType type;

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

}
