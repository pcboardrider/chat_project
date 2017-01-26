// Assignment: Chat
// Program:    Group
// Created:    Jan 24, 2017
// Author:     lcattle - Lauren Ribeiro
//
package chatProject;

public class Group implements Comparable<Group>{
	private Student student1;
	private Student student2;
	
	public Group(Student s1, Student s2) {
		this.student1 = s1;
		this.student2 = s2;
		System.out.println("\n" + s1 + " and " + s2);
		groupChat();
	}

	private void groupChat() {
		System.out.printf("%s: %s%n", student1, student1.getResponse1());
		System.out.printf("%s: %s%n", student2, student2.getResponse1());
		System.out.printf("%s: %s%n", student1, student1.getResponse2());
		System.out.printf("%s: %s%n", student2, student2.getResponse2());
	}

	@Override
	public int compareTo(Group g) {
		int compare = student1.compareTo(g.student1);
		return compare;	
	}
}
