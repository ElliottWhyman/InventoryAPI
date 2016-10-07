package me.itselliott.invetoryapi;

import org.bukkit.plugin.java.JavaPlugin;

public class InventoryAPI extends JavaPlugin {

    private static InventoryAPI instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {

    }

    public static InventoryAPI getInstance() {
        return instance;
    }

}
