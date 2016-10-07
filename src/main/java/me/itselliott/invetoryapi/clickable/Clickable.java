package me.itselliott.invetoryapi.clickable;

import me.itselliott.invetoryapi.Display;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class Clickable implements Listener {

    private List<ItemStack> itemStack;
    private Display display;

    /**
     * Creates a wrapped class for item listeners that is inventory specific
     * @param display {@link Display} the listener will belong to
     * @param itemStack a {@link List} of {@link ItemStack} that the listener looks for
     */
    public Clickable(Display display, List<ItemStack> itemStack) {
        this.itemStack = itemStack;
        this.display = display;
        this.register();
    }

    /**
     * @return A {@link List} of {@link ItemStack} that the listener looks for
     */
    public List<ItemStack> getItemStacks() {
        return this.itemStack;
    }

    /**
     * Set the {@link List} of {@link ItemStack}
     * @param itemStack list to set
     */
    public void setItemStack(List<ItemStack> itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * @return {@link Display} that this listener will be looking in
     */
    public Display getDisplay() {
        return this.display;
    }

    /**
     * Registers this {@link Clickable} using {@link Display#registerListener(Listener)}  method
     */
    public void register() {
        this.display.registerListener(this);
    }

    /**
     * Unregisters this {@link Clickable} using {@link Display#unregisterListener(Listener)} method
     */
    public void unregister() {
        this.display.unregisterListener(this);
    }


    /**
     * An abstract method that is created as an anonymous class to define what happens when one of the border items is clicked
     * @param event {@link InventoryClickEvent}
     */
    public abstract void click(InventoryClickEvent event);

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().equals(this.display.getInventory()) && event.getCurrentItem() != null && this.itemStack.contains(event.getCurrentItem())) {
            this.click(event);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getInventory().equals(this.display.getInventory())) {
            this.unregister();
        }
    }

}
