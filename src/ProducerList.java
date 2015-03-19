import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Collection of producer objects
 *
 */
public class ProducerList extends ItemList<Producer, String> implements Serializable {
	private static final long serialVersionUID = 1L;
	private static ProducerList producerList;

	private ProducerList() {

	}
	/**
	 * Private constructor for singleton pattern
	 *
	 */
	public static ProducerList instance() {
		if (producerList == null) {
			return (producerList = new ProducerList());
		} else {
			return producerList;
		}
	}
	/**
	 * Supports the singleton pattern
	 *
	 * @return the singleton object
	 */
	public Producer search(String producerId) {
		for (Iterator iterator = super.iterator(); iterator.hasNext(); ) {
			Producer producer = (Producer) iterator.next();
			if (producer.getId().equals(producerId)) {
				return producer;
			}
		}
		return null;
	}
	/**
	 * adds a producer to the list
	 * @param producer
	 * @return true or false boolean
	 */
	public boolean insertProducer(Producer producer) {

		return super.add(producer);
	}

	/**
	 * Removes producer from list with given ID
	 * @param producerId
	 * @return true if completed
	 */
	public boolean removeProducer(String producerId) {
		Producer producer = search(producerId);
		if (producer == null) {
			return false;
		} else {
			return super.remove(producer);
		}
	}

	/**
	 * Writes to file
	 * @param output
	 */
	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(producerList);
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	/**
	 * reads file
	 * @param input
	 */
	private void readObject(java.io.ObjectInputStream input) {
		try {
			if (producerList != null) {
				return;
			} else {
				input.defaultReadObject();
				if (producerList == null) {
					producerList = (ProducerList) input.readObject();
				} else {
					input.readObject();
				}
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

	/**
	 * Prints a list of the producers
	 */
	public void listProducers(){

		if(super.isEmpty()){
			System.out.println("No producers available");
		}
		else {
			for (Iterator iterator = super.iterator(); iterator.hasNext();){
				Producer producer = (Producer)iterator.next();
				System.out.println(producer);

			}
		}
	}

	/**
	 * returns the string of all producers by calling the
	 * method from the producer class
	 */

	@Override
	public String toString() {
		return super.toString();
	}
}
