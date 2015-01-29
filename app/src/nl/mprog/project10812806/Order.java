/* John Wesselingh project
 * 10812806
 * Een class met methodes die gebruikt worden om orders op te slaan en op te vragen.
 */
package nl.mprog.project10812806;

import java.util.ArrayList;

public class Order {
	
	private static Order instance = null;
	private ArrayList<Orderline> orderList = new ArrayList<Orderline>();
	
	// Geef een instantie van het object terug
	public static Order getInstance(){		
		if (instance != null){		
			return instance;
		}
		
		else {
			instance = new Order();
			return instance;	
		}
	}
	
	// Methode om een item aan de ordeline toe te voegen
	public void add(Orderline hup){
		orderList.add(hup);
	}
	
	// Verwijder een item uit de Orderline
	public void remove(Orderline hup){
		orderList.remove(hup);
	}
	
	// Vraag de Arraylist van Orderline op
	public ArrayList<Orderline> getList(){
		return orderList;
	}
}


