package chatProject;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Chat {

	public static void main(String[] args) {
		// StudentSet set = new StudentSet();
		Set<Student> set = new HashSet<>();
		TreeSet<Group> groups = new TreeSet<>();
		Student groupMember1 = null;
		Student groupMember2 = null;
		try (Scanner reader = new Scanner(Chat.class.getResourceAsStream("Students.csv"))) {
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				Student student = getStudent(line);
				if (student != null) {
					set.add(student);
					if (groupMember1 == null) {
						groupMember1 = student;
					} else {
						groupMember2 = student;
						groups.add(new Group(groupMember1, groupMember2));
						groupMember1 = null;
						groupMember2 = null;
					}
				}
			}
		}
//		for (Student s : set) {
//			System.out.println(s);	
//		}
	}

	private static Student getStudent(String line) {
		String[] details = line.split(",");
		Student nextStudent = null;
		try {
			nextStudent = new Student(details[0], details[1], details[2], details[3]);
		} catch (NumberFormatException | IndexOutOfBoundsException e) {
			System.err.printf("%s .. could not be read as a student.%n", line);
		}
		return nextStudent;
	}
}
