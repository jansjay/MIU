package lesson4.labs.prob3;

import java.time.LocalDate;

public class Order
{
    private String orderNo;
    private LocalDate orderDate;
    private double orderAmount;
    
    public Order(final String orderNo, final LocalDate orderDate, final double orderAmount) {
        if (orderNo == null || orderNo.isEmpty()) {
            throw new IllegalArgumentException("Order cannot be null or empty.");
        }
        if (orderDate == null) {
            throw new IllegalArgumentException("Order Date cannot be null");
        }
        this.orderNo = orderNo;
        this.setOrderDate(orderDate);
        this.setOrderAmount(orderAmount);
    }
    
    public String getOrderNo() {
        return this.orderNo;
    }
    
    public LocalDate getOrderDate() {
        return this.orderDate;
    }
    
    public void setOrderDate(final LocalDate orderDate) {
        if (orderDate == null) {
            throw new IllegalArgumentException("Order Date cannot be null");
        }
        this.orderDate = orderDate;
    }
    
    public double getOrderAmount() {
        return this.orderAmount;
    }
    
    public void setOrderAmount(final double orderAmount) {
        this.orderAmount = orderAmount;
    }
}