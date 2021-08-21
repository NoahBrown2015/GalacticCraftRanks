package me.brownie.galacticraftranks;

import me.brownie.galacticraftranks.Commands.Ranks;
import me.brownie.galacticraftranks.Commands.Rankup;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class GalacticraftRanks extends JavaPlugin implements Listener {

    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;

    public Utils util;
    public Ranks rank;
    public Rankup rankup;
    public Events events = new Events(this);

    public List<String> Ranks = new ArrayList<>();
    String defaultRank;

    @Override
    public void onEnable() {
        //   Vault setup
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        setupChat();
        //   Registers listeners
        this.getServer().getPluginManager().registerEvents(events,this);
        //   Adds all ranks in the config onto the server using vault
        for (String rank : getConfig().getKeys(false)) {
            Ranks.add(rank.toLowerCase());
            getChat().setGroupPrefix("world",rank,getConfig().getString(rank + ".prefix"));
        }
    }

    //   Vault setup
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
    //   Vault setup
    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }
    //   Vault setup
    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    //   Access vault api
    public static Economy getEconomy() {
        return econ;
    }
    public static Permission getPermissions() {
        return perms;
    }
    public static Chat getChat() {
        return chat;
    }
}
