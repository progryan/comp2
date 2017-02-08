import java.util.ArrayList;

/**
 * Created by filipebraida on 31/05/16.
 */
public class Player extends Character {
	
	private String nome;
	
	public String getNome(){
		
		return nome;
	}
	
	public void setNome(String nome){
		
		this.nome = nome;
	}

    public Player(int life, int attack) {
        super(life, attack);
       
    }
}
