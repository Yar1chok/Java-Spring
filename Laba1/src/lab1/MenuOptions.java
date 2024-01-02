package lab1;

/**
 * This enum contains the constants of menu option.
 */
public enum MenuOptions {
    ADD_CLOTHES_DEFAULT(1), DELETE_CLOTHES(2), SHOW_CLOTHES(3), COMPARE_CLOTHES(4),
    EXIT(5);

    final int number;
    MenuOptions(int number) {this.number = number;}
}
