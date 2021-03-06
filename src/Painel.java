import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Painel extends JPanel {
	
	private String url;
			
	private BufferedImage img = null;    
    private int x = 0;  
    private int y = 0;  
    
    public Painel(String url) throws IOException {  
        this.img = ImageIO.read(new File(url));  
    }  
    @Override  
    public void paintComponent(Graphics g) {  
        super.paintComponent(g);        
        Graphics gr = (Graphics2D)g.create();  
        gr.drawImage(img, x, y,this.getWidth(),this.getHeight(),this);  
        gr.dispose();     
    }    

}
