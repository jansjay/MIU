package lesson4.labs.prob3;

public abstract class Employee
{
    private String empId;
    
    public Employee(final String empId) {
        this.empId = empId;
    }
    
    public String getEmpId() {
        return this.empId;
    }
    
    public void print(final int month, final int year) {
        System.out.println(this);
        System.out.print(this.calcCompensation(month, year));
    }
    
    public Paycheck calcCompensation(final int month, final int year) {
        return new Paycheck(this.calcGrossPay(month, year));
    }
    
    @Override
    public String toString() {
        return "Employee Id: " + this.empId;
    }
    
    public abstract double calcGrossPay(final int p0, final int p1);
}