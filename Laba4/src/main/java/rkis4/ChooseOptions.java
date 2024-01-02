package rkis4;

/**
 * These are constants for values from the menu when entering the field of table
 */
public enum ChooseOptions {
    ID(1), BRAND(2), TYPE_WATCH(3), PRICE(4), WARRANTY_PERIOD(5),
    WEIGHT(6);
    final int number;
    ChooseOptions(int number) {this.number = number;}
}