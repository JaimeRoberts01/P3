import java.util.Arrays;

/** Defines an object representing a single fitness class
 */
public class FitnessClass implements Comparable <FitnessClass> {

	private final int WEEK = 5;
	private String classID, className, tutorName, startTime;
	private int attendanceRecords;
	private double averageAttendance;
	//private String [] classDetails; // I don't really think this should go here.


	public FitnessClass (String classes) {

		String [] classDetails = classes.split(" ");
		//classDetails = line.split(" ");

		classID = classDetails [0];
		className = classDetails [1];
		tutorName = classDetails [2];
		startTime = classDetails [3];

		System.out.println("FitnessClass " + classID);
		System.out.println("FitnessClass " + className);
		System.out.println("FitnessClass " + tutorName);
		System.out.println("FitnessClass " + startTime);
		
		System.out.println("In your array: " + Arrays.toString(classDetails) +"\n"); // definitely has values here.

	}

//	public String[] getClassDetails() {
//		return classDetails;
//	}
//
//	public void setClassDetails(String[] classDetails) {
//		this.classDetails = classDetails;
//	}
//
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public int getAttendanceRecords () {
		return attendanceRecords;
	}
	
	public void setAttendanceRecords (int attendanceRecords) {
		this.attendanceRecords = attendanceRecords;
	}
	
	public double getAverageAttendance() {
		return averageAttendance;
	}
	
	public void setAttendanceRecords (double averageAttendance) {
		this.averageAttendance = averageAttendance;
	}
				
 	//private int attendanceRecords ();

//	public FitnessClass (int a) {
//		this.a = a;
//
//	}
//
//	public int getA() {
//		return a;	
//
//	}
	
	
	//write a new method to populate attendances getting the string from the method in the GUI (String line)

	public void populateAttendances () { // deal with all this later.

		//for (int i =0; i< WEEK; i++) {


		//}

	}

	public double averageAttendance () {

		int attSum = 0;
		double attAvg = 0.0;

		for (int i = 0; i< WEEK; i++) {
			attSum += //attendance;
					attAvg = attSum/WEEK;
			String.format("%.2f", (attAvg)); // should this go in the return bit? - I would have to make it a double.
		}

		return attAvg;
	}


	public String attendanceFormat () {

		// a method to return a string formatted appropriately on average attendance.

		return "";

	}

	public int compareTo (FitnessClass other) { // has to do with attendances.

		double thisAverage = 0.0;
		double otherAverage = 0.0;
		
		thisAverage = this.averageAttendance();
		otherAverage = other.averageAttendance();
				
		if (thisAverage > otherAverage) {
			return -1;
		}
		else if (thisAverage < otherAverage) {
			return 1;
		}
		else {
			System.err.println("I am reading the compare Method");
			return 0;
		}
	}
	
	
	

	public String toString () {

		String displayDetails = String.format("%10s %10s %10s %10s %10s\n", classID, className, tutorName, getAttendanceRecords(), getAverageAttendance());
		return displayDetails;
	}
}

