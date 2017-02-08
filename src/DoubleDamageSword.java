
public class DoubleDamageSword extends Item {
	
	static int danoBase = 100;
	
	@Override
	public void aplicarEfeito(Player player, Enemy enemy) {
		
		player.setAttack(player.getAttack()*2);
		
	}

}
