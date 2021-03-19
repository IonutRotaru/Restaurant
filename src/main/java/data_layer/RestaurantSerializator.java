package data_layer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import businessLayer.Restaurant;
/**
 * Clasa pentru salvarea datelor din restaurant
 * @author Ionut
 *
 */
public class RestaurantSerializator implements java.io.Serializable {

	Restaurant r;
	
	public RestaurantSerializator(Restaurant x)
	{
		r=x;
	}
	/**
	 * Metoda creeaza un fisier ser cu datele din obiectul restaurant
	 */
	public void save()
	{
		 FileOutputStream fileOut;
			try {
				fileOut = new FileOutputStream("restaurant.ser");
				 ObjectOutputStream out = new ObjectOutputStream(fileOut);
				 out.writeObject(r);
		         out.close();
		         fileOut.close();
		         System.out.printf("Serialized data is saved in restaurant.ser");
			} catch (IOException i) {
		         i.printStackTrace();
		      }
	}
	
	/**
	 * Metoda este apelata pentru a incarca datele din fisierul ser
	 * @param name numele fisierului ser care contine datele restaurantului
	 * @return obiect de tip Restaurant cu datele din fisier
	 * @throws ClassNotFoundException
	 */
	public static Restaurant load(String name) throws ClassNotFoundException
	{
		Restaurant ret = new Restaurant();
		try{
	    	FileInputStream fileIn = new FileInputStream(name);
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        ret = (Restaurant) in.readObject();
	        in.close();
	        fileIn.close();
	    	}
	    	catch (IOException i) {
	            i.printStackTrace();
	           
	         }
		return ret;
	}
	
}
