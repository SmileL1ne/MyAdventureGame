package com.mustik.game.Creatures.Monsters;

import com.mustik.game.Creatures.Creatures;

import java.util.concurrent.ThreadLocalRandom;

public class Ghoul extends Creatures implements Comparable<Ghoul> {
    private String rarity;

    public Ghoul() {
    }

    public Ghoul(int health, String rarity) {
        super(health);
        this.rarity = rarity;
    }

    @Override
    public int attack(String attackType){
        return ThrowACube(rarity);
    }

    public int ThrowACube(String action){
        if(action.equals("Legendary")) return (int)(1.5 * ThreadLocalRandom.current().nextInt(10, 26));
        else if(!action.equals("0")) return ThreadLocalRandom.current().nextInt(10,26);
        else return 0;
    }

    public void damageByPlayer(int damage){
        if(rarity.equals("Legendary")){
            setHealth(getHealth() - (damage-5));
        }else{
            setHealth(getHealth() - damage);
        }
    }

    @Override
    public boolean death(){
        System.out.println("The ghoul is dead!");
        return true;
    }

    @Override
    public int compareTo(Ghoul o) {
        return Double.compare(getHealth(), o.getHealth());
    }

    public String getRarity() {
        return rarity;
    }
}
