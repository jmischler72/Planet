package org.openjfx.Models.Character.Enemy;

import java.util.Random;

public enum EnemyName {
    kaezex,
    crezin,
    bravail,
    vir,
    ox,
    shehlath,
    ocu,
    udhell,
    crirlo,
    tuqaex,
    ghannal,
    storaens,
    feilnets,
    gurzo,
    scraats,
    almo,
    sticiers,
    cusnae,
    iphrits,
    bude,
    ximhaits,
    kulnie,
    throqrurs,
    qhilpheks,
    tiesaen,
    anqull,
    setoi,
    ur,
    els,
    zandull,
    hegats,
    strislids,
    qhors,
    grot,
    kriestrell,
    lagnaids,
    virqru,
    stairged,
    eelphex,
    bhaignair,
    kirren,
    cherrir,
    drakto,
    grelix,
    ciccaets,
    cazoll,
    tostaek,
    stiltux,
    hankraill,
    statil,
    aadhak,
    thruhats;

    public static EnemyName getRandomName() {
        Random random = new Random();
        return values()[random.nextInt(values().length-1)];
    }
}
