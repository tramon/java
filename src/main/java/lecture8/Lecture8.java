package lecture8;

public class Lecture8 {

    static void main() {

        Lecture8 lecture8 = new Lecture8();

        try {
            lecture8.pay(50.0, 75.0);
        } catch (InsufficientFundsException e) {
            System.out.println("Оплата відхилена: " + e.getMessage());
        }
    }

    public void pay(double balance, double orderTotal) {
        if (balance < orderTotal) {
            throw new InsufficientFundsException(
                    "Бракує коштів: потрібно " + orderTotal
                            + ", є " + balance);
        }
        System.out.println("Оплату на " + orderTotal + " проведено");
    }

}
