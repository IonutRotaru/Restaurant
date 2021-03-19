package businessLayer;
/**
 * Clasa care salveaza masa si id-ul unei comenzi
 * @author Ionut
 *
 */
public class Order implements java.io.Serializable {

	int id;
	String data;
	int table;
	
	public Order(int i,String d,int t)
	{
		id=i;
		data=d;
		table=t;
	}
	/**
	 * Afla id-ul comenzii
	 * @return id id-ul comenzii
	 */
	public int getId()
	{
		return id;
	}
	/**
	 * Afla masa corespunzatoare comenzii
	 * @return table numarul mesei
	 */
	public int getTable()
	{
		return table;
	}
}
