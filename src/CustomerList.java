import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * Collection of customer objects
 *
 */

public class CustomerList extends ItemList<Customer, String> implements Serializable {

	private static final long serialVersionUID = 1L;

	private static CustomerList customerList;

	/**
	 * Private constructor for singleton pattern
	 *
	 */
	private CustomerList() {
	}
	/**
	 * Supports the singleton pattern
	 *
	 * @return the singleton object
	 */
	public static CustomerList instance() {
		if (customerList == null) {
			return (customerList = new CustomerList());
		} else {
			return customerList;
		}
	}
	/**
	 * adds a customer to the list
	 * @param customer
	 * @return true or false boolean
	 */
	public boolean addCustomer(Customer customer) {
		return super.add(customer);
	}

	/**
	 * Removes customer with the given id
	 * @param customerId
	 * @return true if completed
	 */
	public boolean removeCustomer(String customerId) {

		Customer customer = search(customerId);
		if (customer == null) {
			return false;
		} else {
			return super.remove(customer);
		}

	}
	/**
	 * checks if a customer with the given id exists
	 * @param customerId
	 * @return customer object
	 */
	public Customer search(String customerId) {
		for (Iterator iterator = super.iterator(); iterator.hasNext(); ) {
			Customer customer = (Customer) iterator.next();
			if (customer.getCustomerId().equals(customerId)) {
				return customer;
			}
		}
		return null;
	}
	/**
	 * writes objects to file
	 * @param output
	 */
	private void writeObject(java.io.ObjectOutputStream output) {
		try {
			output.defaultWriteObject();
			output.writeObject(customerList);
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
			if (customerList != null) {
				return;
			} else {
				input.defaultReadObject();
				if (customerList == null) {
					customerList = (CustomerList) input.readObject();
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
	 * Prints a list of the customers
	 */
	public void listCustomers(){

		if(super.isEmpty()){
			System.out.println("No customers available");
		}
		else {
			for (Iterator iterator = super.iterator(); iterator.hasNext();){
				Customer customer = (Customer)iterator.next();
				System.out.println(customer);

			}
		}
	}
	
	/**
	 * prints all the information of each customer to a string by calling
	 * the to string from customer class
	 */

	@Override
	public String toString() {
		return super.toString();
	}

}


