package org.openjfx.Models.Enemy;

public class Alien extends Enemy {

    public Alien() {
        super(EnemyName.getRandomName().name() + "en", EnemyType.alien);

    }
}
