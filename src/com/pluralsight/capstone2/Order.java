package com.pluralsight.capstone2;
import java.util.ArrayList;
import java.util.List;
/**
 * An Order has the name of the order (which is the customer name), the order number,
 * and the items of the order which can consist of Sandwiches (with Toppings), Drinks,
 * and/or Chips.
 *
 * @author Ravi Spigner
 */
public class Order {
    private String orderName;
    private int orderNumber;
    private List<Item> items;
    public Order(String orderName, int orderNumber) {
        this.orderName = orderName;
        this.orderNumber = orderNumber;
        this.items = new ArrayList<>();
    }
    public void addItem(Item i) {
        if (i == null) {
            Menus.errorMessage("Item Entered", "is null");
            return;
        }
        if (i instanceof Sandwich ||
                i instanceof Drink ||
                i instanceof Chip) {
            items.add(i);
        } else {
            Menus.errorMessage("Item Entered", "is not a valid item");
        }
    }
    public String getOrderName() {
        return orderName;
    }
    public int getOrderNumber() {
        return orderNumber;
    }
    public List<Item> getItems() {
        return items;
    }
    public double getTotalPrice() {
        double price = 0.0;
        for (Item i : items) {
            price += i.getTotalPrice();
        }
        return price;
    }
    public String totalPrice() {
        return "Total Order Price: $" + String.format("%.2f", getTotalPrice());
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(orderNumber).append(",").append(orderName);
        for (Item i : items) {
            sb.append(",").append(i.getItemName()).append(":").append(String.format("%.2f", i.getTotalPrice()));
        }
        sb.append(",").append(String.format("%.2f", getTotalPrice()));
        return sb.toString();
    }
}
