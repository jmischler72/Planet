package org.openjfx.Models.Level;

import org.openjfx.Models.Personage.Enemy.Boss;

public class LevelBoss extends Level{

    public LevelBoss(Boss boss) {
        super(boss.getName(), LevelType.Boss);
    }
}
