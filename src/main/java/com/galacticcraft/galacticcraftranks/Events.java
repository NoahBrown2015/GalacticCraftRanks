package com.galacticcraft.galacticcraftranks;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener {

    private static GalacticCraftRanks main;
    public Events(GalacticCraftRanks Main) {
        this.main = Main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("ranks") || label.equalsIgnoreCase("rankup")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("[GalacticCraftRanks] This command is only available in-game!");
                return true;
            }
            Player p = (Player) sender;
            switch(label.toLowerCase()) {
                case "ranks":
                    main.rank.execute(p,args);
                    return true;
                case "rankup":
                    main.rankup.execute(p,args);
                    return true;
                default:
                    Bukkit.getLogger().info("Unknown Error com.galacticcraft.galacticcraftranks.Events.onCommand(Events.java:25-35) ~[?:?]");
                    main.log.severe(String.format("[%s] - Disabled due to fatal error!", main.getDescription().getName()));
                    main.getServer().getPluginManager().disablePlugin(main);
            }
        }
        return false;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!main.util.hasRank(p)) {
            main.getPermissions().playerAddGroup(p,main.defaultRank);
        }
    }
}
