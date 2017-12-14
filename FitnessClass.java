import java.util.Arrays;

/** Defines an object representing a single fitness class
 */

public class FitnessClass implements Comparable <FitnessClass> {

	/* Instance variables for the classes array size and array details as well as the array for the attendance details.*/
	
	private final int WEEK = 5;
	private String classID, className, tutorName; 
	private int startTime; 
	private int [] attendanceRecords;


	/* Splits the classesIn data into its component parts (based on spaces) and puts the data into an array. The components of
	 * the array are assigned to class Id, class name, tutor name and the start time.*/
	
	public FitnessClass (String classes) {

		String [] classDetails = classes.split(" ");

		classID = classDetails [0];
		className = classDetails [1];
		tutorName = classDetails [2];
		startTime = Integer.parseInt (classDetails [3]);

		System.out.println("In your class array: " + Arrays.toString(classDetails) +"\n");
	}

	
	/* A series of getters and setters for the array details.*/
	
	public String getClassID() {
		return classID;
	}

	public void setClassID(String classID) {
		this.classID = classID;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTutorName() {
		return tutorName;
	}

	public void setTutorName(String tutorName) {
		this.tutorName = tutorName;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public String getAttendanceRecords () {

		String attendanceString = " ";

		for (int i=0; i<WEEK; i++) {

			attendanceString +=  (attendanceRecords [i]+ " ");

		}
		//String.format ("%1.2f", attendanceString);
		System.out.println("AttendanceString " + attendanceString);	
		return attendanceString;
	}
	
	
	public void setAttendanceRecords (int [] attendanceRecords) { 

		this.attendanceRecords = attendanceRecords;

		for (int i=0; i<WEEK; i++) {

			System.out.println("AttendanceRecords " + attendanceRecords[i]);

		}
		System.out.println("In your attendance array: " + Arrays.toString(attendanceRecords) + "\n");

	}


	/* The average attendance method adds up the values in an array and divides them by the length of the array.*/
	
	public double averageAttendance() { 

		int attSum = 0;
		double attAvg = 0.0;

		for (int i = 0; i< attendanceRecords.length; i++) {

			attSum += attendanceRecords [i];	
		}

		attAvg = (double) attSum/WEEK;
		System.out.println("attAvg " + String.format("%.2f",attAvg));
		String.format("%.2f", attAvg);
		return attAvg;		
	}
	
	
	/*(non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * Compares the average attendances and organises them into order.*/
	
	public int compareTo (FitnessClass other) { 

		double thisAverage = 0.0;
		double otherAverage = 0.0;

		thisAverage = this.averageAttendance();
		otherAverage = other.averageAttendance();

		if (thisAverage > otherAverage) {
		System.out.println ("I am reading the compare Method - if");
			return -1;
		}
		
		else if (thisAverage < otherAverage) {
		System.out.println ("I am reading the compare Method - else if");
			return 1;
		}
		
		else {
			System.out.println ("I am reading the compare Method - else");
			return 0;
		}
	}

		public String toString() {
	
		String attendanceDisplay = String.format("%-5s %-10s %-13s %-20s %-20s\n", classID, className, tutorName, getAttendanceRecords(), String.format("%.2f", averageAttendance()) + "\n");
		//String attendanceDisplay = String.format("%-5s %-10s %-15s %-20s %-20s\n", classID, className, tutorName, getAttendanceRecords(), averageAttendance() + "\n");

		return attendanceDisplay;
	
	}
		

}

