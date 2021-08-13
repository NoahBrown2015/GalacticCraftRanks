package me.brownie.galaticraftranks;

import org.bukkit.plugin.java.JavaPlugin;

public final class GalaticraftRanks extends JavaPlugin {

    private static Economy econ = null;

    @Override
    public void onEnable() {
        setupEconomy();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }
}
