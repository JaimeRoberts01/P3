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
@SuppressWarnings("serial") // Why does it do this? It's annoying.
public class SportsCentreGUI extends JFrame implements ActionListener {

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
	public SportsCentreGUI() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Boyd-Orr Sports Centre");
		setSize(700, 300);
		setResizable (false); // cannot change the size.
		display = new JTextArea();
		display.setFont(new Font("Courier", Font.PLAIN, 14));
		display.setEditable(false); // cannot write in the display area.
		add(display, BorderLayout.CENTER);
		layoutTop();
		layoutBottom();
		
		initLadiesDay();
		updateDisplay ();
		initAttendances();
		fitProg.checkArray();
	}


	/**
	 * Creates the FitnessProgram list ordered by start time
	 * using data from the file ClassesIn.txt
	 */
	
	public void initLadiesDay() {

		fitProg = new FitnessProgram ();

		FileReader reader = null;
		Scanner scanner = null;

		try {
			reader = new FileReader(classesInFile);
			scanner = new Scanner(reader);
			while (scanner.hasNextLine()) { 
				String classes = scanner.nextLine();
				System.out.println("The initLadies day method " + classes);
				fitClass = new FitnessClass (classes);
				fitProg.getfClass()[Integer.parseInt(fitClass.getStartTime())-9] = fitClass;  
//				fitClass = new FitnessClass (line);
//				fitProg.getfClass(fitClass);
			}
			//updateDisplay (fitProg)
			reader.close();
			scanner.close();
		}

		catch(IOException IOE) {
			IOE.printStackTrace();		
		}
	}


	/**
	 * Initialises the attendances using data
	 * from the file AttendancesIn.txt
	 */
	
	public void initAttendances() {

		FileReader reader = null;
		Scanner scanner = null;

		try {
			reader = new FileReader(attendancesFile); // deal with this problem once you've sorted the classes problem.
			scanner = new Scanner(reader);
			while (scanner.hasNextLine()) { 
				String attendances = scanner.nextLine();
				System.out.println("The initAttendances method " + attendances);
				//display.append(line + "\n");

				// search array of fitnessclasses for LB1, then call populatewAttendances with the object whose id is LB1
				//step 1 for every item in fitnessclass array
				// step 2 if item's id is equal to LB1
				// step 3 call populate attendances with the object whose id is equal to LB1. item.populateAttendances(line);
			}

			reader.close();
			scanner.close();
		}

		catch(IOException e) {
			e.printStackTrace();		
		}
	}


	/**
	 * Instantiates timetable display and adds it to GUI
	 */

	public void updateDisplay() {

		display.setBorder (new EmptyBorder (8,8,8,8));
		
		String [] header = {"9-10", "10-11", "11-12", "12-13", "13-14", "14-15", "15-16"};
		for (int i = 0; i<7; i++) {
		display.append(String.format("%-13s", header[i]));
		}
			
		display.append("\n");
		
		for (int i = 0; i<7; i++) {
		display.append (String.format("%-13s", fitProg.buildClassList(i)));
		}
		
		display.append("\n");
		
		for (int i = 0; i<7; i++) {
		display.append (String.format("%-13s", fitProg.buildTutorList(i)));
		}	
	}

	
	/**
	 * adds buttons to top of GUI
	 */
	
	public void layoutTop() {
		JPanel top = new JPanel();
		closeButton = new JButton("Save and Exit");
		closeButton.addActionListener(this);
		top.add(closeButton);
		attendanceButton = new JButton("View Attendances");
		attendanceButton.addActionListener(this);
		top.add(attendanceButton);
		add(top, BorderLayout.NORTH);
	}

	
	/**
	 * adds labels, text fields and buttons to bottom of GUI
	 */
	
	public void layoutBottom() {
		// instantiate panel for bottom of display
		JPanel bottom = new JPanel(new GridLayout(3, 3));

		// add upper label, text field and button
		JLabel idLabel = new JLabel("Enter Class Id");
		bottom.add(idLabel);
		idIn = new JTextField();
		bottom.add(idIn);
		JPanel panel1 = new JPanel();
		addButton = new JButton("Add");
		addButton.addActionListener(this);
		panel1.add(addButton);
		bottom.add(panel1);

		// add middle label, text field and button
		JLabel nmeLabel = new JLabel("Enter Class Name");
		bottom.add(nmeLabel);
		classIn = new JTextField();
		bottom.add(classIn);
		JPanel panel2 = new JPanel();
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		panel2.add(deleteButton);
		bottom.add(panel2);

		// add lower label text field and button
		JLabel tutLabel = new JLabel("Enter Tutor Name");
		bottom.add(tutLabel);
		tutorIn = new JTextField();
		bottom.add(tutorIn);

		add(bottom, BorderLayout.SOUTH);
	}

	
	/**
	 * Processes adding a class
	 */
	
	public void processAdding() {
		System.err.println("processAdding method");
		// your code here
	}

	
	/**
	 * Processes deleting a class
	 */
	
	public void processDeletion() {
		System.err.println("processDeletion method");
		// your code here
	}

	/**
	 * Instantiates a new window and displays the attendance report
	 */
	
	public void displayReport() {

		System.err.println("displayReport method");
		report = new ReportFrame ();
	}

	
	/**
	 * Writes lines to file representing class name, 
	 * tutor and start time and then exits from the program
	 */
	
	public void processSaveAndClose() {
		
		FileWriter fWriter = null;

		try{
			try {
				fWriter = new FileWriter (classesOutFile);
				fWriter.write("This file writer is working");// this bit needs to be in a loop, i think.
				System.err.println("The file writer is working");
			}

			finally {
				if (fWriter != null) fWriter.close (); // Closes the writer.
			}
		}

		catch (IOException IOE) {
			JOptionPane.showMessageDialog (null, "THERE WAS A PROBLEM WITH YOUR FILE", "WARNING", JOptionPane.ERROR_MESSAGE);
		}					
	}


	/**
	 * Process button clicks.
	 * @param ae the ActionEvent
	 */
	
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == closeButton) { // The save and exit button.

			System.out.println("You have hit the close button");
			processSaveAndClose();
			System.exit(0);
		}

		else if (ae.getSource() == attendanceButton) { // View Attendances button.

			System.out.println("You have hit the attendance button");
			//report = new ReportFrame ();
			displayReport();
		}

		else if (ae.getSource() == addButton) {

			System.out.println("You have hit the add button");
			processAdding();
		}

		else if (ae.getSource() == deleteButton) { 

			System.out.println("You have hit the delete button");
			processDeletion();
		}
	}
}
