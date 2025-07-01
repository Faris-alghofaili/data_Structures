
public class LinkedList<T> {

	Node<T> head;
	Node<T> current;
	int size;

	LinkedList() {
		head = current = null;
		size = 0;
	}

	public T retrieve() {
		return current.data;
	}

	public void update(T e) {
		((WordInformation) current.data).size++;
		((WordInformation) current.data).occList.insertOccurrence(((WordInformation) e).occList.retrieve());
	}

	public void insertOccurrence(T e) {
		if (head == null) {
			current = head = new Node<>(e);
			this.size++;
		} else {
			current.next = new Node(e);
			current = current.next;
			this.size++;
		}
	}
	public boolean insertWord(T e) {
		if (head != null) {
			if (Search(((WordInformation) e).word) == null) {
				current.next = new Node(e);
				current = current.next;
				this.size++;
				return true;
			} else
			{
				update(e);
				return false;
			}
		}
		current = head = new Node<>(e);
		this.size++;
		return true;
	}

	public Node<T> Search(String word) {
		if (head != null) {
			Node<T> temp = head;
			do {
				current = temp;
				String w = ((WordInformation) temp.data).word;
				if (w.equals(word))
				{
					return temp;
				}
				temp = temp.next;
			} while (temp != null);
		}
		return null;
	}

}