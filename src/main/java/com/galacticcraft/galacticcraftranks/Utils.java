package com.galacticcraft.galacticcraftranks;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {

    private GalacticCraftRanks main;
    public Utils(GalacticCraftRanks Main) {
        this.main = Main;
    }

    public void sendMessage(Player p, String msg) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    public boolean permCheck(Player p, String perm) {
        return p.hasPermission(perm) || p.hasPermission("galacticcraftranks.*") || p.hasPermission("*") || p.isOp();
    }

    public boolean hasRank(Player p) {
        for (String g : main.getPermissions().getPlayerGroups(p)) {
            if (main.Ranks.contains(g)) return true;
        }
        return false;
    }
}
