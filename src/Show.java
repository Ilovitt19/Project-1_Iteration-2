import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * stores shows
 *
 */
public class Show implements Serializable, Matchable<String>{
	private static final long serialVersionUID = 1L;
	private String showTitle;
	private String producerId;
	private String start;
	private String end;
	private Double ticketPrice;
	private List ticketsPurchased = new LinkedList();
	
	/**
	 * constructor for show class
	 *
	 * @param showTitle
	 * @param producerId
	 * @param start
	 * @param ticketPrice
	 */
	public Show(String showTitle, String producerId, String start, String end, double ticketPrice) {
		this.showTitle = showTitle;
		this.producerId = producerId;
		this.start =start;
		this.end=end;
		this.ticketPrice = ticketPrice;
	}
	/**
	 * gets show title
	 * @return title
	 */
	public String getTitle() {
		return showTitle;
	}

	/**
	 * test if date is in range
	 * @return true or false boolean
	 * @throws ParseException 
	 */
	public boolean isValid() throws ParseException{
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String dateInString = start;
		String dateInString2 = end;
		Date date5 = sdf.parse(dateInString);
		Date date8 = sdf.parse(dateInString2);
		
		return (d.after(date5) && d.before(date8));
	}
	/** 
	 * returns the producerID 
	 */
	public String getProducerId() {
		return producerId;
	}
	
	/**
	 * returns the price of the ticket
	 * @return
	 */

	public double getPrice() {
		return ticketPrice;
	}


	/**
	 * Prints string of show info
	 * @return show info
	 */
	public String toString() {
		String string = "Show Title: " + showTitle + " Start date: " + start + " End date: " + end;
		return string;
	}

	@Override
	public boolean matches(String key) {
		return showTitle.equals(key);
	}
}



