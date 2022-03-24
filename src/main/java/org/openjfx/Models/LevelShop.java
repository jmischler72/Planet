package org.openjfx.Models;

public class LevelShop extends Level{

    public LevelShop(PlanetType planetType) {
        super(planetType);
        name = "Marchand quelconque";
        type = LevelType.Shop;
    }
}
