package com.example.graci.myapplication;

import java.util.Scanner;

public class Driver {

	public static void main(String args[]) {
		
		//player with base stats
		Player_model player = new Player_model("Player",100,100,10,5);	
		
		//stats to scale for enemy
		int baseHp = 60;
		int baseStrength = 6;
		int baseDefense = 2;
		
		
		//loops till you fight 5 enemies or lose 1 fight
		for(int matchCount=0;matchCount<5;matchCount++) {
			//new enemy
			Player_model enemy = new Player_model("Enemy"+(matchCount+1),baseHp,baseHp,baseStrength,baseDefense);
			
			//Think(enemy,Difficulty)
			Think brain = new Think(enemy,matchCount+1);
			//make new battle (includes counters and such)
			Battle functions = new Battle(player, enemy);
			
			//just prints
			System.out.println("Enemy HP "+enemy.getHp());
			System.out.println("Enemy Strength "+enemy.getStrength());
			System.out.println("Enemy Defense "+enemy.getDefense());
			
			// this is the battle loop. fight till 0 hp one side		
			while(player.getCurrHp()>0 && enemy.getCurrHp()>0) {
				
				//this asks for input from the user 1- guard 2- attack 3 heal
				Scanner sc = new Scanner(System.in);
				
				int choice = sc.nextInt();
				
				switch(choice) {
					case 1: functions.defend(player);
							System.out.println("Player guards!");
							functions.defendCount++;
							break;
					case 2: functions.attack(player, enemy);
							System.out.println("Player attacks!");
							functions.attackCount++;
							break;
					case 3: if(player.getCurrHp()+(player.getHp()/2)>player.getHp()) {
								player.setCurrHp(player.getHp());
							}
							else
								player.setCurrHp(player.getCurrHp()+(player.getHp()/2));
							System.out.println("Player heals!");
							break;
				}
				
				System.out.println(enemy.getName()+" has "+enemy.getCurrHp());
				
				//escapes if enemy is dead
				if(enemy.getCurrHp()<=0) 
					break;
				
				//enemy turn
				//copy paste lang this but you can change the system.out and link it to android studio
				switch(brain.Choice(brain.getDifficulty())) {
					case 1: functions.defend(enemy);
							System.out.println("Enemy guards!");
							break;
					case 2: functions.attack(enemy, player);
							System.out.println("Enemy attacks!");
							break;
					case 3: enemy.setCurrHp(enemy.getCurrHp()+(enemy.getHp()/2));
							System.out.println("Enemy heals!");
							break;
				}
				System.out.println(player.getName()+" has "+player.getCurrHp());
				
				//you died
				if(player.getCurrHp()<=0) {
					System.out.println("You died");
					break;
				}
			}
			
			//scaling after the fight (copy paste this after a fight)
			baseHp = baseHp+(matchCount+1)*5+40;
			baseStrength = baseStrength+(matchCount+1)*4+8;
			baseDefense = baseDefense+(matchCount+1)*4+3;
			
			//player scaling feature
			functions.postBattle();
			
			//prints
			System.out.println("Max HP: "+player.getHp());
			System.out.println("Current HP: "+player.getCurrHp());
			System.out.println("Strength: "+player.getStrength());
			System.out.println("Defense: "+player.getDefense());
		}
	}
}
