package data_layer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import businessLayer.MenuItem;
import businessLayer.Order;
import businessLayer.Restaurant;
/**
 * Clasa genereaza nota de plata pentru comanda dorita.
 * @author Ionut
 *
 */
public class Bill implements java.io.Serializable {

	Restaurant R;
	int index=0;
	
	public Bill (Restaurant res)
	{
		R=res;
		
	}
	
	/**
	 * In aceasta metoda se genereazafisierul txt cu detaliile comenzii (costul total si pretul fiecarui produs)
	 * @param id id-ul comenzii
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void generateBill(int id) throws IOException
	{	
		
		int t=0;
		String a="";
		HashMap<Order,ArrayList<MenuItem>> map=R.getOrders();
		 for (Map.Entry mapElement : map.entrySet()) { 
	       	  
	       	  Order o = (Order) mapElement.getKey();
	         
	       	  if(o.getId()==id)
	       	  {
	       		ArrayList<MenuItem> m = (ArrayList<MenuItem>) mapElement.getValue();
	       		 for(MenuItem i:m)
	       		 {
	       			 t+=i.getPrice();
	       			 a+=i.getName()+"-"+i.getPrice()+"\n";
	       		 }
	       	  }
	         } 
		index++;
		 FileWriter myWriter = new FileWriter("bill"+index+".txt");
	      myWriter.write("Nota: "+t+" lei:\n"+a);
	      myWriter.close();
	
		
	}
	
}

