package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import GUI.MainGUI.ButtonListener;
import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.IRestaurantProcessing;
import businessLayer.MenuItem;
import businessLayer.Order;
import businessLayer.Restaurant;
/**
 * 
 * @author Ionut
 *Interfata grafica pentru Administrator. Aici sepot vizualiza, adauga, modifica sau sterge produse din meniul restaurantului. 
 *Editarea sau stergerea unui item se face prin  selectarea liniei din tabel(click) si apasarea butonului corespunzator.
 */
public class AdministratorGUI implements IRestaurantProcessing, java.io.Serializable{
	
	JFrame main_frame;
	JTable table = new JTable();
    
	Object[] columns = {"Name","Price","B/C"};
    Restaurant r;
	
    public  AdministratorGUI(JFrame main_frame,Restaurant res) 
	{ 
		this.main_frame=main_frame;
		r=res;
		this.creare();
		
	}
	/**
	 * metoda ce tine de partea de interfata grafica. Se creeaza butoane, text fields, tabele etc.
	 */
	 public void creare(){
		
		    JFrame frame = new JFrame("ADMINISTRATOR");
	          
		    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.setSize(600,450);
	        
	        JButton button1 = new JButton("back");
	        JButton button2 = new JButton("add");
	        JButton button3 = new JButton("delete");
	        JButton button4 = new JButton("edit");
	        
	        JTextField name = new JTextField("name");
	        JTextField price = new JTextField("price");
	        JTextField type = new JTextField("type");
	        JTextField comp = new JTextField("composition");
            
	        JPanel panel = new JPanel();
	      
	          panel.add(button1);
	          panel.add(button2);	          
	         
	          DefaultTableModel model = new DefaultTableModel();
	          model.setColumnIdentifiers(columns);
	          table.setModel(model);
	          JScrollPane pane = new JScrollPane(table);
	          pane.setBounds(200, 100, 250, 250);
	          
	          button1.setBounds(10, 20, 100, 25);
	          button2.setBounds(150, 20, 100, 25);
	          button3.setBounds(290, 20, 100, 25);
	          button4.setBounds(430, 20, 100, 25);
	          name.setBounds(10,100,150,20);
	          price.setBounds(10,140,150,20);
	          type.setBounds(10,180,150,20);
	          comp.setBounds(10,220,150,20);
	          frame.setLayout(null);
	          
	          frame.add(pane);
	         
	          ArrayList<MenuItem> produse = r.getItems();
	          for(MenuItem i:produse)
	          {		
	        	  String type_p;
	        	  if(i instanceof BaseProduct)
	        		  type_p="B";
	        	  else 
	        		  type_p="C";
	        		  
	        	  String[] rowData = {i.getName(),Integer.toString(i.getPrice()),type_p};
					model.addRow(rowData);	
	          }
	          
	          button1.addActionListener(new ButtonListener(1,frame,main_frame));
	          button2.addActionListener(new ButtonListener2(model,name,price,type,comp,0));
	          button3.addActionListener(new ButtonListener2(model,name,price,type,comp,1));
	          button4.addActionListener(new ButtonListener2(model,name,price,type,comp,2));
	          
	          frame.add(button1);
	          frame.add(button2);
	          frame.add(button3);
	          frame.add(button4);
	          
	          frame.add(name);
	          frame.add(price);
	          frame.add(type);
	          frame.add(comp);
	          
	          frame.setLocation(440,300);
	          frame.getContentPane().add(BorderLayout.BEFORE_FIRST_LINE, panel);
	      
	        
	          frame.setVisible(true);
	    }
	 
	 /**
	  * 
	  * @author Ionut
	  *ButtonListener ce tine de modul in care sunt afisate interfetele la un moment dat (visible/nonvisible)
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
 * butonul de back: fereastra administratorului dispare, iar cea principala apare
 */
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(i==1)
				{
					frame.setVisible(false);
					main_frame.setVisible(true);
				}
				
			}
		
			}
	 /**
	  * 
	  * @author Ionut
	  *ButtonListener ce tine de partea de editare, adaugare si stergere a obiectelor
	  */
	 public class ButtonListener2 implements ActionListener{
		 		
		 JTextField field1;
		 JTextField field2;
		 JTextField field3;
		 JTextField field4;
		 DefaultTableModel model;
		int c;
			public ButtonListener2(DefaultTableModel m, JTextField f1,JTextField f2,JTextField f3,JTextField f4,int cmd)
			{
			
				field1 = f1;
				field2=f2;
				field3=f3;
				field4=f4;
				model = m;
				c=cmd;
				
				
			}
			
			/**
			 * in functie de comanda ceruta de administrator(c), se executa algoritmii necesari. 
			 * De exemplu, c==0 inseamna adaugare unui produs nou
			 */
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(c==0)
					{
					
					try {
					MenuItem a;
					if(field3.getText().contentEquals("B"))
						 {
						field3.setBackground(field1.getBackground());
						field2.setBackground(field1.getBackground());
						a = new BaseProduct(Integer.parseInt(field2.getText()),field1.getText());
						addMenuItem(a);
						String[] rowData = {field1.getText(),field2.getText(),field3.getText()};
						model.addRow(rowData);
						}
					else 
						if(field3.getText().contentEquals("C"))
						{
							field4.setBackground(field1.getBackground());
							field3.setBackground(field1.getBackground());
							field2.setBackground(field1.getBackground());
							
							String s = field4.getText();
							String s2 = field2.getText();
							int suma=0;
							String[] arr = s.split(" ");
							String[] arr2 = s2.split(" ");
							if(arr.length == arr2.length)
							{ArrayList<MenuItem> l =new ArrayList<MenuItem>();
							
							for(int i = 0;i<arr.length;i++)
							{
								MenuItem x = new BaseProduct(Integer.parseInt(arr2[i]),arr[i]);
								addMenuItem(x);
								l.add(x);
								suma+=Integer.parseInt(arr2[i]);
							}
							
							a= new CompositeProduct(suma,field1.getText(),l);
							addMenuItem(a);
							String[] rowData = {field1.getText(),Integer.toString(suma),field3.getText()};
							model.addRow(rowData);
							suma=0;
							}
							else 
							{
								field2.setBackground(new Color(255,0,0));
								field2.setText("Nr preturi != NR produse"+arr.length+" "+arr2.length);
							}
						
						}
						else 
							{
							field3.setBackground(new Color(255,0,0));
							field3.setText("ONLY B OR C");
							}
					}
					catch (NumberFormatException nfe)
				    {field2.setBackground(new Color(255,0,0));
						field2.setText("ONLY INTEGER!!!" );
				      System.out.println("NumberFormatException: " + nfe.getMessage());
				    }
					}
				if(c==1)
				{
					 int i = table.getSelectedRow();
		                if(i >= 0){
		                    MenuItem a;
		                    if(model.getValueAt(i, 2).equals("B"))
		                    	a=new BaseProduct(Integer.parseInt((String) model.getValueAt(i, 1)),(String)model.getValueAt(i, 0));
		                    else 
		                    	a=new CompositeProduct(Integer.parseInt((String) model.getValueAt(i, 1)),(String)model.getValueAt(i, 0),null);
		                    model.removeRow(i);
		                    deleteMenuItem(a);
		                   
		                }
				}
				if(c==2)
				{
					try {
					 int i = table.getSelectedRow();
		             if(i >= 0 && (field3.getText().contentEquals("B")))
		                		{
		                	field3.setBackground(new Color(255,255,255));
		                	field2.setBackground(new Color(255,255,255));
	                  	
		                  	 MenuItem a = null,b = null;
			                 if(model.getValueAt(i, 2).equals("B"))
			                    	a=new BaseProduct(Integer.parseInt((String) (String)model.getValueAt(i, 1)),(String)model.getValueAt(i, 0));
			                
			                 
			                 if(field3.getText().equals("B"))
			                     b = new BaseProduct(Integer.parseInt((String)field2.getText()),field1.getText());
			               
			                 
			                 model.setValueAt(field2.getText(), i, 1);		                	
			                 model.setValueAt(field1.getText(), i, 0);	                		                  	
			                 model.setValueAt(field3.getText(), i, 2);
			                 editMenuItem(a,b);
		                }
		                else {
		                	field3.setBackground(new Color(255,0,0));
							field3.setText("ONLY B FOR EDIT ");
		                }
					}
					catch (NumberFormatException nfe)
				    {field2.setBackground(new Color(255,0,0));
						field2.setText("ONLY INTEGER!!!" );
				      System.out.println("NumberFormatException: " + nfe.getMessage());
				    }
					
				}
			}
		
			}

	
	

	public void editMenuItem(MenuItem a,MenuItem b) {
		// TODO Auto-generated method stub
		r.editMenuItem(a,b);
	}

	public void addNewOrder() {
		// TODO Auto-generated method stub
		
	}

	public int computePrice(int id) {
		// TODO Auto-generated method stub
		
		return r.computePrice(id);
	}


	public void deleteMenuItem(MenuItem i) {
		// TODO Auto-generated method stub
		r.deleteMenuItem(i);
	}

	public void addNewOrder(Order o) {
		// TODO Auto-generated method stub
		r.addNewOrder(o);
	}

	public void addMenuItem(MenuItem i) {
		// TODO Auto-generated method stub
		
		r.addMenuItem(i);
	}
	public void generateBill(int id) {
		// TODO Auto-generated method stub
		
	}
}
