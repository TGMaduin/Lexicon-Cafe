package se.lexicon;

import java.util.HashMap;

public class CafeApp {

    String customerName;
    boolean loyaltyMember = false;
    int itemNumber, quantity, customersServed;
    double subtotal, discount, vat, total, totalRevenue;
    HashMap<Integer, String> menuItems = new HashMap<>();
    HashMap<Integer, Double> menuItemPrices = new HashMap<>();

    public CafeApp(){
        buildMenu();
        customersServed = 0;
        totalRevenue = 0;
    }

    public void buildMenu(){
        menuItems.put(1, "Espresso");
        menuItems.put(2, "Cappuccino");
        menuItems.put(3, "Latte");
        menuItems.put(4, "Croissant");
        menuItems.put(5, "Sandwich");
        menuItemPrices.put(1, 25.00);
        menuItemPrices.put(2, 35.00);
        menuItemPrices.put(3, 40.00);
        menuItemPrices.put(4, 30.00);
        menuItemPrices.put(5, 55.00);
    }

    public double calculateDiscount(){
        if (loyaltyMember){
            return subtotal * 0.15;
        } else if (subtotal >= 150) {
            return subtotal * 0.1;
        }
        else{
            return 0;
        }
    }

    public double calculateSubtotal(){
        return menuItemPrices.get(itemNumber) * quantity;
    }

    public double calculateTotal(){
        return subtotal - discount + vat;
    }

    public double calculateVat(){
        return (subtotal - discount) * 0.12;
    }

    public void customerGreeting(){
        IO.print("Welcome! What is your name? ");
        customerName = InputUtils.readName();
    }

    public void printEndOfDayReport(){
        IO.println("");
        IO.println("==============================");
        IO.println("      END OF DAY REPORT       ");
        IO.println("==============================");
        IO.println("Customers served\t: " + customersServed);
        IO.println("Total revenue\t: " + totalRevenue + " SEK");
        IO.println("==============================");
        IO.println("");
    }

    public void printMenu(){
        IO.println("Hi " + customerName + "! Here is our menu:");
        IO.println("");
        IO.println("==============================");
        IO.println("         Lexicon Cafe         ");
        IO.println("==============================");
        IO.println("1. Espresso         25.00 SEK ");
        IO.println("2. Cappuccino       35.00 SEK ");
        IO.println("3. Latte            40.00 SEK ");
        IO.println("4. Croissant        30.00 SEK ");
        IO.println("5. Sandwich         55.00 SEK ");
        IO.println("==============================");
        IO.println("");
    }

    public void printReceipt(){
        IO.println("");
        IO.println("==============================");
        IO.println("         Lexicon Cafe         ");
        IO.println("==============================");
        IO.println("Customer\t: " + customerName);
        IO.println("Item\t\t: " + menuItems.get(itemNumber) + " x " + quantity);
        IO.println("Subtotal\t: " + subtotal + " SEK");
        if (discount > 0) {
            IO.println("Discount\t: -" + discount + " SEK");
        }
        IO.println("VAT\t\t\t: " + vat + " SEK");
        IO.println("------------------------------");
        IO.println("TOTAL\t\t: " + total + " SEK");
        IO.println("==============================");
        IO.println("   Thank you, " + customerName + "!");
        IO.println("   See you next time.");
        IO.println("==============================");
        IO.println("");
    }

    public void processTransaction(){
        subtotal = calculateSubtotal();
        discount = calculateDiscount();
        vat = calculateVat();
        total = calculateTotal();
        totalRevenue += total;
        customersServed++;
        printReceipt();
    }

    public void resetTransaction(){
        customerName = "";
        loyaltyMember = false;
        subtotal = 0;
        discount = 0;
        vat = 0;
        total = 0;
    }

    public void run(){

        customerGreeting();
        do {
            printMenu();
            resetTransaction();
            transactionInput();
            processTransaction();
            IO.print("Next customer name (or 'done' to close): ");
            customerName = InputUtils.readName();
        } while (!customerName.equals("done"));
        printEndOfDayReport();
    }

    public void transactionInput(){

        itemNumber = InputUtils.readValidatedInt("Enter item number (1-5): ", "Input has to be numeric only.", "Number has to be within range.", 1, 5);
        quantity = InputUtils.readValidatedInt("How many? ", "Input has to be numeric only.", "Number has to be positive.", 1, Integer.MAX_VALUE);
        loyaltyMember = InputUtils.checkMembership();

    }
}
