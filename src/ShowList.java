import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.*;


public class ShowList extends ItemList<Show, String> implements Serializable {
	private static final long serialVersionUID = 1L;
	private static ShowList showlist;

	private ShowList() {
	}
	/**
	 * Supports the singleton pattern
	 *
	 * @return the singleton object
	 */
	public static ShowList instance() {
		if (showlist == null) {
			return ( showlist = new ShowList());
		} else {
			return showlist;
		}
	}

	/**
	 * Inserts a show into the collection
	 * @param show the show to be inserted
	 * @return true iff the show could be inserted. Currently always true
	 */
	public boolean insertShow(Show show) {
		super.add(show);
		return true;
	}
	/**
	 * Checks to see if a show matches a certain producer Id;
	 * and also checks to see if the show is playing (by calling isValid())
	 * if the show is found, the method returns the show
	 */
	public Show search(String producerId) throws ParseException {
		for (Iterator iterator = super.iterator(); iterator.hasNext(); ) {
			Show show = (Show) iterator.next();
			if (show.getProducerId().equals(producerId) && show.isValid()) {
				return show;
			}
		}
		return null;
	}
	
	/**
	 * searches for a show by the shows title and returns
	 * the show if it was found within the list.
	 * @param title
	 * @return
	 * @throws ParseException
	 */

	public Show searchTitle(String title) throws ParseException {
		for (Iterator iterator = super.iterator(); iterator.hasNext(); ) {
			Show show = (Show) iterator.next();
			if (show.getTitle().equals(title) && show.isValid()) {
				return show;
			}
		}
		return null;
	}

	/**
	 * Returns an iterator to all shows
	 * @return iterator to the collection
	 */
	public Iterator getShows() {
		return super.iterator();
	}
	/**
	 * writes objects to file
	 * @param output
	 */
	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(showlist);
		} catch(IOException ioe) {
			System.out.println(ioe);
		}
	}
	/**
	 * reads file
	 * @param input
	 */
	private void readObject(java.io.ObjectInputStream input) {
		try {
			if (showlist != null) {
				return;
			} else {
				input.defaultReadObject();
				if (showlist == null) {
					showlist = (ShowList) input.readObject();
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
	 * Prints a list of the shows
	 */
	public void listShows(){

		if(super.isEmpty()){
			System.out.println("No shows available");
		}
		else {
			for (Iterator iterator = super.iterator(); iterator.hasNext();){
				Show show = (Show)iterator.next();
				System.out.println(show);

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