package lesson4.labs.prob3;

import java.time.chrono.ChronoLocalDate;
import java.time.LocalDate;
import java.util.List;

public class Commissioned extends Employee
{
    private double baseSalary;
    private double commision;
    private List<Order> orders;
    
    public Commissioned(final String empId, final double commision, final double baseSalary, final List<Order> orders) {
        super(empId);
        if (baseSalary < 0.0) {
            throw new IllegalArgumentException("Base Salary should not be negative.");
        }
        if (commision < 0.0 || commision > 1.0) {
            throw new IllegalArgumentException("Commision should be between 0 and 1.");
        }
        if (orders == null) {
            throw new IllegalArgumentException("Orders cannot be null.");
        }
        this.baseSalary = baseSalary;
        this.commision = commision;
        this.orders = orders;
    }
    
    @Override
    public double calcGrossPay(final int month, final int year) {
        return this.baseSalary + this.calculateCommision(month, year);
    }
    
    public double getBaseSalary() {
        return this.baseSalary;
    }
    
    public void setBaseSalary(final double baseSalary) {
        if (baseSalary < 0.0) {
            throw new IllegalArgumentException("Base Salary should not be negative.");
        }
        this.baseSalary = baseSalary;
    }
    
    public double getCommision() {
        return this.commision;
    }
    
    public void setCommision(final double commision) {
        if (commision < 0.0 || commision > 1.0) {
            throw new IllegalArgumentException("Commision should be between 0 and 1.");
        }
        this.commision = commision;
    }
    
    public List<Order> getOrders() {
        return this.orders;
    }
    
    public void setOrders(final List<Order> orders) {
        if (orders == null) {
            throw new IllegalArgumentException("Orders cannot be null.");
        }
        this.orders = orders;
    }
    
    private double calculateCommision(int month, final int year) {
        double commision = 0.0;
        int nextMonth = month++;
        int theYear = year;
        if (nextMonth > 12) {
            ++theYear;
            --nextMonth;
        }
        for (final Order order : this.orders) {
            final LocalDate orderDate = order.getOrderDate();
            final LocalDate nextFirstDayToReport = LocalDate.of(theYear, nextMonth, 1);
            if (orderDate.compareTo((ChronoLocalDate)nextFirstDayToReport) < 0) {
                commision += order.getOrderAmount() * commision;
            }
        }
        return commision;
    }
}