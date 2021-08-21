package me.brownie.galacticcraftranks;

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
            if (sender instanceof Player) return false;
            sender.sendMessage("[GalacticCraftRanks] This command is only available in-game!");
            return true;
        }
        return false;
    }

    @EventHandler
    public void PlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        String[] args = e.getMessage().split(" ");
        String cmd = args[0].replace("/", "").toLowerCase();

        if (cmd.equalsIgnoreCase("ranks")) {
            e.setCancelled(true);
            main.rank.execute(p,args);
            return;
        }
        if (cmd.equalsIgnoreCase("rankup")) {
            e.setCancelled(true);
            main.rankup.execute(p,args);
            return;
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!main.util.hasRank(p)) {
            main.getPermissions().playerAddGroup(p,main.defaultRank);
        }
    }
}
