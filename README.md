# Year Up United / Pluralsight - Java Capstones (Ravi's Solutions)
## Course Taught By: Maaike Van Putten

### 🛠️ Tools Used
![Java](https://img.shields.io/badge/language-Java-blue.svg)
![IDE](https://img.shields.io/badge/IDE-IntelliJ-orange)

| Active/Inactive                                                   | Active Dates            |
|-------------------------------------------------------------------|-------------------------|
| ![Status](https://img.shields.io/badge/status-active-brightgreen) | 10/10/2025 - XX/XX/XXXX |

### 📝 Description
Contains a collection of my capstone projects. <br>
The PDF containing most of the workshop writeups from each week cannot be uploaded due to Pluralsight ownership rights. <br>
But below you will find brief descriptions of each of the projects, which should give a better idea of their intended function and requirements. <br>
The Javadoc-style class comments I've made for each of the java files developed for each capstone were designed to be descriptive enough to give the reader a good idea of their intended function and requirements as well.

#### 🕒 Commits History Here<br>
[Click Here](https://github.com/gitraspigner/capstones/commits/master) <br>

#### 📋 Project Boards Here<br>
[Click Here](https://github.com/gitraspigner?tab=projects) <br>

### 💭 Capstones Detailed: <br>

---
# Capstone 2
  ## - **Capstone Title:** Sandwich Shop (Command Line Application)
  - **Description:** Simulates a Sandwich Shop application (ran from a command line interface). <br>
    The user (typically an employee of the shop) navigates a series of menus to accomplish basic order-related tasks of a sandwich shop. These tasks include creating a new order (with an order name) that consists of a series of items (either Sandwich, Chip, or Drink). Sandwiches can have multiple toppings, Chips are only offered of one size (Regular). Drinks and Sandwiches are offered of one of three sizes: Small, Medium, or Large. For Sandwiches, Meat & Cheese toppings each have a charge based off of the size of the Sandwich, and if the topping is an extra helping or not. Other toppings (which include Veggies and Sauces) have no charge for them. A user may also display reports including the current receipts/transactions of the day, and the total revenue from all previous user sessions. All receipts are written to a file (which, for this program is called "receipts.csv"). Each user session has its date recorded to the receipts file. All orders from that user session are recorded under the date of the user session, each order contains the name, order number (which starts from order #001 for each user session), and the items of the order.
<br> <br>
  - **UML Diagram:**
![UML Diagram.png](src/com/pluralsight/capstone2/Screenshots/UML%20Diagram.png)
  - **Application Screens:**
    - **First Program Run & Welcome Message (With No Prior Receipts File):** <br>
![firstProgramRunNoReceiptsFile.png](src/com/pluralsight/capstone2/Screenshots/firstProgramRunNoReceiptsFile.png)
    - **Invalid User Input (Number Out Of Range Of Menu Options):** <br> <br>
![invalidInputNumberOutOfRange.png](src/com/pluralsight/capstone2/Screenshots/invalidInputNumberOutOfRange.png)
    - **Invalid User Input (Word or Number Expected):** <br> <br>    
![invalidInputNonWordOrNonNumber.png](src/com/pluralsight/capstone2/Screenshots/invalidInputNonWordOrNonNumber.png)
    - **A Complex Sandwich (pt1):** <br> <br>
![addComplexSandwich1.png](src/com/pluralsight/capstone2/Screenshots/addComplexSandwich1.png)
    - **A Complex Sandwich (pt2):** <br> <br>
![addComplexSandwich2.png](src/com/pluralsight/capstone2/Screenshots/addComplexSandwich2.png)
    - **A Complex Sandwich (pt3):** <br> <br>
![addComplexSandwich3.png](src/com/pluralsight/capstone2/Screenshots/addComplexSandwich3.png)
    - **A Non-Sandwich Order:** <br> <br>
![addNonSandwichOrder.png](src/com/pluralsight/capstone2/Screenshots/addNonSandwichOrder.png)
    - **Add Simple Sandwich (No Drink or Chip) Order:** <br> <br>
![addSimpleSandwichOrder.png](src/com/pluralsight/capstone2/Screenshots/addSimpleSandwichOrder.png)
    - **Display Orders of Current Session:** <br> <br>
![displayOrdersOfCurrentSession.png](src/com/pluralsight/capstone2/Screenshots/displayOrdersOfCurrentSession.png)
    - **Receipts File After Tests (Above):** <br> <br>
![receipts-csv-afterExitingApplication.png](src/com/pluralsight/capstone2/Screenshots/receipts-csv-afterExitingApplication.png)
    - **Display Total Revenue Of Previous Sessions:** <br> <br>
![displayTotalRevenuePreviousSessions.png](src/com/pluralsight/capstone2/Screenshots/displayTotalRevenuePreviousSessions.png)
    - **Receipts File, Multiple Days/Sessions Supported** <br> <br>
![receipts-csv-multipleDaysSessions.png](src/com/pluralsight/capstone2/Screenshots/receipts-csv-multipleDaysSessions.png)

    <br> <br>
  - **Interesting Code Snippets (Lambda Functions):**
  ```java
      public static Order getOrder(int orderNumber) {
          return orders.stream()
              .filter(o -> o.getOrderNumber() == orderNumber)
              .findFirst()
              .orElse(null);
    }
  ```
  ```java
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
  ```
  - **Why they're interesting:**
   - They use lambdas, and I had to use lambdas to fulfill a requirement for this capstone. They both use Java stream, getOrder() retrieves an order via its order number using filter(), and displayOrders() uses two nested forEachs to display Order and Item data fields in order to displays all orders. That's pretty much it, nothing too special here.
  - **Extra Features:**
   - Total Revenue (File Reading)
   - Total Revenue Of Specified Date (File Reading)
   - Class Javadoc-style comments are included for each class
  - **Additional Thoughts:**
   - It should be noted that for both extra features implemented (Total Revenue and Total Revenue Of Specified Date), that revenue from the current user session is not included. I thought this would be good design since revenue should only be added and reported for a successfully completed (and properly exited) user session (which typically should be at the end of the day of an employee operating the Sandwich Shop application to take orders).
   - Class Javadoc-style comments are included for each class, including the JUnit test classes.
   - Development branch & project board were utilized for this project. Neither were particularly hard to use for me.
   - The most recent orders/transactions are appended to the end of the receipts.csv file, which is different from my first capstone where they are appended to the top. This design lead to a more efficient program where all orders/transactions from the output file did not have to be read, built, stored, and written twice during a single program session (once at the start and once at the end).
   - There was an extra feature I wanted to implement: The Combo Meal, where if a user placed an order for a Sandwich, Drink, and Chip they'd receive 10% off their order. Due to time constraints and wanting to focus on other aspects of this project (including documentation, which I consider to be important as per my University of Washington teachings), I decided to omit this feature.
---

# Capstone 1
  ## - **Capstone Title:** Accounting Ledger (Command Line Application)
  - **Description:** Simulates/is an Accounting Ledger application (ran from a command line interface). <br>
The user (typically an employee of the firm) navigates a series of menus to accomplish different basic functions of the ledger which are primarily either creating new transactions (either adding a deposit or making a payment) or displaying a report/list of transactions (deposits, payments, or transactions from a range of time). This ledger manages (stores & manipulates) transactions according to the current user running the application's input from the command line and are all written to a file (which, for this program is called "transactions.csv"). Transactions are of 2 categories: deposits (positive dollar amounts) and payments (negative dollar amounts). A transaction contains the following information: the date and time of its processing, a description of the type of deposit or (for payments) the item purchased, the name of the depositor or vendor, and the dollar amount deposited or paid.
  - **Application Screens:**
    - Welcome/Greeting, Main Menu Screen, Make Deposit & Make Payment Screens: <br> <br>
    ![welcomeMainMenuDepositAndPaymentScreens.png](src/com/pluralsight/capstone1/Screenshots/welcomeMainMenuDepositAndPaymentScreens.png)
    - Ledger Menu Screen, All Transactions, All Deposits, and All Payments Screens: <br> <br>
    ![ledgerMenuAllTransactionsAllDepositsAllPayments.png](src/com/pluralsight/capstone1/Screenshots/ledgerMenuAllTransactionsAllDepositsAllPayments.png)
    - Reports Menu Screen, Vendor Search & Custom Search Screens: <br> <br>
    ![reportsMenuVendorSearchCustomSearch.png](src/com/pluralsight/capstone1/Screenshots/reportsMenuVendorSearchCustomSearch.png)
    - Multiple User Sessions Logged (Transactions from previous sessions are accessible): <br> <br>
    ![bobL.TransactionsFile.MultipleSessions.png](src/com/pluralsight/capstone1/Screenshots/bobL.TransactionsFile.MultipleSessions.png)
    - Different Files Created For Each User (usernames can be any kind of name (First&Last, First, Last, etc...) an Employee ID, or an Email): <br> <br>
    ![differentFilesForDifferentUsernames.png](src/com/pluralsight/capstone1/Screenshots/differentFilesForDifferentUsernames.png)
    - Malformed File Data <br> <br>
    ![malformedDataFile.png](src/com/pluralsight/capstone1/Screenshots/malformedDataFile.png)
    - Malformed Data Warnings <br> <br>
    ![malformedDataFileWarnings.png](src/com/pluralsight/capstone1/Screenshots/malformedDataFileWarnings.png)
    - Malformed Data Removed By Application (screenshot of transactions file after executing application) <br> <br>
    ![malformedDataRemovedAfterExecution.png](src/com/pluralsight/capstone1/Screenshots/malformedDataRemovedAfterExecution.png)
      <br> <br>
  - **Interesting Code Snippet:**
    - **Snippet:** <br>
    ![codeSnippetBuildLedgerFromFile.png](src/com/pluralsight/capstone1/Screenshots/codeSnippetBuildLedgerFromFile.png) <br> <br>
    - **Why it's interesting:**
    - Although I have chosen not to include the entire code for this method (buildLedgerFromFile() due to length), building the current ledger to use during a user's session was one of the most time-consuming parts of this application. The reason I've chosen to highlight this section out of all of my code, is that it does 3 things I am very proud of. The first of which, is that it reads a user file (if one exists for their username) and parses all of the data to create a ledger for the current session, which can be searched and sorted, meaning it includes data from all of the user's previous sessions. The second of which, is that it detects malformed file data, notifies the user of what data it is, and exactly where it appears in the file. And the third of which, is that it will remove the aforementioned malformed data from their transaction file, which helps keep the data stored clean, free of issues, and in a form that conserves space and money that would otherwise be spent on saving problematic data. 
  - **Additional Thoughts:** 
    - When it comes to (as mentioned in the rubric for this capstone) "tricking out" this application, some of the extra features I implemented were as follows: different users (usernames can be a first, last, or first and last name, or an email, or an employee ID number), test transactions being supported (largely implemented for demo purposes so I don't need to enter 10 unique transactions during my presentation) and these test transactions would not typically be used in a business setting, a rolling log file for each user which tracks their login and transactions added while preserving the order of most recent logins/transactions at the top of their log file, letting each user be able to access their transactions from all previous sessions from within their current session, notification to user of malformed file data, removing malformed file data, type checking for every input (whether user input or file data, where appropriate and giving consideration to what kinds of values should be allowed). I fell short of two goals I had for this project: to make javadoc comments for each method and class field/variable (although I was able to make javadoc comments for each class) and to implement OpenAI somehow in some area of the application. I think I met the requirements for this capstone and implemented multiple bonus features for additional credit (multiple users, custom search, optional test transactions (without having covered JUnit in this course, even though I've used it before)) so therefore I think I'm satisfied with my work.

---
### Capstone 3
 - **Capstone Title:** -In Progress-
 - **Description:** -In Progress-
 - **Application Screens:** -In Progress-
  - **Interesting Code Snippet:** -In Progress-
    - **Snippet:** -In Progress-
    - **Why it's interesting:** -In Progress-
  - **Additional Thoughts:** -In Progress-

### 🔖 Citation
I wrote this README.md, but I did indeed use ChatGPT to give my initial framework and to learn markdown formatting. Therefore here is an APA Style Citation for it:  <br>
OpenAI. (2025). ChatGPT (Oct 1 version) [Large language model]. https://chatgpt.com/ <br>

*I have to give credit where it's due, right?* <br>

**Last Edited: 10/16/2025**
