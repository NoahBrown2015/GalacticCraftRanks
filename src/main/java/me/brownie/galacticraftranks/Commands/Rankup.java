package me.brownie.galacticraftranks.Commands;

import me.brownie.galacticraftranks.GalacticraftRanks;
import org.bukkit.entity.Player;

public class Rankup {

    private static GalacticraftRanks main;
    public Rankup(GalacticraftRanks Main) {
        this.main = Main;
    }

    public static void execute(Player p, String[] args) {
        if (!main.util.permCheck(p,"galacticraftranks.rankup")) {
            main.util.sendMessage(p,"&c[GalacticraftRanks] You do not have permission to use this command!");
            return;
        }
        String group;
        for (String Group : main.getPermissions().getPlayerGroups(p)) {
            if (!main.Ranks.contains(Group)) {
                continue;
            }
            group = Group;
        }
        //   Get next rank from player's current
        //   Check if player has enough money
        //   Remove money from player
        //   Remove current rank from player
        //   Give new rank to player
        //   Announce rankup to the server
    }
}
