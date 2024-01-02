package lab1;/*
 * Смолянинов Ярослав, КИ21-16/1Б, Вариант - 23
 * Работа с наследованием в Java. От класса lab1.Clothes наследуются классы lab1.Overcoat, lab1.Shoes и lab1.Hats.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The main class, in which you can add different things.
 */
public class Main {
    /**
     * Entry point to the program.
     */
    public static void main(String[] args) {
        List<Clothes> ClothesList = new ArrayList<>();
        boolean menu = true;
        int flagAdd = 0;
        while (menu){
            System.out.println("***Menu***");
            System.out.println("*Write 1 to add thing with params.");
            System.out.println("*Write 2 to delete things.");
            System.out.println("*Write 3 to show things.");
            System.out.println("*Write 4 to compare two things.");
            System.out.println("*Write 5 to exit from program.");
            int point = checkInputMenu();
            MenuOptions menu_option = MenuOptions.values()[point - 1];
            switch (menu_option) {
                case ADD_CLOTHES_DEFAULT -> { // Добавление объекта с параметрами, введенными пользователем
                    int choice = addObject();
                    if (choice == 1) {
                        ClothesList.add(createClothes());
                    } else if (choice == 2) {
                        ClothesList.add(createOvercoat());
                    } else if (choice == 3) {
                        ClothesList.add(createShoes());
                    } else if (choice == 4) {
                        ClothesList.add(createHats());
                    }
                    System.out.println("*The object was added.");
                    flagAdd++;
                }
                case DELETE_CLOTHES -> { // Удаление объектов
                    if (flagAdd > 0) {
                        showClothes(ClothesList);
                        int option = checkIndexObject(ClothesList);
                        ClothesList.remove(option);
                        System.out.println("*The object was deleted.");
                        flagAdd--;
                    } else {
                        System.out.println("*Firstly add object!");
                    }
                }
                case SHOW_CLOTHES -> { // Вывод объектов
                    if (flagAdd > 0) {
                        showClothes(ClothesList);
                    } else {
                        System.out.println("*Firstly add object!");
                    }
                }
                case COMPARE_CLOTHES -> { // Сравнение двух элементов
                    if (flagAdd > 0) {
                        showClothes(ClothesList);
                        compareObjects(ClothesList);
                    } else {
                        System.out.println("*Firstly add object!");
                    }
                }
                case EXIT -> { // Выход из программы
                    System.out.println("*The program is end.");
                    menu = false;
                }
                default -> System.out.println("*Error input.");
            }
        }
    }

    /**
     * This method check input from menu on positive digit and digit from menu.
     * @return The digit from menu.
     */
    public static int checkInputMenu(){
        int MAX_MENU_POINTS = 5;
        Scanner number = new Scanner(System.in);
        int point;
        do {
            System.out.print("*Enter an item from the menu: ");
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

    /**
     * This method check input on digit from menu.
     * @return The digit meaning user choice.
     */
    public static int addObject(){
        System.out.println("*What would you like to add?");
        System.out.println("*1 - Clothes");
        System.out.println("*2 - Overcoat");
        System.out.println("*3 - Shoes");
        System.out.println("*4 - Hat");
        return checkObject();
    }

    /**
     * This method output all object from class lab1.Clothes.
     * @param Things List lab1.Clothes with objects.
     */
    public static void showClothes(List<Clothes> Things) {
        int i = 1;
        for (Clothes item: Things){
            System.out.println("*** The number of thing - " + i + " ***");
            System.out.println("*  " + item.getClass().getName() + "  *");
            System.out.println(item);
            System.out.println();
            i++;
        }
    }

    /**
     * This method compare two objects from list lab1.Clothes.
     * @param Product List lab1.Clothes with objects.
     */
    public static void compareObjects(List<Clothes> Product) {
        int firstIndex = checkIndexObject(Product);
        int secondIndex = checkIndexObject(Product);
        if (Product.get(firstIndex).equals(Product.get(secondIndex))){
            System.out.println("*Two objects are equals!");
        } else {
            System.out.println("*Two objects are not equals.");
        }
    }

    /**
     * This method check input on digit to delete object from list.
     * @param Product List lab1.Clothes with objects.
     */
    public static int checkIndexObject(List<Clothes> Product) {
        Scanner number = new Scanner(System.in);
        int choice;
        do {
            System.out.print("*Enter a number object: ");
            while (!number.hasNextInt()) {
                System.out.println("*Input error, try again.");
                System.out.print("*Enter a number object: ");
                number.next();
            }
            choice = number.nextInt();
            if (choice > Product.size()){
                choice = -1;
                System.out.println("*Input error, try again.");
            }
        } while (choice <= 0);
        choice--;
        return choice;
    }

    /**
     * This method check input to choose object.
     * @return Integer to choose object.
     */
    public static int checkObject(){
        int MAX_POINTS_OBJECT = 5;
        Scanner number = new Scanner(System.in);
        int point;
        do {
            System.out.print("*Your choice: ");
            while (!number.hasNextInt()) {
                System.out.println("*Input error, try again.");
                System.out.print("*Your choice: ");
                number.next();
            }
            point = number.nextInt();
            if (point > MAX_POINTS_OBJECT) {
                point = -1;
            }
        } while (point <= 0);
        return point;
    }

    /**
     * This method get the text input by the user
     * @return Text, accepted by user
     */
    public static String checkInputLine(String message){ // Ввод строки
        String lines;
        Scanner str = new Scanner (System.in);
        System.out.print(message);
        while (true) {
            lines = str.nextLine(); // Считывание
            if (lines.trim().length() == 0){
                System.out.print("*Line is empty, write your line again: ");
            }else {
                break;
            }
        }
        return lines;
    }

    /**
     * This method check input on double.
     * @param message A string that contains the text for a certain class.
     * @return The positive double.
     */
    public static double checkInputDouble(String message) {
        Scanner number = new Scanner(System.in);
        double point;
        System.out.println(message);
        do {
            System.out.print("*Enter a positive float number: ");
            while (!number.hasNextDouble()) {
                System.out.println("*Input error, try again.");
                System.out.print("*Enter a positive float number: ");
                number.next();
            }
            point = number.nextDouble();
        } while (point < 0.0);
        return point;
    }

    /**
     * This method check input on digit.
     * @param message A string that contains the text for a certain class.
     * @return The positive digit.
     */
    public static int checkInputInt(String message){
        Scanner number = new Scanner(System.in);
        int point;
        System.out.println(message);
        do {
            System.out.print("*Enter a positive number: ");
            while (!number.hasNextInt()) {
                System.out.println("*Input error, try again.");
                System.out.print("*Enter a positive number: ");
                number.next();
            }
            point = number.nextInt();
        } while (point < 0);
        return point;
    }

    /**
     * This method create object 'lab1.Clothes'.
     * @return Object 'lab1.Clothes'.
     */
    public static Clothes createClothes() {
        return new Clothes(checkInputLine("*Write the name of thing: "), checkInputDouble("*Enter the cost of thing."));
    }

    /**
     * This method create object 'lab1.Overcoat'.
     * @return Object 'lab1.Overcoat'.
     */
    public static Overcoat createOvercoat(){
        Clothes Clothes = createClothes();
        return new Overcoat(Clothes.getName(), Clothes.getCost(), checkInputLine("*Write the season socks: "),
                checkInputDouble("*Enter the weight of overcoat."));
    }

    /**
     * This method create object 'lab1.Shoes'.
     * @return Object 'lab1.Shoes'.
     */
    public static Shoes createShoes(){
        Clothes Clothes = createClothes();
        return new Shoes(Clothes.getName(), Clothes.getCost(), checkInputLine("*Write the type o boots: "),
                checkInputInt("*Enter the size of boots."));
    }

    /**
     * This method create object 'lab1.Hats'.
     * @return Object 'lab1.Hats'.
     */
    public static Hats createHats(){
        Clothes Clothes = createClothes();
        return new Hats(Clothes.getName(), Clothes.getCost(), checkInputLine("*Write the material: "),
                checkInputDouble("*Enter the head size."));
    }
}