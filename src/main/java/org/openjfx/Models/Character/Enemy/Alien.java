package org.openjfx.Models.Character.Enemy;

public class Alien extends Enemy {

    public Alien() {
        super(EnemyName.getRandomName().name() + "en", EnemyType.alien,100,10,10,10);

    }
}
