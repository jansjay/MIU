package lesson4.labs.prob3;

public class Hourly extends Employee
{
    private double hourlyWage;
    private double hoursPerWeek;
    
    public Hourly(final String empId, final double hourlyWage, final double hoursPerWeek) {
        super(empId);
        if (hourlyWage < 0.0) {
            throw new IllegalArgumentException("Hourly Wage should not be negative.");
        }
        if (hoursPerWeek < 0.0) {
            throw new IllegalArgumentException("Hours per Week should not be negative.");
        }
        this.hourlyWage = hourlyWage;
        this.hoursPerWeek = hoursPerWeek;
    }
    
    @Override
    public double calcGrossPay(final int month, final int year) {
        return this.hourlyWage * this.hoursPerWeek;
    }
    
    public double getHourlyWage() {
        return this.hourlyWage;
    }
    
    public void setHourlyWage(final double hourlyWage) {
        if (hourlyWage < 0.0) {
            throw new IllegalArgumentException("Hourly Wage should not be negative.");
        }
        this.hourlyWage = hourlyWage;
    }
    
    public double getHoursPerWeek() {
        return this.hoursPerWeek;
    }
    
    public void setHoursPerWeek(final double hoursPerWeek) {
        if (hoursPerWeek < 0.0) {
            throw new IllegalArgumentException("Hours per Week should not be negative.");
        }
        this.hoursPerWeek = hoursPerWeek;
    }
}