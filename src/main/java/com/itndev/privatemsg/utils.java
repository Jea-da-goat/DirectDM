package com.itndev.privatemsg;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

public class utils {
    public static void sendmsg(ProxiedPlayer p, String msg) {
        if(p.isConnected()) {
            p.sendMessage((BaseComponent)new TextComponent(ChatColor.translateAlternateColorCodes('&', msg)));
        }
    }
    public static void staffspy(String msg) {
        for(ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            if (player.hasPermission("itndev.staffspy") && Main.staffspytoggle.containsKey(player.getUniqueId()) && Main.staffspytoggle.get(player.getUniqueId()).equals(true)) {
                utils.sendmsg(player, msg);
            }
        }
    }
}
