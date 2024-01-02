package rkis4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * The entry point of program
 */
public class Main {
    /**
     * A constant for indicating the number of items in the menu
     */
    public static final int MAX_MENU_POINTS = 6;

    /**
     * The main method, from program starts work
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        WatchDao watchDao = context.getBean("watchDao", WatchDao.class);
        boolean menu = true;
        while (menu) {
            System.out.println("***Menu***");
            System.out.println("*Write 1 to add watch in database.");
            System.out.println("*Write 2 to show added watches in database.");
            System.out.println("*Write 3 to edit record in database.");
            System.out.println("*Write 4 to delete record in database.");
            System.out.println("*Write 5 to search record in database.");
            System.out.println("*Write 6 to exit from program.");
            int point = checkInputMax(MAX_MENU_POINTS);
            MenuOptions menuOptions = MenuOptions.values()[point - 1];
            switch (menuOptions) {
                case ADD_WATCH -> addNewRecord(watchDao);
                case SHOW_WATCHES -> showDataBase(watchDao);
                case EDIT_WATCH -> editRecordInDataBase(watchDao);
                case DELETE_WATCH -> deleteRecordInDataBase(watchDao);
                case SEARCH_WATCH -> searchRecordByField(watchDao);
                case EXIT -> {
                    System.out.println("*The program is end.");
                    menu = false;
                    context.close();
                }
                default -> System.out.println("*Error input.");
            }
        }
    }

    /**
     * Searches for watch records in the database based on a selected field (e.g., ID, brand, price) and its value.
     * Users are prompted to choose a field and input a value to search for. Matching records are displayed.
     * @param watchDao The instance used for database interactions.
     */
    public static void searchRecordByField(WatchDao watchDao) {
        List<Watch> allWatches = watchDao.findAll();
        if (!allWatches.isEmpty()) {
            System.out.println("*Choose the field in the table:");
            System.out.println("*ID - 1\n*Brand - 2\n*Type Of rkis4.Watch - 3\n*Price - 4\n*Warranty Period - 5\n*Weight - 6");
            int point = checkInputMax(MAX_MENU_POINTS);
            ChooseOptions chooseOptions = ChooseOptions.values()[point - 1];
            List<Watch> watches = null;
            switch (chooseOptions) {
                case ID -> {
                    int ID = checkIDRecord(watchDao);
                    watches = watchDao.findByField("id", ID);
                }
                case BRAND -> {
                    String brand = checkInputString("*Write the brand of watch: ");
                    watches = watchDao.findByField("brand", brand);
                }
                case TYPE_WATCH -> {
                    String typeWatch = checkInputString("*Write the type of watch: ");
                    watches = watchDao.findByField("type_watch", typeWatch);
                }
                case PRICE -> {
                    double price = checkInputDouble("the price of watch: ");
                    watches = watchDao.findByField("price", price);
                }
                case WARRANTY_PERIOD -> {
                    int warrantyPeriod = checkInputInt("to add the warranty period of watch: ");
                    watches = watchDao.findByField("warranty_period", warrantyPeriod);
                }
                case WEIGHT -> {
                    double weight = checkInputDouble("the weight of watch: ");
                    watches = watchDao.findByField("weight", weight);
                }
                default -> System.out.println("*Error input.");
            }
            if (!watches.isEmpty()){
                System.out.println("-".repeat(102));
                System.out.println("|  ID  |       Brand       |       Type       |      Price      | Warranty Period |      Weight      |");
                System.out.println("-".repeat(102));
                for (Watch watch : watches) {
                    System.out.printf("| %-4d | %-17s | %-16s | %-15.2f | %-15d | %-16.2f |\n", watch.getId(),
                            watch.getBrand(), watch.getTypeWatch(), watch.getPrice(), watch.getWarrantyPeriod(),
                            watch.getWeight());
                }
                System.out.println("-".repeat(102));
            } else {
                System.out.println("*The watch not found with this parameters!");
            }
        } else {
            System.out.println("*No records in database!");
        }
    }

    /**
     * Deletes a watch record from the database based on the user's input of the record's ID.
     * @param watchDao The instance used for database interactions.
     */
    public static void deleteRecordInDataBase(WatchDao watchDao) {
        List<Watch> allWatches = watchDao.findAll();
        if (!allWatches.isEmpty()) {
            watchDao.deleteById(checkIDRecord(watchDao));
            System.out.println("*Record successfully deleted!");
        } else {
            System.out.println("*No records in database!");
        }
    }

    /**
     * Edits a watch record in the database based on user input. Users provide values for all watch properties
     * (ID, brand, type, price, warranty period, and weight) to update the record.
     * @param watchDao The instance used for database interactions.
     */
    public static void editRecordInDataBase(WatchDao watchDao) {
        List<Watch> allWatches = watchDao.findAll();
        if (!allWatches.isEmpty()) {
            Watch watch = new Watch();
            watch.setId(checkIDRecord(watchDao));
            watch.setBrand(checkInputString("*Write the brand of watch: "));
            watch.setTypeWatch(checkInputString("*Write the type of watch: "));
            watch.setPrice(checkInputDouble("the price of watch: "));
            watch.setWarrantyPeriod(checkInputInt("to add the warranty period of watch: "));
            watch.setWeight(checkInputDouble("the weight of watch: "));
            watchDao.updateById(watch);
            System.out.println("*The record successfully updated!");
        } else {
            System.out.println("*No records in database!");
        }
    }

    /**
     * Displays all watch records in the database in a tabular format.
     * @param watchDao The instance used for database interactions.
     */
    public static void showDataBase(WatchDao watchDao) {
        List<Watch> allWatches = watchDao.findAll();
        if (!allWatches.isEmpty()) {
            Comparator<Watch> watchComparator = Comparator.comparing(Watch::getId);
            allWatches.sort(watchComparator);
            System.out.println("-".repeat(102));
            System.out.println("|  ID  |       Brand       |       Type       |      Price      | Warranty Period |      Weight      |");
            System.out.println("-".repeat(102));
            for (Watch watch : allWatches) {
                System.out.printf("| %-4d | %-17s | %-16s | %-15.2f | %-15d | %-16.2f |\n", watch.getId(),
                        watch.getBrand(), watch.getTypeWatch(), watch.getPrice(), watch.getWarrantyPeriod(),
                        watch.getWeight());
            }
            System.out.println("-".repeat(102));
        } else {
            System.out.println("*No records in database!");
        }
    }

    /**
     * Adds a new watch record to the database. Users provide values for all watch properties
     * (brand, type, price, warranty period, and weight) to create the new record.
     * @param watchDao The instance used for database interactions.
     */
    public static void addNewRecord(WatchDao watchDao) {
        Watch watch = new Watch();
        watch.setBrand(checkInputString("*Write the brand of watch: "));
        watch.setTypeWatch(checkInputString("*Write the type of watch: "));
        watch.setPrice(checkInputDouble("the price of watch: "));
        watch.setWarrantyPeriod(checkInputInt("to add the warranty period of watch: "));
        watch.setWeight(checkInputDouble("the weight of watch: "));
        watchDao.insert(watch);
        System.out.println("*Record successfully added.");
    }

    /**
     * Prompts the user to input the ID of a watch record they want to select from the database.
     * Ensures that the provided ID is valid by checking it against the existing IDs in the database.
     * @param watchDao The instance used for database interactions.
     * @return The valid ID selected by the user.
     */
    public static int checkIDRecord(WatchDao watchDao) {
        boolean flag = true;
        showDataBase(watchDao);
        Integer[] existingIds = watchDao.getAllIds();
        int numRecord = 0;
        while (flag) {
            numRecord = checkInputInt("ID of record, which you want to choose: ");
            boolean isValidId = Arrays.asList(existingIds).contains(numRecord);
            if (isValidId) {
                flag = false;
            }
        }
        return numRecord;
    }

    /**
     * Validates and retrieves a positive integer input from the user
     * @param message The text to display to the user
     * @return The validated positive integer input from the user
     */
    public static int checkInputInt(String message){
        Scanner number = new Scanner(System.in);
        int point;
        do {
            System.out.print("*Enter a positive number " + message);
            while (!number.hasNextInt()) {
                System.out.println("*Input error, try again.");
                System.out.print("*Enter a positive number " + message);
                number.next();
            }
            point = number.nextInt();
        } while (point <= 0);
        return point;
    }

    /**
     * This function check input on float.
     * @param message The text to display to the user
     * @return The positive float digit
     */
    public static double checkInputDouble(String message){
        Scanner number = new Scanner(System.in);
        double point;
        do {
            System.out.print("*Enter a float number to add " + message);
            while (!number.hasNextDouble()) {
                System.out.println("*Input error, try again.");
                System.out.print("*Enter a float number to add " + message);
                number.next();
            }
            point = number.nextDouble();
        } while (point < 0.0);
        return point;
    }

    /**
     * This function get the text input by the user
     * @param message The text to display to the user
     * @return Text, accepted by user
     */
    public static String checkInputString(String message){
        String line;
        Scanner str = new Scanner (System.in);
        System.out.print(message);
        while (true) {
            line = str.nextLine(); // Считывание
            if (line.trim().length() == 0){
                System.out.print("*Input is empty, write again: ");
            }else {
                break;
            }
        }
        return line;
    }

    /**
     * This method check input on integer and a range of menu
     * @return Integer digit
     */
    public static int checkInputMax(int MAX_MENU_POINTS){
        Scanner number = new Scanner(System.in);
        int point;
        System.out.print("*Enter an item from the menu: ");
        do {
            while (!number.hasNextInt()) {
                System.out.println("*Input error, try again.");
                System.out.print("*Enter an item from the menu: ");
                number.next();
            }
            point = number.nextInt();
            if (point > MAX_MENU_POINTS) {
                point = -1;
            }
        } while (point <= 0);
        return point;
    }
}
