package Homework5;

/* 
 * Maxwell Nardi
 * mrn2ka
 * Homework 5
 * https://stackoverflow.com/questions/18448671/how-to-avoid-concurrentmodificationexception-while-removing-elements-from-arr 
 * https://stackoverflow.com/questions/17887927/adding-items-to-a-jcombobox
 * https://stackoverflow.com/questions/22506331/simple-dropdown-menu-in-java
 * https://examples.javacodegeeks.com/desktop-java/swing/java-swing-boxlayout-example/
 * https://stackoverflow.com/questions/685521/multiline-text-in-jlabel
 * https://stackoverflow.com/questions/7080205/popup-message-boxes
 * https://coderanch.com/t/344096/java/flow-lay-force-add-component
 * https://stackoverflow.com/questions/22484011/jtable-how-to-get-deleted-rows
 * https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
 * http://www.mathcs.emory.edu/~cheung/Courses/377/Syllabus/8-JDBC/GUI/layout.html
 * https://www.codejava.net/java-se/swing/a-simple-jtable-example-for-display
 * http://www.virginia.edu/registrar/acadrecord.html
 * 
 */

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class gpaViewer extends JFrame {

	/** Instance J variables **/
	private JTextArea viewCurrentCourses;
	private JButton removeSelectedCourses;
	private JButton removeAllCourses;
	private JButton addCourse;
	private JLabel creditHours;
	private JTextField enterCreditHours;
	private JLabel courseGrade;
	private JTextField enterCourseGrade;
	private JLabel courseName;
	private JTextField enterCourseName;
	private JLabel gpa1Instructions;
	private JLabel gpaInstructions;
	private JTextField enterTargetGPA;
	private JButton getRequiredGPA;
	private JComboBox<String> cb;

	/** Courselist to keep track of all courses **/
	public courseList mycourses = new courseList();

	/** Main method which runs / executes the graphics methods **/
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI());
	}

	/** CreateAndShowGUI creates an instance of the current class and displays it */
	private static void createAndShowGUI() {
		// Create and set up the window.
		gpaViewer frame = new gpaViewer();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set up the content pane.
		frame.addComponentsToPane(frame.getContentPane());
		// Display the window.
		frame.pack();
		frame.setVisible(true);
		frame.setSize(700, 500);

	}

	/** Add components to pane manages all of the elements shows in the graphic **/
	public void addComponentsToPane(Container pane) {

		/** Creating JPanel's to be added / put in the final frame **/
		JPanel panel = new JPanel();
		JPanel courseDisplay = new JPanel();
		JPanel removeSub = new JPanel();
		JPanel addCourseSub = new JPanel();
		JPanel calcCurrentGPASub = new JPanel();
		JPanel calcRequiredGPASUB = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		/** Creates an inner class for the button actions **/
		class ButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				
				try {

				/**
				 * If the remove selected courses button is clicked, removes current course in
				 * JComboBox
				 **/
				if (e.getActionCommand().equals("removeSelectedCourses")) {
					Object item = cb.getSelectedItem();
					if (item != null) {
						String myitem = item.toString();
						mycourses.removeCourse(myitem);
					}

					viewCurrentCourses.setText(mycourses.toString());
					removeSub.remove(cb);
					String[] updatedCourseNames = mycourses.getCourseNameArray();
					cb = new JComboBox<String>(updatedCourseNames);
					removeSub.add(cb);
				}

				/**
				 * If remove all courses button is clicked, removes all courses from list and
				 * JComboBox
				 **/
				if (e.getActionCommand().equals("removeAllCourses")) {
					mycourses.clearList();
					viewCurrentCourses.setText(mycourses.toString());

					removeSub.remove(cb);
					String[] updatedCourseNames = mycourses.getCourseNameArray();
					cb = new JComboBox<String>(updatedCourseNames);
					removeSub.add(cb);
				}

				/**
				 * If the add course button is clicked, checks for credit hours to be added,
				 * then adds the course if the user has inputed credit hours. Otherwise,
				 * returns an error message requesting the user to enter number of credit hours.
				 */
				if (e.getActionCommand().equals("addCourse")) {

					if (!enterCreditHours.getText().equals("")) {
						String myString = enterCreditHours.getText();
						int creditHours = Integer.parseInt(myString);
						Course newCourse = new Course(creditHours);

						if (enterCourseGrade.getText() != null) {
							String courseGrade = enterCourseGrade.getText();
							newCourse.setGrade(courseGrade);
						}

						if (enterCourseName.getText() != null) {
							String courseName = enterCourseName.getText();
							newCourse.setCourseName(courseName);
						}

						mycourses.addCourse(newCourse);
						removeSub.remove(cb);
						String[] updatedCourseNames = mycourses.getCourseNameArray();
						cb = new JComboBox<String>(updatedCourseNames);
						removeSub.add(cb);
						JOptionPane.showMessageDialog(null, "Course Successfully Added!", "Success!",
								JOptionPane.INFORMATION_MESSAGE);
						viewCurrentCourses.setText(mycourses.toString());

					} else {
						JOptionPane.showMessageDialog(null, "Sorry. Please add the number of credit hours first.",
								"Failed to Add", JOptionPane.INFORMATION_MESSAGE);
					}
				}

				/**
				 * If calculate current GPA button is clicked, opens a pop-up displaying the
				 * user's current GPA
				 **/
				if (e.getActionCommand().equals("calculateCurrentGPA")) {
					JOptionPane.showMessageDialog(null, "Your current GPA is: " + mycourses.getGPA(), "Your GPA",
							JOptionPane.INFORMATION_MESSAGE);
				}

				/**
				 * If calculate required GPA button is clicked and a target GPA is entered,
				 * opens a pop-up displaying the user's required GPA based on target
				 **/
				if (e.getActionCommand().equals("getRequiredGPA")) {
					if (enterTargetGPA.getText() != null) {
						String myString = enterTargetGPA.getText();
						if (!myString.equals("")) {
							double targetGPA = Double.parseDouble(myString);
							JOptionPane.showMessageDialog(null, mycourses.getRequiredGPA(targetGPA),
									"Your Required GPA Status", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					// repaint();
				}
				}
				catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "It looks like you entered an invalid input. Try again.",
							"Your Required GPA Status", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		}
		/**
		 * Display current course method
		 * 
		 * @see labels, textfields, and button
		 * @return success or error message
		 **/
		viewCurrentCourses = new JTextArea(mycourses.toString());
		courseDisplay.add(viewCurrentCourses);

		/**
		 * Remove a course methods
		 * 
		 * @see labels, textfields, and button
		 * @return success or error message
		 **/

		String[] courseNames = { "-----" };
		cb = new JComboBox<String>(courseNames);
		removeSub.add(cb);

		removeSelectedCourses = new JButton("Remove Selected Course");
		removeSelectedCourses.setActionCommand("removeSelectedCourses");
		removeSelectedCourses.addActionListener(new ButtonListener());
		removeSub.add(removeSelectedCourses);

		removeAllCourses = new JButton("Remove All Courses");
		removeAllCourses.setActionCommand("removeAllCourses");
		removeAllCourses.addActionListener(new ButtonListener());
		removeSub.add(removeAllCourses);

		/**
		 * Add a course method
		 * 
		 * @see labels, textfields, and button
		 * @return success or error message
		 **/
		creditHours = new JLabel("Enter Credit Hours (Required): ");
		addCourseSub.add(creditHours);

		enterCreditHours = new JTextField(5);
		addCourseSub.add(enterCreditHours);

		courseGrade = new JLabel("Enter Course Grade (Optional): ");
		addCourseSub.add(courseGrade);

		enterCourseGrade = new JTextField(5);
		addCourseSub.add(enterCourseGrade);

		courseName = new JLabel("Enter Course Name (Optional): ");
		addCourseSub.add(courseName);

		enterCourseName = new JTextField(5);
		addCourseSub.add(enterCourseName);

		addCourse = new JButton("Add Course");
		addCourse.setActionCommand("addCourse");
		addCourse.addActionListener(new ButtonListener());
		addCourseSub.add(addCourse);

		/**
		 * Calculating current GPA method
		 * 
		 * @see labels, textfields, and button
		 * @return current GPA value
		 **/
		gpa1Instructions = new JLabel("Find your current GPA: ");
		calcCurrentGPASub.add(gpa1Instructions);

		getRequiredGPA = new JButton("Calculate Current GPA");
		getRequiredGPA.setActionCommand("calculateCurrentGPA");
		getRequiredGPA.addActionListener(new ButtonListener());
		calcCurrentGPASub.add(getRequiredGPA);

		/**
		 * Calculating required future GPA method
		 * 
		 * @see labels, textfields, and button
		 * @return required GPA value (or error)
		 **/
		gpaInstructions = new JLabel("Enter target GPA: ");
		calcRequiredGPASUB.add(gpaInstructions);

		enterTargetGPA = new JTextField(5);
		calcRequiredGPASUB.add(enterTargetGPA);

		getRequiredGPA = new JButton("Calculate Required GPA");
		getRequiredGPA.setActionCommand("getRequiredGPA");
		getRequiredGPA.addActionListener(new ButtonListener());
		calcRequiredGPASUB.add(getRequiredGPA);

		/**
		 * Setting the borders/sections of each method set (IE: adding a course,
		 * calculating gpa), and adding it to the panel to be displayed
		 **/
		courseDisplay.setBorder(BorderFactory.createTitledBorder("Your Courses"));
		panel.add(courseDisplay);
		removeSub.setBorder(BorderFactory.createTitledBorder("Remove Your Courses:"));
		panel.add(removeSub);
		addCourseSub.setBorder(BorderFactory.createTitledBorder("Add a Course:"));
		panel.add(addCourseSub);
		calcCurrentGPASub.setBorder(BorderFactory.createTitledBorder("Calculate Current GPA: "));
		panel.add(calcCurrentGPASub);
		calcRequiredGPASUB.setBorder(BorderFactory.createTitledBorder("Calculate Required GPA: "));
		panel.add(calcRequiredGPASUB);
		pane.add(panel);

	}
}
