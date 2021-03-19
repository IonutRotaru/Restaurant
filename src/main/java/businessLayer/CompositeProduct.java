package businessLayer;

import java.util.ArrayList;

/**
 * Clasa care mosteneste MenuItem.
 * @author Ionut
 *
 */
public class CompositeProduct extends MenuItem implements java.io.Serializable{
	
	ArrayList<MenuItem> l = new ArrayList<MenuItem>();
	
	public CompositeProduct(int p,String n,ArrayList<MenuItem> a)
	{	
		l=a;
		super.setName(n);
		super.setPrice(p);
	}
	
}
