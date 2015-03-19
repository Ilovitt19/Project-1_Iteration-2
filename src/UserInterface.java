/**
 *
 *
 * We used the UserInterface class from Library project as a shell for our own interface, see note below:
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
import java.util.*;
import java.text.*;
import java.io.*;
/**
 * UserInterface
 * This class implements the user interface for the Theater project.
 * The commands are encoded as integers using a number of
 * static final variables. A number of utility methods exist to
 * make it easier to parse the input.
 *
 */
public class UserInterface {
	private static UserInterface userInterface;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Theater theater;
	private static final int EXIT = 0;
	private static final int ADD_PRODUCER = 1;
	private static final int REMOVE_PRODUCER = 2;
	private static final int LIST_PRODUCERS = 3;
	private static final int ADD_CUSTOMER = 4;
	private static final int REMOVE_CUSTOMER = 5;
	private static final int ADD_CARD = 6;
	private static final int REMOVE_CARD = 7;
	private static final int LIST_CUSTOMERS = 8;
	private static final int ADD_SHOW = 9;
	private static final int LIST_SHOWS = 10;
	private static final int STORE_DATA = 11;
	private static final int RETRIEVE = 12;	
	private static final int BUYTICKET = 13;
	private static final int BUYADVTICKET = 14;
	private static final int BUYSTUADVTICKET = 15;
	private static final int PAYPRODUCER = 16;
	private static final int TRANSACTIONS = 17;
	private static final int HELP = 18;

	/**
	 * Made private for singleton pattern.
	 * Conditionally looks for any saved data. Otherwise, it gets
	 * a singleton Theater object.
	 */
	private UserInterface() {
		if (yesOrNo("Look for saved data and use it?")) {
			retrieve();
		} else {
			theater = Theater.instance();
		}
	}
	/**
	 * Supports the singleton pattern
	 *
	 * @return the singleton object
	 */
	public static UserInterface instance() {
		if (userInterface == null) {
			return userInterface = new UserInterface();
		} else {
			return userInterface;
		}
	}
	/**
	 * Gets a token after prompting
	 *
	 * @param prompt - whatever the user wants as prompt
	 * @return - the token from the keyboard
	 *
	 */
	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}
	/**
	 * Queries for a yes or no and returns true for yes and false for no
	 *
	 * @param prompt The string to be prepended to the yes/no prompt
	 * @return true for yes and false for no
	 *
	 */
	private boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}
	/**
	 * Converts the string to a number
	 * @param prompt the string for prompting
	 * @return the integer corresponding to the string
	 *
	 */
	public int getNumber(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Integer number = Integer.valueOf(item);
				return number.intValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a number ");
			}
		} while (true);
	}

	/**
	 * Prompts for a date and gets a date object
	 * @param prompt the prompt
	 * @return the data as a Calendar object
	 */

	public Calendar getDate(String prompt) {
		do {
			try {
				Calendar date = new GregorianCalendar();
				String item = getToken(prompt);
				DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
				date.setTime(dateFormat.parse(item));
				return date;
			} catch (Exception fe) {
				System.out.println("Please input a date as mm/dd/yy");
			}
		} while (true);
	}

	/**
	 * Prompts for a command from the keyboard
	 *
	 * @return a valid command
	 *
	 */

	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
				if (value >= EXIT && value <= HELP) {
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		} while (true);
	}
	/**
	 * Displays the help screen
	 *
	 */
	public void help() {
		System.out.println("Enter a number between 0 and 13 (not 12) as explained below:");
		System.out.println(EXIT + " to Exit\n");
		System.out.println(ADD_PRODUCER + " to add a producer");
		System.out.println(REMOVE_PRODUCER + " to remove producer");
		System.out.println(LIST_PRODUCERS + " to list all producers");
		System.out.println(ADD_CUSTOMER + " to add a customer");
		System.out.println(REMOVE_CUSTOMER + " to remove a customer");
		System.out.println(ADD_CARD + " to add a credit card");
		System.out.println(REMOVE_CARD + " to remove a credit card");
		System.out.println(LIST_CUSTOMERS + " to list all customers");
		System.out.println(ADD_SHOW + " to add a show");
		System.out.println(LIST_SHOWS + " to list all shows");
		System.out.println(STORE_DATA + " to store data.");
		System.out.println(BUYTICKET + " to purchase a ticket");
		System.out.println(BUYADVTICKET + " to preorder a ticket for a show");
		System.out.println(BUYSTUADVTICKET + " to preorder a student ticket.");
		System.out.println(PAYPRODUCER + " to pay a producer a share.");
		System.out.println(TRANSACTIONS + " to print tickets sold on a certain day");
		System.out.println(HELP + " for help");
	}
	/**
	 * Method to be called for adding a producer.
	 * Prompts the user for the appropriate values and
	 * uses the appropriate Theater method for adding the producer.
	 *
	 */
	public void addProducer() {
		String name = getToken("Enter producer name");
		String address = getToken("Enter address");
		String phone = getToken("Enter phone");
		Producer result;
		result = theater.addProducer(name, address, phone);
		if (result == null) {
			System.out.println("Could not add producer");
		}
		System.out.println(result);
	}

	/**
	 * Method to be called for removing a producer.
	 * Prompts the user for the appropriate id and
	 * uses the appropriate Theater method for removing the producer.
	 * @throws ParseException 
	 *
	 */
	public void removeProducer() throws ParseException {
		int result;
		do {
			String producerID = getToken("Enter producer id");
			result = theater.removeProducer(producerID);
			switch(result){
			case Theater.PRODUCER_NOT_FOUND:
				System.out.println("No such Producer in Theater");
				break;
			case Theater.SHOW_PLAYING:
				System.out.println("Cannot remove producer, producer has show playing");
				break;
			case Theater.OPERATION_FAILED:
				System.out.println("Producer could not be removed");
				break;
			case Theater.OPERATION_COMPLETED:
				System.out.println("Producer has been removed");
				break;
			default:
				System.out.println("An error has occurred");
			}
			if (!yesOrNo("Remove more producers?")) {
				break;
			}
		} while (true);

	}

	/**
	 * Method to be called for listing producers.
	 *
	 */
	public void listProducers() {
		theater.listProducers();
	}

	/**
	 * Method to be called for adding a customer.
	 * Prompts the user for the appropriate values and
	 * uses the appropriate Theater method for adding the customer.
	 *
	 */
	public void addCustomer() {
		String name = getToken("Enter customer name");
		String address = getToken("Enter address");
		String phone = getToken("Enter phone");
		String cardNumber = getToken("Enter Credit Card Number: ");
		String date = getToken("Enter Credit Card Expiration Date: ");
		CreditCard card = new CreditCard(cardNumber, date);
		Customer result;

		result = theater.addCustomer(name, address, phone, card);
		if (result == null) {
			System.out.println("Could not add customer");
		}
		System.out.println(result);
	}

	/**
	 * Method to be called for removing a customer.
	 * Prompts the user for the appropriate id and
	 * uses the appropriate Theater method for removing the customer.
	 *
	 */
	public void removeCustomer() {
		int result;
		do {
			String customerId = getToken("Enter customer id");
			result = theater.removeCustomer(customerId);
			switch(result){
			case Theater.CUSTOMER_NOT_FOUND:
				System.out.println("No such Customer in Theater");
				break;
			case Theater.OPERATION_FAILED:
				System.out.println("Customer could not be removed");
				break;
			case Theater.OPERATION_COMPLETED:
				System.out.println("Customer has been removed");
				break;
			default:
				System.out.println("An error has occurred");
			}
			if (!yesOrNo("Remove more customers?")) {
				break;
			}
		} while (true);

	}

	/**
	 * Method to be called for adding a credit card for a customer.
	 * Prompts the user for the appropriate values and
	 * uses the appropriate Theater method for adding the card.
	 *
	 */
	public void addCreditCard() {
		String cardNumber = getToken("Enter credit card number");
		String expDate = getToken("Enter expiration date ");
		String customerId = getToken("Enter customerId");

		CreditCard card = new CreditCard(cardNumber, expDate);
		Customer result;

		result = theater.addCreditCard(customerId, cardNumber, expDate);
		if (result == null) {
			System.out.println("Could not add credit card");
		}
		System.out.println(result + " " + card.getNumber());
	}

	/**
	 * Method to be called for removing a credit card.
	 * Prompts the user for the appropriate id and
	 * uses the appropriate Theater method for removing the credit card.
	 *
	 */
	public void removeCreditCard() {
		int result;
		do{
			String customerId = getToken("Enter customer id");
			String cardNumber = getToken("Enter card number to remove");
			result = theater.removeCreditCard(customerId, cardNumber);
			switch(result){
			case Theater.CREDIT_CARD_NOT_FOUND:
				System.out.println("No such Customer/Credit in Theater");
				break;
			case Theater.ONLY_CREDIT_CARD:
				System.out.println("Cannot delete, need at least one credit card on file");
				break;
			case Theater.OPERATION_FAILED:
				System.out.println("Customer/Credit could not be removed");
				break;
			case Theater.OPERATION_COMPLETED:
				System.out.println("Credit card has been removed");
				break;
			default:
				System.out.println("An error has occurred");
			}
			if (!yesOrNo("Remove more credit cards?")) {
				break;
			}
		} while (true);
	}

	/**
	 * Method to be called for listing customers.
	 *
	 */
	public void listCustomers() {
		theater.listCustomers();
	}

	/**
	 * Method to be called for adding a show for a producer.
	 * Prompts the user for the appropriate values and
	 * uses the appropriate Theater method for adding the show.
	 *
	 */
	
	public void addShow() {
		String title = getToken("Enter show title");
		String producer = getToken("Enter producer ID");
		String startDate = getToken("Enter start date (dd-MM-yyyy hh:mm:ss)");
		String endDate = getToken("Enter end date (dd-MM-yyyy hh:mm:ss)");
		String price = getToken("Enter the regular price of ticket.");
		Show result;
		result = theater.addShow(title, producer , startDate, endDate, price);
		if (result == null) {
			System.out.println("Could not add show");
		}
		System.out.println(result);
	}

	/**
	 * Method to be called for listing shows.
	 *
	 */
	public void listShows() {
		theater.listShows();
	}

	/**
	 * Method used for issuing a regular ticket to a particular customer.
	 * Prompts the user for the appropriate values and
	 * uses the appropriate Theater method for issuing a regular ticket to a customer.
	 * @throws ParseException
	 *
	 */

	public void issueTickets() throws ParseException {
		Show result;
		String showTitle = getToken("Enter show title");
		String customerID = getToken("Enter customer id");
		Show show = theater.searchShowTitle(showTitle);
				
			if (theater.searchCustomer(customerID) == null) {	 
				System.out.println("No such customer");
				return;
			}
			if (show == null) { //check if show exists
				System.out.println("No such show.");
				return;
			}
			do {
				String quantity = getToken("Enter the quantity of tickets to purchase");
				String card = getToken("Enter the customers credit card number");
				String date = getToken("Enter the date for the show viewing");
				
				
				result = theater.issueShowTickets(showTitle, customerID, quantity, card, date);
				if (result != null && show.isValid()) { // need to update isValid()
					
					double price = result.getPrice() * (Double.parseDouble(quantity));
					
					Producer producer = theater.searchProducer(show.getProducerId());
					Producer payProducer = theater.payProducer(producer.getId(), price/2.0);
	
					System.out.println("Show ticket pruchased: " + result.getTitle()+ "   " 
										+  (price));
				} else {
					System.out.println("Ticket could not be purchased for show");
				}
				if (!yesOrNo("Purchase more Shows?")) {
					break;
				}

			} while (true);
		
	}

	/**
	 * Method used for issuing an advance ticket to a particular customer.
	 * Prompts the user for the appropriate values and
	 * uses the appropriate Theater method for issuing an advance ticket to a customer.
	 * @throws ParseException
	 *
	 */

	public void issueAdvTickets() throws ParseException {
		Show result;
		String showTitle = getToken("Enter show title");
		String customerID = getToken("Enter customer id");
		
			if (theater.searchCustomer(customerID) == null) {	
				System.out.println("No such customer");
				return;
			}
			if (theater.searchShowTitle(showTitle) == null) { //check if show exists
				System.out.println("No such show.");
				return;
			}
			do {
				String quantity = getToken("Enter the quantity of tickets to purchase");
				String card = getToken("Enter the customers credit card number");
				String date = getToken("Enter the date for the show viewing");
				
				result = theater.issueAdvShow(showTitle, customerID, quantity, card, date);
				if (result != null){
					
					double price = (result.getPrice() * 0.7) * (Double.parseDouble(quantity));
					Show show = theater.searchShowTitle(showTitle);
					Producer producer = theater.searchProducer(show.getProducerId());
					Producer payProducer = theater.payProducer(producer.getId(), price/2.0);
					
					
					System.out.println("Show ticket pruchased: " + result.getTitle()+ "   " 
										+  (price));
				} else {
					System.out.println("Ticket could not be purchased for show");
				}
				if (!yesOrNo("Purchase more Shows?")) {
					break;
				}

			} while (true);
		
	}

	/**
	 * Method used for issuing a student advance ticket to a particular customer.
	 * Prompts the user for the appropriate values and
	 * uses the appropriate Theater method for issuing a student advance ticket to a customer.
	 * @throws ParseException
	 *
	 */

	public void issueStuAdvTickets() throws ParseException {
		System.out.println( "Student must show a valid Id." );
		Show result;
		String showTitle = getToken("Enter show title");
		String customerID = getToken("Enter customer id");
		
			if (theater.searchCustomer(customerID) == null) {	
				System.out.println("No such customer");
				return;
			}
			if (theater.searchShowTitle(showTitle) == null) { //check if show exists
				System.out.println("No such show.");
				return;
			}
			do {
				String quantity = getToken("Enter the quantity of tickets to purchase");
				String card = getToken("Enter the customers credit card number");
				String date = getToken("Enter the date for the show viewing");
				
				result = theater.issueShowTickets(showTitle, customerID, quantity, card, date);
				if (result != null){
					
					double price = ((result.getPrice() * 0.7)* 0.5) * (Double.parseDouble(quantity));
					Show show = theater.searchShowTitle(showTitle);
					Producer producer = theater.searchProducer(show.getProducerId());
					Producer payProducer = theater.payProducer(producer.getId(), price/2.0);
					
				
					System.out.println("Show ticket pruchased: " + result.getTitle()+ "   " 
										+  (price ));
				} else {
					System.out.println("Ticket could not be purchased for show");
				}
				if (!yesOrNo("Purchase more Shows?")) {
					break;
				}

			} while (true);
		
	}

	/**
	 * Method used for paying an amount to a customer.
	 * Prompts the user for the appropriate values, producer Id,  and
	 * uses the appropriate Theater method for issuing an advance ticket to a customer.
	 * @throws ParseException
	 *
	 */

	private void payProducer() {

		String producerID = getToken("Enter producer ID: ");
		Producer producerBalance = theater.searchProducer(producerID);
		if (producerBalance == null) {
			System.out.println("Producer not found.");
		}
		System.out.println("Current Balance: " + producerBalance.getBalance());
		String amount = getToken("Amount to be paid to producer: ");
		double amountDouble = Double.parseDouble(amount);
		Producer result = theater.payProducer(producerID, amountDouble);
		System.out.println(result);
	} 


	/**
	 * Method to be called for listing the tickets sold on a certain date.
	 * Method prompts the user for the apropriate values, date, and
	 * uses the appropriate Theater method for displaying all tickets sold on a certain day.
	 *
	 */

	public void listTickets() {
		String date  = getToken("Please enter the date for which you want records as dd-mm-yyyy");
		theater.listTickets(date);
	}




	/**
	 * Method to be called for saving the Theater object.
	 * Uses the appropriate Theater method for saving.
	 *
	 */
	private void save() {
		if (theater.save()) {
			System.out.println(" The theater has been successfully saved in the file TheaterData \n" );
		} else {
			System.out.println(" There has been an error in saving \n" );
		}
	}
	/**
	 * Method to be called for retrieving saved data.
	 * Uses the appropriate Theater method for retrieval.
	 *
	 */
	private void retrieve() {
		try {
			Theater tempTheater = Theater.retrieve();
			if (tempTheater != null) {
				System.out.println(" The theater has been successfully retrieved from the file TheaterData \n" );
				theater = tempTheater;
			} else {
				System.out.println("File doesn't exist; creating new theater" );
				theater = Theater.instance();
			}
		} catch(Exception cnfe) {
			cnfe.printStackTrace();
		}
	}
	/**
	 * Orchestrates the whole process.
	 * Calls the appropriate method for the different functionalties.
	 * @throws ParseException 
	 *
	 */
	public void process() throws ParseException {
		int command;
		help();
		while ((command = getCommand()) != EXIT) {
			switch (command) {
			case ADD_PRODUCER:        addProducer();
			break;
			case REMOVE_PRODUCER:        removeProducer();
			break;
			case LIST_PRODUCERS:       listProducers();
			break;
			case ADD_CUSTOMER:      addCustomer();
			break;
			case REMOVE_CUSTOMER:      removeCustomer();
			break;
			case ADD_CARD:       addCreditCard();
			break;
			case REMOVE_CARD:        removeCreditCard();
			break;
			case LIST_CUSTOMERS:       listCustomers();
			break;
			case ADD_SHOW:      addShow();
			break;
			case LIST_SHOWS:  listShows();
			break;
			case STORE_DATA:              save();
			break;
			case BUYTICKET :	issueTickets();
			break;
			case BUYADVTICKET: issueAdvTickets();
			break;
			case BUYSTUADVTICKET: issueStuAdvTickets();
			break;
			case PAYPRODUCER :	payProducer();
			break;		
			case TRANSACTIONS : listTickets();
			break;
			case HELP:              help();
			break;
			}
		}
	}



	/**
	 * The method to start the application. Simply calls process().
	 * @param args not used
	 * @throws ParseException 
	 */

	public static void main(String[] args) throws ParseException {
		UserInterface.instance().process();
	}
}