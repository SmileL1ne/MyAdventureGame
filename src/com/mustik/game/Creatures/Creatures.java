package com.mustik.game.Creatures;

import com.mustik.game.Characteristics.Killable;
import com.mustik.game.GameEngine.WorldInteraction;

public abstract class Creatures implements WorldInteraction, Killable {
    private static int id;
    private int health;

    public Creatures(){
        id++;
    }

    public Creatures(int health) {
        this();
        this.health = health;
    }

    public abstract int attack(String attackType);

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

}
