package lab2;

public class Order implements Comparable<Order> {

    private int orderNumber;

    public Order() {
    }
    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setOrderNumber(int orderNumber){
        this.orderNumber = orderNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    @Override
    public int compareTo(Order order) {
        int result = 0;
        if (orderNumber < order.getOrderNumber()){
            result = -1;
        } else if (orderNumber == order.getOrderNumber()) {
            result = 0;
        } else if (orderNumber > order.getOrderNumber()) {
            result = 1;
        }
        return result;
    }

}
