package lab2;

public class Something {

    static void main() {
        Order order1 = new Order(1);
        Order order2 = new Order(2);

        System.out.println("\nПорівняємо замовлення за допомогою інтерфейсу Comparable<T> "
                + order1.compareTo(order2));

        OrderComparator orderComparator = new OrderComparator();
        System.out.println("Порівняємо замовлення за допомогою інтерфейсу Comparator<T> "
                + orderComparator.compare(order1, order2));

    }

}
