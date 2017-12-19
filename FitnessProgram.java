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
	private int numClasses;


	public FitnessProgram () { 

		fClass = new FitnessClass [MAXCLASSES];	// Creates the FitnessClass array.	
	}


	public int getNumClasses () { // Returns the number of non-null classes in the array.
		return numClasses;
	}


	/* Organises the FitnessClass array based on the start time of the classes. It increases the class count with each one.*/

	public void addClass (FitnessClass fitClass, int getStartTime) {

		int startTime = getStartTime - 9;

		if (numClasses == MAXCLASSES) {
		}

		else { 

			fClass [startTime] = fitClass;
			numClasses ++;
		}
	}


	public FitnessClass [] getfClass () { // Returns the array.

		return fClass;	
	}


	/*Locates the first available time-slot.*/

	public int getAvailableTimeSlots () {

		for (int i = 0; i < MAXCLASSES; i++)	{

			if (fClass [i] == null) {

				return i+9;
			}
		}

		return 0;
	}


	/* If there is no value in the array index return a String "Available". Otherwise, returns a String composed of the class names. The 
	 * names are organised by start time.*/

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


	/* If there is no value in the array index, skip. Otherwise, returns a String composed of the tutor names. The names are organised by 
	 * start time.*/

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


	/* If there is no value in the array index, skip. Otherwise, compare the class IDs and, if there's a match, return the information at 
	 * that index.*/

	public FitnessClass byClassID (String attClassID) {

		for (int i=0; i<MAXCLASSES; i++) {

			if (fClass [i] == null) {
				i++;
			}

			else  {

				String getID = fClass [i].getClassID ();

				if (getID.equals (attClassID)) {

					return fClass[i];
				}
			}
		}

		return null;
	}


	/*If null, sets the details for class ID, class name, tutor name and the start time of the first available time-slot. Increases the
	 * number of classes in the array and sets the attendances.*/

	public void classAddition (String classIDIn, String classNameIn, String tutorNameIn) {

		int startTime = this.getAvailableTimeSlots();

		for (int i = 0; i< MAXCLASSES; i++) {

			if (fClass [i] == null) {

				fClass [i] = new FitnessClass ();

				fClass [i].setClassID(classIDIn);
				fClass [i].setClassName(classNameIn);
				fClass [i].setTutorName(tutorNameIn);
				fClass [i].setStartTime(startTime);

				numClasses ++;

				int [] addedAttendance = {0,0,0,0,0};
				fClass [i].setAttendanceRecords(addedAttendance);

				break;
			}
		}
	}


	/*If not null and the class ID in the TextField matches one in the timetable, remove that class and decrease the class count.*/

	public void classDeletion (String classIDIn) {

		for (int i = 0; i< MAXCLASSES; i++) {

			if ((fClass [i] != null) && (fClass [i].getClassID().equals(classIDIn))) {

				fClass [i] = null;
				numClasses --;
			}
		}
	}


	/* Copies the sorted attendance from the fClass array into the sortedClasses array. Returns a formatted String of the array.*/

	public String sortedAttendance () {

		String sortedAttendance = " ";
		int size = 0;

		for (int i = 0; i < fClass.length; i++) { 

			if (fClass [i] != null) { 
				size++;
			}
		}

		sortedClasses = new FitnessClass [size];
		
		int j = 0;

		for (int i = 0; i < MAXCLASSES; i++) {
			
			if (fClass [i] != null) {
				
				sortedClasses [j++] = fClass [i];
			}
		}

		Arrays.sort (sortedClasses);

		/* Replaces all the commas and brackets of the array.*/

		sortedAttendance = (Arrays.toString (sortedClasses) + "\n");
		String sortedAttendance1 = sortedAttendance.replace (",",""); 
		String sortedAttendance2 = sortedAttendance1.replace ("[",""); 
		String sortedAttendanceFinal = sortedAttendance2.replaceAll ("]", ""); 

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

		overallAverageAttendance = String.format("%15.2f", overallAverage);	
		return overallAverageAttendance;
	}	
}