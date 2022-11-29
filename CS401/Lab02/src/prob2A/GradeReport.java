package prob2A;

import java.security.InvalidParameterException;

public class GradeReport {
	private Student student;
	private String grade;
	
	GradeReport(Student student){
		if(student == null) {
			throw new InvalidParameterException("Invalid parameter. Student cannot be null");
		}
		GradeReport gradeReport = student.getGradeReport();
		//Should not let assign a different GradeReport to the student 
		//If a gradeReport is assigned already		
		if(gradeReport != null) {
			throw new InvalidParameterException("Invalid parameter. Student has a different gradeReport attached already");
		}
		this.student = student;
	}
	
	public Student getStudent() {
		return this.student;
	}
	
	public String getGrade() {
		return this.grade;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
	}	
	
	public static GradeReport createGradeReport(Student student) {
		return new GradeReport(student);
	}
		
	public String toString() {
		return grade;
	}
}
