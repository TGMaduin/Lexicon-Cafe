package se.lexicon;

import java.util.*;

public class CafeApp {

    String customerName;
    boolean loyaltyMember = false;
    int itemNumber, quantity, customersServed;
    double totalRevenue;
    ArrayList<MenuItem> menu;

    public CafeApp(){
        buildMenu();
        customersServed = 0;
        totalRevenue = 0;
    }

    public void buildMenu(){
        menu = new ArrayList<>();
        menu.add(new MenuItem("Espresso", 25.00));
        menu.add(new MenuItem("Cappuccino", 35.00));
        menu.add(new MenuItem("Latte", 40.00));
        menu.add(new MenuItem("Croissant", 30.00));
        menu.add(new MenuItem("Sandwich", 55.00));
    }

    public void customerGreeting(){
        IO.print("Welcome! What is your name? ");
        customerName = InputUtils.readName();
    }

    public void printEndOfDayReport(){
        IO.println(String.format(""" 
                \n
                ==============================
                      END OF DAY REPORT
                ==============================
                %-18s: %d
                %-18s: %.2f SEK
                ==============================\n""",
                "Customers served",
                customersServed,
                "Total revenue",
                totalRevenue));
    }

    public void printMenu(){
        IO.println(String.format("""
                \n
                Hi %s! Here is our menu:\n
                ==============================
                         Lexicon Cafe
                ==============================""",
                customerName));
        for(int i = 0; i < menu.size(); i++){
            IO.println(String.format(
                    "%d. %-15s %6.2f SEK",
                    (i + 1),
                    menu.get(i).getName(),
                    menu.get(i).getPrice()
            ));
        }
        IO.println("==============================\n");
    }

    public void run(){

        customerGreeting();
        do {
            printMenu();
            transactionInput();
            Order order = new Order(customerName,
                    menu.get(itemNumber - 1),
                    quantity,
                    loyaltyMember);
            order.processTransaction();
            totalRevenue += order.getTotal();
            customersServed++;
            IO.print("Next customer name (or 'done' to close): ");
            customerName = InputUtils.readName();
        } while (!customerName.equals("done"));
        printEndOfDayReport();
    }

    public void transactionInput(){

        itemNumber = InputUtils.readValidatedInt(
                "Enter item number (1-5): ",
                "Input has to be numeric only.",
                "Number has to be within range.",
                1,
                5);
        quantity = InputUtils.readValidatedInt(
                "How many? ",
                "Input has to be numeric only.",
                "Number has to be positive.",
                1,
                Integer.MAX_VALUE);
        loyaltyMember = InputUtils.checkMembership();

    }
}
