package com.company.module;

/**
 * Константы исходов игры.
 */
public enum Exodus {
    BankerPlayer ("BankerPlayer"),
    Banker ("Banker"),
    Player ("Player"),
    Draw ("Draw"),
    Multicolored ("Multicolored"),
    Monochromatic ("Monochromatic"),
    None ("None");

    /**
     * @param result строковое значение победителя.
     */
    Exodus(String result) { }

}

