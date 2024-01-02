package rkis2;

/**
 * An interface representing the player's behavior
 */
public interface Player {
    /**
     * The method determines whether the player is playing on the field or not
     */
    void play();

    /**
     * The method determines when the player needs to take a break
     */
    void relax();
}
