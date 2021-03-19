package businessLayer;
/**
 * MenuItem este o clasa abstracta. Un obiect de acest tip poate fi BaseProduct sau CompositeProduct
 * @author Ionut
 *
 */
public abstract class MenuItem implements java.io.Serializable {

	int price;
	String name;
	
	/**
	 * Seteza pretul.
	 * @param p pretul
	 * 
	 */
	public void setPrice(int p)
	{
		price=p;
	}
	/**
	 * Seteaza numele produsului
	 * @param n numele produsului
	 */
	public void setName(String n)
	{
		name=n;
	}
	/**
	 * Afla pretul produsului
	 * @return price pretul produsului
	 */
	public int getPrice()
	{
		return price;
	}
	/**
	 * Afla numele produsului
	 * @return numele produsului
	 */
	public String getName()
	{
		return name;
	}
}
