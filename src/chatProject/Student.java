package chatProject;

import java.util.ArrayList;
import java.util.Arrays;

public class Student implements Comparable<Student> {
	private String firstName;
	private String lastName;
	private double score;
	ArrayList<String> responses;
	
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
		responses = new ArrayList<String>(Arrays.asList(words.split("/")));
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public double getScore() {
		return score;
	}
	
	public void setScore(double sc) {
		this.score = sc;
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
