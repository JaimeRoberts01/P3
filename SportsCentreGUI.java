import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.*;
import java.io.*;


/**
 * Defines a GUI that displays details of a FitnessProgram object
 * and contains buttons enabling access to the required functionality.
 */

@SuppressWarnings("serial")

public class SportsCentreGUI extends JFrame implements ActionListener {

	/* Instance variables.*/
	
	/** GUI JButtons */
	private JButton closeButton, attendanceButton;
	private JButton addButton, deleteButton;

	/** GUI JTextFields */
	private JTextField idIn, classIn, tutorIn;

	/** Display of class timetable */
	private JTextArea display;

	/** Display of attendance information */
	private ReportFrame report;

	/** Names of input text files */
	private final String classesInFile = "ClassesIn.txt";
	private final String classesOutFile = "ClassesOut.txt";
	private final String attendancesFile = "AttendancesIn.txt";

	private FitnessClass fitClass;
	private FitnessProgram fitProg;
	
	
	/**
	 * Constructor for AssEx3GUI class
	 */
	
	public SportsCentreGUI () {

		setDefaultCloseOperation (EXIT_ON_CLOSE);
		setTitle ("Boyd-Orr Sports Centre");
		setSize (700, 300);
		setResizable (false); // Cannot change the size.
		display = new JTextArea ();
		display.setFont (new Font ("Courier", Font.PLAIN, 14));
		display.setEditable (false); // Cannot write in the display area.
		add (display, BorderLayout.CENTER);
		layoutTop ();
		layoutBottom ();

		/* Calls the initLadiesDay, updateDisplay and initAttendance methods.*/
		
		initLadiesDay ();  
		updateDisplay (); // Calls the updateDisplay 
		initAttendances ();
	}


	/**
	 * Creates the FitnessProgram list ordered by start time
	 * using data from the file ClassesIn.txt
	 */
	
	public void initLadiesDay () { 

		fitProg = new FitnessProgram ();

		FileReader reader = null;
		Scanner scanner = null;

		try {
			reader = new FileReader (classesInFile); // Reads the ClassesIn file.
			scanner = new Scanner (reader);
			while (scanner.hasNextLine ()) { 
				String classes = scanner.nextLine ();
				System.out.println("The initLadies day method " + classes);
				fitClass = new FitnessClass (classes);
				fitProg.getfClass()[fitClass.getStartTime()-9] = fitClass; // Organised by start time.
			}
			
			reader.close ();
			scanner.close ();
		}

		catch (IOException IOE) { 
			
			JOptionPane.showMessageDialog (null, "FILE NOT FOUND", "ERROR", JOptionPane.ERROR_MESSAGE);
			IOE.printStackTrace();		
		}
		
		catch (InputMismatchException IME) {
			
			JOptionPane.showMessageDialog (null, "INVALID FILE", "ERROR", JOptionPane.ERROR_MESSAGE);
			IME.printStackTrace();	
		}
	}


	/**
	 * Initialises the attendances using data
	 * from the file AttendancesIn.txt
	 */
	
	public void initAttendances () {

		FileReader reader = null;
		Scanner scanner = null;

		try {
			
			reader = new FileReader (attendancesFile); // Reads the AttendancesIn file.
			scanner = new Scanner (reader);
			while (scanner.hasNextLine ()) { 

				String attendances = scanner.nextLine ();
				System.out.println ("The initAttendances method " + attendances);

				String [] attendanceDetails = attendances.split (" "); // Splits the text based on spaces and assigns to an array.
				String attclassID = attendanceDetails [0];  

				int week1 = Integer.parseInt (attendanceDetails [1]);
				int week2 = Integer.parseInt (attendanceDetails [2]);
				int week3 = Integer.parseInt (attendanceDetails [3]);
				int week4 = Integer.parseInt (attendanceDetails [4]);
				int week5 = Integer.parseInt (attendanceDetails [5]);
				
				int [] allWeeks = {week1, week2, week3, week4, week5}; // Creates an integer array for the attendance data.

				fitClass = fitProg.populateAttendances(attclassID);
				fitClass.setAttendanceRecords(allWeeks);
			}

			reader.close ();
			scanner.close ();
		}

		catch (IOException IOE) { 
			
			JOptionPane.showMessageDialog (null, "FILE NOT FOUND", "ERROR", JOptionPane.ERROR_MESSAGE);
			IOE.printStackTrace();		
		}
		
		catch (InputMismatchException IME) {
			
			JOptionPane.showMessageDialog (null, "INVALID FILE", "ERROR", JOptionPane.ERROR_MESSAGE);
			IME.printStackTrace();	
		}
	}	


	/**
	 * Instantiates timetable display and adds it to GUI
	 */

	public void updateDisplay () {

		display.setBorder (new EmptyBorder (10,10,10,10)); // Creates a border around the edge of the display.

		String [] header = {"9-10", "10-11", "11-12", "12-13", "13-14", "14-15", "15-16"}; // Creates an array for the times.
		
		for (int i = 0; i<7; i++) {
			
			display.append(String.format("%-13s", header[i])); // Adds the times.
		}

		display.append("\n");

		for (int i = 0; i<7; i++) {
			display.append (String.format("%-13s", fitProg.buildClassList(i))); // Adds the class names.
		}

		display.append("\n");

		for (int i = 0; i<7; i++) {
			display.append (String.format("%-13s", fitProg.buildTutorList(i))); // Adds the tutor names.
		}	
	}


	/**
	 * adds buttons to top of GUI
	 */

	public void layoutTop () {
		JPanel top = new JPanel ();
		closeButton = new JButton ("Save and Exit");
		closeButton.addActionListener (this);
		top.add (closeButton);
		attendanceButton = new JButton ("View Attendances");
		attendanceButton.addActionListener (this);
		top.add (attendanceButton);
		add (top, BorderLayout.NORTH);
	}


	/**
	 * adds labels, text fields and buttons to bottom of GUI
	 */
	
	public void layoutBottom() {
		
		// instantiate panel for bottom of display
		JPanel bottom = new JPanel (new GridLayout (3, 3));
		bottom.setBorder (new EmptyBorder (10,10,10,10));
		// add upper label, text field and button
		JLabel idLabel = new JLabel ("Enter Class Id");
		bottom.add (idLabel);
		idIn = new JTextField ();
		bottom.add (idIn);
		JPanel panel1 = new JPanel ();
		addButton = new JButton ("Add");
		addButton.addActionListener (this);
		panel1.add (addButton);
		bottom.add (panel1);

		// add middle label, text field and button
		JLabel nmeLabel = new JLabel ("Enter Class Name");
		bottom.add (nmeLabel);
		classIn = new JTextField ();
		bottom.add (classIn);
		JPanel panel2 = new JPanel();
		deleteButton = new JButton ("Delete");
		deleteButton.addActionListener (this);
		panel2.add (deleteButton);
		bottom.add (panel2);

		// add lower label text field and button
		JLabel tutLabel = new JLabel ("Enter Tutor Name");
		bottom.add (tutLabel);
		tutorIn = new JTextField();
		bottom.add (tutorIn);

		add (bottom, BorderLayout.SOUTH);
	}


	/**
	 * Processes adding a class
	 */

	public void processAdding () {
		
		System.err.println("processAdding method");
		// your code here
	}


	/**
	 * Processes deleting a class
	 */
	
	public void processDeletion () {
		
		System.err.println("processDeletion method");
		// your code here
	}

	
	/**
	 * Instantiates a new window and displays the attendance report
	 */

	public void displayReport () {

		System.err.println ("displayReport method");
		report = new ReportFrame (fitProg);
		report.reportFormatter ();
	}

	
	/**
	 * Writes lines to file representing class name, 
	 * tutor and start time and then exits from the program
	 */
	
	public void processSaveAndClose () {

		FileWriter fWriter = null;

		try{
			
			try {
				fWriter = new FileWriter (classesOutFile);
//				fWriter.write("This file writer is working"); // this bit needs to be in a loop, i think.
				while (fWriter.hasNextLine ()) { 
				fWriter.write(fitClass.toString()); // this bit needs to be in a loop, i think.
				System.err.println("The file writer is working");
			}

			finally {
				if (fWriter != null) fWriter.close (); // Closes the writer.
				
			}
		}

		catch (IOException IOE) {
			
			JOptionPane.showMessageDialog (null, "FILE NOT FOUND", "ERROR", JOptionPane.ERROR_MESSAGE);
			IOE.printStackTrace ();	
		}	
		
	}


	/**
	 * Process button clicks.
	 * @param ae the ActionEvent
	 */

	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == closeButton) { // Save and exit button.
			
			processSaveAndClose();
			System.exit(0); // Closes the program.
		}

		else if (ae.getSource () == attendanceButton) { // View Attendances button.
			
			displayReport();
		}

		else if (ae.getSource () == addButton) {
			processAdding();
		}

		else if (ae.getSource () == deleteButton) { 
			processDeletion();
		}
	}
}