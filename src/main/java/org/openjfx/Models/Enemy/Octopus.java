package org.openjfx.Models.Enemy;

public class Octopus extends Enemy{

    public Octopus(){
        super(EnemyName.getRandomName().name()+"us", EnemyType.octopus);
        setHealth(200);

    }
}
