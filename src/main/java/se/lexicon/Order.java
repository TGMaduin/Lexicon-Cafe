package se.lexicon;

import java.util.ArrayList;

public class Order {

    String customerName;
    boolean membership;
    ArrayList<LineItem> items;
    double subtotal, discount, vat, total;

    public Order(String customerName, boolean membership){
        this.customerName = customerName;
        this.membership = membership;
        items = new ArrayList<>();
    }

    public void addItem(LineItem item){
        items.add(item);
    }

    public double calculateDiscount(){
        if (membership){
            return subtotal * 0.15;
        } else if (subtotal >= 150) {
            return subtotal * 0.1;
        }
        else{
            return 0;
        }
    }

    public double calculateSubtotal(){
        double sum = 0;
        for (LineItem i : items){
            sum += i.lineTotal();
        }
        return sum;
    }

    public double calculateTotal(){
        return subtotal - discount + vat;
    }

    public double calculateVat(){
        return (subtotal - discount) * 0.12;
    }

    public double getTotal(){ return total;}

    public void printReceipt(){
        IO.println(String.format("""
            \n
            ==============================
                      Lexicon Cafe
            ==============================
            %-10s: %s
            ------------------------------""",
            "Customer", customerName));
        for(LineItem i : items){
            IO.println(String.format(
                "   %-10s x%d %6.2f SEK",
                i.getMenuItem().getName(),
                i.getQuantity(),
                i.lineTotal()));
        }
        IO.println(String.format("""
            ------------------------------
            %-10s: %.2f SEK
            %-10s: -%.2f SEK
            %-10s: %.2f SEK
            ------------------------------
            %-10s: %.2f SEK
            ==============================
               Thank you, %s!
               See you next time.
            ==============================\n
            """,
            "Subtotal", subtotal,
            "Discount", discount,
            "VAT", vat,
            "TOTAL", total,
            customerName));
    }

    public void processTransaction(){
        subtotal = calculateSubtotal();
        discount = calculateDiscount();
        vat = calculateVat();
        total = calculateTotal();
        printReceipt();
    }
}
