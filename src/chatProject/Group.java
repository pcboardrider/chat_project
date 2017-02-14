package chatProject;

public class Group implements Comparable<Group>{
	private Student student1;
	private Student student2;
	
	public Group(Student s1, Student s2) {
		this.student1 = s1;
		this.student2 = s2;
	}

	public StringBuffer groupChat() {
		StringBuffer sb = new StringBuffer();
		int length;
		if (student1.getResponses().size() > student2.getResponses().size()) {
			length = student1.getResponses().size();
		} else {
			length = student2.getResponses().size();
		}
		for (int i = 0; i < length; i++) {
			try {
				sb.append(student1 + ": " + student1.getResponses().get(i) + "\n");
			} catch (IndexOutOfBoundsException e) {
				sb.append(student1 + ": \n");
			}
			try {
				sb.append(student2 + ": " + student2.getResponses().get(i) + "\n");
			} catch (IndexOutOfBoundsException e) {
				sb.append(student2 + ": \n");
			}
		}
		return sb;
	}

	@Override
	public String toString() {
		return "Group [student1:" + student1 + ", student2:" + student2 + "]";
	}

	@Override
	public int compareTo(Group g) {
		int compare = student1.compareTo(g.student1);
		return compare;	
	}
}
