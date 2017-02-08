/**
 * Created by filipebraida on 31/05/16.
 */
public class Character {

    public Character(int life, int attack) {
        this.life = life;
        this.attack = attack;
    }

    public void battle(Character enemy) {
        this.takeDamage(enemy.getAttack());
        enemy.takeDamage(this.getAttack());
    }

    public boolean isAlive() {
        if(this.life > 0)
            return true;

        return false;
    }

    public void takeDamage(int attack) {
    	
    	if(life - attack > 0)
        this.life -= attack;
    	
    	else
    		life = 0;
    }

    public int getAttack() {
        return this.attack;
    }
    
    public void setAttack(int attack){
    	
    	this.attack = attack;
    }
    
    public int getLife(){
    	
    	return life;
    }
    
    public void setLife(int life){
    	
    	this.life = life;
    }
    public void setID(int id){
    	this.id = id;
    }
    
    public int getID(){
    	return id;
    }

    private int life;
    private int attack;
    private int id;
}
