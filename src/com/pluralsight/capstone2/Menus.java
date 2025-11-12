package com.pluralsight.capstone2;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Displays menus and gathers user input to simulate a Sandwich Shop application.
 * Contains Orders of Items placed on the current day. Items include Sandwiches, Drinks,
 * and Chips. An Order has a name and an order number for the order.
 *
 * @author Ravi Spigner
 */
public class Menus {
    private static final Scanner scanner = new Scanner(System.in);
    public static final List<Order> orders = new ArrayList<Order>();
    private static double totalRevenue = 0.0;
    private static int currentOrderNumber = 1;
    public static final String filePath = "src/com/pluralsight/capstone2/receipts.csv";
    public static void displayWelcome() {
        System.out.println("---Welcome To The Sandwich Shop!---");
    }
    public static void displayGoodbye() {
        System.out.println("---Thank you!---");
        System.out.println("---See you next time!---");
    }
    public static void mainMenu() {
        String input;
        displayWelcome();
        totalRevenue = ReceiptsFileManager.getTotalRevenuePreviousSessions();
        //begin main menu
        while (true) {
            System.out.println("---Main Menu---");
            System.out.print("Please enter a Main Menu option: (1-New Order, 2-Display, 3-Exit): ");
            input = scanner.nextLine().trim();
            //verify menu option is a number
            if (!isNumber(input)) {
                errorMessageNumber(input, true);
                continue;
            }
            if (input.equals("1")) {
                //New Order
                String orderName;
                while (true) {
                    System.out.print("Please enter your a name for your order: ");
                    orderName = scanner.nextLine().trim();
                    //check name not number
                    if (isNumber(orderName)) {
                        errorMessageNumber(orderName, false);
                        continue;
                    }
                    //check name not empty
                    if (!orderName.isEmpty()) {
                        break;
                    } else {
                        errorMessage("", "Empty Name For Order Entered");
                    }
                }
                //add new order: create new order using name
                Order currentOrder = addNewOrder(orderName);
                //New Item
                while (true) {
                    System.out.print("Add an item? (1-Yes, 2-No): ");
                    input = scanner.nextLine().trim();
                    if (!isNumber(input)) {
                        errorMessageNumber(input, true);
                        continue;
                    }
                    if (input.equals("1")) {
                        addItemToOrder(currentOrder.getOrderNumber()); //add item to order
                        break;
                    } else if (input.equals("2")) {
                        break;
                    } else {
                        errorMessage(input, "Is An Invalid Menu Option. Only 1 or 2 is " +
                                "acceptable for your menu input");
                    }
                }
            } else if (input.equals("2")) {
                while (true) {
                    System.out.print("Display (1-Orders, 2-Total Revenue Of Previous Sessions, " +
                            "3-Exit to Main Menu): ");
                    input = scanner.nextLine().trim();
                    if (!isNumber(input)) {
                        errorMessageNumber(input, true);
                        continue;
                    }
                    if (input.equals("1")) {
                        displayOrders();
                    } else if (input.equals("2")) {
                        displayTotalRevenue();
                    } else if (input.equals("3")) {
                        break;
                    } else {
                        errorMessage(input, "Is An Invalid Menu Option. Only 1 or 2 is " +
                                "acceptable for your menu input");
                    }
                }
            } else if (input.equals("3")) {
                //Exit program
                ReceiptsFileManager.writeToReceipts();
                displayGoodbye();
                break;
            } else {
                errorMessage(input, "Is An Invalid Menu Option. Only a single number " +
                        "(1, 2, or 3) entered is acceptable for your menu input");
            }
        }
    }
    public static Order addNewOrder(String name) {
        Order newOrder = new Order(name, currentOrderNumber);
        orders.add(0, newOrder);
        currentOrderNumber++;
        System.out.println("---New Order Added---");
        return newOrder;
    }
    public static void addItemToOrder(int orderNumber) {
        Order order = getOrder(orderNumber);
        while (true) {
            System.out.println("---Add New Item To Order Number: " + order.getOrderNumber() +
                    " for Name: " + order.getOrderName() + "---");
            String input;
            System.out.print("Enter an option for the type of item you'd like to add: ");
            System.out.println();
            System.out.print("(1-Sandwich, 2-Chips, 3-Drink) ");
            System.out.println();
            System.out.print("(or 4-Checkout/Done Adding Items): ");
            input = scanner.nextLine();
            if (input.equals("1")) {
                Sandwich sandwich  = getSandwich();
                if (sandwich != null) {
                    order.addItem(sandwich);
                    System.out.println("---Sandwich Added To Order---");
                } else {
                    System.out.println("---No Sandwich Added---).");
                }
            } else if (input.equals("2")) {
                Chip chip  = getChip();
                if (chip != null) {
                    order.addItem(chip);
                    System.out.println("---Chip Added To Order---");
                } else {
                    System.out.println("---No Chip Added---).");
                }
            } else if (input.equals("3")) {
                Drink drink  = getDrink();
                if (drink != null) {
                    order.addItem(drink);
                    System.out.println("---Drink Added To Order---");
                } else {
                    System.out.println("---No Drink Added---).");
                }
            } else if (input.equals("4")) {
                System.out.println("---Order Checkout Complete---");
                return;
            } else {
                errorMessage(input, "Is An Invalid Menu Option. Only a single number " +
                        "(1, 2, 3, or 4) entered is acceptable for your menu input");
            }
        }
    }
    public static void displayItemOptions(List<String> options) {
        System.out.print("(");
        for (int i = 0; i < options.size(); i++) {
            if (i != options.size()-1) {
                System.out.print(i+1 + "-" + options.get(i) + ", ");
            } else {
                System.out.print(i+1 + "-" + options.get(i) + ")");
            }
        }
    }
    public static void removeNewOrder(String name) {
        orders.remove(0);
        currentOrderNumber--;
        System.out.println("---Order Removed---");
    }

    public static Order getOrder(int orderNumber) {
        return orders.stream()
                .filter(o -> o.getOrderNumber() == orderNumber)
                .findFirst()
                .orElse(null);
    }

    public static void displayTotalRevenue() {
        System.out.println("----Total Revenue----");
        System.out.printf("$%.2f", totalRevenue);
        System.out.println();
    }
    public static void displayOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders for the day found");
            return;
        }
        System.out.println("----All Orders----");
        orders.stream().forEach(o -> {
            System.out.println("-Order Number: " + o.getOrderNumber() +
                    ", Order Name: " + o.getOrderName());
            if (o.getItems().isEmpty()) {
                System.out.println("No Items Found");
            } else {
                o.getItems().forEach(item ->
                        System.out.println("\tItem: " + item.toString())
                );
                System.out.println(o.totalPrice());
            }
        });
    }
    public static List<Order> getAllOrders() {
        return orders.stream().toList();
    }
    public static boolean isNumber(String input) {
        try {
            Double.parseDouble(input); //will return true for doubles/decimals and ints
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void errorMessageNumber(String input, boolean wantedNumber) {
        if (wantedNumber) {
            errorMessage(input, " should be a number, not a word");
        } else {
            errorMessage(input, " should be a word, not a number");
        }
    }
    public static void errorMessage(String input, String errorMessage) {
        System.out.println("-------------------");
        System.out.println("ERROR: " + input + " " + errorMessage);
        System.out.println("-------------------");
    }

    public static Chip getChip() {
        System.out.println("---Add Chip---");
        String input;
        String chipChoice;
        while (true) {
            //display chip options
            System.out.print("Enter an option for the type of chip you want: ");
            System.out.println();
            List<String> chipOptions = Chip.getOptions();
            displayItemOptions(chipOptions);
            System.out.println();
            System.out.print(" (or 9-Exit to Main Menu): ");
            //get chip choice
            input = scanner.nextLine().trim();
            if (!isNumber(input)) {
                errorMessageNumber(input, true);
                continue;
            }
            if (input.equals("1")) {
                chipChoice = chipOptions.get(0);
            } else if(input.equals("2")) {
                chipChoice = chipOptions.get(1);
            } else if(input.equals("3")) {
                chipChoice = chipOptions.get(2);
            } else if(input.equals("4")) {
                chipChoice = chipOptions.get(3);
            } else if(input.equals("5")) {
                chipChoice = chipOptions.get(4);
            } else if(input.equals("6")) {
                chipChoice = chipOptions.get(5);
            } else if(input.equals("7")) {
                chipChoice = chipOptions.get(6);
            } else if(input.equals("8")) {
                chipChoice = chipOptions.get(7);
            } else if(input.equals("9")) {
                return null;
            } else {
                errorMessage(input, "Is An Invalid Size Option. Only a single number " +
                        "(1-8 (or 9 to exit)) entered is acceptable for your chip input");
                continue;
            }
            return new Chip(chipChoice);
        }
    }
    public static Drink getDrink() {
        System.out.println("---Add Drink---");
        String input;
        String drinkType;

        while (true) {
            String drinkSize;
            //display drink size options
            System.out.print("Enter an option for the size of the drink: ");
            System.out.println();
            List<String> sizeOptions = Drink.getSizes();
            displayItemOptions(sizeOptions);
            System.out.println();
            System.out.print(" (or 4-Exit to Main Menu): ");
            //get drink size
            input = scanner.nextLine().trim();
            if (!isNumber(input)) {
                errorMessageNumber(input, true);
                continue;
            }
            if (input.equals("1")) {
                drinkSize = sizeOptions.get(0);
            } else if (input.equals("2")) {
                drinkSize = sizeOptions.get(1);
            } else if (input.equals("3")) {
                drinkSize = sizeOptions.get(2);
            } else if (input.equals("4")) {
                return null;
            } else {
                errorMessage(input, "Is An Invalid Size Option. Only a single number " +
                        "(1, 2, 3, (or 4 to exit)) entered is acceptable for your drink size input");
                continue;
            }
            //display drink type options
            System.out.println("Enter an option for the type of drink you want: ");
            List<String> drinkOptions = Drink.getOptions();
            displayItemOptions(drinkOptions);
            System.out.println();
            System.out.print(" (or 11-Exit To Main Menu): ");
            //get drink type
            input = scanner.nextLine().trim();
            if (!isNumber(input)) {
                errorMessageNumber(input, true);
                continue;
            }
            if (input.equals("1")) {
                drinkType = drinkOptions.get(0);
            } else if (input.equals("2")) {
                drinkType = drinkOptions.get(1);
            } else if (input.equals("3")) {
                drinkType = drinkOptions.get(2);
            } else if (input.equals("4")) {
                drinkType = drinkOptions.get(3);
            } else if (input.equals("5")) {
                drinkType = drinkOptions.get(4);
            } else if (input.equals("6")) {
                drinkType = drinkOptions.get(5);
            } else if (input.equals("7")) {
                drinkType = drinkOptions.get(6);
            } else if (input.equals("8")) {
                drinkType = drinkOptions.get(7);
            } else if (input.equals("9")) {
                drinkType = drinkOptions.get(8);
            } else if (input.equals("10")) {
                drinkType = drinkOptions.get(9);
            } else if (input.equals("11")) {
                continue;
            } else {
                errorMessage(input, "Is An Invalid Bread Option. Only a single number " +
                        "(1-10 (or 11 to exit)) entered is acceptable for your drink type input");
                continue;
            }
            return new Drink(drinkType, drinkSize);
        }
    }
    public static Sandwich getSandwich() {
        System.out.println("---Add Sandwich---");
        String input;
        String sandwichName = "Custom Sandwich";
        ArrayList<Topping> toppings = new ArrayList<>();
        Sandwich newSandwich = null;
        while (true) {
            String sandwichSize;
            String breadType;
            //display sandwich size options
            System.out.print("Enter an option for the size of the sandwich: ");
            System.out.println();
            List<String> sizeOptions = Sandwich.getSizeOptions();
            displayItemOptions(sizeOptions);
            System.out.println();
            System.out.print(" (or 4-Exit to Main Menu): ");
            //get sandwich size
            input = scanner.nextLine().trim();
            if (!isNumber(input)) {
                errorMessageNumber(input, true);
                continue;
            }
            if (input.equals("1")) {
                sandwichSize = sizeOptions.get(0);
            } else if(input.equals("2")) {
                sandwichSize = sizeOptions.get(1);
            } else if(input.equals("3")) {
                sandwichSize = sizeOptions.get(2);
            } else if(input.equals("4")) {
                return null;
            } else {
                errorMessage(input, "Is An Invalid Size Option. Only a single number " +
                        "(1, 2, 3, (or 4 to exit)) entered is acceptable for your sandwich size input");
                continue;
            }
            //display bread type options
            System.out.println("Enter an option for the type of bread for your sandwich: ");
            List<String> breadOptions = Sandwich.getBreadOptions();
            displayItemOptions(breadOptions);
            System.out.println();
            System.out.print(" (or 9-Exit To Main Menu): ");
            //get bread type
            input = scanner.nextLine().trim();
            if (!isNumber(input)) {
                errorMessageNumber(input, true);
                continue;
            }
            if (input.equals("1")) {
                breadType = breadOptions.get(0);
            } else if(input.equals("2")) {
                breadType = breadOptions.get(1);
            } else if(input.equals("3")) {
                breadType = breadOptions.get(2);
            } else if(input.equals("4")) {
                breadType = breadOptions.get(3);
            } else if(input.equals("5")) {
                breadType = breadOptions.get(4);
            } else if(input.equals("6")) {
                breadType = breadOptions.get(5);
            } else if(input.equals("7")) {
                breadType = breadOptions.get(6);
            } else if(input.equals("8")) {
                breadType = breadOptions.get(7);
            } else if(input.equals("9")) {
                continue;
            } else {
                errorMessage(input, "Is An Invalid Bread Option. Only a single number " +
                        "(1-8 (or 9 to exit)) entered is acceptable for your bread type input");
                continue;
            }
            newSandwich = new Sandwich(sandwichName, sandwichSize, breadType);
            String topping;
            //get as many topping as the user would like
            while (true) {
                String toppingType;
                boolean wantExtraTopping;
                //display topping types
                System.out.println("Enter an option for the type of topping to add to " +
                        "your sandwich: ");
                List<String> toppingTypes = Topping.getToppingTypes();
                displayItemOptions(toppingTypes);
                System.out.println();
                System.out.print(" (or 5-Add Other Items To Order): ");
                //get topping type
                input = scanner.nextLine().trim();
                if (!isNumber(input)) {
                    errorMessageNumber(input, true);
                    continue;
                }
                if (input.equals("1")) {
                    toppingType = toppingTypes.get(0);
                } else if(input.equals("2")) {
                    toppingType = toppingTypes.get(1);
                } else if(input.equals("3")) {
                    toppingType = toppingTypes.get(2);
                } else if(input.equals("4")) {
                    toppingType = toppingTypes.get(3);
                } else if(input.equals("5")) {
                    return newSandwich;
                } else {
                    errorMessage(input, "Is An Invalid Topping Option. Only a single number " +
                            "(1-3 (or 4-Exit To Main Menu)) entered is acceptable for your " +
                            "topping type input");
                    continue;
                }
                //display topping options
                if (toppingType.equals("Meat")) {
                    //display meat toppings
                    System.out.println("Enter an option for the type of meat to add to " +
                            "your sandwich: ");
                    List<String> meatOptions = Topping.getMeatOptions();
                    displayItemOptions(meatOptions);
                    System.out.println();
                    System.out.print(" (or 8-Exit to Main Menu): ");
                    //get meat topping
                    input = scanner.nextLine().trim();
                    if (!isNumber(input)) {
                        errorMessageNumber(input, true);
                        continue;
                    }
                    if (input.equals("1")) {
                        topping = meatOptions.get(0);
                    } else if(input.equals("2")) {
                        topping = meatOptions.get(1);
                    } else if(input.equals("3")) {
                        topping = meatOptions.get(2);
                    } else if(input.equals("4")) {
                        topping = meatOptions.get(3);
                    } else if(input.equals("5")) {
                        topping = meatOptions.get(4);
                    } else if(input.equals("6")) {
                        topping = meatOptions.get(5);
                    } else if(input.equals("7")) {
                        topping = meatOptions.get(6);
                    } else if(input.equals("8")) {
                        break;
                    } else {
                        errorMessage(input, "Is An Invalid Menu Option. Only a single number " +
                                "(1-7 (or 8-Exit To Main Menu)) entered is acceptable for your " +
                                "meat input");
                        continue;
                    }
                } else if (toppingType.equals("Veggie")) {
                    //display veggie toppings
                    System.out.println("Enter an option for the type of veggie to add to " +
                            "your sandwich: ");
                    List<String> veggieOptions = Topping.getVeggieOptions();
                    displayItemOptions(veggieOptions);
                    System.out.println();
                    System.out.print("(or 11-Exit to Main Menu): ");
                    //get veggie topping
                    input = scanner.nextLine().trim();
                    if (!isNumber(input)) {
                        errorMessageNumber(input, true);
                        continue;
                    }
                    if (input.equals("1")) {
                        topping = veggieOptions.get(0);
                    } else if(input.equals("2")) {
                        topping = veggieOptions.get(1);
                    } else if(input.equals("3")) {
                        topping = veggieOptions.get(2);
                    } else if(input.equals("4")) {
                        topping = veggieOptions.get(3);
                    } else if(input.equals("5")) {
                        topping = veggieOptions.get(4);
                    } else if(input.equals("6")) {
                        topping = veggieOptions.get(5);
                    } else if(input.equals("7")) {
                        topping = veggieOptions.get(6);
                    } else if(input.equals("8")) {
                        topping = veggieOptions.get(7);
                    } else if(input.equals("9")) {
                        topping = veggieOptions.get(8);
                    } else if(input.equals("10")) {
                        topping = veggieOptions.get(9);
                    } else if(input.equals("11")) {
                        return null;
                    } else {
                        errorMessage(input, "Is An Invalid Menu Option. Only a single number " +
                                "(1-10 (or 11-Exit To Main Menu)) entered is acceptable for your " +
                                "veggie input");
                        continue;
                    }
                } else if (toppingType.equals("Cheese")) {
                    //display cheese toppings
                    System.out.println("Enter an option for the type of cheese to add to " +
                            "your sandwich:");
                    List<String> cheeseOptions = Topping.getCheeseOptions();
                    displayItemOptions(cheeseOptions);
                    System.out.println();
                    System.out.print("(or 7-Exit to Main Menu): ");
                    //get cheese topping
                    input = scanner.nextLine().trim();
                    if (!isNumber(input)) {
                        errorMessageNumber(input, true);
                        continue;
                    }
                    if (input.equals("1")) {
                        topping = cheeseOptions.get(0);
                    } else if(input.equals("2")) {
                        topping = cheeseOptions.get(1);
                    } else if(input.equals("3")) {
                        topping = cheeseOptions.get(2);
                    } else if(input.equals("4")) {
                        topping = cheeseOptions.get(3);
                    } else if(input.equals("5")) {
                        topping = cheeseOptions.get(4);
                    } else if(input.equals("6")) {
                        topping = cheeseOptions.get(5);
                    } else if(input.equals("7")) {
                        return null;
                    } else {
                        errorMessage(input, "Is An Invalid Menu Option. Only a single number " +
                                "(1-6 (or 7-Exit To Main Menu)) entered is acceptable for your " +
                                "cheese input");
                        continue;
                    }
                } else if (toppingType.equals("Sauce")) {
                    //display sauce topping options
                    System.out.println("Enter an option for the type of sauce to add to " +
                            "your sandwich: ");
                    List<String> sauceOptions = Topping.getSauceOptions();
                    displayItemOptions(sauceOptions);
                    System.out.println();
                    System.out.print("(or 9-Exit to Main Menu): ");
                    //get sauce topping
                    input = scanner.nextLine().trim();
                    if (!isNumber(input)) {
                        errorMessageNumber(input, true);
                        continue;
                    }
                    if (input.equals("1")) {
                        topping = sauceOptions.get(0);
                    } else if(input.equals("2")) {
                        topping = sauceOptions.get(1);
                    } else if(input.equals("3")) {
                        topping = sauceOptions.get(2);
                    } else if(input.equals("4")) {
                        topping = sauceOptions.get(3);
                    } else if(input.equals("5")) {
                        topping = sauceOptions.get(4);
                    } else if(input.equals("6")) {
                        topping = sauceOptions.get(5);
                    } else if(input.equals("7")) {
                        topping = sauceOptions.get(6);
                    } else if(input.equals("8")) {
                        topping = sauceOptions.get(7);
                    } else if(input.equals("9")) {
                        return null;
                    } else {
                        errorMessage(input, "Is An Invalid Menu Option. Only a single number " +
                                "(1-8 (or 9-Exit To Main Menu)) entered is acceptable for your " +
                                "sauce input");
                        continue;
                    }
                } else {
                    continue;
                }
                //ask if user wants extra of topping
                //display extra topping option
                System.out.println("Do you want extra of this topping? (1-Yes, 2-No)");
                System.out.print("(or 3-Cancel This Topping): ");
                //get extra topping response
                input = scanner.nextLine().trim();
                if (!isNumber(input)) {
                    errorMessageNumber(input, true);
                    continue;
                }
                if (input.equals("1")) {
                    wantExtraTopping = true;
                } else if(input.equals("2")) {
                    wantExtraTopping = false;
                } else if(input.equals("3")) {
                    return null;
                } else {
                    errorMessage(input, "Is An Invalid Menu Option. Only a single number " +
                            "(1,2 (or 3-Exit To Main Menu)) entered is acceptable for your " +
                            "extra topping input");
                    continue;
                }
                //add regular topping w/ or w/o extra topping
                Topping regularTopping = new Topping(toppingType, topping,
                        sandwichSize, false);
                if (wantExtraTopping) {
                    Topping extraTopping = new Topping(toppingType, topping,
                            sandwichSize,true);
                    newSandwich.addTopping(regularTopping);
                    newSandwich.addTopping(extraTopping);
                } else {
                    newSandwich.addTopping(regularTopping);
                }
            } //ask for another topping, because of while loop for toppings
            return newSandwich;
        }
    }
}
