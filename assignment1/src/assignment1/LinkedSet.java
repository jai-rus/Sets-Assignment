package assignment1;

public class LinkedSet<T> implements SetInterface<T> {
	//Node class
	class Node {
		private T data;
		private Node next;
		
		//Constructors
		private Node(T dataPortion) {
			this(dataPortion, null);
		}
		
		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		}
		
		private T getData() {
			return data;
		}
		
		private void setData(T newData) {
			data = newData;
		}
		
		private Node getNextNode() {
			return next;
		}
		
		private void setNextNode(Node nextNode) {
			next = nextNode;
		}
	}
	
	private Node firstNode;
	private int numOfEntries;
	
	//Constructor
	public LinkedSet() {
		firstNode = null;
		numOfEntries = 0;
	}
	
	//adds a new entry
	public boolean add(T newEntry) {
		if (numOfEntries == 0) {
			Node newNode = new Node(newEntry);
			firstNode = newNode;
			numOfEntries++;
			//System.out.println("Added: " + firstNode.getData());
			return true;
		} else {
			if (!contains(newEntry)) {
				Node newNode = new Node(newEntry);
				newNode.setNextNode(firstNode);
				firstNode = newNode;
				numOfEntries++;
				//System.out.println("Added: " + firstNode.getData());
				return true;
			}
		}
		return false;
	}
	
	//removes the first node
	public T remove() {
		if (numOfEntries == 0) {
			return null;
		} else {
			T temp = firstNode.getData();
			firstNode = firstNode.getNextNode();
			numOfEntries--;
			return temp;
		}
	}
	
	//removes specific node
	public boolean remove(T anEntry) {
		boolean result = false;
		Node nodeN = getReferenceTo(anEntry);
		System.out.println(nodeN.getData());
		//sets firstNode data to the removedNode
		//removes firstNode
		if (nodeN != null) {
			nodeN.setData(firstNode.getData());
			firstNode = firstNode.getNextNode();
			numOfEntries--;
			
			result = true;
		}
		
		return result;
	}
	
	//finds a specific entry and returns the node
	private Node getReferenceTo(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;
		
		//while its not found and curretNode isn't null
		while (!found && (currentNode != null)) {
			
			if (anEntry.equals(currentNode.getData())) {
				found = true;
			} else {
				currentNode = currentNode.getNextNode();
			}
		}
		
		return currentNode;
	}
	
	//gets current size of set
	public int getCurrentSize() {
		return numOfEntries;
	}
	
	//checks if set is empty
	public boolean isEmpty() {
		if (numOfEntries == 0) {
			return true;
		}
		
		return false;
	}
	
	//removes everything in the set
	public void clear() {
		while (!isEmpty()) {
			remove();
		}
	}
	
	//check if the set contains an entry
	public boolean contains(T anEntry) {
		boolean found = false;
		Node curNode = firstNode;
		
		while(!found && (curNode != null)) {
			if (anEntry.equals(curNode.getData())) {
				found = true;
				break;
			} else {
				curNode = curNode.getNextNode();
			}
		}
		
		return found;
	}
	
	//turns the set into an array
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numOfEntries];
		
		int index = 0;
		Node curNode = firstNode;
		
		while((index < numOfEntries) && (curNode != null)) {
			result[index] = curNode.getData();
			index++;
			curNode = curNode.getNextNode();
		}
		
		return result;
	}

	//gets a union of curSet and otherSet
	public SetInterface<T> union(SetInterface<T> otherSet) {
		Node curNode = firstNode;
		
		SetInterface<T> everything = new LinkedSet<T>();
		
		//add everything from the current node to the new set
		while (curNode != null) {
			everything.add(curNode.getData());
			//System.out.println(curNode.getData());
			curNode = curNode.getNextNode();
		}
		
		T[] otherSetArray= otherSet.toArray();

		for (int i = 0; i < otherSet.getCurrentSize(); i++) {
			if (!contains(otherSetArray[i])) { //if curSet doesn't contain the current element add it
				everything.add(otherSetArray[i]);
				//System.out.println(otherSetArray[i]);
			}
		}
		
		return everything;
	}
	
	public SetInterface<T> intersection(SetInterface<T> otherSet) {
		Node curNode = firstNode;
		int length = 0;
		
		//set length to the biggest set
		if (numOfEntries < otherSet.getCurrentSize()) {
			length = otherSet.getCurrentSize();
		} else {
			length = numOfEntries;
		}
		
		SetInterface<T> commonItems = new LinkedSet<T>();
		
		T[] otherSetArray= otherSet.toArray();
		
		for (int i = 0; i < length; i++) {
			if(contains(otherSetArray[i]) && (!commonItems.contains(otherSetArray[i]))) {
				commonItems.add(otherSetArray[i]);
			}
		}
		
		return commonItems;
	}
	
	public SetInterface<T> difference(SetInterface<T> otherSet) {
		Node curNode = firstNode;
		SetInterface<T> leftOvers = new LinkedSet<T>();
		
		while (curNode != null) {
			leftOvers.add(curNode.getData());
			curNode = curNode.getNextNode();
		}
		
		T[] otherSetArray= otherSet.toArray();
		
		for (int i = 0; i < otherSet.getCurrentSize(); i++) {
			if(contains(otherSetArray[i])) {
				remove(otherSetArray[i]);
			}
		}
		
		return leftOvers;
	}
}
