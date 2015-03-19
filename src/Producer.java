import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Stores producers
 *
 */
public class Producer implements Serializable, Matchable<String> {
	private static final long serialVersionUID = 1L;
	private String name;
	private String id;
	private String address;
	private String phone;
	private double balance = 0;
	private static final String PRODUCER_STRING = "P";
	private List showsProduced = new LinkedList();
	/**
	 * constructor for producer class
	 * @param name
	 * @param address
	 * @param phone
	 */
	public Producer(String name, String address, String phone){

		this.name = name;
		this.address = address;
		this.phone = phone;
		id = PRODUCER_STRING + (ProducerIdServer.instance()).getId();
	}
	/**
	 * gets pruducer name
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * sets producer name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * gets producer id
	 * @return id
	 */
	public String getId() {
		return id;
	}
	/**
	 * sets producer id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * gets producer address
	 * @return adress
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * sets producer adress
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * gets producer phone number
	 * @return
	 */
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * gets balance
	 * @return balance
	 */
	public double getBalance() {
		return balance;
	}
	/**
	 * sets producer balance
	 * @param balance
	 */
	public void setBalance(double balance) {

		this.balance = this.balance + balance;


		}

	/**
	 * String representaion of a producer
	 * @return
	 */
	@Override
	public String toString() {
		String string = "Producer name: " + name + " address: " + address + " id: " + id + " phone: " + phone + " balance: " + balance;
		return string;
	}

	@Override
	public boolean matches(String key) {
		return id.equals(key);
	}
}