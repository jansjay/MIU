package lesson4.labs.prob3.extpackage;

import java.util.List;
import lesson4.labs.prob3.Hourly;
import lesson4.labs.prob3.Salaried;
import lesson4.labs.prob3.Employee;
import lesson4.labs.prob3.Commissioned;
import java.time.LocalDate;
import lesson4.labs.prob3.Order;
import java.util.ArrayList;

public class Main
{
    public static void main(final String[] args) {
        final List<Order> list = new ArrayList<Order>();
        list.add(new Order("100", LocalDate.of(2019, 9, 1), 200.0));
        list.add(new Order("100", LocalDate.of(2019, 9, 10), 100.0));
        final Commissioned cm = new Commissioned("123", 0.8, 500.0, list);
        final Employee[] emp = { new Salaried("121", 4000.0), new Hourly("122", 15.67, 20.0), cm };
        Employee[] array;
        for (int length = (array = emp).length, i = 0; i < length; ++i) {
            final Employee e = array[i];
            e.print(10, 2019);
        }
    }
}