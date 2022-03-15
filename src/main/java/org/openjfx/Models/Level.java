package org.openjfx.Models;

public class Level implements LevelInt{

    private LevelInfosSubScene levelInfos;

    public Level() {
        this.levelInfos = new LevelInfosSubScene();
    }

    public LevelInfosSubScene getLevelInfos() {
        return levelInfos;
    }
}
