import java.io.Serializable;

/**This class stores a credit card*/

public class CreditCard implements Serializable {
	private static final long serialVersionUID = 1L;
	private String number;
	private String expirationDate;
	/** Constructor for credit card class
	 * @param number
	 * @param expirationDate
	 * */
	public CreditCard(String number, String expirationDate) {
		super();
		this.number = number;
		this.expirationDate = expirationDate;
	}
	/**
	 * gets credit card number
	 * @return number
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * sets credit card number
	 * @param number
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * gets card expiration date
	 * @return expirationDate
	 */
	public String getExpirationDate() {
		return expirationDate;
	}
	/**
	 * sets credit card expiration date
	 * @param expirationDate
	 */
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

}