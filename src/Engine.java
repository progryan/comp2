import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 * Created by filipebraida on 31/05/16.
 */


public class Engine extends JFrame implements ActionListener{
	
	static Scanner in = new Scanner(System.in);
	
	private JLabel texto, escolha, espaco, titulo;
	private ArrayList<JRadioButton> opcoes = new ArrayList<JRadioButton>();
	private ButtonGroup grupo = new ButtonGroup();
	private Painel painel;
	
	
	private JButton confirmar, sair;
	
	public static boolean controle = true;
	
	public static int i, j;
	
    public Engine() {
    	super("** BECOME A LEGEND **"); 
    	
       	try{
    		painel = new Painel("imagens/menu.jpg");
    	}
    	catch(IOException e){
    		e.printStackTrace();                
    	}
      
    	Container cp = getContentPane();
    	painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
    	
        Book book = Engine.createBook();
        
        titulo = new JLabel(book.showHistoryBook());
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Purisa", Font.BOLD, 30));
        painel.add(titulo);    

        do {
        	            
            texto = new JLabel(book.showHistory());
            texto.setFont(new Font("Purisa", Font.ITALIC, 30));
            texto.setForeground(Color.CYAN);
            painel.add(texto);
            
            for(Choice choice:book.nextEvents()) {
                
                JRadioButton op = new JRadioButton(choice.getDescription());
                op.setForeground(Color.GREEN);
                op.setFont(new Font("Purisa", Font.BOLD, 25));
                op.setContentAreaFilled(false);
               
            	   opcoes.add(op);	
            	   
            }
            
            espaco = new JLabel("<html>" + "</html>");
            painel.add(espaco);
            
            for(JRadioButton op : opcoes){
            	if(!(op.getActionCommand().equals(".") || op.getActionCommand().equals(","))){
            		grupo.add(op);
                	painel.add(op);
            	}
            	
            }
            
            confirmar = new JButton("Confirmar");
          	sair = new JButton("Sair");
          
            confirmar.addActionListener(this);
          	sair.addActionListener(this);
            sair.setVisible(false);
                     
            for(JRadioButton op : opcoes){
            	if(op.getActionCommand().equals(",")){
            		confirmar.setVisible(false);
            		sair.setVisible(true);
            	}
            }
            painel.add(confirmar);
            painel.add(sair);
            
            cp.add(painel);
            
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(1280, 720);
            setResizable(false);
            setLocationRelativeTo(null);
            setVisible(true);
                 	
            
            do {
            	while(controle){
            		System.out.println(""); 
            			
            	}
            	controle = true;
            	grupo.clearSelection();
            	
                //Batalha
                
                if(book.selectChoice(i) instanceof BattleChoice){
                	this.setVisible(false);
                	Enemy enemy = ((BattleChoice)book.selectChoice(i)).getEnemy(); 
                	Batalha battle = new Batalha(book.getPlayer(), enemy);
                
                do{
                	
                	while(controle){
                		System.out.println(""); 
                			
                	}
                	controle = true;
                	
                	
                	if(!book.getPlayer().isAlive() ||(!book.getPlayer().isAlive() && !enemy.isAlive())){
                		
                		JOptionPane.showMessageDialog(null, "Game Over");
        				creditos();
                	}

                	if(!enemy.isAlive()){
        				
                		JOptionPane.showMessageDialog(null, "Voce venceu");
        				book.getPlayer().setLife(book.getPlayer().getLife()+100);
        				book.getPlayer().setAttack(book.getPlayer().getAttack()+100);
        				DoubleDamageSword.danoBase += 100;
        				battle.dispose();
        				this.setVisible(true);
        				break;
        			}
                	            	               	
                	
                  }while(enemy.isAlive() || book.getPlayer().isAlive());
                	 	
                }
                
                for(JRadioButton op : opcoes)
            		op.setVisible(false);
                
                titulo.setVisible(false);
                texto.setVisible(false);
                espaco.setVisible(false);
                confirmar.setVisible(false);
                
            	
            } while(!book.nextEvent(i));
        } while(!book.isTheEnd());

        System.out.println(book.showHistory());
    }

    public static Book createBook() {

        Player player = new Player(1000, 100);
        String nome = JOptionPane.showInputDialog("Ola, digite seu nome: ");
        
        if(nome == null){
        	JOptionPane.showMessageDialog(null, "Voce saiu do jogo!");
        	creditos();
        }
        else 	
        	player.setNome(nome);
        
        ArrayList<Choice> escolhas1 = new ArrayList<Choice>();
        ArrayList<Choice> escolhas2 = new ArrayList<Choice>();
        ArrayList<Choice> escolhas3 = new ArrayList<Choice>();
        ArrayList<Choice> escolhas4 = new ArrayList<Choice>();
        ArrayList<Choice> escolhas5 = new ArrayList<Choice>();
        ArrayList<Choice> escolhas6 = new ArrayList<Choice>();
        ArrayList<Choice> escolhas7 = new ArrayList<Choice>(); 
        ArrayList<Choice> escolhas8 = new ArrayList<Choice>(); 
        ArrayList<Choice> escolhas9 = new ArrayList<Choice>();
        ArrayList<Choice> escolhasFinal = new ArrayList<Choice>();
      
        Choice finall = new BlankChoice(",",null);
        escolhasFinal.add(finall);

        Event finalEvent = new BlankEvent("<html>"+"Muito bem, grande " + player.getNome() +"! Voce provou que es um grande campeao"
        		+ ". Boa sorte em sua jornada e obrigado por jogar este jogo." + "</html>", escolhasFinal);
        Enemy dragon = new Enemy(600,100);
        dragon.setID(1);
        BattleChoice batalhaDrag = new BattleChoice("<html>"+"Tentar matar o dragao e provar que es o campeao"+"</html>", finalEvent, dragon);
        
        Event desistirEvent = new BlankEvent("<html>"+"Toda comunicade te rejeita. Voce eh desonrado! "+"</html>", escolhasFinal);
        Choice desistir = new BlankChoice("<html>"+"Desistir e enfrentar as consequencias"+"</html>", desistirEvent);
        
        escolhas6.add(batalhaDrag);
        escolhas6.add(desistir);
        
        
        Event eventoAA = new BlankEvent("<html>" + "Voce encontrou um pergaminho no chao e o leu. "
        		+ "O pergaminho dizia que havia um dragao perto do rio onde fica o aronguejo. "
        		+ "De acordo com o pergaminho, o dragao dava habilidades jamais vistas para um mortal, alem de tornar quem"
        		+ " o vencesse o mais poderoso. O que voce deseja fazer?" + "</html>",escolhas6);
        
        Event lutaZilean = new BlankEvent("O terrivel Zilean te derrotou.", escolhasFinal);
        Enemy Zilean = new Enemy(1500, 100);
        Zilean.setID(2);
        BattleChoice ZileanLuta = new BattleChoice("LUTAR", lutaZilean, Zilean);
        BattleChoice ZileanLuta2 = new BattleChoice(".", lutaZilean, Zilean);
        escolhas5.add(ZileanLuta);
        escolhas5.add(ZileanLuta2);
        
       
        Event eventoAB = new BlankEvent("<html>" + "Andando para fora da selva, depois de um tempo, " + player.getNome() + " se ve"
        		+ " perdido em Summoner's Rift, quando encontra um velho que o vem atacar. Lute com ele e tente"
        		+ " sobreviver." + "</html>", escolhas5);
        
        Choice escolhaAA = new BlankChoice("<html>" + "Ignorar e continuar seguindo pela selva", eventoAA);
        Choice escolhaAB = new BlankChoice("<html>" + "Confiar e seguir a orientacao", eventoAB);
        
        escolhas4.add(escolhaAA);
        escolhas4.add(escolhaAB);
        
        Event lutaWarwick = new BlankEvent("<html>" + "Voce venceu a luta! O Warwick te orientou a nao continuar indo pela selva, "
        		+ "pois coisas terriveis podem acontecer. Decida o que voce vai fazer!" + "</html>", escolhas4);
        Enemy Warwick = new Enemy(1000, 100);
        Warwick.setID(3);
        
        // MID
        
        Event BaraoEvent = new BlankEvent("<html>" + "Voce derrotou o Barao e provou para todos que es o escolhido."
        		+ " Os guerreiros testemunharam seus feitos para todo o povo, entao o pove lhe escolheu como rei"
        		+ " e agora voce possui honra e respeito em todo o reino. "
        		+ "Parabens pela consquista, invocador " + player.getNome() + "!" + "</html>", escolhasFinal);
        
        Event guerreirosEvent = new BlankEvent("<html>" +"Voce nao resistiu aos ferimentos da batalha e "
        		+ "pereceu. Voce nao sera lembrado nem pela sua mae." + "</html>", escolhasFinal);
        
        Enemy Barao = new Enemy(600,100);
        Enemy guerreiros = new Enemy(1000, 100);
        Barao.setID(4);
        guerreiros.setID(5);
        
        BattleChoice battleGuerreiros = new BattleChoice("<html>" + "Tentar matar todos os guerreiros", guerreirosEvent, guerreiros);
        BattleChoice battleBarao = new BattleChoice("<html>" + "Lutar contra o Barao",BaraoEvent , Barao);
        
        escolhas8.add(battleBarao);
        escolhas8.add(battleGuerreiros);
        
        Event eventoBA = new BlankEvent("<html>" + "Voce encontrou o Barao Na'shor, o ser mais temido de toda Rift. "
        		+ "Alem disso, encontrou tambem guerreiros perto do covil do Barao."
        		+ " Os guerreiros sao seus inimigos e querem lhe matar. "
        		+ "Voce precisa provar para eles que es o escolhido e derrotara o grande monstro. "
        		+ "Se nao lutares contra a fera, os guerreiros irao lhe matar. Decida o que voce vai fazer!" + "</html>", escolhas8);
        
        Enemy drag = new Enemy(600,100);
        drag.setID(6);
        Event eventDrag = new BlankEvent("<html>" + "Muito bem, invocador " + player.getNome() + ", voce eh o grande campeao"
        		+ ". Voce tera muitos prestigios e responsabilidades daqui pra frente."
        		+ " Summoner's Rift eh um lugar melhor gracas a voce!" + "</html>", escolhasFinal);
        
        BattleChoice battleDrag = new BattleChoice("Enfrentar o dragao", eventDrag, drag);
        BattleChoice battleDrag2 = new BattleChoice(".", eventDrag, drag);
        escolhas9.add(battleDrag);
        escolhas9.add(battleDrag2);
   
        Event eventoBB = new BlankEvent("<html>" + "Seguindo a direita, mais a frente, voce avistou um covil repleto de"
        		+ " odio e terror. Os ventos cortantes, que traziam o cheiro do sangue de milhares de guerreiros"
        		+ " que morreram tentando derrotar o grande e temivel dragao de Rift. O velho sabio Zilean havia"
        		+ " dito que quem derrotasse o dragao seria o campeao. Agora va e lute pela sua"
        		+ " honra. Enfrente e mate o grande dragao!" + "</html>", escolhas9);
        
        Choice escolhaBA= new BlankChoice("Esquerda", eventoBA);
        Choice escolhaBB = new BlankChoice("Direita",eventoBB);
        
        escolhas7.add(escolhaBA);
        escolhas7.add(escolhaBB);
        
        Event lutaZilean2  = new BlankEvent("<html>" + "Zilean reconheceu a derrota e resolveu lhe ajudar com informacoes."
        		+ "Zilean, mago do tempo, que possui o dominio do futuro"
        		+ " e do passado, lhe deu dois caminhos para seguir. "
        		+ "A esquerda vai te levar para o terrivel Barao Na'shor, grande monstro com grandes recompensas, porem muito forte. "
        		+ "A direita vai te levar para o um grande dragrao, responsavel pela morte de muitos guerreiros que o enfrentaram pela gloria."
        		+ "Sua escolha vai influenciar diretamente no seu futuro. Escolha sabiamente!" + "</html>", escolhas7);
        
        
        Enemy Zilean2 = new Enemy(1000, 100);
        Zilean2.setID(7);
        
        BattleChoice batalhaWarwick = new BattleChoice("LUTAR", lutaWarwick, Warwick);
        BattleChoice batalhaWarwick2 = new BattleChoice(".", lutaWarwick, Warwick);
        
        escolhas2.add(batalhaWarwick);
        escolhas2.add(batalhaWarwick2);
        
        BattleChoice batalhaZilean2 = new BattleChoice("LUTAR", lutaZilean2, Zilean2);
        BattleChoice batalhaZilean3 = new BattleChoice(".", lutaZilean2, Zilean2);
        escolhas3.add(batalhaZilean2);
        escolhas3.add(batalhaZilean3);  
        
        Event eventoA = new BlankEvent("<html>"+"Voce encontrou o lobo Warwick. Lute contra ele e tente vencer!"+"</html>", escolhas2);
        Event eventoB = new BlankEvent("<html>" + "Voce encontrou o mago do tempo Zilean. "
        		+ "Enfrente-o!", escolhas3);
        
        Choice escolhaA = new BlankChoice("Pela Selva", eventoA);
        Choice escolhaB = new BlankChoice("Pelo Mid", eventoB);
       
        
        escolhas1.add(escolhaA);
        escolhas1.add(escolhaB);
          
        Event eventoInitial = new BlankEvent("<html>" + "Depois de uma longa e cansativa viagem pelos arredores do mundo, "
        		+ player.getNome() + " percebe que esta perdido num lugar um tanto sombrio. "
        				+ "Neste lugar ha duas rotas para seguir, voce deve escolhas uma delas e por a "
        				+ "prova sua sorte" + "</html>" ,escolhas1);

        Book livro = new Book("Bem-vindo a Summoner's Rift, invocador.", eventoInitial, player);

        return livro;
    }
    public static void creditos(){
    	JOptionPane.showMessageDialog(null, "<html>"+"UFRRJ-IM\n"
				+ "AA de Computacao II\n"
				+ "Alunos:\n"
				+ "-> Matheus Oliveira\n"
				+ "-> Nelson Neto\n"
				+ "-> Ryan Arthur");
    	System.exit(0);
     
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		
		int aux;
		
		for(JRadioButton op : opcoes){
			
			if(op.isSelected()){				
				i = opcoes.indexOf(op)%2;
				controle = false;
				break;
			}
			
		}			
		
		if(e.getActionCommand().equals("Sair")){
    	
			creditos();
		}
		
	}
  
}