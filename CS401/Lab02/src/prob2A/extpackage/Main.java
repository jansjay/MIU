package prob2A.extpackage;

import prob2A.Student;

public class Main {
	public static void main(String args[])
	{
		//Create Student object
		Student student = Student.createStudent("Student Name");
		//Set grade
		student.getGradeReport().setGrade("A");
		
		System.out.println("Student to GradeReport association - Printing Grade using a Student object");
		System.out.println(student.getGradeReport());

		System.out.println("GradeReport to Student association - Printing Student Name using a Gradepoint object");
		System.out.println(student.getGradeReport().getStudent());
	}
}
