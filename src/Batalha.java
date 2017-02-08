import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


// Falta habilitar os itens
public class Batalha extends JFrame implements ActionListener {
	
	private JLabel hpPlayer, hpEnemy, itens, attack;

	private JRadioButton potion, doubleDamage, dodge;
	private ButtonGroup grupo = new ButtonGroup();
	private JButton usar, atacar;
	
	private Painel painel;
	
	private Player player;
	private Enemy enemy;
	
	public Batalha(Player player, Enemy enemy){
		
		super("Batalha");
		
		this.player = player;
		this.enemy = enemy;
		
		try{
			switch (enemy.getID())
			{
			case 1: //Drag
				painel = new Painel("imagens/drag.jpg");
				break;
			case 2: //Zilean
				painel = new Painel("imagens/zilean.jpg");
				break;
			
			case 3: //WW
				painel = new Painel("imagens/warwick.jpg");
				break;
			
			case 4: //Barao
				painel = new Painel("imagens/baron.jpg");
				break;
			case 5: //Guerreiros
				painel = new Painel("imagens/guerreiros.jpg");
				break;
			
			case 6: //Dragon
				painel = new Painel("imagens/dragon.jpg");
				break;
			
			case 7: //Zilean2
				painel = new Painel("imagens/zilean2.png");
				break;
			
			default:
				break;
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		
		Container cp = getContentPane();
		
		painel.setLayout(null);
		
		itens = new JLabel("Itens");
		itens.setBounds(20, 410, 50, 10);
		itens.setForeground(Color.WHITE);
		
		attack = new JLabel("Ataque do player: " + player.getAttack());
		attack.setBounds(20, 350, 200, 30);
		attack.setForeground(Color.WHITE);
		
		potion = new JRadioButton("Potion");
		potion.setBounds(0, itens.getBounds().y + 20, 100, 25);
		potion.setContentAreaFilled(false); // Tirar o background
		potion.setForeground(Color.WHITE); // Cor da fonte
		potion.addActionListener(this);
		
		doubleDamage = new JRadioButton("Double Damage Sword");
		doubleDamage.setBounds(0, potion.getBounds().y + 20, 190, 25);
		doubleDamage.setContentAreaFilled(false);
		doubleDamage.setForeground(Color.WHITE);
		
		dodge = new JRadioButton("Dodge Armor");
		dodge.setBounds(0, doubleDamage.getBounds().y + 20, 150, 25);
		dodge.setContentAreaFilled(false);
		dodge.setForeground(Color.WHITE);	
		
		grupo.add(potion);
		grupo.add(doubleDamage);
		grupo.add(dodge);
		
		usar = new JButton("Usar");
		usar.setBounds(5, dodge.getBounds().y + 30, 85, 30);		
		usar.addActionListener(this);
		
		hpPlayer = new JLabel("HP Player: " + player.getLife());
		hpPlayer.setBounds(5, 0, 110, 30);
		hpPlayer.setForeground(Color.WHITE);
		
		hpEnemy = new JLabel("HP Enemy: " + enemy.getLife());
		hpEnemy.setBounds(680, 0, 110, 30);
		hpEnemy.setForeground(Color.WHITE);
		
		atacar = new JButton("Atacar");
		atacar.setBounds(350, 460, 80, 30);
		atacar.addActionListener(this);
		
		painel.add(itens);
		painel.add(hpPlayer);
		painel.add(hpEnemy);
		painel.add(attack);
		
		painel.add(potion);
		painel.add(doubleDamage);
		painel.add(dodge);
		
		painel.add(usar);
		painel.add(atacar);

		cp.add(painel);		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setResizable(false); // Evita o redimensionamento
		setLocationRelativeTo(null);
		setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch(e.getActionCommand()){
		
		case "Usar":
			if(potion.isSelected()){
				
				new Potion().aplicarEfeito(player, enemy);
				hpPlayer.setText("HP Player: " + player.getLife());
				grupo.getSelection().setEnabled(false);
				grupo.clearSelection();
				JOptionPane.showMessageDialog(null, "Voce recebeu 100 de vida");
			}
			
			if(doubleDamage.isSelected()){
				
				new DoubleDamageSword().aplicarEfeito(player, enemy);
				
				attack.setText("Ataque do player: " + player.getAttack());
				grupo.getSelection().setEnabled(false);
				grupo.clearSelection();
				JOptionPane.showMessageDialog(null, "Voce vai aplicar o dobro de dano");
			}
			
			if(dodge.isSelected()){
				
				new DodgeArmor().aplicarEfeito(player, enemy);
				grupo.getSelection().setEnabled(false);
				grupo.clearSelection();
				JOptionPane.showMessageDialog(null, "Voce vai desviar do proximo ataque inimigo");
			}
		break;
			
		case "Atacar":
			player.battle(enemy);
			Engine.controle = false;
			
			hpPlayer.setText("HP Player: " + player.getLife());
			hpEnemy.setText("HP Enemy: " + enemy.getLife());
			
			player.setAttack(DoubleDamageSword.danoBase);
			attack.setText("Ataque do player: " + player.getAttack());
			
			enemy.setAttack(DodgeArmor.danoBase);
			
			break;
					
		}
		
	}
}
