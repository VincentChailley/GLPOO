package Vue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.event.*;


public class Chrono extends JPanel
{
	@Override
    public void paintComponent(Graphics g){
		super.paintComponent(g);
        ImageIcon icon = new ImageIcon("cadre_tuto.jpg");
        g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
  } 

	private static final long serialVersionUID = 1L;
	
	private static int heure=0,minute=0,seconde=0;
	private Font font = new Font("Arial", Font.BOLD, 30);
	private int delais=1000;
	private JLabel label;
	GridBagConstraints gbc =new GridBagConstraints();
	private Timer timer;
	
	public Chrono(){
		buildChrono();
		setLayout(new BorderLayout());
		add(label, BorderLayout.CENTER);
		setBorder(BorderFactory.createLineBorder(Color.WHITE));
		setPreferredSize(new Dimension(200, 100));
	}
	public void buildChrono()	{
		ActionListener tache_timer;
		
		label = new JLabel(heure+":"+minute+":"+seconde); 
		label.setFont(font);
		label.setBorder(new EmptyBorder(10,65,10,10));
		
		
		tache_timer= new ActionListener()
		{
			public void actionPerformed(ActionEvent e1)
			{
				seconde++;
				if(seconde==60)
				{
					seconde=0;
					minute++;
				}
				if(minute==60)
				{
					minute=0;
					heure++;
				}
				label.setText(heure+":"+minute+":"+seconde);
			}
		};
		
		timer= new Timer(delais,tache_timer);
	}
	
	public void startChrono(){
		timer.start();		
	}
	
	public void stopChrono(){
		timer.stop();
	}
	
	public String toString(){
		return "Time: "+heure+":"+minute+":"+seconde;
	}
}