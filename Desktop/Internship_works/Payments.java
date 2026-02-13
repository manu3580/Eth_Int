import java.util.Scanner;


interface Payment {
    void pay(double amount);
}

class UpiPayment implements Payment {

    
    public void pay(double amount) {
        System.out.println("Rs"+amount+"paid successfully using UPI.");
    }
}

class CardPayment implements Payment {

    public void pay(double amount) {
        System.out.println("Rs"+amount+"paid successfully using Card.");
    }
}

class CashPayment implements Payment {

    public void pay(double amount) {
        System.out.println("Rs"+amount+" paid successfully using Cash.");
    }
}

public class Payments {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter amount to pay:");
        double amount = scan.nextDouble();

        System.out.println("Choose payment method:");
        System.out.println("1.UPI");
        System.out.println("2.Card");
        System.out.println("3.Cash");

        int choice=scan.nextInt();

        Payment paymentMethod = null;

        switch (choice) {
            case 1:
                paymentMethod = new UpiPayment();
                break;
            case 2:
                paymentMethod = new CardPayment();
                break;
            case 3:
                paymentMethod = new CashPayment();
                break;
            default:
                System.out.println("Invalid choice");
                return;
        }

        paymentMethod.pay(amount);
    }
}
