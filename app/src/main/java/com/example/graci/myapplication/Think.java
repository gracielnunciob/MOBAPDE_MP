package com.example.graci.myapplication;

import java.util.Random;
public class Think {

	int brain[] = new int[100];
	private int difficulty;
	static int healCount = 0;
	private Player_model person;	
	
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public int getHealCount() {
		return healCount;
	}
	public void setPlayer_model(Player_model person) {
		this.person = person;
	}
	public Player_model getPlayer_model() {
		return person;
	}
	public Think(Player_model person, int difficulty) {
		this.person = person;
		this.difficulty = difficulty;
	}
	
	// 1 - defend
	// 2 - attack
	// 3 - heal
	public int Choice(int difficulty) {
		int choice=0;
		Random rand = new Random();
		int lowerLimit = person.getHp()/4;
		switch(difficulty) {
			case 1: for(int i=0;i<80;i++) 
						brain[i]=1;
					for(int j=80;j<100;j++)
						brain[j]=2;
					choice = rand.nextInt(brain.length);
					break;
			case 2: for(int i=0;i<50;i++) 
						brain[i]=1;
					for(int j=50;j<100;j++)
						brain[j]=2;
					choice = rand.nextInt(brain.length);
					break;
			case 3: for(int i=0;i<100;i++) 
						brain[i]=2;
					choice = rand.nextInt(brain.length);
					break;
			case 4: for(int i=0;i<25;i++) 
						brain[i]=1;
					for(int j=25;j<100;j++)
						brain[j]=2;
					choice = rand.nextInt(brain.length);
					break;
			case 5: if(person.getCurrHp()>=person.getHp()/2) {
						for(int i=0;i<50;i++)
							brain[i]=1;
						for(int j=50;j<100;j++)
							brain[j]=2;
					}
					else if(person.getCurrHp()<person.getCurrHp()/2 && person.getCurrHp()>lowerLimit) {
						for(int i=0;i<65;i++)
							brain[i]=1;
						for(int j=65;j<100;j++)
							brain[j]=2;
					}
					else if(person.getCurrHp()<=lowerLimit && healCount<2) {
						for(int i=0;i<100;i++) {
							brain[i]=3;
						}
						healCount+=1;
					}
					else if(person.getCurrHp()<=lowerLimit && healCount>=2) {
						for(int i=0;i<100;i++) 
							brain[i]=2;
					}			
					choice = rand.nextInt(brain.length);
					break;
		}
		
		return brain[choice];
	}
}
