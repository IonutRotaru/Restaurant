package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import businessLayer.Restaurant;

/**
 * Interfata grafica a Chef-ului. Are un buton de back si este vizibila cand se adauga o comanda noua.
 * @author Ionut
 *
 */
public class ChefGUI implements Observer, java.io.Serializable{

    JFrame main_frame;
	Restaurant r;
	static JFrame frame = new JFrame("CHEF!");
	
	public  ChefGUI(JFrame main_frame,Restaurant res) 
	{ this.main_frame=main_frame;
	r=res;
		this.creare();
	}
	
	 public void creare(){
		
		   
	          
		    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	         frame.setSize(600,150);
	        
	          JButton button1 = new JButton("exit");
	        
	          
	          button1.addActionListener(new ButtonListener(1,frame,main_frame));
	          
	         
	          
	          JPanel panel = new JPanel();
	         
	          panel.add(button1);
	       
	          
	         
	     
	          frame.setLocation(440,300);
	          frame.getContentPane().add(BorderLayout.CENTER, panel);
	        
	         
	    }
	 
	

	 public static void setFrameVisible(boolean visible) {
		 frame.setVisible(visible);
	 }
	 
	 /**
	  * 
	  * @author Ionut
	  *ButtonListener ce tine de partea de aparitie/disparitie a interfetei
	  */
	 public class ButtonListener implements ActionListener{
		 
			
			int i;
			JFrame frame;
			JFrame main_frame;
			
			public ButtonListener(int i,JFrame f,JFrame main_frame)
			{
				this.i=i;
				frame = f;
				this.main_frame=main_frame;
				
			}
			
			/**
			 * butonul back face ca interfata Chef sa se ichida 
			 */
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(i==1)
				{
					frame.setVisible(false);
					//main_frame.setVisible(true);
				}
				
			}
		
			}
	
	 /**
	  * la adaugarea unei comenzi, aceasta interfata este vizibila
	  */
	 public void update(Observable o, Object arg) {
			// TODO Auto-generated method stub
			frame.setVisible(true);
			JOptionPane.showConfirmDialog(null, arg, "Notification !", 2);
			System.out.println("Chef anuntat!");
		}

	
}
