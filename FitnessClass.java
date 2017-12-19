/** Defines an object representing a single fitness class
 */

public class FitnessClass implements Comparable <FitnessClass> {

	/* Instance variables for the classes array size and array details as well as the array for the attendance details.*/

	private String classID, className, tutorName; 
	private int startTime; 
	private int [] attendanceRecords;


	/* Default constructor*/
	
	public FitnessClass () {
	}


	/* Splits the classesIn data into its component parts (based on spaces) and puts the data into an array. The components of
	 * the array are assigned to class Id, class name, tutor name and the start time.*/

	public FitnessClass (String classes) {

		String [] classDetails = classes.split (" ");

		classID = classDetails [0];
		className = classDetails [1];
		tutorName = classDetails [2];
		startTime = Integer.parseInt (classDetails [3]);
	}


	/* A series of getters and setters for the array details.*/

	public String getClassID () {
		return classID;
	}

	public void setClassID (String classID) {
		this.classID = classID;
	}

	public String getClassName () {
		return className;
	}

	public void setClassName (String className) {
		this.className = className;
	}

	public String getTutorName () {
		return tutorName;
	}

	public void setTutorName (String tutorName) {
		this.tutorName = tutorName;
	}

	public int getStartTime () {
		return startTime;
	}

	public void setStartTime (int startTime) {
		this.startTime = startTime;
	}

	public String getAttendanceRecords () {

		String attendanceString = " ";

		for (int i = 0; i<attendanceRecords.length; i++) {

			attendanceString +=  String.format ("%5.2s", attendanceRecords [i]);
		}
	
		return attendanceString;
	}

	public void setAttendanceRecords (int [] attendanceRecords) { 

		this.attendanceRecords = attendanceRecords;
	}


	/* The average attendance method adds up the values in an array and divides them by the length of the array.*/

	public double getAverageAttendance() { 

		double attSum = 0.0;
		double attAvg = 0.0;

		for (int i = 0; i< attendanceRecords.length; i++) {

			attSum += attendanceRecords [i];	
		}

		attAvg = attSum/attendanceRecords.length;
		return attAvg;		
	}
	

	/*(non-Javadoc) @see java.lang.Comparable#compareTo(java.lang.Object). Compares the average attendances and organises them into order.*/

	public int compareTo (FitnessClass other) { 

		double thisAverage = 0.0;
		double otherAverage = 0.0;

		thisAverage = this.getAverageAttendance();
		otherAverage = other.getAverageAttendance();

		if (thisAverage > otherAverage) {
			return -1;
		}

		else if (thisAverage < otherAverage) {
			return 1;
		}

		else {
			return 0;
		}
	}


	/* Method to format the array contents when printing to the TextArea.*/

	public String toString() {

		String attendanceDisplay = (String.format ("%-10s %-15s %-6s %-20s %15s \n", classID, className, tutorName, getAttendanceRecords(), String.format("%.2f", getAverageAttendance())));
		return attendanceDisplay;
	}


	/* Method to format the classesOut data when printing to the classesOut file.*/

	public String classesOutFile () {

		String classesOut = (classID + " " + className + " " + tutorName + " " + startTime);
		return classesOut;
	}
}