package dev.mja00.fuckthewhitelist;

import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public final class FuckTheWhitelist extends JavaPlugin {

    @lombok.SneakyThrows
    @Override
    public void onEnable() {
        // Plugin startup logic
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
        generateWhitelist(whitelist);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static void generateWhitelist(ArrayList<String> whitelist) throws IOException{
        PrintWriter pw = new PrintWriter(new File("whitelist-gen.json"));
        pw.print(whitelist);
        pw.close();
    }

}
