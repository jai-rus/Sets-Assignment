package assignment1;

public class ArraySetTest {
	public static void main(String[] args) {
		SetInterface<String> set1 = new ResizeableArraySet<>();
		
		System.out.println("Using Resizable Array");
		//Uses isEmpty()
		System.out.println("Is empty = " + set1.isEmpty());
		
		//Uses add()
		set1.add("A");
		set1.add("B");
		set1.add("C");
		
		//Uses getCurretnSize()
		System.out.println("Current size is " + set1.getCurrentSize());
		System.out.println("Is empty = " + set1.isEmpty()); //Uses isEmpty();
		
		//Uses remove()
		System.out.println("Removed " + set1.remove());
		set1.add("D");
		
		//Uses remove(anEntry)
		System.out.println("Removing B: " + set1.remove("B"));
		
		//Uses clear()
		set1.clear();
		System.out.println("Is empty = " + set1.isEmpty());
		
		set1.add("A");
		set1.add("B");
		set1.add("C");
		
		//Uses contains()
		System.out.println("Does this set contain A: " + set1.contains("A"));
		System.out.println("Does this set contain Z: " + set1.contains("Z"));
		
		//Uses toArray()
		Object[] testArray = new Object[set1.getCurrentSize()]; 	
		testArray = set1.toArray();
		for (int i = 0; i < testArray.length; i++) {
			System.out.print(testArray[i] + " ");
		}
		
		System.out.println();
		
		SetInterface<String> set2 = new ResizeableArraySet<>();
		//set2.add("B");
		set2.add("D");
		set2.add("E");
		set2.add("F");
		
		//testing union
		SetInterface<String> everything = set1.union(set2);
		
		Object[] testArray2 = new Object[everything.getCurrentSize()];
		testArray2 = everything.toArray();
		System.out.println("Union: ");
		for (int i = 0; i < testArray2.length; i++) {
			System.out.print(testArray2[i] + " ");
		}
		
		System.out.println();
		
		//testing intersection
		set1.add("D");
		set2.add("A");
		SetInterface<String> commonItems = set1.intersection(set2);

		Object[] testArray3 = new Object[commonItems.getCurrentSize()];
		testArray3 = commonItems.toArray();
		System.out.println("Intersection: ");
		for (int i = 0; i < testArray3.length; i++) {
			System.out.print(testArray3[i] + " ");
		}
		
		System.out.println();
		
		//testing difference
		SetInterface<String> set3 = new ResizeableArraySet<>();
		set3.add("A");
		set3.add("B");
		set3.add("C");
		
		SetInterface<String> set4 = new ResizeableArraySet<>();
		set4.add("B");
		set4.add("C");
		set4.add("D");
		set4.add("E");
		
		SetInterface<String> leftOver1 = set3.difference(set4);
		Object[] testArray4 = new Object[leftOver1.getCurrentSize()];
		testArray4 = leftOver1.toArray();
		System.out.println("Difference: ");
		for (int i = 0; i < testArray4.length; i++) {
			System.out.print(testArray4[i] + " ");
		}
		
		System.out.println();
		
		SetInterface<String> leftOver2 = set4.difference(set3);
		Object[] testArray5 = new Object[leftOver2.getCurrentSize()];
		testArray5 = leftOver2.toArray();
		System.out.println("Difference: ");
		for (int i = 0; i < testArray5.length; i++) {
			System.out.print(testArray5[i] + " ");
		}
	}
}
