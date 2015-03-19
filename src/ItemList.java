import java.io.Serializable;
import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Maintains a list of items with type T and key of type K. Subclassed by
 * Catalog and MemberList
 *
 * @param <T>
 *            type of item
 * @param <K>
 *            key of item
 */
public class ItemList<T extends Matchable<K>, K> implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<T> list = new LinkedList<T>();

	/**
	 * Checks whether an item with a given id exists.
	 *
	 * @param key
	 *            the id of the item
	 * @return the item iff the item exists
	 *
	 */
	public T search(K key) throws ParseException {
		for (T item : list) {
			if (item.matches(key)) {
				return item;
			}
		}
		return null;
	}

	/**
	 * Adds an item to the list.
	 *
	 * @param item
	 *            the item to be added
	 * @return true iff the item could be added
	 */
	public boolean add(T item) {
		return list.add(item);
	}

	/**
	 * Removes the item from the list
	 *
	 * @param item
	 *            the item to be removed
	 * @return true iff the item could be removed
	 */
	public boolean remove(T item) {
		return list.remove(item);
	}

	/**
	 * Returns an iterator for the collection
	 *
	 * @return iterator for the collection
	 */
	public Iterator<T> iterator() {
		return list.iterator();
	}

	public boolean isEmpty(){
		return list.isEmpty();
	}
}
