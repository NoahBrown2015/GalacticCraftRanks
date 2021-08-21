package me.brownie.galacticraftranks.Commands;

import me.brownie.galacticraftranks.GalacticraftRanks;
import org.bukkit.entity.Player;

public class Rankup {

    private static GalacticraftRanks main;
    public Rankup(GalacticraftRanks Main) {
        this.main = Main;
    }

    public static void execute(Player p, String[] args) {
        //   Checks for permissions
        if (!main.util.permCheck(p,"galacticraftranks.rankup")) {
            main.util.sendMessage(p,"&c[GalacticraftRanks] You do not have permission to use this command!");
            return;
        }
        //   Gets player's current rank
        String group;
        for (String Group : main.getPermissions().getPlayerGroups(p)) {
            if (!main.Ranks.contains(Group)) {
                continue;
            }
            group = Group;
        }
        //   Get next rank from player's current
        //   Checks if player has enough money to purchase next rank

        //   Removes money from player

        //   Removes current rank from player

        //   Gives new rank to player

        //   Announces rankup to the server

    }
}
