package businessLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * Clasa Restaurant contine lista de produse din meniu si lista de comenzi. Exista mai multe metode utile 
 * pentru manipularea corecta a datelor.
 * @author Ionut
 *
 */
public class Restaurant extends Observable implements IRestaurantProcessing,java.io.Serializable{
	
	ArrayList<MenuItem> items = new ArrayList<MenuItem>();
	ArrayList<Order> orders = new ArrayList<Order>();
	HashMap<Order,ArrayList<MenuItem>> map=new HashMap<Order,ArrayList<MenuItem>>();  
	int id=100;
	
	/**
	 * Se adauga in HashMap o comanda noua.
	 * @param o detaliile comenzii (id,masa)
	 * @param m lista cu produse din meniu
	 * 
	 */
	public void addMap(Order o,ArrayList<MenuItem> m)
	{
		assert o!=null;
		assert m!=null;
	map.put(o,m);
	
	setChanged();
	notifyObservers("Chef notified\n");
	}
	
	/*@Override
	public int hashCode()
	{	
		
	}
	@Override
	public boolean equals(Object obj)
	{
		
		
	}*/
	/**
	 * Se genereaza toate numele (String) disponibile in meniu. Metoda este apelata 
	 * in interfata Waiter, unde se listeaza aceste nume, si utilizatorul alege ce sa adauge in comanda.
	 * @return vector de String-uri cu toate numele deproduse
	 * 
	 */
	public String[] getNames()
	{	
		int i=0;
		String[] a = new String[100];
		
		for(MenuItem x:items)
		{
			a[i]=x.getName();
			i++;
		}
		
		return a;
		
	}
	
	/**
	 * Metoda calculeaza nota de plata pentru o anumita comanda. 
	 * @param index id-ul comenzii
	 * @return pretul total al comenzii
	 */
	public int computePrice(int index)
	{
		int total=0;
		 for (Map.Entry mapElement : map.entrySet()) { 
       	  
       	  Order o = (Order) mapElement.getKey();
          
       	  if(o.getId()==index)
       	  {
       		ArrayList<MenuItem> m = (ArrayList<MenuItem>) mapElement.getValue();
       		 for(MenuItem i:m)
       		 {
       			 total+=i.getPrice();
       		 }
       	  }
         } 
		return total;
	}
	/**
	 * Metoda primeste numele produselor adaugate in GUI sub forma de String-uri. Dupa aceste nume, se cauta 
	 * in lista de produse, iar cele care au numele dorit, sunt adaugate.
	 * @param lista numele produselor sub forma de String
	 * @return lista de MenuItems care vor fi adaugate comenzii
	 */
	public ArrayList<MenuItem> getItemsToAdd(String[] lista)
	{	
		ArrayList<MenuItem> ret = new ArrayList<MenuItem>();
		for(String a:lista)
		{	
			if(a!=null)
			ret.add(findItem(a));
		}
		return ret;
	}
	
	/**
	 * Metoda este apelata degetItemsToAdd atunci cand se cauta produsul dupa nume
	 * @param a numele produsului cautat
	 * @return produsul MenuItem cu numele cautat
	 */
	public MenuItem findItem(String a)
	{	
		
		for(MenuItem m:items)
		{
			if(a.contentEquals(m.getName()))
					{
						return m;
					}
		}
		return null;
	}
	
	/**
	 * Se adauga un produs nou
	 */
	public void addMenuItem(MenuItem i) {
		// TODO Auto-generated method stub
		assert i!=null;
		items.add(i);
		
	}
	
	
	public void deleteMenuItem(MenuItem a) {
		// TODO Auto-generated method stub
		assert a!=null;
		for(int i=0;i<items.size();i++)
			if(items.get(i).getName().contentEquals(a.getName()))
				items.remove(i);
	}
	
	
	public void editMenuItem(MenuItem a,MenuItem b) {
		// TODO Auto-generated method stub
		assert a!=null;
		assert b!=null;
		
			for(int i=0;i<items.size();i++)
				if(items.get(i).getName().contentEquals(a.getName()) && items.get(i).getPrice()==a.getPrice())
						{
					items.remove(i);
						}
		
		items.add(b);
	}

	public void addNewOrder(Order o) {
		// TODO Auto-generated method stub
		assert o!=null;
		orders.add(o);
	}

	/**
	 * Metoda pentru gasirea tuturor produselor
	 * @return lista deproduse
	 */
	public ArrayList<MenuItem> getItems()
	{
		return items;
	}
	/**
	 * Metoda pentru gasirea tuturor comenzilor
	 * @return comenzile actuale
	 */
	public HashMap<Order,ArrayList<MenuItem>> getOrders()
	{
		return map;
	}
	/**
	 * Metoda pentru generarea unui id diferit noii comenzi
	 * @return id comanda
	 */
	public int getId()
	{	id++;
		return id;
	}

	public void generateBill(int id) {
		// TODO Auto-generated method stub
		
	}
	

}
