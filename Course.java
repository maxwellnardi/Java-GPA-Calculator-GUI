package Homework5;

/* 
 * Maxwell Nardi
 * mrn2ka
 * Homework 5
 */

public class Course {
	
	/** Instance Variables **/
	public int creditHours;
	public String grade;
	public String courseName;
	
	
	/** Getters and Setters **/
	public int getCreditHours() {
		return creditHours;
	}
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	
	/** Constructor Methods **/
	public Course(int creditHours){
		this.creditHours = creditHours;
		this.grade = " ";
		this.courseName = " ";
	}
	
	public Course(int creditHours, String grade){
		this.creditHours = creditHours;
		this.grade = grade;
		this.courseName = " ";
	}
	
	public Course(String courseName, int creditHours){
		this.creditHours = creditHours;
		this.courseName = courseName;
		this.grade = " ";
	}
	
	public Course(int creditHours, String grade, String courseName){
		this.creditHours = creditHours;
		this.grade = grade;
		this.courseName = courseName;
	}
	
}
