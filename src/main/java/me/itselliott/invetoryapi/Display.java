package me.itselliott.invetoryapi;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * The display interface provides the accessible customisation and creation of a Bukkit {@link Inventory}
 */
public interface Display extends Listener {

    /**
     * Places an item in the display at the given position.
     * This is to replace {@link Inventory#setItem(int, ItemStack)}.
     * The native inventory function to add an item requires the knowledge of the cumulative position of the slot,
     * which in many cases can be incontinent.
     * This method will allow a specified row number and  column.
     *
     * @param row the row in which to find the slot
     * @param column the column in which to find the slot
     * @param itemStack the {@link ItemStack} to place at the specified slot
     * @param force weather to override an existing item if the specified slot is occupied
     *
     * @return if the item was shamefully placed, can only return false if the slot was occupied and force was false
     */
    boolean setItem(int row, int column, ItemStack itemStack, boolean force);

    /**
     * Returns an {@link ItemStack} of the item found at a given position.
     * The ItemStack can be null if the specified slot was empty
     * This is to replace {@link Inventory#getItem(int)}.
     * The native inventory function to get an item requires the knowledge of the cumulative position of the slot.
     * This method will allow you to get the ItemStack at a specified row and column.
     *
     * @param row the row in which to get the ItemStack
     * @param column the column in which to get the ItemStack
     *
     * @return the ItemStack found at the given position, null if slot was empty
     */
    ItemStack getItem(int row, int column);

    /**
     * Returns the number of rows (the height) of the display
     *
     * @return the number of rows
     */
    int getRows();

    /**
     * Sets the number of rows (the height) of the display
     *
     * @param rows new number of rows
     */
    void setRows(int rows);

    /**
     * This will register a listener inside the display, to allow easy tracking of listeners
     */
    void registerListener(Listener listener);

    /**
     * This will unregister a listener inside the display
     */
    void unregisterListener(Listener listener);


    /**
     * Returns the wrapped {@link Inventory} for this the display
     *
     * @return the wrapped inventory
     */
    Inventory getInventory();

    /**
     * Wrapper method to open a display to a player without having to call {@link Display#getInventory()}
     *
     * @param player to open the display to
     */
    void open(Player player);

}
