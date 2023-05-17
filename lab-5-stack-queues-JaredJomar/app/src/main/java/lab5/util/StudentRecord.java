package lab5.util;
public class StudentRecord implements Comparable<StudentRecord> {
		private String name;
		private int studentID;
		private String major;
		
		public StudentRecord(String name, int studentID, String major) {
			this.name = name;
			this.studentID = studentID;
			this.major = major;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getStudentID() {
			return studentID;
		}

		public void setStudentID(int studentID) {
			this.studentID = studentID;
		}

		public String getMajor() {
			return major;
		}

		public void setMajor(String major) {
			this.major = major;
		}

		@Override
		public int compareTo(StudentRecord o) {
			return getName().compareTo(o.getName());
		}
		
	}