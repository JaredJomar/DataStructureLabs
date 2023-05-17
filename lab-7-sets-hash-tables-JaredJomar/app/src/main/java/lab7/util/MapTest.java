package lab7.util;

import lab7.interfaces.HashFunction;
import lab7.interfaces.List;
import lab7.interfaces.Map;
import lab7.util.hashTable.HashTableSC;
import lab7.util.studentRecord.StudentRecord;

public class MapTest {

	public static void main(String[] args) {
		HashFunction<String> simpleHashFunction =  (key) -> {
			String temp = key.toString();
			int result = 0;
			for (int i = 0; i < temp.length(); i++)
				result += temp.charAt(i);
			return result;
		}; 
		
		Map<String, StudentRecord> map = new HashTableSC<>(2, simpleHashFunction);

		StudentRecord s1 = new StudentRecord("123", "Apu", "Smith", 18, 4.0, "NY");
		StudentRecord s2 = new StudentRecord("647", "Xi",  "Li",    19, 2.0, "SF");
		StudentRecord s3 = new StudentRecord("934", "Amy", "Wo",    22, 3.0, "LA");
		StudentRecord s4 = new StudentRecord("12",  "Bo",  "Ron",   30, 1.0, "SJ");

		map.put(s1.getStudentID(), s1);
		map.put(s2.getStudentID(), s2);
		map.put(s3.getStudentID(), s3);
		map.put(s4.getStudentID(), s4);
		map.print(System.out);

		System.out.println("\nAdding with same key (647)");
		StudentRecord s5 = new StudentRecord("647", "Mi", "Mo", 31, 2.5, "SJ");
		map.put(s5.getStudentID(), s5);
		map.print(System.out);

		System.out.println("\nElement with key 934: " + map.get("934"));
		System.out.println("Element with key 92: " + map.get("92"));
		System.out.println("Removing element with key 934: " + map.remove("934"));
		System.out.println("After remove, get with key 934: " + map.get("934"));

		System.out.println("\nAdding element with key 111");
		map.put("111", new StudentRecord("111", "Ron", "Clark", 19, 3.90, "SJ"));
		map.print(System.out);

		System.out.println("\nPrinting keys");
		printKeys(map.getKeys());
		System.out.println("Printing values");
		printValues(map.getValues());

		System.out.println("\nSize of Hash Table: " + map.size());
		System.out.println("Map contains 111: " + map.containsKey("111"));
		System.out.println("Map contains 934: " + map.containsKey("934"));
		System.out.println("Map is empty: " + map.isEmpty());
		System.out.println("Clearing the Hash Table...");
		map.clear();
		System.out.println("Hash Table is empty: " + map.isEmpty());

		System.out.println("Done!");
	}

	private static void printKeys(List<String> keys) {
		for (String s : keys)
			System.out.println(s);
	}

	private static void printValues(List<StudentRecord> values) {
		for (StudentRecord s : values)
			System.out.println(s);
	}
}