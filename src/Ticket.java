/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Represents a single Transaction (issue, renew, etc.)

 *
 */
public class Ticket implements Serializable, Matchable<String> {
	private static final long serialVersionUID = 1L;
	private String id;
	private String date;
	private String type;
	private double price;
	private final String TICKET_STRING = "T";


	/**
	 * Creates the ticket with a given type and show price. The date is the
	 * current date.
	 *
	 * @param type
	 *            The type of transaction
	 * @param price
	 *            The price of the ticket
	 *
	 */

	public Ticket(String type, double price) {
		this.type = type;
		this.price = price;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		date = sdf.format(new Date());

		id = TICKET_STRING + TicketIdServer.instance().getId();
	}

//	/**
//	 * Checks whether this transaction is on the given date
//	 *
//	 * @param date
//	 *            The date for which transactions are being sought
//	 * @return true iff the dates match
//	 */
//	public boolean onDate(Calendar date) {
//		return ((date.get(Calendar.YEAR) == this.date.get(Calendar.YEAR))
//				&& (date.get(Calendar.MONTH) == this.date.get(Calendar.MONTH)) && (date
//					.get(Calendar.DATE) == this.date.get(Calendar.DATE)));
//	}

	/**
	 * Returns the type field
	 * 
	 * @return type field
	 */
	public String getType() {
		return type;
	}

	/**
	 * Returns the title field
	 * 
	 * @return title field
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Returns the date as a String
	 * 
	 * @return date with month, date, and year
	 */
	public String getDate() {
		
		return date;
	}

	/**
	 * String form of the ticket
	 * 
	 */
	@Override
	public String toString() {
		return "ID: " + id + " Type: " + type + " Date: " + date + " Price: $" + price;
	}

	@Override
	public boolean matches(String key) {
		return id.equals(key);
	}
}