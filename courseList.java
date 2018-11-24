package Homework5;

/* 
 * Maxwell Nardi
 * mrn2ka
 * Homework 5
 */

import java.util.ArrayList;

public class courseList {

	/** Instance Variables **/
	public ArrayList<Course> myCourseList;
	public int totalCreditHours = 0;
	public int ungradedCreditHours;
	public int gradedCreditHours;
	public int numberofCourses;

	public courseList() {
		myCourseList = new ArrayList<Course>();
	}

	public courseList(ArrayList<Course> inputList) {
		myCourseList = inputList;
	}

	/** Getters and Setters **/
	public int getNumberOfCourses() {
		return this.myCourseList.size();
	}

	/** Adds a course to list **/
	public void addCourse(Course a) {
		myCourseList.add(a);
		totalCreditHours += a.getCreditHours();
		if (a.getGrade().equals("")) {
			ungradedCreditHours += a.getCreditHours();
		} else {
			gradedCreditHours += a.getCreditHours();
		}
	}

	/** Removes a specific course from the list **/
	public void removeCourse(Course a) {
		myCourseList.remove(a);
		totalCreditHours -= a.getCreditHours();
		if (a.getGrade().equals("")) {
			ungradedCreditHours -= a.getCreditHours();
		} else {
			gradedCreditHours -= a.getCreditHours();
		}
	}

	/** Removes a specific course from the list based on name **/
	public void removeCourse(String a) {
		Course courseFound = null;
		for (Course courseItem : this.myCourseList) {
			if (courseItem.getCourseName().equals(a)) {
				courseFound = courseItem;
			}
		}
		myCourseList.remove(courseFound);
		totalCreditHours -= courseFound.getCreditHours();
		if (courseFound.getGrade().equals("")) {
			ungradedCreditHours -= courseFound.getCreditHours();
		} else {
			gradedCreditHours -= courseFound.getCreditHours();
		}

	}

	/** Clears a course from the list **/
	public void clearList() {
		myCourseList = new ArrayList<Course>();
		totalCreditHours = 0;
		gradedCreditHours = 0;
		ungradedCreditHours = 0;
	}

	/** Retrieves course name **/
	public String[] getCourseNameArray() {
		String[] myArray = new String[myCourseList.size()];
		int counter = 0;
		for (Course currentCourse : myCourseList) {
			myArray[counter] = currentCourse.getCourseName();
			counter++;
		}
		return myArray;
	}

	/** Returns GPA **/
	public double getGPA() {
		double gpa = 0.0;
		for (Course courseItem : this.myCourseList) {
			String grade = courseItem.getGrade();
			double creditHours = courseItem.getCreditHours();

			if (grade.equals("A+")) {
				gpa += 4.0 * creditHours;
			} else if (grade.equals("A-")) {
				gpa += 3.7 * creditHours;
			} else if (grade.equals("A")) {
				gpa += 4.0 * creditHours;
			} else if (grade.equals("B+")) {
				gpa += 3.3 * creditHours;
			} else if (grade.equals("B-")) {
				gpa += 2.7 * creditHours;
			} else if (grade.equals("B")) {
				gpa += 3.0 * creditHours;
			} else if (grade.equals("C+")) {
				gpa += 2.3 * creditHours;
			} else if (grade.equals("C-")) {
				gpa += 1.7 * creditHours;
			} else if (grade.equals("C")) {
				gpa += 2.0 * creditHours;
			} else if (grade.equals("D+")) {
				gpa += 1.3 * creditHours;
			} else if (grade.equals("D-")) {
				gpa += 0.7 * creditHours;
			} else if (grade.equals("D")) {
				gpa += 1.0 * creditHours;
			} else if (grade.equals("F")) {
				gpa += 0.0 * creditHours;
			}
		}

		gpa = gpa / gradedCreditHours;
		return Math.round(gpa * 1000.0) / 1000.0;
	}

	/** Returns required GPA to meet target **/
	public String getRequiredGPA(double targetGPA) {

		double currentGPA = this.getGPA();

		if (this.ungradedCreditHours <= 0) {
			return "Sorry. Please add the courses you plan on taking in the future first. "
					+ "\nThen I can show you your required GPA to meet your target GPA";
		}

		double requiredGPA = ((targetGPA * totalCreditHours) - (currentGPA * gradedCreditHours)) / ungradedCreditHours;

		if (requiredGPA > 4.0) {
			return "Your required GPA is: " + requiredGPA + ". " + "\nIt looks like this isn't possible. "
					+ "\nTry adding more credit hours and recalculating";
		}

		if (requiredGPA <= 2.0) {
			return "Your required GPA is: " + requiredGPA + ". It looks like you can lighten your courseload.";
		}
		return Math.round(requiredGPA * 1000.0) / 1000.0 + " ";

	}

	/** Returns string list **/
	@Override
	public String toString() {
		String coursePrint = "";
		coursePrint += "\nCredit Hrs\t\tGrade\t\tCourse Name";
		coursePrint += "\n--------------------------------------------------------------";
		for (Course courseItem : this.myCourseList) {
			String grade = courseItem.getGrade();
			String courseName = courseItem.getCourseName();
			int creditHours = courseItem.getCreditHours();

			if (grade.equals("") && courseName.equals("")) {
				coursePrint += "\n" + creditHours + "\t\t___" + "\t\t___";
			}

			else if (grade.equals("")) {
				coursePrint += "\n" + creditHours + "\t\t___" + "\t\t" + courseName;

			}

			else if (courseName.equals("")) {
				coursePrint += "\n" + creditHours + "\t\t" + grade + "\t\t___";
			}

			else {
				coursePrint += "\n" + creditHours + "\t\t" + grade + "\t\t" + courseName;
			}
		}
		return coursePrint;

	}

}
