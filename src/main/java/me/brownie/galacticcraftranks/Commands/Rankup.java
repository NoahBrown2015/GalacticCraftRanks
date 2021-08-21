package me.brownie.galacticcraftranks.Commands;

import me.brownie.galacticcraftranks.GalacticCraftRanks;
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
        //   Get next rank from player's current
        String nextGroup = "";

        /*
        Can't quite figure this part out :|
         */

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
