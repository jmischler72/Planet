package org.openjfx.Models.Enemy;

public class Boss extends Enemy{
    private String name;

    public Boss(){
        super("Boss", EnemyType.boss);
        name = generateName();
    }

    private String generateName() {
        return EnemyName.getRandomName().name();

    }

    public String getName() {
        return name;
    }
}
