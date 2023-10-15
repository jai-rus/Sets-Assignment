package assignment1;
/*is it fine to import this?
 * Do we have to worry about the input being too large?
*/
import java.util.Arrays;

public class ResizeableArraySet<T> implements SetInterface<T> {
	private int numOfEntries;
	private static final int DEFAULT_CAPACITY = 25;
	private T[] set;
	
	public ResizeableArraySet() {
		this(DEFAULT_CAPACITY);
	}
	
	public ResizeableArraySet(int capacity) {
		numOfEntries = 0;
		@SuppressWarnings("Unchecked")
		T[] temp = (T[])new Object[capacity];
		set = temp;
	}
	
	//checks numOfEntries
	public int getCurrentSize() {
		return numOfEntries;
	}
	
	//checks if numOfEntries is not equal to zero
	public boolean isEmpty() {
		if (numOfEntries != 0) {
			return false;
		}
		return true;
	}
	
	//adds a new entry
	public boolean add(T newEntry) {
		//checks if entry already exists in the set
		if (contains(newEntry)) { 
			return false;
		}
		
		isArrayFull(); //check if array is full
		set[numOfEntries] = newEntry;
		numOfEntries++;
		return true;
	}
	
	
	//checks if array is full by comparing numOfEntries to length
	private void isArrayFull() {
		if (numOfEntries == set.length) {
			doubleCapacity();
		}
	}
	
	//doubles the capacity of the set
	private void doubleCapacity() {
		int newLength = 2 * set.length;
		set = Arrays.copyOf(set, newLength);
	}
	
	//removes last added item and returns it
	public T remove() {
		T temp = set[numOfEntries-1];
		set[numOfEntries-1] = null;
		numOfEntries--;
		return temp;
	}
	
	//removes a specific entry
	public boolean remove(T anEntry) {
		int index = getIndexOf(anEntry);
		if (index == -1) { //check if index exists if it doesnt return false
			return false;
		}
		set[index] = set[numOfEntries - 1];
		set[numOfEntries-1] = null;
		numOfEntries--;
		return true;
	}
	
	//clears the set
	public void clear() {
		while(!isEmpty()) {
			remove();
		}
	}
	
	//check if the set contains a specific entry
	public boolean contains(T anEntry) {
		if (getIndexOf(anEntry) == -1) {
			return false;
		}
		
		return true;
	}
	
	//finds the index of a specific entry
	private int getIndexOf(T entry) {
		int cur = -1;
		
		for (int i = 0; i < numOfEntries; i++) {
			if (entry.equals(set[i])) {
				cur = i;
				break;
			}
		}
		
		return cur;
	}
	
	//turns the set into an array
	public T[] toArray() {
		@SuppressWarnings("Unchecked")
		T[] temp = (T[]) new Object[numOfEntries];
		for (int i = 0; i < numOfEntries; i++) {
			temp[i] = set[i];
		}
		
		return temp;
	}
	
	//gets union of curSet and otherSet
	public SetInterface<T> union(SetInterface<T> otherSet) {
		int lengthNewSet = 0;
		
		//checks to see how big the set should be depending on which one is bigger
		if (numOfEntries < otherSet.getCurrentSize()) {
			lengthNewSet = otherSet.getCurrentSize();
		} else {
			lengthNewSet = numOfEntries;
		}
		
		//Creates object SetInterface
		SetInterface<T> everything = new ResizeableArraySet<T>(lengthNewSet);
		
		//Create an array of otherSet
		T[] otherSetArray = otherSet.toArray();
		
		//add all items from the this set to everything
		for (int i = 0; i < numOfEntries; i++) {
			//newSet[i] = set[i];
			everything.add(set[i]);
		}
		
		//adds all leftover items from otherSet
		for (int i = 0; i < otherSet.getCurrentSize(); i++) {
			T curElem = otherSetArray[i];
			if (!contains(curElem)) { //if everything doesn't contain the current element, add it
				everything.add(curElem);
			}
		}
		
		return everything;
	}
	
	//get intersection of curSet and otherSet
	public SetInterface<T> intersection(SetInterface<T> otherSet) {
		int lengthNewSet = 0;
		
		//checks to see how big the set should be depending on which one is bigger
		if (numOfEntries < otherSet.getCurrentSize()) {
			lengthNewSet = otherSet.getCurrentSize();
		} else {
			lengthNewSet = numOfEntries;
		}
		//creates object setinterface
		SetInterface<T> commonItems = new ResizeableArraySet<T>(lengthNewSet);
		
		T[] otherSetArray = otherSet.toArray();
		//if this set contains an item from the otherSet, add it to commonItems
		for (int i = 0; i < otherSetArray.length; i++) {
			if (contains(otherSetArray[i]) && (!commonItems.contains(otherSetArray[i]))) {
				commonItems.add(otherSetArray[i]);
			}
		}
		
		return commonItems;
	}
	
	//get difference of curSet and otherSet
	public SetInterface<T> difference(SetInterface<T> otherSet) {
		int lengthNewSet = 0;
		
		//checks to see how big the set should be depending on which one is bigger
		if (numOfEntries < otherSet.getCurrentSize()) {
			lengthNewSet = otherSet.getCurrentSize();
		} else {
			lengthNewSet = numOfEntries;
		}
		
		//creates object leftOvers
		SetInterface<T> leftOvers = new ResizeableArraySet<T>(lengthNewSet);
		
		T[] otherSetArray = otherSet.toArray();
		
		for (int i = 0; i < numOfEntries; i++) {
			leftOvers.add(set[i]);
		}

		for (int i = 0; i < otherSet.getCurrentSize(); i++) {
			if(contains(otherSetArray[i])) {
				leftOvers.remove(otherSetArray[i]);
			}
		}
		
		return leftOvers;
	}
}
