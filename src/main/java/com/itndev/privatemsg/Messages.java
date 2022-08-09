package com.itndev.privatemsg;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.Locale;

public class Messages extends Command {
    public Messages() {
        super("귓", null, new String[] { "귓" });
    }
//"w", "mp", "m",, "귓말"
    public void execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
        if (paramCommandSender instanceof ProxiedPlayer) {
            ProxiedPlayer proxiedPlayer = (ProxiedPlayer)paramCommandSender;
            if (paramArrayOfString.length > 1) {
                ProxiedPlayer proxiedPlayer1 = ProxyServer.getInstance().getPlayer(paramArrayOfString[0]);

                if (proxiedPlayer1 != null) {
                    if(Main.ignore.get(proxiedPlayer1.getUniqueId()) != null && !Main.ignore.get(proxiedPlayer1.getUniqueId()).isEmpty() && Main.ignore.get(proxiedPlayer1.getUniqueId()).contains(proxiedPlayer.getName().toLowerCase(Locale.ROOT))) {
                        proxiedPlayer.sendMessage((BaseComponent)new TextComponent(ChatColor.translateAlternateColorCodes('&', "&f&l[&r&c귓말&f&l] &r&f해당 유저는 당신을 차단했습니다")));
                        return;

                    }
                    String str = "";
                    byte b;
                    for (b = 1; b < paramArrayOfString.length; b = (byte)(b + 1)) {
                        str = String.valueOf(str) + paramArrayOfString[b] + " ";
                    }
                    Main.msg(proxiedPlayer, proxiedPlayer1, str);
                } else {
                    proxiedPlayer.sendMessage((BaseComponent)new TextComponent(ChatColor.translateAlternateColorCodes('&',"&f&l[&r&c귓말&f&l] &r&f해당 유저는 오프라인입니다 &8:&c " + paramArrayOfString[0])));
                }
            } else {
                proxiedPlayer.sendMessage((BaseComponent)new TextComponent(ChatColor.translateAlternateColorCodes('&',"&f&l[&r&c귓말&f&l] &r&f/귓 &7[유저이름] [귓말내용]".trim().replace("&", "§"))));
            }
        }
    }
}
