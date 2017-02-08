
public class DodgeArmor extends Item {
	
	static int danoBase = 100;
	
	@Override
	public void aplicarEfeito(Player player, Enemy enemy) {
		
		enemy.setAttack(0);
		
	}

	

}
