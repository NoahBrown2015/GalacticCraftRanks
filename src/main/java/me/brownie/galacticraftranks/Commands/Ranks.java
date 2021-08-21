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
        String previous = "";
        for (String rank : main.getConfig().getKeys(false)) {
            if (main.getConfig().getBoolean(rank + ".default")) {
                main.util.sendMessage(p,"&6[GalacticraftRanks] " + main.getConfig().getString(rank + ".prefix") + "&b&a>> &r&3(&aDefault&3)");
                previous = rank;
                continue;
            }
            String price = "&r&3(&a " + main.getConfig().getDouble(rank + ".cost") + "&3)";
            main.util.sendMessage(p,previous + " &a&b>> " + main.getConfig().getString(rank + ".prefix") + price);
            previous = rank;
        }
    }
}
