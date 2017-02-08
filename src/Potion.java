import javax.swing.JOptionPane;

public class Potion extends Item {

	@Override
	public void aplicarEfeito(Player player, Enemy enemy) {
		player.setLife((int)(player.getLife() + 100));
	
	}

}
