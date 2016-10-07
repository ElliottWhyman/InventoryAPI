package me.itselliott.invetoryapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SimpleDisplay implements Display {

    private int rows;
    private String title;
    private Inventory inventory;

    private List<Listener> listeners;

    public SimpleDisplay(int rows, String title) {
        this.rows = rows;
        this.title = title;
        this.listeners = new ArrayList<>();
        this.inventory = Bukkit.createInventory(null, rows * 9);
    }

    @Override
    public boolean setItem(int row, int column, ItemStack itemStack, boolean force) {
        if (this.getItem(row, column) != null || force) {
            this.inventory.setItem(this.cartesianToLinearSlot(row, column), itemStack);
            return true;
        }
        return false;
    }

    @Override
    public ItemStack getItem(int row, int column) {
        return this.inventory.getItem(this.cartesianToLinearSlot(row, column));
    }

    @Override
    public int getRows() {
        return this.rows;
    }

    @Override
    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public void registerListener(Listener listener) {
        if (!this.listeners.contains(listener)) {
            this.listeners.add(listener);
            Bukkit.getPluginManager().registerListener(InventoryAPI.getInstance(), listener);
        }
    }

    @Override
    public void unregisterListener(Listener listener) {
        if (this.listeners.contains(listener)) {
            this.listeners.remove(listener);
            Bukkit.getPluginManager().unregisterListener(listener);
        }
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    @Override
    public void open(Player player) {
        DisplayRegistry.registerDisplay(this);
        player.openInventory(this.inventory);
    }

    private int cartesianToLinearSlot(int row, int column) {
        return (row * 9) + column;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getInventory().equals(this.inventory)) {
            DisplayRegistry.unregisterDisplay(this);
        }
    }
}
