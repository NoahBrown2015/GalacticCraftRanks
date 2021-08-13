package me.brownie.galacticraftranks.Commands;

import me.brownie.galacticraftranks.GalacticraftRanks;
import org.bukkit.entity.Player;

public class Ranks {

    private static GalacticraftRanks main;
    public Ranks(GalacticraftRanks Main) {
        this.main = Main;
    }

    public static void execute(Player p, String[] args) {
        if (!main.util.permCheck(p,"galacticraftranks.ranks")) {
            main.util.sendMessage(p,"&c[GalacticraftRanks] You do not have permission to use this command!");
            return;
        }
        //   Get a list of all ranks
        //   Send a message to the player containing them all and their price.
    }
}
