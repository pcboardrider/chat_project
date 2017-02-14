package chatProject;

import java.util.ArrayList;
import java.util.Arrays;

public class Student implements Comparable<Student> {
	private String firstName;
	private String lastName;
	private double score;
	private ArrayList<String> responses;
	
	public Student(String fName, String lName, String s, String words) {
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
		setResponses(new ArrayList<String>(Arrays.asList(words.split("/"))));
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public ArrayList<String> getResponses() {
		return responses;
	}

	public void setResponses(ArrayList<String> responses) {
		this.responses = responses;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
}
