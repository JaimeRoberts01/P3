import java.util.*;


/**
 * Maintains a list of Fitness Class objects
 * The list is initialised in order of start time
 * The methods allow objects to be added and deleted from the list
 * In addition an array can be returned in order of average attendance
 */


public class FitnessProgram {

	private final int MAXCLASSES = 7;
	private FitnessClass[] fClass, sortedClasses;
	private final int FIVEWEEKS = 5; 
	

	public FitnessProgram () { //Creating the array.

		fClass = new FitnessClass [MAXCLASSES];		
	}


	public FitnessClass [] getfClass () {
		return fClass;	
	}


	public String buildClassList (int startTime) {

		String className = " ";

		for (int i=0; i<MAXCLASSES; i++) {

			if (fClass[i] == null) {
				className = "Available";	
			}

			else if (startTime + 9  == (fClass[i].getStartTime())) {
				className = fClass[i].getClassName();	
				return className;
			}
		}	
	
		return className;
	}


	public String buildTutorList (int startTime) {

		String tutorName = " ";

		for (int i=0; i<MAXCLASSES; i++) {

			if (fClass [i] == null) {
				i++;
			}

			else if (startTime + 9  == (fClass[i].getStartTime())) {
				tutorName = fClass[i].getTutorName();	
				return tutorName;
			}
		}	
	
		return tutorName;
	}


	public FitnessClass populateAttendances (String attClassID) {

		
		
		for (int i=0; i<MAXCLASSES; i++) {

			if (fClass [i] == null) {
				i++;
			}

			else  {
				String getID = fClass[i].getClassID();

				if (getID.equals(attClassID)) {

					System.out.println(getID + " " + attClassID);

					return fClass[i];
				}
			}

		}

		return null;
	}


	public String sortedAttendance () {

		String sortedAttendance = " ";
		sortedClasses = new FitnessClass [FIVEWEEKS];
		int j = 0;

		for (int i = 0; i < MAXCLASSES; i++) {
			if (fClass[i] != null) {
				sortedClasses[j++] = fClass [i];
			}
		}

		//Arrays.sort(descendingClasses, Collections.reverseOrder());

		Arrays.sort(sortedClasses);
		sortedAttendance = (Arrays.toString(sortedClasses) + "\n");


		String sortedAttendance1 = sortedAttendance.replace(",","");
		String sortedAttendance2 = sortedAttendance1.replace("[","");
		String sortedAttendanceFinal = sortedAttendance2.replaceAll("]", "");

		System.out.println("sortedClasses " + sortedAttendanceFinal);
		//System.out.println ("sortedClasses1" + Arrays.toString(sortedClasses));

		//return sortedAttendance;
		return sortedAttendanceFinal;
	}

	
	public String overallAttendanceAverage () {


		double individualAverage = 0.00;
		double sumAverages = 0.00;
		double overallAverage = 0.00;

		String overallAverageAttendance = " ";

		for (FitnessClass fC: sortedClasses) {

			individualAverage = fC.averageAttendance();
			sumAverages += individualAverage;

			overallAverage = (double) sumAverages/sortedClasses.length;
		}

		overallAverageAttendance = String.format("%9.2f", overallAverage);	
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




