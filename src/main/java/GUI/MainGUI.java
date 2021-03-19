package GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import businessLayer.Restaurant;
import data_layer.RestaurantSerializator;
/**
 * 
 * @author Ionut
 *Interfata principala: de aici se poate ajunge in oricare dintre celelalte 3 interfete grafice
 */
public class MainGUI implements java.io.Serializable {
	Restaurant r;
	
	public  MainGUI(Restaurant res) 
	{   r=res;
	
		this.creare();
	}
	/**
	 * metoda pentru crearea butoanelor
	 */
	 public void creare(){
		
		       JFrame frame = new JFrame("MAIN");
	           frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	           frame.setSize(600,150);
	        
	          JButton button1 = new JButton("Administrator GUI ");
	          JButton button2 = new JButton("Waiter GUI");
	          JButton button3 = new JButton("Chef GUI");
	          JButton button4 = new JButton("Save");
	          
	          button1.addActionListener(new ButtonListener(1,frame));
	          button2.addActionListener(new ButtonListener(2,frame));
	          button3.addActionListener(new ButtonListener(3,frame));
	          button4.addActionListener(new ButtonListener(4,frame));
	          
	          
	          JPanel panel = new JPanel();
	         
	          panel.add(button1);
	          panel.add(button2);
	          panel.add(button3);
	          panel.add(button4);
	          
	            ChefGUI a = new ChefGUI(frame,r);	
				r.addObserver((Observer) a);
				ChefGUI.setFrameVisible(false);
				
	     
	          frame.setLocation(440,300);
	          frame.getContentPane().add(BorderLayout.CENTER, panel);
	        
	          frame.setVisible(true);
	    }
	 /**
	  * 
	  * @author Ionut
	  *
	  */
	 public class ButtonListener implements ActionListener{
		 
			
			int i;
			JFrame frame;
			
			public ButtonListener(int i,JFrame f)
			{
				this.i=i;
				frame = f;
			}
			
/**
 * in functie de butoul apasat, o anumita interfata va deveni vizibila, iar cea principala va disparea
 */
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(i==1)
				{
					AdministratorGUI a = new AdministratorGUI(frame,r);			
					frame.setVisible(false);
				}
				if(i==2)
				{
					WaiterGUI a = new WaiterGUI(frame,r);			
					frame.setVisible(false);
				}
				
				if(i==4)
				{
					RestaurantSerializator rs=new RestaurantSerializator(r);
					rs.save();
				}
			}
			}
	
}
