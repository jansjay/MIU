package lesson4.labs.prob3;

public class Salaried extends Employee
{
    private double salary;
    
    public Salaried(final String empId, final double salary) {
        super(empId);
        if (salary < 0.0) {
            throw new IllegalArgumentException("Salary should not be negative.");
        }
        this.salary = salary;
    }
    
    @Override
    public double calcGrossPay(final int month, final int year) {
        return this.salary;
    }
    
    public double getSalary() {
        return this.salary;
    }
    
    public void setSalary(final double salary) {
        if (salary < 0.0) {
            throw new IllegalArgumentException("Salary should not be negative.");
        }
        this.salary = salary;
    }
}