//import java.util.*;

/**
 * Maintains a list of Fitness Class objects
 * The list is initialised in order of start time
 * The methods allow objects to be added and deleted from the list
 * In addition an array can be returned in order of average attendance
 */
public class FitnessProgram {

	private final int MAXCLASSES = 7;
	private FitnessClass[] fClass;
	private int numClasses;
	
	
	public FitnessProgram () { //Creating the array.

		fClass = new FitnessClass [MAXCLASSES];	
		
	}

	
	public FitnessClass [] getfClass () {
		return fClass;
		
	}
		
	public String buildClassList (int startTime) {

		String className = " ";
		
		try {

			for (int i=0; i<MAXCLASSES; i++) {

				FitnessClass fc = this.getfClass()[i];

				if (fc == null) {
					className = "Available";	
				}

				else if (startTime + 9  == (Integer.parseInt(fc.getStartTime()))) {
					className = fc.getClassName();	
					return className;	
				}
			}	
		}
		catch (NullPointerException NPE)  {
			NPE.printStackTrace();
		}

		return className;
	}
	
	
	public String buildTutorList (int startTime) {

		String tutorName = " ";
		
		try {

			for (int i=0; i<MAXCLASSES; i++) {

				FitnessClass fc = this.getfClass()[i];

				if (fc == null) {
					tutorName = " ";		
				}

				else if (startTime + 9  == (Integer.parseInt(fc.getStartTime()))) {
					tutorName = fc.getTutorName();	
					return tutorName;	
				}
			}	
		}
		catch (NullPointerException NPE)  {
			NPE.printStackTrace();
		}

		return tutorName;
	}
	
	//////////////////////////////////////////////////////////
	
	public String populateAttendances (String line) {
		
		
		for (int i=0; i<MAXCLASSES; i++) {
		//FitnessClass fc = this.getfClass() [i];
		
		
		
		
		}
		return " ";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int getNumClasses() {
		return numClasses;
	}

	public void setNumClasses(int numClasses) {
		this.numClasses = numClasses;
	}
	
	public void checkArray () {
		
		for (int i =0; i< MAXCLASSES; i++) {
			if (fClass[i] == null) {
				System.err.println ("null");
		}
		else {
			System.err.println(fClass[i].getStartTime());
			//System.err.println(fClass [i]);
		}
			//System.err.println(fClass.getClass());
			//
		}
		
	}
}




