package com.mustik.game.Creatures.Player;

import com.mustik.game.Characteristics.Healable;
import com.mustik.game.Creatures.Creatures;

import java.util.concurrent.ThreadLocalRandom;

public class Player extends Creatures implements Healable {
    private static int move;
    private int currentEnergy;

    private int armor;

    public Player(){
    }

    public Player(int health, int currentEnergy) {
        super(health);
        this.currentEnergy = currentEnergy;
    }

    public int attack(String action){
        if(currentEnergy<2){
            return 0;
        }
        if(action.equals("Sword") && currentEnergy>=10){
            currentEnergy -= 10;
            return ThrowACube("Sword");
        }else if(action.equals("Bow") && currentEnergy>=7){
            currentEnergy -= 7;
            return ThrowACube("Bow");
        }else{
            currentEnergy -= 2;
            return ThrowACube("Fist");
        }
    }

    public int ThrowACube(String attackWeapon){
        return switch (attackWeapon) {
            case "Sword" -> ThreadLocalRandom.current().nextInt(10, 21);
            case "Bow" -> ThreadLocalRandom.current().nextInt(5, 16);
            case "Fist" -> ThreadLocalRandom.current().nextInt(3, 11);
            default -> 0;
        };
    }

    public void damageByMonster(int damage){
        if(armor==0){
            setHealth(getHealth() - damage);
        }else{
            armor -= damage;
            if(armor<0){
                setHealth(getHealth() - Math.abs(armor));
                armor = 0;
            }
        }
        currentEnergy -= 5;
    }

    @Override
    public boolean death(){
        System.out.println("You are dead!");
        return true;
    }

    public void move(){
        move += 1;
        if(move%2==0) currentEnergy += 15;
    }

    @Override
    public int heal() {
        int health = ThreadLocalRandom.current().nextInt(10, 31);
        if(currentEnergy>=7){
            setHealth(getHealth() + health);
            currentEnergy -= 7;
        }
        return health;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public static int getMove() {
        return move;
    }
}
