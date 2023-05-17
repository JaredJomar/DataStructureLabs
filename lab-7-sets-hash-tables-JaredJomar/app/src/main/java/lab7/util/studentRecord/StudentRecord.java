package lab7.util.studentRecord;

public class StudentRecord {
	private String stdId;
	private String firstName;
	private String lastName;
	private int age;
	private double gpa;
	private String city;

	public StudentRecord(String stdId, String firstName, String lastName, int age, double gpa, String city) {
		if (stdId == null)
			throw new IllegalArgumentException("Parameter cannot be null.");
		this.stdId = stdId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gpa = gpa;
		this.city = city;
	}

	/***********/
	/* Getters */
	/***********/
	public String getStudentID() {
		return stdId;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public int getAge() {
		return age;
	}
	public double getGpa() {
		return gpa;
	}
	public String getCity() {
		return city;
	}
	
	/**
	 * This method allows to specify a Student object within a print statement
	 */
	public String toString() {
		return "(" + stdId + ", " + firstName + " " + lastName + ", " +
				age + ", " + gpa + ", " + city + ")";
	}

}