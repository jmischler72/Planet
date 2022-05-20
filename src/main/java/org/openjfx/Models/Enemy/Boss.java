package org.openjfx.Models.Enemy;

public class Boss extends Enemy{
    private String name;

    public Boss(){
        name = generateName();
    }

    private String generateName() {
        return EnemyName.getRandomName().name();

    }

    public String getName() {
        return name;
    }
}
