package org.openjfx.Models.Personage.Enemy;

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
