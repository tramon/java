package lab2;

import java.util.Comparator;

public class OrderComparator implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        int result = 0;
        if (o1.getOrderNumber() < o2.getOrderNumber()){
            result = -1;
        } else if (o1.getOrderNumber() == o2.getOrderNumber()) {
            result = 0;
        } else if (o1.getOrderNumber() > o2.getOrderNumber()) {
            result = 1;
        }
        return result;
    }

}
