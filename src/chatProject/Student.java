// Assignment: Chat
// Program:    Student
// Created:    Jan 24, 2017
// Author:     lcattle - Lauren Ribeiro
//
package chatProject;

public class Student implements Comparable<Student> {
	private String firstName;
	private String lastName;
	private double score;
	private String response1;
	private String response2;
	
	public Student(String fName, String lName, double s, String r1, String r2) {
		super();
		this.firstName = fName;
		this.lastName = lName;
		this.score = s;
		this.response1 = r1;
		this.response2 = r2;
	}
	
	public Student() {
		
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public double getScore() {
		return score;
	}
	
	public void setScore(double sc) {
		this.score = sc;
	}
	
	public String getResponse1() {
		return response1;
	}
	
	public String getResponse2() {
		return response2;
	}
	
	@Override
	public String toString() { 
		return String.format("%s %s", getFirstName(), getLastName());
	}

	@Override
	public int compareTo(Student s) {
		int compare = firstName.compareTo(s.firstName);
        return (compare != 0 ? compare : lastName.compareTo(s.lastName));
	}
}
