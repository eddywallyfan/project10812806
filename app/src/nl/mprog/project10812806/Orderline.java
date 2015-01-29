/* John Wesselingh project
 * 10812806
 * Een class waarin een order omschreven wordt
 */
package nl.mprog.project10812806;

public class Orderline {
	
	public String name;
	public String number;
	public String size;
	public String photo;
	
	// Zorg dat de toString methode een String teruggeeft
	@Override
	public String toString() {
		String s = "naam:     " + name + "\n";
		s += "aantal:    " + number + "\n";
		s += "potmaat: " + size + "\n\n";
		return s;
	}
}

