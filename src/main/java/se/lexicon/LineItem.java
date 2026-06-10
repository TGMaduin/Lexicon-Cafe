package se.lexicon;

public class LineItem {

    private final MenuItem item;
    private final int quantity;

    public LineItem(MenuItem item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }

    public MenuItem getMenuItem(){ return item; }

    public int getQuantity() { return quantity; }

    public double lineTotal(){
        return item.getPrice() * quantity;
    }
}
