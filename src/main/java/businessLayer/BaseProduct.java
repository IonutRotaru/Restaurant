package businessLayer;
/**
 * 
 * @author Ionut
 *
 */
public class BaseProduct extends MenuItem implements java.io.Serializable{

	public BaseProduct(int p,String n)
	{
		super.setPrice(p);
		super.setName(n);
	}
	
}
