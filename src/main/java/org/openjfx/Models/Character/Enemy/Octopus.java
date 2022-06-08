package org.openjfx.Models.Character.Enemy;

public class Octopus extends Enemy {

    public Octopus() {
        super(EnemyName.getRandomName().name() + "us", EnemyType.octopus,100,10,10,10);

    }
}
