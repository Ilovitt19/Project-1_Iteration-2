import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class TicketList extends ItemList<Ticket, String> implements Serializable {
	private static final long serialVersionUID = 1L;

	private static TicketList ticketList;

	private TicketList() {
	}
	/**
	 * Supports the singleton pattern
	 *
	 * @return the singleton object
	 */
	public static TicketList instance() {
		if ( ticketList == null ) {
			return ( ticketList = new TicketList() );
		} else {
			return ticketList;
		}
	}

	/**
	 * inserts a new ticket into the ticket collection
	 * @param ticket
	 * @return
	 */

	public boolean insertTicket(Ticket ticket) {
		return super.add(ticket);
	}

	/**
	 * searches for tickets within the collection by comparing dates.
	 * if the dates match then the method returns each matching ticket.
	 * @param ticketId
	 * @return
	 * @throws ParseException
	 */

	public Ticket search(String ticketId) throws ParseException {
		return super.search(ticketId);
	}

	/**
	 * returns the all the tickets.
	 * @return
	 */

	public Iterator getTickets() {
		return super.iterator();
	}

	/**
	 * method writes to a file
	 * @param output
	 */

	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(ticketList);
		} catch(IOException ioe) {
			System.out.println(ioe);
		}
	}

	/**
	 * method reads from a file
	 * @param input
	 */

	private void readObject(java.io.ObjectInputStream input) {
		try {
			if (ticketList != null) {
				return;
			} else {
				input.defaultReadObject();
				if (ticketList == null) {
					ticketList = (TicketList) input.readObject();
				} else {
					input.readObject();
				}
			}
		} catch(IOException ioe) {
			System.out.println("in Catalog readObject \n" + ioe);
		} catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	/**
	 * Prints a list of the tickets
	 */
	public void listTickets(String date){

		if(super.isEmpty()){
			System.out.println("No tickets available");
		}
		else {
			System.out.println("Tickets Purchased: ");
			for (Iterator iterator = super.iterator(); iterator.hasNext();){
				Ticket ticket = (Ticket)iterator.next();

				if(ticket.getDate().equals((date))) {
					System.out.println(ticket);
				}
			}
		}



	}
	/**
	 * returns string of show info
	 * @return string
	 */
	public String toString() {
		return super.toString();
	}
}
