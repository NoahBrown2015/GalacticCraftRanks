package com.galacticcraft.galacticcraftranks.Commands;

import com.galacticcraft.galacticcraftranks.GalacticCraftRanks;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Rankup {

    private static GalacticCraftRanks main;
    public Rankup(GalacticCraftRanks Main) {
        this.main = Main;
    }

    public static void execute(Player p, String[] args) {
        //   Checks for permissions
        if (!main.util.permCheck(p,"galacticcraftranks.rankup")) {
            main.util.sendMessage(p,"&c[GalacticCraftRanks] You do not have permission to use this command!");
            return;
        }
        //   Gets player's current rank
        String group = "";
        for (String Group : main.getPermissions().getPlayerGroups(p)) {
            if (!main.Ranks.contains(Group)) {
                continue;
            }
            group = Group;
        }
        //   Checks if player has reached max rank
        String nextGroup = "";
        int i = main.getConfig().getInt(group + ".priority");
        i++;
        if (main.rankPriority.get(i) == null) {
            main.util.sendMessage(p,"&c[GalacticCraftRanks] You have already reached the highest rank!");
            return;
        }
        //   Get next rank from player's current
        nextGroup = main.rankPriority.get(i);
        //   Checks if player has enough money to purchase next rank
        double price = main.getConfig().getDouble(nextGroup + ".cost");
        if (!main.getEconomy().has(p,price)) {
            main.util.sendMessage(p,"&c[GalacticCraftRanks] You cannot afford to rankup!");
            return;
        }
        //   Removes money from player
        main.getEconomy().withdrawPlayer(p,price);
        //   Removes current rank from player
        main.getPermissions().playerRemoveGroup(p,group);
        //   Gives new rank to player
        main.getPermissions().playerAddGroup(p,nextGroup);
        //   Announces rankup to the server
        Bukkit.broadcastMessage("&6[GalacticCraftRanks] " + p.getName() + " has ranked up to " + main.getConfig().getString(nextGroup + ".prefix"));
    }
}
