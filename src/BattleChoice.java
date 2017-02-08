/**
 * Created by filipebraida on 31/05/16.
 */
public class BattleChoice extends Choice {
    public BattleChoice(String description, Event event, Enemy enemy) {
        super(description, event);

        this.enemy = enemy;
    }

    @Override
    public void executeChoice(Character character) {
        character.battle(this.enemy);
    }
    
    public Enemy getEnemy(){
    	
    	return enemy;
    	
    }

    private Enemy enemy;
}
