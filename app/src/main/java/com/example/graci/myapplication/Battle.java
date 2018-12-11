package com.example.graci.myapplication;

public class Battle {

    static int attackCount = 0;
    static int defendCount = 0;
    private Player_model player;
    private Player_model enemy;

    public Battle(Player_model player, Player_model enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public Player_model getPlayer() {
        return player;
    }

    public void setPlayer(Player_model player) {
        this.player = player;
    }

    public Player_model getEnemy() {
        return enemy;
    }

    public void setEnemy(Player_model enemy) {
        this.enemy = enemy;
    }

    public void attack(Player_model source, Player_model target) {

        if (target.isDefending()) {
            target.setDefense(target.getDefense() * 2);
        }
        int damage = source.getStrength() - target.getDefense();

        if (damage <= 1) {
            damage = 1;
        }

        target.setCurrHp(target.getCurrHp() - damage);
        if (target.isDefending()) {
            target.setDefense(target.getDefense() / 2);
            target.setDefend(false);
        }
    }

    public void defend(Player_model source) {
        source.setDefend(true);
    }

    public void postBattle() {
        this.player.setHp(this.player.getHp() + 50 + defendCount / 3);
        this.player.setDefense(this.player.getDefense() + 10 + defendCount / 2);
        this.player.setStrength(this.player.getStrength() + 15 + attackCount / 3);
        this.player.setCurrHp(this.player.getCurrHp() + (this.player.getCurrHp() / 3));
        System.out.println(defendCount);
        System.out.println(attackCount);
        defendCount = 0;
        attackCount = 0;
    }
}
