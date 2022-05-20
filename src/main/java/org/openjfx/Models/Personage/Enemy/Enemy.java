package org.openjfx.Models.Personage.Enemy;

public class Enemy {
    private String name;

    public Enemy(){
        name = generateName();
    }

    private String generateName() {
        return EnemyName.getRandomName().name();

    }

    public String getName() {
        return name;
    }
}
