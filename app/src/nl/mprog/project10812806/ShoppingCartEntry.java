/* John Wesselingh project
 * 10812806
 * Een class met methodes die in de winkelwagen gebruikt worden
 */
package nl.mprog.project10812806;

public class ShoppingCartEntry {
	
	private Product mProduct;
	private int mQuantity;
	
	public ShoppingCartEntry(Product product, int quantity) {
		mProduct = product;
		mQuantity = quantity;
	}
	
	public Product getProduct() {
		return mProduct;
	}
	
	public int getQuantity() {
		return mQuantity;
	}
	
	public void setQuantity(int quantity) {
		mQuantity = quantity;
	}

}
