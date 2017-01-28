// Assignment: Chat
// Program:    Student
// Created:    Jan 24, 2017
// Author:     lcattle - Lauren Ribeiro
//
package chatProject;

import java.util.ArrayList;

public class Student implements Comparable<Student> {
	private String firstName;
	private String lastName;
	private double score;
	//private String words;
	String[] responses = new String[5];
	
	public Student(String fName, String lName, String s, String words) {
		super();
		this.firstName = fName;
		this.lastName = lName;
		try
		{
		  this.score = Double.parseDouble(s);
		}
		catch(NumberFormatException e)
		{
		  this.score = 0;
		}
		this.responses = words.split("/");
		//this.words = w;
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
	
	
	
	@Override
	public String toString() { 
		return String.format("%s %s %.1f", getFirstName(), getLastName(), getScore());
	}

	@Override
	public int compareTo(Student s) {
		int compare = firstName.compareTo(s.firstName);
        return (compare != 0 ? compare : lastName.compareTo(s.lastName));
	}
}
