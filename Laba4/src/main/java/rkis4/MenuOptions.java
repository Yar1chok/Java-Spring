package rkis4;

/**
 * These are constants for values from the menu when entering
 */
public enum MenuOptions {
    ADD_WATCH(1), SHOW_WATCHES(2), EDIT_WATCH(3), DELETE_WATCH(4), SEARCH_WATCH(5),
    EXIT(6);

    final int number;
    MenuOptions(int number) {this.number = number;}
}
