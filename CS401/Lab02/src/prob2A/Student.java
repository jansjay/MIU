package prob2A;

public class Student {
	private GradeReport gradeReport;
	private String name;
	
	Student(String name){
		this.name = name;
	}
	
	public GradeReport getGradeReport() {
		return gradeReport;
	}
	
	void setGradeReport(GradeReport gradeReport) {
		this.gradeReport = gradeReport;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static Student createStudent(String name){
		Student student = new Student(name);
		GradeReport report = GradeReport.createGradeReport(student);
		student.setGradeReport(report);
		return student;
	}
	
	public String toString() {
		return this.name;
	}
}
