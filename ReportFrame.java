import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Class to define window in which attendance report is displayed.
 */


@SuppressWarnings("serial")
public class ReportFrame extends JFrame {

	/* Instance variables.*/

	private JTextArea attReport;
	private FitnessProgram fitP;


	/* Setting up the frame properties.*/

	public ReportFrame (FitnessProgram fit) {

		fitP = fit;	
		setDefaultCloseOperation (DISPOSE_ON_CLOSE);
		setTitle ("Attendance Report");
		setSize (700, 300);
		setResizable (false);
		attReport = new JTextArea();
		attReport.setFont (new Font ("Courier", Font.PLAIN, 14));
		attReport.setEditable (false);
		add (attReport);
		setVisible (true);
	}


	/* Sets up the contents of the frame including header, separating bar and display content.*/

	public void reportFormatter () {

		attReport.setBorder (new EmptyBorder (10,10,10,10)); // Puts a border around the edge of the TextArea.

		String idHeader = "ID"; String classHeader = "Class"; String tutorHeader = "Tutor"; 
		String attendanceHeader = "Attendance"; String avgattendanceHeader = "Average Attendance";
		String header = String.format ("%-10s %-15s %-15s %-20s %19s", idHeader, classHeader, tutorHeader, attendanceHeader, avgattendanceHeader);
		attReport.append (" " + header + "\n");
		String bar = "-------------------------------------------------------------------------------------";
		attReport.append (bar+ "\n\n");
		attReport.append (" ");
		attReport.append (fitP.sortedAttendance());
		attReport.append (String.format("%77s", ("Overall average: " + fitP.overallAttendanceAverage())));
	}
}