/* John Wesselingh project
 * 10812806
 * Een class met methodes die in de winkelwagen gebruikt worden
 */
package nl.mprog.project10812806;

import java.util.ArrayList;

public class Order {
	
	private static Order instance = null;
	private ArrayList<Orderline> orderList = new ArrayList<Orderline>();
	
	public static Order getInstance(){
		
		if (instance != null){		
			return instance;
		}
		
		else {
			instance = new Order();
			return instance;	
		}

	}
	
	public void add(Orderline hup){
		orderList.add(hup);
	}
	
	public ArrayList<Orderline> getList(){
		return orderList;
	}
	
	public void remove(Orderline hup){
		orderList.remove(hup);
	}
}


