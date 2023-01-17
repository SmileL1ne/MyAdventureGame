package com.mustik.game;

import com.mustik.game.Creatures.Monsters.Ghoul;
import com.mustik.game.Creatures.Player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Ghoul> list = new ArrayList<>();
        int randomHealth;
        int randomRarity;
        int randomEnergy;
        for(int i = 0; i<3; i++){
            randomHealth = ThreadLocalRandom.current().nextInt(30, 71);
            randomRarity = ThreadLocalRandom.current().nextInt(1, 1001);
            if(randomRarity%2==0 && randomRarity%5==0){
                list.add(new Ghoul((randomHealth + 20), "Legendary"));
            }else{
                list.add(new Ghoul(randomHealth - 10, "Common"));
            }
        }
        Collections.sort(list);

        randomHealth = ThreadLocalRandom.current().nextInt(70, 101);
        randomEnergy = ThreadLocalRandom.current().nextInt(60, 91);

        Player player = new Player(randomHealth, randomEnergy);
        greeting();

        int n = 0;
        boolean death = false;
        boolean deathOfMonster = false;
        Scanner sc = new Scanner(System.in);

        while(!death){
            int damage;
            int healed;
            int enemyDamage;
            Ghoul enemy = list.get(n);
            if(Player.getMove() == 0 || deathOfMonster){
                System.out.println("Currently you have " + player.getHealth() + " point of health and " + player.getCurrentEnergy() + " points of energy.");
                System.out.println("You've encountered a " + enemy.getRarity() + " " + enemy.getClass().getSimpleName() + " with a " + enemy.getHealth() + " points of health!");
            }
            deathOfMonster = false;
            System.out.println("So, what do you want to do? (Attack, Heal): ");
            String move = sc.next();
            System.out.println();
            if(move.equals("Attack")){
                System.out.println("With what weapon do you want to attack? (Sword, Bow, Fist): ");
                String weapon = sc.next();
                damage = player.attack(weapon);
                enemy.damageByPlayer(damage);
                Thread.sleep(100);
                System.out.println("You've hit that monster with " + weapon + " by " + damage + " points of damage!");
                if(enemy.getHealth()>0) System.out.println("It left with " + enemy.getHealth() + " points of health.");
                System.out.println();
            }else if(move.equals("Heal")){
                healed = player.heal();
                System.out.println("Healing by " + healed + " points of health.");
                System.out.println("Your healed health is " + player.getHealth() + " points and your energy is " + player.getCurrentEnergy() + " points.");
                System.out.println();
            }else{
                System.out.println("Try to type correctly, as in examples! You missed your move!");
                System.out.println();
            }
            if(enemy.getHealth()<=0){
                deathOfMonster = enemy.death();
                n++;
            }else{
                enemyDamage = enemy.attack("Default");
                player.damageByMonster(enemyDamage);
                System.out.println("Your move is done! " + enemy.getClass().getSimpleName() + " is currently taking it's move.");
                System.out.println(enemy.getClass().getSimpleName() + " is attacking you by " + enemyDamage + " points!");
                if(player.getHealth()<=0){
                    death = player.death();
                    System.out.println("The ancient artifact couldn't survive and the World is will infinitely suffer from monsters invasion!");
                }else{
                    System.out.println("You've survived attack of " + enemy.getClass().getSimpleName() + " and left with " + player.getHealth() + " points health and " + player.getCurrentEnergy() + " points energy.");
                    System.out.println("Note that every your move wastes your SE(Soul Energy) and weapons have different damage! (Sword - 10 SE, Bow - 7 SE, Fist - 2 SE)");
                }
            }
            if(list.isEmpty()) System.out.println("You won this game! Amazing! I just don't know how it is possible!");
            player.move();
        }
    }

    public static void greeting(){
        System.out.println("Hello, fellow Player! You're currently in a dark world full of monsters.");
        System.out.println("You must survive, because your body carries the strongest artifact in the world - Soul Of The Last Man.");
        System.out.println("So, start you journey here, with a basic sword, bow and fist in your inventory and crush these monsters down!");
        System.out.println();
    }
}
