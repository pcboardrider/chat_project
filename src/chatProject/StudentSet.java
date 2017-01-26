// Assignment: Chat
// Program:    StudentSet
// Created:    Jan 25, 2017
// Author:     lcattle - Lauren Ribeiro
//
package chatProject;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class StudentSet {
	Set<Student> studentSet;
	
	public StudentSet(Student... students) {
		studentSet = new HashSet<>(Arrays.asList(students));
	}
	
	
	

//	public void addStudent(Student s) {
//		studentSet.add(s);
//		System.out.println(s);
//	}


}
