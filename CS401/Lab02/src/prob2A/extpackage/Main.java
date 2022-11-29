package prob2A.extpackage;

import prob2A.Student;

public class Main {
	public static void main(String args[])
	{
		Student student = Student.createStudent("Test Name");
		student.getGradeReport().setGrade("A");
		
		// Student to GradeReport association
		System.out.println(student.getGradeReport());
		// GradeReport to Student association
		System.out.println(student.getGradeReport().getStudent());
	}
}
