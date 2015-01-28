/* John Wesselingh project
 * 10812806
 * Een class waarin een order omschreven wordt
 */
package nl.mprog.project10812806;

public class Orderline {
	
	public String plant_naam;
	public String aantal;
	public String potmaat;
	public String afleverAdres;
	public String contactPersoon;
	public String bedrijf;
	public String email;
	public String phoneNr;
	public String factuurAdres;
	public String foto;
	
	
	@Override
	public String toString() {
		String s = "naam:     " + plant_naam + "\n";
		s += "aantal:    " + aantal + "\n";
		s += "potmaat: " + potmaat + "\n\n";
		return s;
	}
	
	
	

}

