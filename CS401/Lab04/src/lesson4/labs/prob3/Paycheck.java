package lesson4.labs.prob3;

import java.security.InvalidParameterException;

public final class Paycheck
{
    private static final double FICA_RATE = 0.23d;
    private static final double STATE_RATE = 0.05d;
    private static final double LOCAL_RATE = 0.01d;
    private static final double MEDICARE_RATE = 0.03d;
    private static final double SOCIAL_SEC_RATE = 0.075d;
    private double grossPay;
    private double fica;
    private double state;
    private double local;
    private double medicare;
    private double socialSecurity;
    
    public Paycheck(final double grossPay) {
        if (grossPay < 0.0) {
            throw new InvalidParameterException("Gross Pay cannot be negative");
        }
        this.grossPay = grossPay;
        this.fica = grossPay * FICA_RATE;
        this.state = grossPay * STATE_RATE;
        this.local = grossPay * LOCAL_RATE;
        this.medicare = grossPay * MEDICARE_RATE;
        this.socialSecurity = grossPay * SOCIAL_SEC_RATE;
    }
    
    public void print() {
        System.out.println(this);
    }
    
    public double getNetPay() {
        return this.grossPay - (this.fica + this.state + this.local + this.medicare + this.socialSecurity);
    }
    
    public double getGrossPay() {
        return this.grossPay;
    }
    
    public double getFica() {
        return this.fica;
    }
    
    public double getState() {
        return this.state;
    }
    
    public double getLocal() {
        return this.local;
    }
    
    public double getMedicare() {
        return this.medicare;
    }
    
    public double getSocialSecurity() {
        return this.socialSecurity;
    }
    
    @Override
    public String toString() {
        return "Paystub:\r\n  Gross Pay: " + this.grossPay + "\r\n" + " Fica: " + FICA_RATE + "\r\n" + " State: " + STATE_RATE + "\r\n" + " Local: " + LOCAL_RATE + "\r\n" + " Medicare: " + MEDICARE_RATE + "\r\n" + " Social Security: " + SOCIAL_SEC_RATE + "\r\n" + " NET PAY: " + this.getNetPay() + "0\r\n";
    }
}