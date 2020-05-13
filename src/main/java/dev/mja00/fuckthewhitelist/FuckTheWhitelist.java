package dev.mja00.fuckthewhitelist;

import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

public final class FuckTheWhitelist extends JavaPlugin {

    @lombok.SneakyThrows
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Generating backup whitelist.");
        Set<OfflinePlayer> whitelisted = getServer().getWhitelistedPlayers();
        ArrayList<String> whitelist = new ArrayList<String>();
        JSONObject players = new JSONObject();
        for (OfflinePlayer player : whitelisted) {
            String mcuuid = player.getUniqueId().toString();
            String name = player.getName();
            players.put("uuid", mcuuid);
            players.put("name", name);
            //getLogger().info(players.toString());
            whitelist.add(players.toJSONString());
            players.clear();
        }
        getLogger().info("Writing whitelist to file.");
        generateWhitelist(whitelist);
        getLogger().info("Whitelist backed up.");

    }

    @lombok.SneakyThrows
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Generating backup whitelist.");
        Set<OfflinePlayer> whitelisted = getServer().getWhitelistedPlayers();
        ArrayList<String> whitelist = new ArrayList<String>();
        JSONObject players = new JSONObject();
        for (OfflinePlayer player : whitelisted) {
            String mcuuid = player.getUniqueId().toString();
            String name = player.getName();
            players.put("uuid", mcuuid);
            players.put("name", name);
            getLogger().info(players.toString());
            whitelist.add(players.toJSONString());
            players.clear();
        }
        getLogger().info("Writing whitelist to file.");
        generateWhitelist(whitelist);
        getLogger().info("Whitelist backed up.");
    }

    public static void generateWhitelist(ArrayList<String> whitelist) throws IOException{
        File file = new File("whitelist-gen-" + LocalDate.now() + ".json");
        PrintWriter pw = new PrintWriter(file);
        pw.print(whitelist);
        pw.close();
    }

}
