package businessLayer;
/**
 * @author Ionut
 * Interfata cu principalele operatii necesare.
 */
import java.util.Observer;

public interface IRestaurantProcessing  {
	/**
	 * Adaugare produs nou
	 * @param i obiect de tip MenuItem
	 */
	public void addMenuItem(MenuItem i);
	/**
	 * Sterge un produs
	 * @param i produsul care trebuie sters
	 */
	public void deleteMenuItem(MenuItem i);
	/**
	 * Metoda care modifica un produs. Se sterge produsul cu datelevechi si se adauga un produs nou.
	 * @param i produsul vechi
	 * @param j produsul modificat (nou)
	 */
	public void editMenuItem(MenuItem i,MenuItem j);
	/**
	 * Adaugare comanda
	 * @param o comanda care trebuie adaugata
	 */
	public void addNewOrder(Order o);
	public int computePrice(int id);
	public void generateBill(int id);
}
