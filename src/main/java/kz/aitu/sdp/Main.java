package kz.aitu.sdp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ShopSystem shopSystem = new ShopSystem();
        Scanner scanner = new Scanner(System.in);
        String stranswer;
        int intanswer;
        Bouquet bouquet = new Bouquet();
        Customer customer = new Customer();

        System.out.println("Welcome to our Online Flower Shop! Please choose your role: client/admin");
        stranswer = scanner.nextLine();

        if (stranswer.equals("client")){
            do {
            System.out.println("What would you like to do? make an order (enter '1')/view your orders (enter '2') ");
            intanswer = scanner.nextInt();
            scanner.nextLine();
            if (intanswer == 1) {
                    do {
                        shopSystem.showFlowers();
                        System.out.println("Choose a flower by typing its ID");
                        intanswer = scanner.nextInt();
                        scanner.nextLine();
                        shopSystem.addFlower(bouquet, intanswer);
                        System.out.println("Your bouquet contains: ");
                        System.out.println(bouquet);
                        System.out.println("Would you like to add another flower?");
                        stranswer = scanner.nextLine();
                    } while (stranswer.equals("yes"));

                    shopSystem.showAccessories();

                    do {
                        System.out.println("Choose an accessory by typing its ID");
                        intanswer = scanner.nextInt();
                        scanner.nextLine();
                        shopSystem.addAccessory(bouquet, intanswer);
                        System.out.println("Your bouquet contains: ");
                        System.out.println(bouquet);
                        System.out.println("Would you like to add another accessory? yes/no");
                        stranswer = scanner.nextLine();
                    } while (stranswer.equals("yes"));

                    System.out.println("Your bouquet is ready! Its price is " + bouquet.getBouquet_price() + " KZT");
                    System.out.println("Let's make an order!");

                    System.out.println("Have you ever purchased bouquets from our site? yes/no");

                    stranswer = scanner.nextLine();

                    if (stranswer.equals("yes")) {
                        System.out.print("Great! Please enter your phone, so we can check for your data: +7");
                        stranswer = scanner.nextLine();
                        customer = shopSystem.getCustomerByPhone(stranswer);
                        System.out.print("Welcome back, " + customer.getCustomer_fname() + " " + customer.getCustomer_lname() + ". ");
                    } else {
                        System.out.print("Nothing wrong! Enter your first name: ");
                        customer.setCustomer_fname(scanner.nextLine());
                        System.out.print("Then your last name: ");
                        customer.setCustomer_lname(scanner.nextLine());
                        System.out.print("And now your phone number: +7");
                        customer.setCustomer_phone(scanner.nextLine());
                        System.out.print("Almost done! Enter your email address: ");
                        customer.setCustomer_email(scanner.nextLine());
                        System.out.print("Finally! What's address for your shipping?: ");
                        customer.setCustomer_address(scanner.nextLine());
                        shopSystem.addCustomerToDB(customer);
                        customer = shopSystem.getCustomerByPhone(customer.getCustomer_phone());
                    }

                    shopSystem.makeOrder(bouquet, customer);

                    System.out.println("Thank you for your order! Do you want to continue using shop?");
                    stranswer = scanner.nextLine();
            } else {
                    System.out.print("Enter your phone to see your orders: +7");
                    stranswer = scanner.nextLine();
                    shopSystem.showCustomersOrders(stranswer);
                    System.out.println("Do you want to continue using shop?");
                    stranswer = scanner.nextLine();
                }
            } while (stranswer.equals("yes"));
        } else {
            do {
                System.out.println("What would you like to do? update shipping status (enter '1')/update payment status (enter '2') ");
                intanswer = scanner.nextInt();
                scanner.nextLine();

                if (intanswer == 1){
                    System.out.println("Enter shipping ID to update its status to 'delivered'");
                    intanswer = scanner.nextInt();
                    scanner.nextLine();
                    shopSystem.updateShippingStatus(intanswer);
                    System.out.println("Shipping status successfully updated and date inserted!");
                    System.out.println("Do you want to continue using shop?");
                    stranswer = scanner.nextLine();
                } else {
                    System.out.println("Enter payment ID to update its status to 'payed'");
                    intanswer = scanner.nextInt();
                    scanner.nextLine();
                    shopSystem.updatePaymentStatus(intanswer);
                    System.out.println("Payment status successfully updated!");
                    System.out.println("Do you want to continue using shop?");
                    stranswer = scanner.nextLine();
                }
            } while (stranswer.equals("yes"));
        }


    }
}
