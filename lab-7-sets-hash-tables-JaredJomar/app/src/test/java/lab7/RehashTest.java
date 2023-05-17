package lab7;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import lab7.interfaces.HashFunction;
import lab7.util.hashTable.HashTableOA;
import lab7.util.hashTable.HashTableSC;
import lab7.util.studentRecord.StudentRecord;

public class RehashTest {
	
	StudentRecord[] students;
	HashTableOA<String, StudentRecord> ht1;
	HashTableSC<String, StudentRecord> ht2;
	
	@Before
	public void setUp() {
		HashFunction<String> hf = (s) -> {
			int result = 0;
			for (char c : s.toCharArray()) {
				result += c;
			}
			return result;
		};
		
		ht1 = new HashTableOA<>(2, hf);
		ht2 = new HashTableSC<>(2, hf);
		
		students = new StudentRecord[] {
			new StudentRecord("802180426", "Fernando", "Bermudez", 22, 3.85, "Ponce"),
			new StudentRecord("802181234", "Maria", "Munoz", 22, 3.60, "Arecibo"),
			new StudentRecord("802191234", "Yan", "Aquino", 21, 3.55, "Cabo Rojo"),
			new StudentRecord("802201234", "Pepito", "Perez", 19, 3.20, "Fajardo"),
			new StudentRecord("802171234", "Juan", "Del Pueblo", 24, 2.50, "Carolina"),
			new StudentRecord("802165678", "Roberto", "Arias", 34, 3.70, "Bayamon"),
			new StudentRecord("801198943", "Jacob", "Delgado", 21, 3.90, "San Juan"),
			new StudentRecord("843183425", "Kevin", "Correa", 22, 3.75, "Aguadilla"),
			new StudentRecord("802183425", "Leonel", "Aviles", 22, 3.15, "Aguadilla"),
			new StudentRecord("802183325", "Christopher", "Castillo", 22, 3.98, "Aguada")
		};
		
		Arrays.sort(students, (r1, r2) -> r1.getLastName().compareTo(r2.getLastName()));
	}
	
	@Test
	public void testOpenAddressing() {
		for (int i = 0; i < students.length; i++) {
			StudentRecord s = students[i];
			ht1.put(s.getStudentID(), s);
		}
		Object[] records = ht1.toArray();
		Arrays.sort(records, (r1, r2) -> ((StudentRecord) r1).getLastName().compareTo(((StudentRecord) r2).getLastName()));
		assertTrue("Failed to rehash 3 times", ht1.getRehashCount() == 3);
		assertArrayEquals("Failed to add all elements correctly after rehash", students, records);
	}
	
	@Test
	public void testSeparateChaining() {
		for (int i = 0; i < students.length; i++) {
			StudentRecord s = students[i];
			ht2.put(s.getStudentID(), s);
		}
		Object[] records = ht2.toArray();
		Arrays.sort(records, (r1, r2) -> ((StudentRecord) r1).getLastName().compareTo(((StudentRecord) r2).getLastName()));
		assertTrue("Failed to rehash 3 times", ht2.getRehashCount() == 3);
		assertArrayEquals("Failed to add all elements correctly after rehash", students, records);
	}

}
