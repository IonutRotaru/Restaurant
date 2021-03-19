package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLDocument.Iterator;

import com.itextpdf.text.DocumentException;

import GUI.AdministratorGUI.ButtonListener;
import GUI.AdministratorGUI.ButtonListener2;
import businessLayer.IRestaurantProcessing;
import businessLayer.MenuItem;
import businessLayer.Order;
import businessLayer.Restaurant;
import data_layer.Bill;

/**
 * 
 * @author Ionut
 * Interfata grafica in care se pot zivualiza sau adauga comenzi prin alegerea mesei si a produselor.
 *  Pentru a adauga o comandanoua, se introduce numarul mesei, apoi se selecteaza produsele dorite din lista. Daca se doresc mai multe produse, 
 *  ele vor aparea listate in casuta text needitabila.
 *	Pentru a genera nota de plata sau fisieru txt, se da click pe linia din tabel dorita si se apasa butonul.
 */
public class WaiterGUI implements IRestaurantProcessing,java.io.Serializable{

JFrame main_frame;
JTable table = new JTable();
Object[] columns = {"id","Table","Order","Price"};
int id=100;
int ok=0;
int count =0;
Bill fr ;
String toAdd[] = new String[10];
Restaurant r;

	public  WaiterGUI(JFrame main_frame,Restaurant res) 
	{ this.main_frame=main_frame;
	  r=res;
	  fr=new Bill(r);
		this.creare();
	}
	/**
	 * metoda ce tine de estetica interfetei
	 */
	 public void creare(){
		
		    JFrame frame = new JFrame("WAITER");
	          
		    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	         frame.setSize(800,450);
	        
	          JButton button1 = new JButton("back");
	          JButton button2 = new JButton("add");
		      JButton button3 = new JButton("compute price");
		      JButton button4 = new JButton("generate bill");
	          
		      JTextField tables = new JTextField("select table");
		      JTextField items = new JTextField("selected items");
		      items.setEditable(false);
		      DefaultTableModel model = new DefaultTableModel();
	          model.setColumnIdentifiers(columns);
	          table.setModel(model);
	          JScrollPane pane = new JScrollPane(table);
	          pane.setBounds(250, 100, 450, 250);
	          
	          button1.setBounds(10, 20, 100, 25);
	          button2.setBounds(150, 20, 100, 25);
	          button3.setBounds(290, 20, 100, 25);
	          button4.setBounds(430, 20, 100, 25);
	          tables.setBounds(10,100,100,20);
	          items.setBounds(10,140,190,20);
	          frame.setLayout(null);
	          frame.add(pane);
	          
	          String[] petStrings = r.getNames();
	          JComboBox drop = new JComboBox(petStrings);
	        
	          button1.addActionListener(new ButtonListener(1,frame,main_frame));
	          button2.addActionListener(new ButtonListener2(drop,model,tables,items,0));
	          button3.addActionListener(new ButtonListener2(drop,model,tables,items,1));
	          button4.addActionListener(new ButtonListener2(drop,model,tables,items,2));
	          drop.addActionListener(new ButtonListener2(drop,model,tables,items,3));
	          
	          JPanel panel = new JPanel();
	         
	          drop.setSelectedIndex(20);
	          drop.setBounds(10, 180, 100, 20);
	          
	          frame.add(drop);
	          frame.add(button1);
	          frame.add(button2);
	          frame.add(button3);
	          frame.add(button4);
	          frame.add(tables);
	          frame.add(items);
	         
	          HashMap<Order,ArrayList<MenuItem>> orders = r.getOrders();
	          
	         
	          for (Map.Entry mapElement : orders.entrySet()) { 
	        	  
	        	  ArrayList<MenuItem> m = (ArrayList<MenuItem>) mapElement.getValue();
	        	  Order or = (Order) mapElement.getKey();
	        	  String myItems = "";
	        	  for(MenuItem mi:m)
	        	  {
	        		  myItems+= mi.getName()+" ";
	        	  }
	        	  String[] rowData = {Integer.toString(or.getId()), Integer.toString(((Order) mapElement.getKey()).getTable()),myItems,"-"};
	             model.addRow(rowData);
	          } 
	          
	          
	     
	          frame.setLocation(440,300);
	          frame.getContentPane().add(BorderLayout.CENTER, panel);
	         // frame.getContentPane().add(button1);
	        //  frame.getContentPane().add(button2);
	          frame.setVisible(true);
	    }
	 /**
	  * 
	  * @author Ionut
	  *Listener ce tine de partea de aparitie/disparitie a interfetelor
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
	  *Listener pentru operatiile Waiter-ului: introducere id masa, selectare produse
	  */
	 public class ButtonListener2 implements ActionListener{
		 
			
			
		 JTextField field1;
		 JTextField field2;
		 DefaultTableModel model;
		 JComboBox drop;
		 int c;
			public ButtonListener2(JComboBox d,DefaultTableModel m, JTextField f1,JTextField f2,int cmd)
			{
			
				field1 = f1;
				field2=f2;
				model = m;
				c=cmd;
				drop = d;
				
			}
			
/**
 * in functie de butonulapasat, se executa un algoritm. De ex c=1 adauga nota de plata in tabel, lalinia selectata.
 */
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(c==0)
					{
					try {
					id=r.getId();
					String[] rowData = {Integer.toString(id),field1.getText(),field2.getText(),"-"};
					field1.setBackground(new Color(255,255,255));
				    field2.setText("");
				    ok=0;
				    Order o = new Order(id, "1 decembrie", Integer.parseInt(field1.getText()));
				    addNewOrder(o);
				    ArrayList<MenuItem> itemsToAdd = r.getItemsToAdd(toAdd);
				    if(itemsToAdd.size()>0)
				    
				    {
				    r.addMap(o, itemsToAdd);
				   
				    count=0;
				   
				    field2.setBackground(new Color(255,255,255));
				    toAdd=new String[10];
				    
				    model.addRow(rowData);	
				    }
				    
				    else
				    {
				    	field2.setBackground(new Color(255,0,0));
				    	field2.setText("TOO FEW PRODUCTS");
				    	 count=0;
				    	 toAdd=new String[10];
				    	
				    }
				    
				    
					}
				    catch (NumberFormatException nfe)
				    {field1.setBackground(new Color(255,0,0));
						field1.setText("ONLY INTEGER!!!" );
				      System.out.println("NumberFormatException: " + nfe.getMessage());
				    }
					}
				if(c==1)
				{
					 int i = table.getSelectedRow();
		                if(i >= 0){
		                	
		                   int p=r.computePrice(Integer.parseInt((String)model.getValueAt(i, 0)));
		                    model.setValueAt(Integer.toString(p), i, 3);
		                }
				}
				if(c==2)
				{
					
					 int i = table.getSelectedRow();
		                if(i >= 0){
		                	
		                	try {
								fr.generateBill(Integer.parseInt((String)model.getValueAt(i, 0)));
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (NumberFormatException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} 
		                	System.out.println("Bill generated");
		                	
		                }
				}
				if(c==3)
				{	
					
					
				
					String a=(String)drop.getSelectedItem();
					if(a!=null)
						{	
						if(ok==0)
							field2.setText("");
						ok=1;
						String text = field2.getText();
						field2.setText(text+ " " +a);
						toAdd[count++]=a;
						
						}
				}
			}
		
			}


	

	public int computePrice(int id) {
		// TODO Auto-generated method stub
		return r.computePrice(id);
	}

	public void generateBill(int id) {
		// TODO Auto-generated method stub
		
	}

	public void addMenuItem(MenuItem i) {
		// TODO Auto-generated method stub
		r.addMenuItem(i);
	}

	public void deleteMenuItem(MenuItem i) {
		// TODO Auto-generated method stub
		r.deleteMenuItem(i);
	}

	public void addNewOrder(Order o) {
		// TODO Auto-generated method stub
		r.addNewOrder(o);
	}

	public void editMenuItem(MenuItem i, MenuItem j) {
		// TODO Auto-generated method stub
		r.editMenuItem(i, j);
	}
	
}
