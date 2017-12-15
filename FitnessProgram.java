import java.util.*;


/**
 * Maintains a list of Fitness Class objects
 * The list is initialised in order of start time
 * The methods allow objects to be added and deleted from the list
 * In addition an array can be returned in order of average attendance
 */

public class FitnessProgram {

	/* Instance variables.*/
	
	private final int MAXCLASSES = 7;
	private FitnessClass[] fClass, sortedClasses;
	private final int FIVEWEEKS = 5; 
	

	public FitnessProgram () { //Creating the FitnessClass array.

		fClass = new FitnessClass [MAXCLASSES];		
	}


	public FitnessClass [] getfClass () {
		return fClass;	
	}

	
	/* If there is no value in the array index return a String "Available". Otherwise, returns a String composed of the class names. The names are organised by start time.*/

	public String buildClassList (int startTime) { 

		String className = " ";

		for (int i=0; i<MAXCLASSES; i++) {

			if (fClass [i] == null) {
				className = "Available";	
			}

			else if (startTime + 9  == (fClass [i].getStartTime ())) { // sorted by start time.
				className = fClass [i].getClassName ();	
				return className;
			}
		}	
	
		return className;
	}


	/* If there is no value in the array index, skip. Otherwise, returns a String composed of the tutor names. The names are organised by start time.*/
	
	public String buildTutorList (int startTime) {

		String tutorName = " ";

		for (int i=0; i<MAXCLASSES; i++) {

			if (fClass [i] == null) {
				i++;
			}

			else if (startTime + 9  == (fClass [i].getStartTime ())) {
				tutorName = fClass [i].getTutorName ();	
				return tutorName;
			}
		}	
	
		return tutorName;
	}


	/* If there is no value in the array index, skip. Otherwise, compare the class IDs and, if there's a match, return the information at that index*/
	
	public FitnessClass populateAttendances (String attClassID) {

		for (int i=0; i<MAXCLASSES; i++) {

			if (fClass [i] == null) {
				i++;
			}

			else  {
				String getID = fClass [i].getClassID ();

				if (getID.equals (attClassID)) {

					System.out.println(getID + " " + attClassID);

					return fClass[i];
				}
			}
		}

		return null;
	}


	/* Copies the sorted attendance from the fClass array into the sortedClasses array. Returns a formatted String of the array.*/
	
	public String sortedAttendance () {

		String sortedAttendance = " ";
		sortedClasses = new FitnessClass [FIVEWEEKS];
		int j = 0;

		for (int i = 0; i < MAXCLASSES; i++) {
			if (fClass[i] != null) {
				sortedClasses[j++] = fClass [i];
			}
		}

		sortedAttendance = (Arrays.toString (sortedClasses) + "\n");
		String sortedAttendance1 = sortedAttendance.replace(",",""); // Replace the commas.
		String sortedAttendance2 = sortedAttendance1.replace("[",""); // Replace the left-hand brackets.
		String sortedAttendanceFinal = sortedAttendance2.replaceAll("]", ""); // Replace the right-hand brackets.
		System.out.println("sortedClasses " + sortedAttendanceFinal);
		return sortedAttendanceFinal;
	}

	
	/* Calculates the overall average of the average attendance.*/
	
	public String overallAttendanceAverage () {


		double individualAverage = 0.00;
		double sumAverages = 0.00;
		double overallAverage = 0.00;

		String overallAverageAttendance = " ";

		for (FitnessClass fC: sortedClasses) {

			individualAverage = fC.getAverageAttendance();
			sumAverages += individualAverage;

			overallAverage = (double) sumAverages/sortedClasses.length;
		}

		overallAverageAttendance = String.format("%14.2f", overallAverage);	
		System.out.println ("Overall Average = " + overallAverageAttendance);
		return overallAverageAttendance;
	}	










	//	public int getNumClasses() {
	//		return numClasses;
	//	}
	//
	//	public void setNumClasses(int numClasses) {
	//		this.numClasses = numClasses;
	//	}
	//
	//	public void checkArray () {
	//
	//		for (int i =0; i< MAXCLASSES; i++) {
	//			if (fClass[i] == null) {
	//				System.err.println ("null");
	//			}
	//			else {
	//				System.err.println(fClass[i].getStartTime());
	//				//System.err.println(fClass [i]);
	//			}
	//			//System.err.println(fClass.getClass());
	//			//
	//		}
	//
	//	}
}




