package assignment1;

public interface SetInterface<T> {
	public int getCurrentSize();
	
	public boolean isEmpty();
	
	public boolean add(T newEntry);
	
	public T remove();
	
	public boolean remove(T anEntry);
	
	public void clear();
	
	public boolean contains(T anEntry);
	
	public T[] toArray();
	
	public SetInterface<T> union(SetInterface<T> otherSet);
	
	public SetInterface<T> intersection(SetInterface<T> otherSet);
	
	public SetInterface<T> difference(SetInterface<T> otherSet);
}
