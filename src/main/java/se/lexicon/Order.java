package se.lexicon;

public class Order {

    String customerName;
    MenuItem menuItem;
    int quantity;
    boolean membership;
    double subtotal, discount, vat, total;

    public Order(String customerName, MenuItem menuItem, int quantity, boolean membership){
        this.customerName = customerName;
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.membership = membership;
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
        return menuItem.getPrice() * quantity;
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
                %-10s: %s x %d
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
                "Customer", customerName,
                "Item", menuItem.getName(), quantity,
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
